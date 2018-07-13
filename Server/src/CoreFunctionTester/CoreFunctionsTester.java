package CoreFunctionTester;

import java.sql.ResultSet;
import java.sql.SQLException;

import Object.LinkedList;
import Object.User;
import SQLpackage.Database;

public class CoreFunctionsTester {
	
	private static final String dbName = "AmazingMusicDB";
	private static Database db = null;
	private static ResultSet rs = null;
	
	public static String register (LinkedList ll) {
		
		Object u = ll.head.getInfo();
		if (u.getClass().equals(new User().getClass()))
			return "INVALIDREGINFO";
		User user = (User) u;
		
		try {
			db = new Database(dbName);
			db.connectDB();
			/*
			 * Whether user has already registered
			 */
			rs = db.readDB("select pw from userInfo where emailUserName='" + user.getName() + "'");
			if (!rs.next()) 
				return "REGISTERED";
			/*
			 * Assign UID
			 */
			rs.close();
			rs = db.readDB("select count(*) as count from userInfo");
			rs.next();
			int uid = rs.getInt("count");// Note: need to be modified
			
			String result = db.updateDB("insert into userInfo (emailUserName, emailDomain, uid, pw) "
								+ "values ('" + user.getName() + "', '" + user.getDomain() + "', '" + uid + "', '" + user.getUserPW() + "')");
			if (result == "UPS") {
				return "UPS";
			}
			else {
				return "INSERTFAIL";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {}
			db.closeDB();
		}
		
		return "UNREACHABLE";
	}
	
	public static String login (LinkedList ll) {
		
		/**
		 * 1. LinkedList length of 1. Node = User obj
		 * 2. 
		 */
		
		Object user = ll.head.getInfo();
		if (user.getClass().equals(new User().getClass()))
			return "INVALIDREGINFO";
		
		return "DONE";
	}
	
	public static Object upload (LinkedList ll) {
		
		return "DONE";
	}
}
