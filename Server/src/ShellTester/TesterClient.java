package ShellTester;

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
		
		/*
		 * Test:
		 * 1. Valid string request & Exist a correspoding core function
		 * 2. Valid string request & No core function
		 * 3. Valid string + number request & No core function
		 * 4. Valid number request & No core function
		 * 5. Invalid number request
		 * 6. Invalid request (header only)
		 */
		list = new LinkedList();
		list.add("tst");
		list.add("Server received a string.");
		verification(SocketClientTester.request(list).equals("0x1A04"));
		
		list = new LinkedList();
		list.add("tstsdfd");
		list.add("Server received a string.");
		verification(SocketClientTester.request(list).equals("0x1A04"));
		
		list = new LinkedList();
		list.add("0x1");
		list.add("I love server programming in Java.");
		verification(SocketClientTester.request(list).equals("0x1A04"));
		
		list = new LinkedList();
		list.add("0231");
		list.add("I love server programming in Java.");
		verification(SocketClientTester.request(list).equals("0x1A04"));
	
		list = new LinkedList();
		list.add(1231);
		list.add("I love server programming in Java.");
		verification(SocketClientTester.request(list).equals("0x1A02"));		
		
		list = new LinkedList();
		list.add("sdf");
		verification(SocketClientTester.request(list).equals("0x1A03"));
		
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
