package CoreFunctionTester;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5test {

	public static void main(String[] args) {
		
		/**
		 * Purpose: Toy code for a MD5 hash function and MessageDigest class.
		 * For further info:
		 * https://en.wikipedia.org/wiki/MD5
		 * https://en.wikipedia.org/wiki/Md5sum
		 */
		
		try {
			String originalText = "your tsdfdsfsfdfext here";
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(originalText.getBytes());
			byte[] digest = md.digest();
			
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			
			System.out.println(hashtext);
			System.out.println(hashtext.length());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
