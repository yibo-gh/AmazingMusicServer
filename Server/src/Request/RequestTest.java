package Request;

import Object.User;

public class RequestTest extends Thread{
	int cnt = 0;
	static int N = 100;
	
	public static void main(String[] args) {
		/*
		 * parallel request
		 */
		
		int tmp = 0;
		while (tmp++ < N) {
			new RequestTest();
		}
		
		/*
		 * sequential request
		 */
		
		tmp = 0;
		while (tmp++ < N) {
			System.out.println(Request.login("icho4@ucsc.edu", "cofls8680*"));
		}
		
	}
	
	private RequestTest () {
		start();
	}
	
	public void run() {
		System.out.println(Request.login("icho4@ucsc.edu", "cofls8680*"));
		this.cnt++;
		if (this.cnt > N-5)
			System.out.println(this.cnt);
	}

}
