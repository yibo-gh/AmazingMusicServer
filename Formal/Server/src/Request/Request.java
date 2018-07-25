package Request;

import java.io.File;

import Client.FileGiveClient;
import Client.SocketClient;
import Object.Download;
import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import Util.MD5Class;

public class Request {
	
	public static String register(String email, String pw) throws Exception {
		
		LinkedList list = new LinkedList();
		list.add("reg");
		User user = new User(email, pw);
		if (user.getErrorCode().equals("INVALIDEMAIL"))
			return "INVALIDEMAIL";
		list.add(user);
		return (String) SocketClient.request(list);
	}
	
	public static String login(String email, String pw) throws Exception {	
		
		LinkedList list = new LinkedList();
		list.add("lgn");
		User user = new User(email, pw);
		if (user.getErrorCode().equals("INVALIDEMAIL"))
			return "INVALIDEMAIL";
		list.add(user);
		return (String) SocketClient.request(list);
	}
	
	public static String upload(String uid, String directory) throws Exception {	
		
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
		/*
		 * make a unique value of the file for the validating process of the file server.
		 */
		md5 = MD5Class.FileMD5Generator(file);

		FileInfo fInfo = new FileInfo(uid, directory.substring(directory.lastIndexOf("\\")+1), md5, directory.substring(directory.lastIndexOf("."))); // Note: need some tests for file extension
		list.add(fInfo);
		
		/*
		 * start to connect to the server.
		 */
		String tmp = (String) SocketClient.request(list);
		
		/*
		 * if the message that the server give is "UPS",
		 * you can now upload the file to file server.
		 */
		if (tmp.equals("UPS")) {
			LinkedList fileList = new LinkedList();
			fileList.add("upload");
			fileList.add(fInfo);
			
			/*
			 * start to connect to the file server.
			 */
			Object obj = FileGiveClient.fileGiveRequest(fileList, directory);
			if (!obj.getClass().equals("String".getClass())) {
				return "REQ:NOTSTRING";
			}
			return (String) obj;
		}
		
		return tmp;
	}
	
	public static Object search(String name) throws Exception {
		
		LinkedList list = new LinkedList();
		list.add("sch");
		/*
		if(name.equals("")) {
			return "REQ:INVALIDNAME";
		}*/
		list.add(name);
		
		Object llObj = SocketClient.request(list);
		
		/*
		 * Make sure that the llObj is a LinkedList object.
		 * Convert the type of llObj to LinkedList.
		 */
		if (!llObj.getClass().equals(new LinkedList().getClass())) {
			return "REQ:NOTLIST";
		}
		LinkedList ll = (LinkedList) llObj;
		
		/*
		 * Verify that search request has completed normally.
		 * However, this does not mean there is a file in the server the user want.
		 */
		String searchResult = (String) ll.head.getInfo();
		if (!searchResult.equals("SEARCH RESULT")) {
			return "REQ:REQUESTFAIL";
		}
		
		/*
		 * Although the search request has completed successfully,
		 * the server might not have the file which user want.
		 * In this case, there is no SearchResult object in the linked list.
		 * There is only header "SEARCH RESULT"
		 */
		if (ll.getLength() == 1) {
			return "REQ:NOSUCHFILE";
		}
		
		/*
		 * User! Choose a file.
		 * Delete the header.
		 */
		ll.delete(0);
		return ll;
	}
	
	public static String download(String url, String filename) {
		Download dn = new Download(url, filename);
		return dn.start();
	}
}
