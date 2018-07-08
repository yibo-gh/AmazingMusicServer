package Testers;

import Object.LinkedList;
import Client.SocketClient;

public class ClientRequestTester {
	
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.add("This is a string.");
		list.add(3);
		
		SocketClient.request(list);
	}
}
