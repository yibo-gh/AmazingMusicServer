package Object;

public class FileInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -3155511566917792809L;
	private String uid;
	private String oriName; // original file name
	
	public FileInfo() {}
	
	public FileInfo(String uid, String oriName) {
		this.uid = uid;
		this.oriName = oriName;
	}
	
	public String getUID() { 
		return this.uid;
	}
	
	public String getOriName() {
		return this.oriName;
	}
}