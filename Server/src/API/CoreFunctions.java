package API;

import java.sql.ResultSet;
import java.sql.SQLException;

import Object.FileInfo;
import Object.LinkedList;
import Object.User;
import SQLpackage.Database;

public class CoreFunctions {
	
	public static String register (LinkedList ll) {
		
		/**
		 * Purpose: for joining to be a member
		 * Input Requirement: Linked list which (only) contains User() object. User() is user information. Look at the Object.User class.
		 * Output: Registration status. INVALIDREGINFO, (already) REGISTERED, etc.
		 */
		
		Database db = null;
		ResultSet rs = null;
		
		/*
		 * input should include one and only one User() object.
		 */
		if (ll.getLength() != 1)
			return "REG:INVALIDINPUT";	
		Object u = ll.head.getInfo();
		if (!u.getClass().equals(new User().getClass()))
			return "REG:INVALIDINFO";
		User user = (User) u;
		
		/*
		 * main registration code
		 */
		try {
			db = new Database();
			
			/*
			 * Whether user has already registered. (in the case of using same email)
			 */
			rs = db.readDB("select pw from `amazingmusicdb`.`userInfo` where uid='"+user.getUID()+"'");
			if (rs.next()) {
				return "REG:USEREXISTS";
			}
			
			/*
			 * Insert new user information into userInfo table in DB.
			 * File should be match with proper uid (kind of tag).
			 */
			String result = db.updateDB("insert into `amazingmusicdb`.`userInfo` (emailUserName, emailDomain, uid, pw) "
								+ "values ('" + user.getName() + "', '" + user.getDomain() + "', '" + user.getUID() + "', '" + user.getUserPW() + "')");
			if (result == "UPS") {
				return "UPS";
			}
			else {
				return "REG:INSERTFAIL";
			}
			
		} catch (SQLException e) {
			return "REG:ERROR";
		}
	}
	
	public static String login (LinkedList ll) {
		
		/**
		 * Purpose: Login function.
		 * Input Requirement: Linked list which (only) contains User() object. User() is user information. Look at the Object.User class.
		 * Output: If user successfully logs in, return uid of the user.
		 * 		   Otherwise, return login status. INVALIDREGINFO, NOTREGISTERED, PWINCORRECT, etc.
		 */
		
		Database db = null;
		ResultSet rs = null;
		
		if (ll.getLength() != 1)
			return "LOGIN:INVALIDINPUT";
		Object u = ll.head.getInfo();
		if (!u.getClass().equals(new User().getClass()))
			return "LOGIN:INVALIDINFO";
		User user = (User) u;
		
		/*
		 * Main login code
		 */
		try {
			db = new Database();
			
			/*
			 * The user must already been registered.
			 */
			rs = db.readDB("select pw, uid from `amazingmusicdb`.`userInfo` where emailUserName='"+user.getName()+"'"+" and emailDomain='"+user.getDomain()+"'");
			if (!rs.next()) {
				return "LOGIN:NOTREG";
			}
			
			/*
			 * Check the password.
			 */
			if (!rs.getString("pw").equals(user.getUserPW())) {
				return "LOGIN:PWINCORRECT";
			}
			
			/*
			 * Return UID.
			 */
			String uid = rs.getString("uid");
			return uid;
					
		} catch (SQLException e) {
			return "LOGIN:ERROR";
		}
	}
	
	public static Object upload (LinkedList ll) {
		
		/**
		 * Purpose: GPS(General Purpose Server)-side upload function.
		 * 			Receive a FileInfo() class, and then allow user to upload their file on fileserver.
		 * 			Look at the comment below.
		 * Input Requirement: Linked list which (only) contains FileInfo() object.
		 * Output: Upload status. FILEEXISTS, UPS (upload succeed), etc.
		 */
		
		Database db = null;
		ResultSet rs = null;
		
		Object f = ll.head.getInfo();
		if (!f.getClass().equals(new FileInfo().getClass()))
			return "UPL:INVALIDFILEINFO";
		FileInfo fInfo = (FileInfo) f;
		
		try {
			db = new Database();
			
			rs = db.readDB("select fileSerial from `amazingmusicdb`.`postfile` where fileSerial='" + fInfo.getFileSerial() + "'");
			if (rs.next()) {
				return "UPL:FILEEXISTS";
			}
			
			String result = db.updateDB("insert into `amazingmusicdb`.`waitingfile` (MD5, fileSerial, uid, oriName) "
					+"values ('"+fInfo.getMD5()+"', '"+fInfo.getFileSerial()+"', '"+fInfo.getUID()+"', '"+fInfo.getOriName()+"')");
			if (result == "UPS") {
				return "UPS";
			}
			else {
				return "UPL:INSERTFAIL";
			}

		} catch (SQLException e) {
			return "UPL:ERROR";
		}
	}
}
