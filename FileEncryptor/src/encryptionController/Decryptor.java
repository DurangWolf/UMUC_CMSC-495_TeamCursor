/*
 * Author:		Team Cursor
 * 
 * Members:		Ricky Bifford
 * 				Kurt Carico
 * 				Andy Castillo
 * 				Jordan Kovacs
 *
 * Course:		CMSC-495 Section 7981
 * 
 * Assign:		Final Project
 * 
 * File:		Decryptor.java
 * 
 * Date:		3/31/2014
 * 
 * Description:	This file will be used to read an encrypted file and produce
 * 				a plain text copy. 
 */

package encryptionController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decryptor{
	//User input
	private String fileName;
	private String password;
		
	//Variables to hold source and encrypted data
	private byte[] decData;
	private byte[] encData;
	
	//Constructor
	Decryptor(String inPassword, String inFileName){
		try{
			this.fileName = inFileName + ".tce";
			this.password = inPassword;
			this.prepareSourceData();
			this.decData = new CipherGenerator(this.password, Mode.DECRYPT).getCipher().doFinal(this.encData);
			this.generateDecryptedData();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void prepareSourceData() throws Exception{
		File file = new File(this.fileName);
		FileInputStream inFileStream = new FileInputStream(file);
		this.encData = new byte[(int)file.length()];
		inFileStream.read(encData);
		inFileStream.close();
	}
	
	private void generateDecryptedData() throws Exception{
		int padCount = (int)decData[decData.length - 1];
		if(padCount >= 1 && padCount <= 8){
			this.decData = Arrays.copyOfRange(decData, 0, decData.length - padCount);
		}
		FileOutputStream outFileStream = new FileOutputStream(new File(this.fileName + ".tcd"));
		outFileStream.write(decData);
		outFileStream.close();
	}
	
	//Static method used to retrieve key data
	//Returns char[] of key data or null if no key database is present
	protected  static Map<String, String> getKeyData(){
		Map<String, String> result = new HashMap<String, String>();
		byte[] encData = null;
		byte[] decData = null;
		try{
			//Read data from key database
			File file = new File("kdb.tce");
			FileInputStream inFileStream = new FileInputStream(file);
			encData = new byte[(int)file.length()];
			inFileStream.read(encData);
			inFileStream.close();
			
			//Decrypt data from key database
			decData = new CipherGenerator("supersecretpassword", Mode.DECRYPT).getCipher().doFinal(encData);
			int padCount = (int)decData[decData.length - 1];
			if(padCount >= 1 && padCount <= 8){
				decData = Arrays.copyOfRange(decData, 0, decData.length - padCount);
			}
			
			//Convert data to Map
			String currentFile = "";
			String currentPassword = "";
			boolean onFile = true;
			for(int i = 0; i < decData.length; i++){
				//if currently reading a file path
				if(onFile){
					//if a comma is reached switch over to copy password
					if((char)decData[i] == ','){
						onFile = false;
					}
					//else add the current char to the end of the filepath
					else{
						currentFile = currentFile + (char)decData[i];
					}
				}
				//else reading the password
				else{
					//if a new line or end of file is reached add file and password to map
					if((char)decData[i] == '\n' || i == (decData.length - 1)){
						result.put(currentFile.trim(), currentPassword.trim());
					}
					//else add current char to current password
					else{
						currentPassword = currentPassword + (char)decData[i];
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("ERROR: Failed to read key database.");
			result = null;
		}		
		
		return result;
	}

}
