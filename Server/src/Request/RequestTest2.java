package Request;

public class RequestTest2 {

	public static void main(String[] args) {
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
		
		String[] testCases = {"icho@ucsc.edu", "ciy405x@kaist.ac.kr", "me@gmail.com", 
				"me@company.co.uk", "inyoungh@naver.com", "Iam@g.com", 
				"inyoung@navrr.cor", "inyoun@kai.ac.kr", "inyoun@k.ac.kr"};
		for (int i=0; i < testCases.length; i++) {
			System.out.println(Request.login(testCases[i], "c1235"));
		}
	}

}
