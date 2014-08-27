package ProjectBobble;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BobbleScore {
	
	Map<String, List<Integer>> termScores;
	Map<String, Integer> bScores;
	DataLoad dataLoad;
	
	BobbleScore() {
		termScores = new HashMap();
		bScores = new HashMap();
		
		dataLoad = new DataLoad();
		dataLoad.loadDataFromIndexFile("index.txt", "");
		dataLoad.loadDataFromULogFile("ulogcc.txt", "");
	}
	
	public void getTermScores(String serverFileName) {
		dataLoad.serverData.clear();
		dataLoad.loadDataFromServerFile(serverFileName, "");
		
		for (String s: dataLoad.uLogData.keySet()) {
			if (dataLoad.serverData.containsKey(s)) {
				Node uDataNode = dataLoad.uLogData.get(s);
				Node sDataNode = dataLoad.serverData.get(s);
				
				int bScore = 0;
				for (String r: uDataNode.links) {
					if (sDataNode.links.contains(r) == false) {
						++bScore;
					}
				}
				
//				String term = uDataNode.searchTerm; // term doesn't work
				String term = s;
				if (termScores.containsKey(term)) {
					termScores.get(term).add(bScore);
				}
				else {
					List<Integer> scoreList = new ArrayList();
					scoreList.add(bScore);
					termScores.put(term, scoreList);
				}
			}
		}
	}
	
	public void getBScores() {
		
		for (String s: termScores.keySet()) {
			List<Integer> scoreList = termScores.get(s);
			if (scoreList.size() < 1) continue;
			
			int j = 0;
			for (int i: scoreList) {
				j += i;
			}
			
			bScores.put(s, j);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		BobbleScore bobbleScore = new BobbleScore();
		
//		bobbleScore.getTermScores("auckland.in");
//		bobbleScore.getTermScores("waseda.in");
		
		bobbleScore.getBScores();
		
		PrintWriter pw = new PrintWriter(new File("BobbleScores.txt"));
		for (int i = 81; i >= 0; --i) {
			for (String s : bobbleScore.bScores.keySet()) {
				int score = bobbleScore.bScores.get(s);
				if (score == i) {
					pw.println(bobbleScore.dataLoad.uLogData.get(s).searchTerm + " " + bobbleScore.bScores.get(s));
				}
			}
		}
		pw.close();
	}
}
