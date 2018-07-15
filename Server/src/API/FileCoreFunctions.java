package API;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import SQLpackage.Database;


public class FileCoreFunctions {
private static final String dbName = "AmazingMusicDB";
	
	public static String upload (LinkedList ll) {
		
		Object u = ll.head.getInfo();
		
		FileInfo flInfo = (FileInfo) u;
		
		System.out.println("asdf");
		
		System.out.println(flInfo.getUID());
		System.out.println(flInfo.getOriName());
		System.out.println(flInfo.getMD5());
		
		return "OK";
	}
	
	public static String download (LinkedList ll) {
		
		Object u = ll.head.getInfo();
		
		FileInfo flInfo = (FileInfo) u;
		
		System.out.println(flInfo.getUID());
		System.out.println(flInfo.getOriName());
		System.out.println(flInfo.getMD5());
		
		return "downloaded";
	}
	

}
