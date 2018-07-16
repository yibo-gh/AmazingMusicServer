package Request;

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

public class Request {
	
	public static String register(String email, String pw) {
		LinkedList list = new LinkedList();
		list.add("reg");
		User user = new User(email, pw);
		list.add(user);
		return (String) SocketClient.request(list);
	}
	
	public static String login(String email, String pw) {		
		LinkedList list = new LinkedList();
		list.add("lgn");
		User user = new User(email, pw);
		list.add(user);
		return (String) SocketClient.request(list);		
	}
	
	public static String upload(String uid, String directory) {		
		LinkedList list = new LinkedList();
		list.add("upl");
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get(directory)));
		    byte[] digest = md.digest();
		    BigInteger bigInt = new BigInteger(1,digest);		
			FileInfo fInfo = new FileInfo(uid, directory, bigInt.toString(16));
			list.add(fInfo);
			String tmp = (String) SocketClient.request(list);
			//
			//
			// File Server part
			//
			//
			return tmp;
		} catch (NoSuchAlgorithmException | IOException e) {
			return "UPLOADERROR";
		}
	}
}
