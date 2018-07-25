package Object;

import Util.MD5Class;

public class FileInfo implements java.io.Serializable {
   
   private static final long serialVersionUID = -3155511566917792809L;
   
   private String uid;
   private String oriName;		// original file name
   private String md5;			// MD5 hash value
   private String fileSerial;	// New name that the server attaches to the file
   private String ext;			// Extension of a file (.txt, .docx, .wav, etc)
   
   public FileInfo() {} 		// Used to check whether an object is fileInfo type
   
   public FileInfo(String uid, String oriName, String md5, String ext) {
      this.uid = uid;
      this.oriName = oriName;
      this.md5 = md5;
      this.fileSerial = MD5Class.MD5Generator(uid+oriName+md5);
      this.ext = ext;
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
   
   public String getExension() {
      return this.ext;
   }
}