package Testers;

import Object.LinkedList;
import Object.SearchResult;
import Request.Request;

public class FunctionalityTest {

	private static String[] emails = {"ciy405@naver.korea", "test1@gmail.com", "ganadara@xx.xx", "form@hubo.co.jp", "damm@go.go.go"};
	private static String[] uids = new String[emails.length];
	private static String[] musics = {"B Rossette 하얀거탑.mp3", "Bishops Mess.mp3", "Final Fantasy XIII-2 B Rossette.mp3", "Kings Rage.mp3", "Beethoven Violin Sonata No.5 Spring Mov.1.mp3"};
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * System Functionality Tests
		 * 1. Register
		 * 2. Login
		 * 3. Music uploading
		 * 4. Music downloading
		 */
		registerTest();
		musicDownloadingTest2();
	}

	private static void registerTest() throws Exception {
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
	
	private static void loginTest() throws Exception {
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
	
	private static void musicUploadingTest() throws Exception {
		System.out.println("Music uploading test");
		
		for (int i=0; i<emails.length; i++) {
			if (!Request.upload(uids[i], "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + musics[i]).equals("VALIDATE SUCCESS")) {
				printError(0x03, musics[i]);
				return;
			}
		}
		
		System.out.println("Music uploading test pass. Continues.\n");
		musicDownloadingTest();
	}
	
	private static void musicDownloadingTest() throws Exception {
		System.out.println("Music downloading test");
		
		Object llObj, srObj;
		int len;
		LinkedList ll;
		SearchResult sr;
		String url, filename;
		
		/*
		 * Request to search all files whose filename contains the String "Rossette"
		 */
		llObj = Request.search("Rossette");
		if (llObj.getClass().equals("".getClass())) {
			printError(0x04, "Rossette");
			return;
		}
		ll = (LinkedList) llObj;
		
		len = ll.getLength();
		for (int i=0; i<len; i++) {
			srObj = ll.head.getInfo();
			sr = (SearchResult)srObj;
			url = "https://yg-home.site/proj/amamusic/audio/"+sr.getURL();
			filename = "genevahall" + i;
			if (!Request.download(url,filename).equals("SUCCEED")) {
				printError(0x04, filename);
				return;
			}
			ll.delete(0);
		}
		
		System.out.println("Music downloading test pass. Continues.\n");
		System.out.println("All tests pass. Done.");
	}
	
	private static void musicDownloadingTest2() throws Exception {
		System.out.println("Music downloading test 2");
		
		Object llObj, srObj;
		LinkedList ll;
		SearchResult sr;
		String uid, url, filename;
		
		if (!Request.register("muDownTe@st.co.kr", "download").equals("UPS"))
			return;
		uid = Request.login("muDownTe@st.co.kr", "download");
		if (uid.contains("LOGIN:"))
			return;
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "B Rossette 하얀거탑.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Beethoven Violin Sonata No.5 Spring Mov.1.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Bishops Mess.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Final Fantasy XIII-2 B Rossette.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Kings Rage.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Talesweaver OST - Reminiscence.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "Yiruma, (이루마) - Reminiscent.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "센과 치히로의 행방불명 - 언제나 몇번이라도 (Spirited Away - Always With Me).mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "차르디시_몬티 곡_바이올린-신지아_피아노-손열음.mp3");
		Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\클래식\\" + "히사이시 조   Summer.mp3");
		
		Request.register("iwantto@down.load", "downdown");
		uid = Request.login("iwantto@down.load", "downdown");
		
		llObj = Request.search("Mess.mp3");
		ll = (LinkedList) llObj;
		srObj = ll.head.getInfo();
		sr = (SearchResult)srObj;
		url = "https://yg-home.site/proj/amamusic/audio/"+sr.getURL();
		filename = "Mess";
		Request.download(url, filename);
		
		llObj = Request.search("Beethoven");
		ll = (LinkedList) llObj;
		srObj = ll.head.getInfo();
		sr = (SearchResult)srObj;
		url = "https://yg-home.site/proj/amamusic/audio/"+sr.getURL();
		filename = "Beethoven";
		Request.download(url, filename);
		
		llObj = Request.search("Fantasy");
		ll = (LinkedList) llObj;
		srObj = ll.head.getInfo();
		sr = (SearchResult)srObj;
		url = "https://yg-home.site/proj/amamusic/audio/"+sr.getURL();
		filename = "Fantasy";
		Request.download(url, filename);
		
		llObj = Request.search("손열음.mp3");
		ll = (LinkedList) llObj;
		srObj = ll.head.getInfo();
		sr = (SearchResult)srObj;
		url = "https://yg-home.site/proj/amamusic/audio/"+sr.getURL();
		filename = "손열음";
		Request.download(url, filename);
		
		System.out.println("Music downloading test 2 pass. Done.");
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
		case (0x04): System.out.print("Downloading validator"); break;
		default: System.out.println("invalid function.");
		}
		System.out.println(" with " + d);
		System.out.println("Test terminated with error.");
	}
}
