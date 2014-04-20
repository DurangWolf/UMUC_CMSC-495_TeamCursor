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
 * File:		Encryptor.java
 * 
 * Date:		3/31/2014
 * 
 * Description:	This class will be used to read a file and generate an encrypted version of that file.
 */

package encryptionController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encryptor {
	
	//User input
	private String fileName;
	private String password;
	
	//Variables to hold source and encrypted data
	private byte[] decData;
	private byte[] encData;
	
	//Constructor
	Encryptor(String inFileName, String inPassword){
		try {
			this.fileName = inFileName;
			this.password = inPassword;
			this.prepareSourceData();
			this.encData = new CipherGenerator(this.password, Mode.ENCRYPT).getCipher().doFinal(decData);
			this.generateEncryptedData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void prepareSourceData() throws Exception{
		//Open file for reading
		File file = new File(this.fileName);
		FileInputStream inFileStream = new FileInputStream(file);
		
		//Setup array to hold source data
		int blockSize = 8;
		int paddedCount = blockSize - ((int)file.length() % blockSize);
		int padded = (int)file.length() + paddedCount;
		this.decData = new byte[padded];
		
		//Read source data to array
		inFileStream.read(this.decData);
		
		inFileStream.close();
	}
	
	private void generateEncryptedData() throws Exception{
		FileOutputStream outFileStream = new FileOutputStream(new File(this.fileName + ".tce"));
		outFileStream.write(encData);
		outFileStream.close();
	}
}
