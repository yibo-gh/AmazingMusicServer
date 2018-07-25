package API;

import Object.LinkedList;
import Object.Node;

public class Decoder {

	public static Object firewall(Object o) {
		
		/**
		 * Purpose: farewell(Object o) has 3 ways to block the illegal variable
		 * 			1. Verify the input object is the class of LinkedList
		 * 			2. Verify the object is a String
		 * 			3. Verify the object is a string which its length is larger than 0 and it
		 * 				only contain letters or numbers.
		 * 			4. Verify the length of input LinkedList is longer than or equal with 2: at least one header and one information for the request
		 * Input Requirement: This function requires a Object as parameter to run.
		 * Return: If this function returns "0x1A01","0x1A02","0x1A03", it means that input object is illegal
		 * 		   If this function returns getCommand(ll), it means that the input object is legal.
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
		
		if (ll.getLength() < 2 ) {
			return "0x1A03";
		}
		
		return getCommand(ll);
	}

	private static Object getCommand(LinkedList ll) {
		
		/**
		 * Purpose: This function is a decoder. That is, this function will divide the input linkedlist into header and rest
		 * 			using .head and rest() function.
		 * 			Header means what kinds of request client send.
		 * 			Then a core function will be selected according to the header.
		 * Input Requirement:This function requires a LinkedList as parameter to run.
		 * Output: If it is a valid comment, it will return whatever return the core function, else return to "0x1A04" 
		 */
		
		/*
		 * To decide what request is, we need to look the request header, which is ll.head.
		 */
		switch ((String) ll.head.getInfo().toString()) {
			case "reg": return CoreFunctions.register(rest(ll));
			case "lgn": return CoreFunctions.login(rest(ll));
			case "upl": return CoreFunctions.upload(rest(ll));
			case "sch": return CoreFunctions.search(rest(ll));
			default: return "0x1A04";
		}
	}
	
	private static LinkedList rest(LinkedList ll) {
		
		/**
		 * Purpose: obtain the rest(all but head node) of a linkedlist
		 * Input Requirement: linkedlist
		 * Output: the rest of linkedlist.
		 */
		
		ll.delete(0);
		return ll;
	}
	
	private static boolean isLetter(char c) {
		
		/**
		 * Purpose: This function provide the range of the letter and number for the firewell(Object o) 
		 * to test whether the user input object is letter or number
		 * Input Requirement:This function requires a char value as parameter to run.
		 * Return: This function should return "true" or "false"
		 */
		
		if ((Character.isDigit(c)) || (c >= 0x41 && c <= 0x5A) || (c >= 0x61 && c <= 0x7A)) {
			return true;
		}
		return false;
	}
}