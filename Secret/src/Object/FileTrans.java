package Object;

public class FileTrans implements java.io.Serializable {
	
	private static final long serialVersionUID = -6725646019642758581L;
	private String u, m, n, e, s;
	
	public FileTrans (String uid, String MD5, String fileName, String ext) {
		this.u = uid;
		this.m = MD5;
		this.n= fileName;
		this.e= ext;
		this.s = getSerial();
	}
	
	private String getSerial () {
		return Util.MD5Util.crypt(this.u + this.n + this.m);
	}
	
	public String getUid() {return this.u;}
	public String getMD5() {return this.m;}
	public String getFileName() {return this.n;}
	public String getExt() {return this.e;}
	public String getSer() {return this.s;}
}
