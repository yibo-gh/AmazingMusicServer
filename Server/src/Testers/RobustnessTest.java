package Testers;

import Request.Request;

public class RobustnessTest {

	public static void main(String[] args) {
		
		/*
		 * System Functionality Robustness Tests
		 * 1. File does not exist
		 * 1. Sequential uploading
		 * 2. Parallel uploading
		 */
		String uid2 = Request.login("functest@ucsc.edu", "functest");
		if (uid2.contains("LOGIN:"))
			System.out.println("FAIL");
		else
			PorF(Request.upload(uid2, "C:\\Users\\인영\\Dropbox\\Music\\"
					+ "[MV] Chancellor(챈슬러) _ Surrender (Feat. Lyn(린)).mp3").equals("REQ:FILENOTEXIST"));		
	}

	private static void PorF(boolean statement) {
		
		/**
		 * Pass or Fail
		 */
		
		if (statement) {
			System.out.println("Pass");
		} 
		else {
			System.out.println("FAIL");
		}
	}
}
