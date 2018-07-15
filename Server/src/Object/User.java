package Object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SQLpackage.Database;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1284470853593107085L;
	private String emailUserName, emailDomain, uid, pw;
	private String errorCode = "FINE";
	
	public User() {}
	
	public User(String email, String pw) {
		
		/*
		 * uid is generated in User() constructor.
		 * which means, client machine is resposible for generating uid.
		 * which means, server's load(calculating) is reduced
		 */
		
		if(!isValidEmail(email))
			this.errorCode = "INVALIDEMAIL";		
		String[] parts = email.split("@");
		this.emailUserName = parts[0];
		this.emailDomain = parts[1];
		this.pw = pw;
		
		this.uid = generateUID(email);
	}
	
	private String generateUID(String email) { 
		// need some modification, so that client cannot access the other DB tables, except userInfo table.
		String tmp_domainCode, tmp_nameCode, tmp_uid;
		Database db = null;
		ResultSet rs = null;
		
		tmp_domainCode = domainCode(this.emailDomain);
		tmp_nameCode = nameCode(this.emailUserName);
		tmp_uid = tmp_domainCode+tmp_nameCode;
		
		return tmp_uid;
		/*
		try {
			db = new Database("AmazingMusicDB");
			db.connectDB();
			
			tmp_domainCode = domainCode(this.emailDomain);
			tmp_nameCode = nameCode(this.emailUserName);
			tmp_uid = tmp_domainCode+tmp_nameCode;
			
			rs = db.readDB("select uid from domainCode where uid='" + tmp_uid + "'");
			if (rs.next()) {
				tmp_uid += 1;
			}
			
			String result = db.updateDB("insert into domainCode (emailDomain, code, uid) "
					+ "values ('"+this.emailDomain+"', '"+tmp_domainCode+"', '"+tmp_uid+"')");
			if (result != "UPS") {
				rs.close();
				db.closeDB();
				return "GENUIDERROR";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			tmp_uid = "GENUIDERROR";
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (db != null)
				db.closeDB();
		}
		
		return tmp_uid;
		*/
	}
	
	private String domainCode(String s) {
		if (s.length() < 2)
			return "INVALIDDOMAIN";
		
		String[] sArr = s.split("\\.");
		
		if (sArr.length == 2) {
			String str1, str2;
			
			str1 = sArr[0];
			str2 = sArr[1];
			
			if (str1.length() == 0 || str2.length() == 0)
				return "INVALIDDOMAIN";
			
			str1 = str1 + "xx";
			return str1.substring(0,3) + str2.substring(0,1);
		}
		else if (sArr.length >= 3) {
			String str1, str2, str3;
			
			str1 = sArr[0];
			str2 = sArr[1];
			str3 = sArr[2];
			
			if (str1.length() == 0 || str2.length() == 0 || str3.length() == 0)
				return "INVALIDDOMAIN";
			
			str1 = str1 + "x";
			return str1.substring(0,2) + str2.substring(0,1) + str3.substring(0,1);
		}
		else {
			return "INVALIDDOMAIN";
		}
	}
	
	private String nameCode(String s) {
		String str;
		int lenStr;
		
		if (s.length() == 0) 
			return "INVALIDEMAILNAME";
		
		if (s.length() >= 6)
			return s.substring(0, 6);
		else {
			str = "xxxxx"+s;
			lenStr = str.length();
			return str.substring(lenStr-6,lenStr);
		}
	}
	
	private static boolean isValidEmail(String email) {
		
		/**
		 * Purpose: check the validity of email String.
		 * Exact the same code in User.java
		 */
		
		String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // just a complicated regular expression
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