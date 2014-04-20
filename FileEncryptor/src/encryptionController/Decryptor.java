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

public class Decryptor{
	
	//User input
	private String fileName;
	private String password;
		
	//Variables to hold source and encrypted data
	private byte[] decData;
	private byte[] encData;
	
	//Constructor
	Decryptor(String inFileName, String inPassword){
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

}
