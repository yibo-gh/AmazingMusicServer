package API;

import Object.LinkedList;
import Object.Node;

public class Decoder {

	public static Object firewall(Object o) {
		
		/**
		 * Purpose: firewwall(Object o) has 3 ways to block the illegal variable
		 * 1. Verify the input object is the class of LinkedList
		 * 2. Verify the object is a String
		 * 3. Verify the object is a string which its length is larger than 0 and it
		 * only contain letters or numbers.
		 * Input Requirement:This function requires a Object as parameter to run.
		 * Return: If this function returns "0x1A01","0x1A02","0x1A03", it means that input object is illegal
		 * if this function return to getCommand(ll), it means that the input object is legal.
		 */
		
		if (!o.getClass().equals(new LinkedList().getClass())) {
			return "0x1A01";
		}
		LinkedList ll = (LinkedList) o;
		Node cmdNode = ll.head;
		if (!cmdNode.getInfo().getClass().equals("".getClass())) {
			return "0x1A02";
		}
		String str = cmdNode.getInfo().toString();

		for (int i = 0; i < str.length(); i++) {
			if (!isLetter(str.charAt(i))) {
				return "0x1A03";
			}
		}
		return getCommand(ll);
	}

	private static Object getCommand(LinkedList ll) {
		
		/**
		 * Purpose: This function is a decoder
		 * Input Requirement:This function requires a LinkedList as parameter to run.
		 * Output: If it is a valid comment, it will return whatever return the core function, else return to "0x1A04" 
		 */
		
		switch ((String) ll.head.getInfo()) {
		
		case "reg": return CoreServer.Core.register(ll.voidHead());
		case "lgi": return CoreServer.Core.login(ll.voidHead());
		case "ulf": return CoreServer.Core.uploadFile(ll.voidHead());
		
		default: return 0x1A04;
		}
	}
	
	private static boolean isLetter(char c) {
		
		/**
		 * Purpose: This function provide the range of the letter and number for the firewall(Object o) 
		 * to test whether the user input object is letter or number
		 * Input Requirement:This function requires a char value as parameter to run.
		 * Return: This function should return "true" or "false"
		 */
		
		if (!(c < 0x41 && c > 0x5A) || !(c < 0x61 && c > 0x7A)) {
			return false;
		}
		return true;
	}
}
