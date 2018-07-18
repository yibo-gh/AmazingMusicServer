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
			System.out.println(Request.upload(uid, "C:\\Users\\인영\\Dropbox\\Music\\차분한, 조용한, 담담한 랩\\[MV] Chancellor(챈슬러) _ Surrender (Feat. Lyn(린)).mp3"));
		
	}

}
