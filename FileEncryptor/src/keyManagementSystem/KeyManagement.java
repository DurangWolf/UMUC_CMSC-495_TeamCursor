/**
* keyManagementMap - Single Key, Multiple Values using List
* @author Ricky Bifford
* @version 1.0
**/

package keyManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyManagement {
    public static void main(String[] args) {

    // create map to store
    Map<String, List<String>> keyManagementMap = new HashMap<String, List<String>>();

    // Create list one and store values
    List<String> keyValueOne = new ArrayList<String>();

    // Accept keys from GUI to place in keyManagement Map
    // String userKey = jtfUserKeyInputArea.getText();

    // We want to add the text file here
    // currently limited to two entries per user
    // may want to add a while loop for multiple entries
    keyValueOne.add("txt file 1");
    keyValueOne.add("txt file 2");

    // Create list two and store values
    List<String> keyValueTwo = new ArrayList<String>();
    keyValueTwo.add("txt file 1");
    keyValueTwo.add("txt file 2");

    // Create list three and store values
    List<String> keyValueThree = new ArrayList<String>();
    keyValueThree.add("txt file 1");
    keyValueThree.add("txt file 2");

    // Put values into map
    keyManagementMap.put("User 1", keyValueOne);
    keyManagementMap.put("User 2", keyValueTwo);
    keyManagementMap.put("User 3", keyValueThree);

    // Iterate and display values
    System.out.println("Getting Key and corresponding [Multiple] Values");   
    for (Map.Entry<String, List<String>> entry : keyManagementMap.entrySet()) {
        String key = entry.getKey();
        List<String> keyValues = entry.getValue();

        //Check to see if map contains key
        if(keyManagementMap.containsKey(key)){
    
        //Send value for key to encryptionController
        //encryptionController ec = new EncryptionController(keyValues);

        //Print keys and values to the console
        System.out.println("Key = " + key);
        System.out.println("Values = " + keyValues);
        }else{
            System.out.println("There are no matches for the entered key");
            }
        }
    }   
}
