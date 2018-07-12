package ShellTester;

import Object.LinkedList;

public class CoreFunctionsTester {
	private static final String DONE = "0x01";
	
	public static Object testFunc(LinkedList ll) {
		System.out.println((String)ll.head.getInfo());
		return DONE;
	}
}
