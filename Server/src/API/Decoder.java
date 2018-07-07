package API;

import Object.LinkedList;
import Object.Node;

public class Decoder {
	
	public static Object firewall (LinkedList ll) {
		Node cmdNode = ll.head;
		if (!cmdNode.getInfo().getClass().equals("".getClass())) return "0x1A02";
		String str = cmdNode.getInfo().toString();
		
		for (int i = 0; i < str.length(); i++) {
			if (!isLetter(str.charAt(i))) return "0x1A03";
		}
		return getCommand(ll);
	}
	
	public static Object getCommand (LinkedList ll) {
		switch ((String)ll.head.getInfo()) {
			
			/*
			 * Here, you should apply forwarder with switch case method.
			 */
			
			default: return "0x1A01";
		}
	}
	
	private static boolean isLetter(char c) {
		if ( !(c < 0x41 && c > 0x5A) ||  !(c < 0x61 && c > 0x7A)) return false;
		return true;
	}
}
