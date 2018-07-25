package Object;

import Object.Conn;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = -6474326391329892110L;
	private String e, d, p, i;
	private int c;
	
	public User() {
		
	}
	
	public User (String user, String pass) {
		Object o = validateEmail(user);
		if (o.equals(false)) {
			this.c = 0x3104;
			return;
		}
		String[] s = (String[]) o;
		o = getId(s);
		if (o.getClass().equals(String.class)) {
			this.i = (String) o;
			this.c = 0x2F03;
		}
		else this.c = (int)o;
		this.e = s[0];
		this.d = s[1];
		this.p = pass;
	}
	
	private Object validateEmail (String str) {
		if (str.length() < 3) return false;
		String[] str0 = str.split("\\@");
		if (str0.length != 2) return false;
		String[] str1 = str0[1].split("\\.");
		if (str1.length < 2) return false;
		
		String[] rtn = {str0[0], str0[1]};
		return rtn;
	}
	
	private Object getId (String[] str) {
		Conn c = new Conn();
		String sql;
		Object o;
		sql = "SELECT * FROM user.info WHERE `emailUser` = '" + str[0] + "' AND `emailDomain` = '" + 
				str[1] + "';";
		o = c.getFirstCol(sql);
		if (!o.getClass().equals(Integer.class)) return 0x3101;
		
		sql = "SELECT uid FROM user.info WHERE `emailDomain` = '" + str[1] + "';";
		o = c.getFirstCol(sql);
		String emailDomain = "";
		if (o.getClass().equals(Integer.class)) {
			String[] domain = str[1].split("\\.");
			if (domain.length == 2) {
				if (domain[0].length() == 1) emailDomain = domain[0] + "xx";
				else if (domain[0].length() == 2) emailDomain = domain[0] + "x";
				else if (domain[0].length() >= 3) emailDomain = domain[0].substring(0, 3);
				else return 0x3102;
				emailDomain += domain[1].charAt(0);
			} else if (domain.length == 3) {
				if (domain[0].length() == 1) emailDomain = domain[0] + "x";
				else if (domain[0].length() >= 2) emailDomain = domain[0].substring(0, 2);
				else return 0x3102;
				emailDomain = emailDomain + domain[1].charAt(0) + domain[2].charAt(0);
			}
			emailDomain = emailDomain.toLowerCase();
			
			sql = "SELECT `emailDomain` FROM user.info WHERE `uid` LIKE '" + emailDomain + "%';";
			o = c.getFirstCol(sql);
			while ((o.getClass().equals(String.class) && !o.equals(str[1])) || 
					(o.getClass().equals(Integer.class) && !o.equals(0x2100))) {
				emailDomain = changeCode(emailDomain, 3);
				if (emailDomain == null) return 0x3103;
				sql = "SELECT `emailDomain` FROM user.info WHERE `uid` LIKE '" + emailDomain + "%';";
				o = c.getLastCol(sql);
			}
		} else {
			String s = (String) o;
			emailDomain = "" + s.charAt(0) + s.charAt(1) + s.charAt(2) + s.charAt(3);
		}
		
		String uid = emailDomain;
		if (str[0].length() < 6) {
			for (int j = 0; j < 6 - str[0].length(); j++) uid += "x";
			uid += str[0];
		} else uid += str[0].substring(0, 6);
		uid = uid.toLowerCase();
		
		sql = "SELECT `uid` FROM user.info WHERE `uid` LIKE '" + uid + "%';";
		o = c.getFirstCol(sql);
		while (o.getClass().equals(String.class)) {
			uid = changeCode(uid, 9);
			if (uid == null) return 0x3103;
			sql = "SELECT `uid` FROM user.info WHERE `uid` LIKE '" + uid + "%';";
			o = c.getFirstCol(sql);
		} return uid;
	}
	
	private String changeCode (String str, int i) {
		if (str.charAt(i) == 0x7A) return replaceChar(str, i, (char)0x30);
		else if (str.charAt(i) == 0x39 && i != 0) return advancedChange(str, i);
		else if (str.charAt(i) != 0x39 && str.charAt(i) != 0x7A)
			return replaceChar(str, i, (char)(str.charAt(i) + 1));
		else return null;
	}
	
	private String advancedChange (String str, int i) {
		if (i == 0) return null;
		String s = replaceChar(str, i, (char)0x61);
		return changeCode(s, i - 1);
	}
	
	private String replaceChar(String str, int i, char c) {
		String rtn = "";
		for (int j = 0; j < str.length(); j++) {
			if (j != i) rtn += str.charAt(j);
			else rtn += c;
		} return rtn;
	}
	
	public String getEmailUser() {return this.e;}
	public String getEmailDomain() {return this.d;}
	public String getPasscode() {return this.p;}
	public String getID() {return this.i;}
	public int getStatusCode() {return this.c;}
}
