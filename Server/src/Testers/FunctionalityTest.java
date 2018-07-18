package Testers;

import Request.Request;

public class FunctionalityTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * System Functionality Tests
		 * 1. Login
		 * 2. Register
		 * 3. Music uploading
		 */
		Request.register("functest@ucsc.edu", "functest");
		String uid = Request.login("functest@ucsc.edu", "functest");
		if (uid.contains("LOGIN:"))
			System.out.println("FAIL");
		else
			System.out.println(Request.upload(uid, "C:\\Users\\�ο�\\Dropbox\\Music\\������, ������, ����� ��\\[MV] Chancellor(æ����) _ Surrender (Feat. Lyn(��)).mp3"));
		
	}

}
