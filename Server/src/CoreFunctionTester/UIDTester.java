package CoreFunctionTester;

import Object.User;

public class UIDTester {
	
	public static void main(String[] args) {
		
		String[] testCases = {"icho@ucsc.edu", "ciy405x@kaist.ac.kr", "me@gmail.com", 
							"me@company.co.uk", "inyoungh@naver.com", "Iam@g.com", 
							"inyoung@navrr.cor", "inyoun@kai.ac.kr", "inyoun@k.ac.kr"};
		String[] UIDs = {"ucsexxicho", "kaakciy405", "gmacxxxxme", 
						 "cocuxxxxme", "navcinyoun", "gxxcxxxIam",
						 "navcinyoun", "kaakinyoun", "kxakinyoun"};
		
		/*
		 * This test code is no longer working.
		 */
		for (int i=0; i < testCases.length; i++) {
			User u = new User(testCases[i], "");
			if (u.getUID().equals(UIDs[i]) && u.getErrorCode() != "INVALIDEMAIL")
				System.out.println("Pass");
			else
				System.out.println("FAIL");
		}
	}
}
