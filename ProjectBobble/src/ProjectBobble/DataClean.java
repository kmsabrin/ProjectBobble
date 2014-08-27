package ProjectBobble;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataClean {
	
	public static void cleanServerFile(String fileIn, String fileOut) {	
		try {
			 FileInputStream inFile = new FileInputStream(fileIn);
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
	        
	         FileOutputStream outFile = new FileOutputStream(fileOut);
	         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outFile));
	        		
	         String str = reader.readLine();
			 while (str != null) {
				
				String strs[] = str.split("[\t\n\r]+");	
				
				// erroneous line
				if (strs.length < 9 ) { System.out.println(str); str = reader.readLine(); continue; }
				
				String searchID = strs[3].substring(1, strs[3].length() - 1).trim();
				String searchTime = strs[4].trim();
				String searchTerm = strs[5];
				String pageNo = strs[6];
				String returnedLink = strs[strs.length - 1].trim();
				 
				String outLine = searchID + "\t" + searchTime + "\t" + returnedLink + "\t" + pageNo + "\t" + searchTerm;
				writer.write(outLine);
				writer.newLine();
				str = reader.readLine();
			}
			
			reader.close();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cleanULogFile(String fileIn, String fileOut) {	
		try {
			 FileInputStream inFile = new FileInputStream(fileIn);
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
	        
	         FileOutputStream outFile = new FileOutputStream(fileOut);
	         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outFile));
	        		
	         String str = reader.readLine();
			 while (str != null) {
				
				String strs[] = str.split("[\t\n\r]+");	
				
				// erroneous line
				if (strs.length < 5) { System.out.println(str); str = reader.readLine(); continue; }
				
				String searchID = strs[0];
				String userID = strs[1];
				String userIP = strs[2];
				String resultHeader = strs[3];
				String returnedLink = strs[4];
				 
				String outLine = searchID + "\t" + returnedLink;
				writer.write(outLine);
				writer.newLine();
				str = reader.readLine();
			}
			
			reader.close();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		cleanServerFile("planetlab-1.cs.auckland.ac.nz.dat", "auckland.in");
//		cleanDataFile("planetlab1.aut.ac.nz.dat", "aut.in");
		
//		cleanULogFile("ulog.txt", "ulog.clean.txt");
	}
}
