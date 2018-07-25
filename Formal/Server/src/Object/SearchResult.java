package Object;

import java.io.File;

public class SearchResult implements java.io.Serializable {
	
	private static final long serialVersionUID = -4780404484903402849L;
	
	private String url;
	private String oriName;
	
	public SearchResult() {};
	
	public SearchResult(String oriName, String uid, String fileSerial) {
		
		/*
		 * Server gives a series of(linked list) SearchResult objects to client.
		 * The SearchResult object contains file's original name, user id, and file's serial number.
		 * These values are all the information client need to download a file from the server.
		 */
		
		this.oriName = oriName;
		this.url = uid + File.separatorChar + fileSerial + oriName.substring(oriName.lastIndexOf("."));
	}
	
	public String getURL() {
		return this.url;
	}
	
	public String getOriName() {
		return this.oriName;
	}
}
