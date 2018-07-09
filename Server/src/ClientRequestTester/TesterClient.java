package ClientRequestTester;

import Object.LinkedList;

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
		
		list = new LinkedList();
		list.add(-4.2);
		list.add(3);
		list.add(5.123);
		SocketClientTester.request(list);
		
		list = new LinkedList();
		list.add("Damn it's cool");
		list.add(3);
		list.add("Awesome");
		SocketClientTester.request(list);
		
		list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		SocketClientTester.request(list);
		
		list = new LinkedList();
		for (int i = 0; i < 800; i++) {
			list.add(i);
		}
		SocketClientTester.request(list);
	}
}
