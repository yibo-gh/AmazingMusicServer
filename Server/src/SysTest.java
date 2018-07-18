import Request.Request;

public class SysTest {

	public static void main (String args[]) {
		
		/**
		 * Purpose: Test code for the combination of gps and file servers
		 * Input Requirement: nothing
		 * Output: nothing
		 */
		
		/*
		 * System Vulnerability Tests
		 * 1. Invalid email test. 		Expected result: INVALIDEMAIL
		 * 2. Exist id test				Expected result: REG:USEREXISTS
		 * 3. Wrong pw test				Expected result: LOGIN:PWINCORRECT
		 * 4. Registered or not test	Expected result: LOGIN:NOTREG
		 */
		System.out.println("Invalid email test");
		v(Request.register("insd..sdf@cosmf.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.register("N#@LKsdlfk.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.register("ciy405*x@kaist.co.kr", "sdf").equals("INVALIDEMAIL"));
		v(Request.register("cofls@sdnf@ciy.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.register("sdf,@naver.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.login("insd..sdf@cosmf.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.login("N#@LKsdlfk.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.login("ciy405*x@kaist.co.kr", "sdf").equals("INVALIDEMAIL"));
		v(Request.login("cofls@sdnf@ciy.com", "sdf").equals("INVALIDEMAIL"));
		v(Request.login("sdf,@naver.com", "sdf").equals("INVALIDEMAIL"));
		
		System.out.println("Exist id test");
		//Request.register("ciy@naver.co.kr", "sdfsd");
		//Request.register("ci2231y@naver.co.kr", "123dsf");
		//Request.register("ci33y@naver.com", "123dssad");
		v(Request.register("ciy@naver.co.kr", "sdfsd").equals("REG:USEREXISTS"));
		v(Request.register("ci2231y@naver.co.kr", "sdf132").equals("REG:USEREXISTS"));
		v(Request.register("ci33y@naver.com", "12312sdlf").equals("REG:USEREXISTS"));
		
		System.out.println("Wrong pw test");
		v(Request.login("ciy@naver.co.kr", "sdfs1d").equals("LOGIN:PWINCORRECT"));
		v(Request.login("ci2231y@naver.co.kr", "sdfd132").equals("LOGIN:PWINCORRECT"));
		v(Request.login("ci33y@naver.com", "12312s2dlf").equals("LOGIN:PWINCORRECT"));
		
		System.out.println("Not registered yet");
		v(Request.login("ciy2@naver.co.kr", "sdfs1d").equals("LOGIN:NOTREG"));
		v(Request.login("ci22331y@naver.co.kr", "sdf132").equals("LOGIN:NOTREG"));
		v(Request.login("ci323y@naver.com", "12312sdlf").equals("LOGIN:NOTREG"));
		
		
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
		
		
		/*
		 * System Functionality Robustness Tests
		 * 1. File does not exist
		 * 1. Sequential uploading
		 * 2. Parallel uploading
		 */
		/*String uid2 = Request.login("functest@ucsc.edu", "functest");
		if (uid2.contains("LOGIN:"))
			System.out.println("FAIL");
		else
			v(Request.upload(uid2, "C:\\Users\\인영\\Dropbox\\Music\\[MV] Chancellor(챈슬러) _ Surrender (Feat. Lyn(린)).mp3").equals("REQ:FILENOTEXIST"));	*/	
	}
	
	private static void v(boolean statement) {
		if (statement) {
			System.out.println("Pass");
		} 
		else {
			System.out.println("FAIL");
		}
	}
}