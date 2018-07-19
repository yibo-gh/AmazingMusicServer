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
		
		System.out.println("4");
		
		Object first = Request.search("È£¾Æ");
		System.out.println(first);
		LinkedList f = (LinkedList) first;
		SearchResult s1 = (SearchResult) f.end.getInfo();
		
		System.out.println(f.head.getInfo().toString());
		System.out.println(s1.getOriName());
		System.out.println(s1.getURL());

		Object second = Request.search("M.C.");
		LinkedList s = (LinkedList) second;
		SearchResult s2 = (SearchResult) s.end.getInfo();
		
		System.out.println(s.head.getInfo().toString());
		System.out.println(s2.getOriName());
		System.out.println(s2.getURL());
	}

}
