package CoreFunctionTester;

import Client.SocketClient;
import Object.LinkedList;
import Object.User;

public class TesterClient {
	
	public static void main(String[] args) {
		/**
		 * Purpose: a test code for SocketClient.java, Server.java, CreateServerThread.java
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
		 * 1. Valid string request & Exist a correspoding core function
		 * 2. Valid string request & No core function
		 * 3. Valid string + number request & No core function
		 * 4. Valid number request & No core function
		 * 5. Invalid number request
		 * 6. Invalid request (header only)
		 */
		/*
		list = new LinkedList();
		list.add("reg");
		user = new User("icho", "ucsc.edu", "cofls8680*");
		list.add(user);
		System.out.println((String) SocketClient.request(list));
		*/
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("icho", "ucsc.edu", "cofls8680*");
		list.add(user);
		verification(((String) SocketClient.request(list)).equals("LOGINSUCCEED"));
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("ich", "ucsc.edu", "cofls8680*");
		list.add(user);
		verification(((String) SocketClient.request(list)).equals("NOTREGISTERED"));
		
		list = new LinkedList();
		list.add("lgn");
		user = new User("icho", "ucsc.edu", "cofls8680");
		list.add(user);
		verification(((String) SocketClient.request(list)).equals("PWINCORRECT"));
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
