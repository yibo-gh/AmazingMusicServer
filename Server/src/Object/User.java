package Object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1284470853593107085L;
	private String emailUserName, emailDomain, uid, pw;
	private String errorCode;
	
	public User() {}
	
	public User(String email, String pw) {
		
		/*
		 * uid is generated in User() constructor.
		 * which means, client machine is resposible for generating uid.
		 * which means, server's load(calculating) is reduced
		 */
		
		if(!isValidEmail(email))
			this.errorCode = "INVALIDEMAIL";
		this.uid = "1"; //generateUID(email);
		
		String[] parts = email.split("@");
		this.emailUserName = parts[0];
		this.emailDomain = parts[1];
		this.pw = pw;
	}
	
	private static boolean isValidEmail(String email) {
		
		/**
		 * Purpose: check the validity of email String.
		 * Exact the same code in User.java
		 */
		
		String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(email_pattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
		
	public String getName() { 
		return this.emailUserName;
	}
	
	public String getDomain() {
		return this.emailDomain;
	}
	
	public String getUserPW() { 
		return this.pw;
	}
	
	public String getUID() {
		return this.uid;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
/*
 * userInfo (emailUserName char 255, emailDomain char 255, uid char 10, pw char 32)
 * waitingFile (MD5 char 32, fileSerial 32, uid 10, oriName char 255)
 * postFile (fileSerial 32, uid 10, oriName 255)
 */

