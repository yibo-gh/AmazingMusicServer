package Object;

import API.MD5Class;

public class FileInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -3155511566917792809L;
	
	private String uid;
	private String oriName; // original file name
	private String md5;	
	private String fileSerial;
	
	public FileInfo() {} // 
	
	public FileInfo(String uid, String oriName, String md5) {
		this.uid = uid;
		this.oriName = oriName;
		this.md5 = md5;
		this.fileSerial = MD5Class.MD5Generator(uid+oriName+md5);
	}
	
	public String getUID() { 
		return this.uid;
	}
	
	public String getOriName() {
		return this.oriName;
	}
	
	public String getMD5() {
		return this.md5;
	}
	
	public String getFileSerial() {
		return this.fileSerial;
	}
}