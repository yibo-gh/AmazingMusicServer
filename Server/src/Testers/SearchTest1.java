package Testers;

import Request.Request;
import Object.*;

public class SearchTest1 {
	
	public static void main(String[] args) {
		
		Request.register("ciy@naver.co.kr", "sdfsd");
		Request.register("ci2231y@naver.co.kr", "123dsf");
		Request.register("ci33y@naver.com", "123dssad");
		System.out.println("1");
		
		String uid1 = Request.login("ciy@naver.co.kr", "sdfsd");
		Request.upload(uid1, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "ÀÌ¼ö(¿¥¾¾´õ¸Æ½º)-01-My Way-320k.mp3");
		Request.upload(uid1, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "Panic! At The Disco-07-LA Devotee-320k.mp3");
		System.out.println("2");
		
		String uid2 = Request.login("ci2231y@naver.co.kr", "123dsf");
		Request.upload(uid2, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "È£¾Æ-07-¹øÁö³× (OST Remastered Ver.)-320k.mp3");
		
		System.out.println("3");
		
		String uid3 = Request.login("ci33y@naver.com", "123dssad");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "È£¾Æ-07-¹øÁö³× (OST Remastered Ver.)-320k.mp3");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "°ûÁø¾ð-02-³ª¶û °¥·¡-320k.mp3");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\À½¾Ç\\2018.4\\"
				+ "M.C. the Max-10-´Ù½Ã, ³ë·¡...-320k.mp3");
		Request.upload(uid3, "C:\\Users\\user\\Documents\\Ä«Ä«¿ÀÅå ¹ÞÀº ÆÄÀÏ\\"
				+ "Beethoven Violin 'Sonata' No.5 Spring Mov.1.mp3");
		
		System.out.println("4");
		
		musicDownloadingTest();
	}
	
	private static void musicDownloadingTest() {
		System.out.println("Music downloading test");
		
		Object llObj, srObj;
		int len;
		LinkedList ll;
		SearchResult sr;
		String url, filename;
		
		/*
		 * Request to search "Rossette"
		 */
		llObj = Request.search("ee");
		if (llObj.getClass().equals("".getClass())) {
			printError(0x04, "ee");
			return;
		}
		ll = (LinkedList) llObj;
		
		len = ll.getLength();
		for (int i=0; i<len; i++) {
			srObj = ll.head.getInfo();
			sr = (SearchResult)srObj;
			url = "file://localhost/C:\\Users\\user\\Documents\\GitHub\\AmazingMusicServer\\"+sr.getURL();
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
