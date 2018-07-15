package API;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Class {
	
	public static String MD5Generator (String plaintext) {
		
		/**
		 * Purpose: to return randomly-looking String by an input String
		 * Input requirement: any String
		 * Output: randomly-looking & (practically but not theoritically) unique String
		 */
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(plaintext.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			return bigInt.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
