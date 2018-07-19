package Testers;

import Request.Request;

public class FunctionalityTest {

	private static String[] emails = {"ciy405@naver.korea", "test1@gmail.com", "ganadara@xx.xx", "form@hubo.co.jp", "damm@go.go.go"};
	private static String[] uids = new String[emails.length];
	private static String[] musics = {"B Rossette 하얀거탑.mp3", "Bishops Mess.mp3", "Final Fantasy XIII-2 B Rossette.mp3", "Kings Rage.mp3", "Beethoven Violin Sonata No.5 Spring Mov.1.mp3"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * System Functionality Tests
		 * 1. Register
		 * 2. Login
		 * 3. Music uploading
		 */
		
		registerTest();
	}

	private static void registerTest() {
		System.out.println("Register test");
		
		for (int i=0; i<emails.length; i++) {
			if (!Request.register(emails[i], "default").equals("UPS")) {
				printError(0x01, emails[i], "default");
				return;
			}
		}
		
		System.out.println("Register test pass. Continues.\n");
		loginTest();
	}
	
	private static void loginTest() {
		System.out.println("Login test");
		
		for (int i=0; i<emails.length; i++) {
			uids[i] = Request.login(emails[i], "default");
			if (uids[i].contains("LOGIN:")) { // Every error message contains "LOGIN:" string.
				printError(0x02, emails[i], "default");
				return;
			}
		}
		
		System.out.println("Login test pass. Continues.\n");
		musicUploadingTest();
	}
	
	private static void musicUploadingTest() {
		System.out.println("Music uploading test");
		
		for (int i=0; i<emails.length; i++) {
			if (!Request.upload(uids[i], "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + musics[i]).equals("VALIDATE SUCCESS")) {
				printError(0x03, musics[i]);
				return;
			}
		}
		
		System.out.println("Music uploading test pass. Continues.\n");
		System.out.println("All tests pass. Done.");
	}
	
	private static void printError (int i, String e, String p) {
		System.out.print("Test failed at ");
		switch (i) {
		case (0x01): System.out.print("Registration validator"); break;
		case (0x02): System.out.print("Login validator"); break;
		default: System.out.println("invalid function.");
		}
		System.out.println(" with " + e + " & " + p);
		System.out.println("Test terminated with error.");
	}
	
	private static void printError (int i, String d) {
		System.out.print("Test failed at ");
		switch (i) {
		case (0x03): System.out.print("Uploading validator"); break;
		default: System.out.println("invalid function.");
		}
		System.out.println(" with " + d);
		System.out.println("Test terminated with error.");
	}
}
