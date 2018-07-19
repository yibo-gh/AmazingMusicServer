package Testers;

import Request.Request;

public class RobustnessTest {

	public static void main(String[] args) {
		
		/*
		 * System Functionality Robustness Tests
		 * 1. File does not exist
		 */
		
		fileNotExistTest();
	
	}

	private static void fileNotExistTest() {
		String uid, filename;
		System.out.println("File does not exist test");
		
		filename = "C:\\Users\\인영\\Dropbox\\Music\\"
				+ "[MV] Chancellor(챈슬러) _ Surrender (Feat. Lyn(린)).mp3";
		uid = Request.login("ciy405x@kaist.ac.kr", "whdlsdud81204");
		if (uid.contains("LOGIN:")) {
			Request.register("ciy405x@kaist.ac.kr", "whdlsdud81204");
			uid = Request.login("ciy405x@kaist.ac.kr", "whdlsdud81204");
		}
		if (!Request.upload(uid, filename).equals("REQ:FILENOTEXIST")) {
			printError(0x01, filename);
			return;
		}
		
		System.out.println("File does not exist test pass. Continues.\n");
		System.out.println("All tests pass. Done.");
	}
	
	private static void printError (int i, String n) {
		System.out.print("Test failed at ");
		switch (i) {
		case (0x01): System.out.print("file existence validator"); break;
		default: System.out.println("invalid function.");
		}
		System.out.println(" with " + n);
		System.out.println("Test terminated with error.");
	}
}
