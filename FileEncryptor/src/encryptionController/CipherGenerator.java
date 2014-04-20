package encryptionController;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class CipherGenerator {
	//Static attributes
	private final byte[] salt = {
		(byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
        (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17
	};
	
	//Variables 
	private PBEKeySpec keySpec;
	private SecretKeyFactory keyFactory;
	private SecretKey key;
	private Cipher cipher;
	
	//Constructor
	CipherGenerator(String inPassword, Mode currentMode) throws GeneralSecurityException{
		//Generate Key from password
		this.keySpec = new PBEKeySpec(inPassword.toCharArray());
		this.keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		this.key = this.keyFactory.generateSecret(this.keySpec);
		
		//Create parameters from a static salt sequence and iterations
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(this.salt, 55);
		
		
		this.cipher = Cipher.getInstance("PBEWithMD5AndDES");
		
		switch(currentMode){
		case ENCRYPT:
			this.cipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
			break;
		case DECRYPT:
			this.cipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
			break;
		default:
			break;
		}
	}
	
	protected Cipher getCipher(){
		return this.cipher;
	}
}
