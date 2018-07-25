package Testers;

import Request.Request;

public class ValidationTest {

	public static void main(String[] args) throws Exception {
		
		/*
		 * System Validation Tests
		 * 1. Invalid email test. 		Expected result: INVALIDEMAIL
		 * 2. Existing ID test			Expected result: REG:USEREXISTS
		 * 3. Wrong PW test				Expected result: LOGIN:PWINCORRECT
		 * 4. Unregistered ID test		Expected result: LOGIN:NOTREG
		 */
		
		invalidEmail();
		
	}
	
	private static void invalidEmail() throws Exception {
		System.out.println("Invalid email test");
		
		if (!Request.register("ciy@sdf@com.com", "sdf").equals("INVALIDEMAIL")) {
			printError(0x01, "ciy@sdf@com.com", "sdf");
			return;
		}
		if (!Request.register("ciy.com", "sdf").equals("INVALIDEMAIL")) {
			printError(0x01, "ciy.com", "sdf");
			return;
		}
		if (!Request.register("ciy405@naver", "sdf").equals("INVALIDEMAIL")) {
			printError(0x01, "ciy405@naver", "sdf");
			return;
		}
		
		System.out.println("Invalid email test pass. Countinue.\n");
		existingID();
	}

	private static void existingID() throws Exception {
		System.out.println("Existing ID test");
		
		if (!Request.register("test1*@naver.com", "duwn3823*").equals("UPS")) { // registration succeed
			printError(0x02, "test1*@naver.com", "duwn3823*");
			return;
		}
		if (!Request.register("test1*@naver.com", "duwn3823*").equals("REG:USEREXISTS")) { // existing email, registration fail
			printError(0x02, "test1*@naver.com", "duwn3823*");
			return;
		}
		if (!Request.register("test2*@na.ver.co.m", "duwn3823*").equals("UPS")) {
			printError(0x02, "test2*@na.ver.co.m", "duwn3823*");
			return;
		}
		if (!Request.register("test2*@na.ver.co.m", "duwn3823*").equals("REG:USEREXISTS")) {
			printError(0x02, "test2*@na.ver.co.m", "duwn3823*");
			return;
		}
		if (!Request.register("*@na.ver.co.m", "duwn3823*").equals("UPS")) {
			printError(0x02, "*@na.ver.co.m", "duwn3823*");
			return;
		}
		if (!Request.register("*@na.ver.co.m", "duwn3823*").equals("REG:USEREXISTS")) {
			printError(0x02, "*@na.ver.co.m", "duwn3823*");
			return;
		}
		
		System.out.println("Existing ID test pass. Countinue.\n");
		wrongPW();
	}
	
	private static void wrongPW() throws Exception {
		System.out.println("Wrong PW test");
		
		if (!Request.login("test1*@naver.com", "duwn3823").equals("LOGIN:PWINCORRECT")) { // registration succeed
			printError(0x03, "test1*@naver.com", "duwn3823");
			return;
		}
		if (!Request.login("test2*@na.ver.co.m", "duwnsd3823*").equals("LOGIN:PWINCORRECT")) {
			printError(0x03, "test2*@na.ver.co.m", "duwnsd3823*");
			return;
		}
		if (!Request.login("*@na.ver.co.m", "").equals("LOGIN:PWINCORRECT")) {
			printError(0x03, "*@na.ver.co.m", "");
			return;
		}
		
		System.out.println("Wrong PW test pass. Countinue.\n");
		unregisteredID();
	}
	
	private static void unregisteredID() throws Exception {
		System.out.println("Unregistered ID test");
		
		if (!Request.login("ciy2@naver.co.kr", "duwn3823").equals("LOGIN:NOTREG")) { // registration succeed
			printError(0x04, "test1*@naver.com", "duwn3823");
			return;
		}
		if (!Request.login("ci22331y@naver.co.kr", "duwnsd3823*").equals("LOGIN:NOTREG")) {
			printError(0x04, "ci22331y@naver.co.kr", "duwnsd3823*");
			return;
		}
		if (!Request.login("ci323y@naver.com", "").equals("LOGIN:NOTREG")) {
			printError(0x04, "ci323y@naver.com", "");
			return;
		}
		
		System.out.println("Unregistered ID test pass. Countinue.\n");
		System.out.println("All tests pass. Done.");
	}
	
	private static void printError (int i, String e, String p) {
		System.out.print("Test failed at ");
		switch (i) {
		case (0x01): System.out.print("Email validator"); break;
		case (0x02): System.out.print("ID validator"); break;
		case (0x03): System.out.print("PW validator"); break;
		case (0x04): System.out.print("Registration validator"); break;
		default: System.out.println("invalid function.");
		}
		System.out.println(" with " + e + " & " + p);
		System.out.println("Test terminated with error.");
	}
}
