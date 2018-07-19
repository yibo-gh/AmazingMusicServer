package Testers;

import Request.Request;
import Object.*;

public class SearchTest1 {
	
	private static int step = 0;
	
	public static void main(String[] args) {
		
		Request.register("ciy@naver.co.kr", "sdfsd");
		Request.register("ci2231y@naver.co.kr", "123dsf");
		Request.register("ci33y@naver.com", "123dssad");
		System.out.println("1");
		
		String uid1 = Request.login("ciy@naver.co.kr", "sdfsd");
		Request.upload(uid1, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "�̼�(�������ƽ�)-01-My Way-320k.mp3");
		Request.upload(uid1, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "Panic! At The Disco-07-LA Devotee-320k.mp3");
		System.out.println("2");
		
		String uid2 = Request.login("ci2231y@naver.co.kr", "123dsf");
		Request.upload(uid2, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "ȣ��-07-������ (OST Remastered Ver.)-320k.mp3");
		
		System.out.println("3");
		
		String uid3 = Request.login("ci33y@naver.com", "123dssad");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "ȣ��-07-������ (OST Remastered Ver.)-320k.mp3");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "������-02-���� ����-320k.mp3");
		Request.upload(uid3, "C:\\Users\\user\\Desktop\\����\\2018.4\\"
				+ "M.C. the Max-10-�ٽ�, �뷡...-320k.mp3");
		
		System.out.println("4");
		
		Object first = Request.search("ȣ��");
		System.out.println(first);
		LinkedList f = (LinkedList) first;
		SearchResult s1 = (SearchResult) f.end.getInfo();
		
		System.out.println(f.head.getInfo().toString());
		System.out.println(s1.getOriName());
		System.out.println(s1.getURL());

		Object second = Request.search("M.C.");
		LinkedList s = (LinkedList) first;
		SearchResult s2 = (SearchResult) f.end.getInfo();
	}
	
	private static void PorF(boolean statement) {
			
			/**
			 * Pass or Fail
			 */
			step += 1;
			if (statement) {
				System.out.println("Pass"+ step);
			} 
			else {
				System.out.println("FAIL" + step);
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



}