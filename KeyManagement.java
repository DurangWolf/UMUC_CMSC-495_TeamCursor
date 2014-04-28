/**
* keyManagementMap - Single Key, Single Value using List
* @author Ricky Bifford
* @version 1.0
**/

package encryptionController;

import java.util.ArrayList;
import java.util.Properties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class KeyManagement extends EncryptionController{
    
    private String userKey;
    private String file;
    // create map to store
    Map<String, String> keyManagementMap = new HashMap<String, String>();
    //Send value for key to encryptionController
    EncryptionController controller = new EncryptionController();
    
    KeyManagement(){
    }
    
    KeyManagement(String file, String userKey){
    	this.file = file;
    	this.userKey = userKey;
    
    // Create list one and store values
    //List<String> keyValueOne = new ArrayList<String>();

    // Accept keys from GUI to place in keyManagement Map
    // String userKey = jtfUserKeyInputArea.getText();

    // We want to add the text file here
    // currently limited to two entries per user
    // may want to add a while loop for multiple entries
    //keyValueOne.add(file);
    //keyValueOne.add("txt file 2");

    // Create list two and store values
    //List<String> keyValueTwo = new ArrayList<String>();
    //keyValueTwo.add("txt file 1");
    //keyValueTwo.add("txt file 2");

    // Create list three and store values
    //List<String> keyValueThree = new ArrayList<String>();
    //keyValueThree.add("txt file 1");
    //keyValueThree.add("txt file 2");

    // Put values into map
    keyManagementMap.put(this.file, this.userKey);
    keyStore(this.file, this.userKey);
    //keyManagementMap.put("User 2", keyValueTwo);
    //keyManagementMap.put("User 3", keyValueThree);
	
    //Encrypt data
    Encryptor e = new Encryptor(this.file, this.userKey);
    }

public void keyStore(String file, String userKey){

	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("KeyManagementDatabase.txt", true)))) {
		// Iterate and display values   
	    for (Map.Entry<String, String> entry : keyManagementMap.entrySet()) {
	    	String key = entry.getKey();
	    	String Value = entry.getValue();
	    	out.println("Key: " + key + " " + "Value: "+ Value + "\n");
	    	//System.out.println(key + " " + Value);
	    } 
	    out.close();
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	
}
public void searchForValueFromKey(String userKey){
        // Iterate and display values
    System.out.println("Getting Key and corresponding Values...");   
    for (Map.Entry<String, String> entry : keyManagementMap.entrySet()) {
        String key = entry.getKey();
        String keyValues = entry.getValue();

        //Check to see if map contains key
        if(keyManagementMap.containsKey(key)){

	Decryptor d = new Decryptor(key, keyValues);

        //Print keys and values to the console
        //System.out.println("Key = " + key);
        //System.out.println("Values = " + keyValues);
        controller.decryptFile(key, keyValues);
        }else{
            System.out.println("There are no matches for the entered key");
            }
        }    
    }
}
