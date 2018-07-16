package Request;

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
			Request.login("icho4@ucsc.edu", "cofls8680*");
		}
		/*
		System.out.println(Request.login("ciy405x@kaist.ac.kr", "cofls8680*")); // NOTREGISTERED
		System.out.println(Request.register("ciy405x@kaist.ac.kr", "cofls8680*"));
		System.out.println(Request.login("icho4@ucsc.edu", "Wlgn0504"));
		System.out.println(Request.register("icho4@ucsc.edu", "cofls8680*"));
		System.out.println(Request.login("icho4@ucsc.edu", "Wlgn0504"));
		System.out.println(Request.login("icho4@ucsc.edu", "cofls8680*"));
		System.out.println(Request.upload(Request.login("icho4@ucsc.edu", "cofls8680*"), "C:\\Users\\인영\\Desktop\\DailyScrum&Plan\\Plan15.7.18.txt"));
		System.out.println(Request.upload(Request.login("icho4@ucsc.edu", "cofls8680*"), "C:\\Users\\인영\\Desktop\\DailyScrum&Plan\\Plan15.7.txt"));
		*/
	}
	
	private RequestTest () {
		start();
	}
	
	public void run() {
		Request.login("icho4@ucsc.edu", "cofls8680*");
		this.cnt++;
		if (this.cnt > N-5)
			System.out.println(this.cnt);
	}

}
