package Request;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Client.FileGiveClient;
import Client.SocketClient;
import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import Util.MD5Class;

public class Request {
	
	public static String register(String email, String pw) {
		LinkedList list = new LinkedList();
		list.add("reg");
		User user = new User(email, pw);
		if (user.getErrorCode().equals("INVALIDEMAIL"))
			return "INVALIDEMAIL";
		list.add(user);
		return (String) SocketClient.request(list);
	}
	
	public static String login(String email, String pw) {		
		LinkedList list = new LinkedList();
		list.add("lgn");
		User user = new User(email, pw);
		if (user.getErrorCode().equals("INVALIDEMAIL"))
			return "INVALIDEMAIL";
		list.add(user);
		return (String) SocketClient.request(list);
	}
	
	public static String upload(String uid, String directory) {	
		
		/**
		 * Caution: after user login, you have to verify the uid which is returned by login(), 
		 * 			so that (String) uid is not a error message.
		 * 			keep in mind that uid can be a error message.
		 * 			Error message always contains "LOGIN:" substring.
		 * 			Verify that uid does not contain such string before apply upload function.
		 * 			See the code below:
		 * 
		 * if (uid.contains("LOGIN:"))
		 * 		System.out.println("FAIL");
		 * else
		 * 		~~~ upload(uid, directory) ~~
		 */
		
		LinkedList list = new LinkedList();
		list.add("upl");
		String md5;
		File file = new File(directory);
		if (!file.exists())
			return "REQ:FILENOTEXIST";
		md5 = MD5Class.FileMD5Generator(file);

		FileInfo fInfo = new FileInfo(uid, directory, md5, directory.substring(directory.lastIndexOf("."))); // Note: need some tests for file extension
		list.add(fInfo);
		String tmp = (String) SocketClient.request(list);
		
		if (tmp.equals("UPS")) {
			LinkedList fileList = new LinkedList();
			fileList.add("upload");
			fileList.add(fInfo);
			Object obj = FileGiveClient.fileGiveRequest(fileList, directory);
			if (!obj.getClass().equals("String".getClass())) {
				return "REQ:NOTSTRING";
			}
			return (String) obj;
		}
		
		return tmp;
	}
	
}
