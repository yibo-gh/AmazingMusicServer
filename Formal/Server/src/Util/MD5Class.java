package Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.File;

public class MD5Class {
	
	/*
	 * MD5 is an algorithm which generates random-looking hash value.
	 * MD5class includes tools which generate MD5 hash value from String or File object.
	 */

	public static String MD5Generator(String plaintext) {

		/**
		 * Purpose: String -> random-looking hash value 
		 * Input requirement: any String 
		 * Output: randomly-looking & (practically but not theoretically) unique
		 * String
		 */

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(plaintext.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			return bigInt.toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String FileMD5Generator(File file) {
		
		/**
		 * Purpose: File -> random-looking MD5 value
		 * Input Requirement: File object
		 * Output: MD5 value
		 */
		
		FileInputStream fis = null;
		MessageDigest digest = null;
		StringBuilder sb = null;
		String md5 = null;
		try {
			/*
			 * Get file input stream for reading the file content
			 * then create byte array to read data in chunks
			 */
			fis = new FileInputStream(file);
			digest = MessageDigest.getInstance("MD5");
			byte[] byteArray = new byte[1024];
			int bytesCount = 0;

			/*
			 * Read file data and update in message digest
			 */
			while ((bytesCount = fis.read(byteArray)) != -1) {
				digest.update(byteArray, 0, bytesCount);
			}
			fis.close();

			/*
			 * Get the hash's bytes
			 */
			byte[] bytes = digest.digest();

			/*
			 *  This bytes[] has bytes in decimal format
			 *  Convert it to hexadecimal format
			 */
			sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			md5 = sb.toString();

			/*
			 * return complete MD5 value
			 */
			return md5;
		} catch (Exception e) {
			e.printStackTrace();
			return md5;
		}
	}

}
