package ProjectBobble;

import java.io.BufferedReader; 
import java.io.FileInputStream; 
import java.io.InputStreamReader; 
import java.util.HashMap; 
import java.util.Map; 
  
public class DataLoad { 
      
    Map<String, Node> uLogData; 
    Map<String, Node> serverData; 
      
    DataLoad() { 
        uLogData = new HashMap(); 
        serverData = new HashMap(); 
    } 
      
    public void loadDataFromServerFile(String fileIn, String fileID) { 
        try { 
             FileInputStream inFile = new FileInputStream(fileIn); 
             BufferedReader reader = new BufferedReader(new InputStreamReader(inFile)); 
                      
             String str = reader.readLine(); 
             while (str != null) {   
                 String strs[] = str.split("[\t\n\r]+");     
                 String searchID = strs[0]; 
                 String searchTime = strs[1]; 
                 String returnedLink = strs[2]; 
                 String pageNo = strs[3]; 
                 String searchTerm = strs[4]; 
                   
//               String dataID = searchID + "+" + fileID; 
                 String dataID = searchID; 
                 if (serverData.containsKey(dataID)) { 
                     serverData.get(dataID).links.add(returnedLink); 
                 } 
                 else { 
                     Node node = new Node(searchTime, searchTerm, pageNo); 
                     node.links.add(returnedLink); 
                     serverData.put(dataID, node); 
                 } 
                   
                 str = reader.readLine(); 
             } 
               
             reader.close(); 
        }catch(Exception e) { 
             e.printStackTrace(); 
        } 
    } 
      
    public void loadDataFromIndexFile(String fileIn, String fileID) {    
        try { 
             FileInputStream inFile = new FileInputStream(fileIn); 
             BufferedReader reader = new BufferedReader(new InputStreamReader(inFile)); 
                      
             String str = reader.readLine(); 
             while (str != null) {   
                 String strs[] = str.split("[\t\n\r]+");     
                   
                 if(strs.length < 5) { str = reader.readLine(); continue; }  
                 //System.out.println("adding!\n");
                 String searchID = strs[0]; 
                 String pageNo = strs[1]; 
                 String searchIDHash = strs[2]; 
                 String searchTerm = strs[3]; 
                 String searchTime = strs[4]; 
                   
//               String dataID = searchID + "+" + fileID; 
                 String dataID = searchID; 
                 Node node = new Node(searchTime, searchTerm, pageNo); 
                 uLogData.put(dataID, node); 
                   
                 str = reader.readLine(); 
             } 
               
             reader.close(); 
        }catch(Exception e) { 
             e.printStackTrace(); 
        } 
    } 
      
    public void loadDataFromULogFile(String fileIn, String fileID) { 
        try { 
             FileInputStream inFile = new FileInputStream(fileIn); 
             BufferedReader reader = new BufferedReader(new InputStreamReader(inFile)); 
                      
             String str = reader.readLine(); 
             while (str != null) {   
                 String strs[] = str.split("[\t\n\r]+");     
				 if(strs.length < 2) {str = reader.readLine(); continue;}
                 String searchID = strs[0]; 
                 String returnedLink = strs[1]; 
                   
//               String dataID = searchID + "+" + fileID; 
                 String dataID = searchID; 
                 if (uLogData.containsKey(dataID)) { 
                     uLogData.get(dataID).links.add(returnedLink); 
                 } 
                 else { 
                     // some error happened 
                     uLogData.remove(dataID); 
                     System.out.println("SearchID " + dataID + " not found!!!"); 
                 } 
                   
                 str = reader.readLine(); 
             } 
               
             reader.close(); 
        }catch(Exception e) { 
             e.printStackTrace(); 
        } 
    } 
      
    public static void main(String[] args) { 
        DataLoad dataLoad = new DataLoad(); 
          
        //dataLoad.loadDataFromServerFile("auckland.in", ""); 
        //dataLoad.loadDataFromServerFile("aut.in", ""); 
          
        dataLoad.loadDataFromIndexFile("index.txt", ""); 
        dataLoad.loadDataFromULogFile("ulogcc.txt", ""); 
          
//      System.out.println(dataLoad.data.size()); 
    } 
} 