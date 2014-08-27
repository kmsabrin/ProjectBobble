package ProjectBobble;

import java.util.ArrayList;
import java.util.List;

public class Node {
	List<String> links;
	String timeStamp;
	String pageNo;
	String searchTerm;
	
	Node(String timeStamp, String searchTerm, String pageNo) {
		links = new ArrayList();
		this.timeStamp = timeStamp;
		this.pageNo = pageNo;
		this.searchTerm = searchTerm;
	}	
}
