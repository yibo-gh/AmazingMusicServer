package API;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.DigestInputStream;
import java.io.InputStream ;
import java.nio.file.Files;

import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import SQLpackage.Database;


public class FileCoreFunctions {
private static final String dbName = "AmazingMusicDB";
	
	public static LinkedList upload (LinkedList ll) {
		
		Object u = ll.head.getInfo();
		
		FileInfo flInfo = (FileInfo) u;
		
		System.out.println("asdf");
		
		System.out.println(flInfo.getUID());
		System.out.println(flInfo.getOriName());
		System.out.println(flInfo.getMD5());
		
		if(flInfo.getOriName() != "") {
			LinkedList list = new LinkedList();
			list.add("OK");
			list.add(flInfo);
			
			System.out.println(list.getClass());
			System.out.println(list.end.getInfo());
			
			File directory = new File("temporary");
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			return list;
		}
		else {
			ll.add("NO");
			return ll;
		}
	}
	
	public static String download (LinkedList ll) {
		
		Object u = ll.head.getInfo();
		
		FileInfo flInfo = (FileInfo) u;
		
		System.out.println(flInfo.getUID());
		System.out.println(flInfo.getOriName());
		System.out.println(flInfo.getMD5());
		
		return "downloaded";
	}
	
	public static void validate(FileInfo flInfo) {
		String path = "temporary" + File.separatorChar + flInfo.getFileSerial();
		System.out.println(path);
		String md5Value = MD5Class.FileMD5Generator(path);
		System.out.println(md5Value);
		
		Database db = null;
		ResultSet rs = null;
		
		try {
			db = new Database("AmazingMusicDB");
			
			rs = db.readDB("select MD5 from waitingfile where MD5='" + flInfo.getMD5() + "'");
			//System.out.println(rs);
			if(rs.next()) {
				System.out.println("there is not matching MD5 value file");
			}
			else {
				String result = db.updateDB("delete from waitingfile where MD5 = '" + flInfo.getMD5() + "'");
				System.out.println(result);
			}
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	

}