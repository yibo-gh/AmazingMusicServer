package CoreFunctionTester;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validEmailTester {

	public static void main(String[] args) {
		valid("icho@ucsc.edu", true);
		valid("ciy405x@kaist.ac.kr", true);
		valid("me@gmail.com", true);
		valid("me@company.co.uk", true);
	
		valid("inyoung", false);
		valid("ciy/$sdfl@gmlia.se.e", false);
		valid("inyoung..1997@gmail.com", false);
		valid("inyoung.@gmail.com", false);
	}

	private static void valid(String email, boolean validity) {
		
		/**
		 * Purpose: check if the validity checker works well.
		 * 			Compare the actual validity and the result of validity checker
		 */
		
		if(isValidEmail(email) != validity) {
			System.out.println("reject");
		} else {
			System.out.println("accept");
		}
	}
	
	private static boolean isValidEmail(String email) {
		
		/**
		 * Purpose: check the validity of email String.
		 * Exact the same code in User.java
		 */
		
		String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // complex regular expression. no need to pay attention.
		Pattern pattern = Pattern.compile(email_pattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
