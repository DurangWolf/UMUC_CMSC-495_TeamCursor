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
 * File:		EncryptionController.java
 * 
 * Date:		3/31/2014
 * 
 * Description:	This file is to act as a place holder for what will eventually be a GUI.
 * 				This file will be used to test other classes being developed. 
 */

package encryptionController;

import java.security.GeneralSecurityException;

public class EncryptionController {
	
	//Variables
	private String password;
	private String fileName;
	private Boolean isValid;
	
	
	//Constructors
	EncryptionController(){
		this.password = "";
		this.fileName = "";
	}
	
	EncryptionController(String inputPassword, String inputFileName){
		this.password = inputPassword;
		this.fileName = inputFileName;
	}
	
	
	//Setters
	public void setPassword(String inputPassword){
		this.password = inputPassword;
	}
	
	public void setFileName(String inputFileName){
		this.fileName = inputFileName;
	}
	
	
	
	//Methods
	//validateInput checks to make sure that this instance of EncryptionController
	//has a valid password and file name.
	private void validateInput(){
		if(this.password != "" && this.fileName != ""){
			this.isValid = true;
		}
		else{
			this.isValid = false;
		}
	}
	
	//processFile() will decide to encrypt or decrypt the currently stored in fileName,
	//and call the appropriate methods.
	public void processFile(){
		this.validateInput();
		if(this.isValid){
			//continue
		}
		else{
			//display an error
		}
	}
	
	//processFile(String,String) will update the password and file name accordingly,
	//and process the file.
	public void processFile(String inputPassword, String inputFileName){
		this.setPassword(inputPassword);
		this.setFileName(inputFileName);		
		this.processFile();
	}
	
	//encryptFile() will encrypt the file currently stored in fileName
	public void encryptFile(){
		this.validateInput();
		if(this.isValid){
			//continue
		}
		else{
			//display an error
		}
	}
	
	//encryptFile(String,String) will update the password and fileName accordingly,
	//and process the file
	public void encryptFile(String inputPassword, String inputFileName){
		this.setPassword(inputPassword);
		this.setFileName(inputFileName);
		this.encryptFile();
	}
	
	//decryptFile() will decrypt the file currently stored in fileName
	public void decryptFile(){
		this.validateInput();
		if(this.isValid){
			//continue
		}
		else{
			//display an error
		}
	}
	
	//decryptFile(String,String) will update the password and fileName accordingly,
	//and process the file
	public void decryptFile(String inputPassword, String inputFileName){
		this.setPassword(inputPassword);
		this.setFileName(inputFileName);
		this.decryptFile();
	}
	
	
	
	
	
	
	
	
	
	
	//main method that provides a command line interface
	//for use during testing before a GUI is implemented.
	public static void main(String[] args){
		new Encryptor("Test.txt","mypassword");
		new Decryptor("Test.txt","mypassword");
	}
}
