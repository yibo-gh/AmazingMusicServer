package Object;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1284470853593107085L;
	private String emailUserName, emailDomain, pw;
	
	public User() {}
	
	public User(String name, String domain, String pw) {
		this.emailUserName = name;
		this.emailDomain = domain;
		this.pw = pw;
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
}
