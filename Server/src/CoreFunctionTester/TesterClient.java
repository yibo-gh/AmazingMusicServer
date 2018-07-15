package CoreFunctionTester;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Client.SocketClient;
import Object.FileInfo;
import Object.LinkedList;
import Object.User;

public class TesterClient {
	
	public static void main(String[] args) {
		
		/**
		 * Purpose: Test CoreFunctions.java and Decoder.java mainly.
		 * 			Test (1) client send a random linkedlist request, 
		 * 				 (2) server receive the request, 
		 * 				 (3) verify that the server receive the linkedlist accurately.
		 * Input requirement: None
		 * Output: None
		 */
		
		LinkedList list;
		User user;
		
		/*
		 * Test:
		 * 
		 */
		
		list = new LinkedList();
		list.add("reg");
		user = new User("icho@ucsc.edu", "cofls8680*");
		list.add(user);
		System.out.println((String) SocketClient.request(list));
		
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("ich@ucsc.edu", "cofls8680*");
		list.add(user);
		verification(((String) SocketClient.request(list)).equals("NOTREGISTERED"));
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("icho@ucsc.edu", "cofls8680");
		list.add(user);
		verification(((String) SocketClient.request(list)).equals("PWINCORRECT"));
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("icho@ucsc.edu", "cofls8680*");
		list.add(user);
		String uid = ((String) SocketClient.request(list));
		
		list = new LinkedList();
		list.add("upl");
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get("C:\\Users\\인영\\Desktop\\배두환.txt")));
		    byte[] digest = md.digest();
		    BigInteger bigInt = new BigInteger(1,digest);		
			FileInfo fInfo = new FileInfo(uid, "C:\\Users\\인영\\Desktop\\배두환.txt", bigInt.toString(16));
			list.add(fInfo);
			verification(((String) SocketClient.request(list)).equals("UPS"));
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void verification(boolean statement) {
		if (statement) {
			System.out.println("verified");
		} 
		else {
			System.out.println("verification fail.");
		}
	}
}
