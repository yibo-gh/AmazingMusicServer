package Util;

import java.util.Hashtable;

public class ReturnCodeChecker {

	static Hashtable<Integer, String> h;
	
	public static boolean setReturnCodeLib () {
		h = new Hashtable<Integer, String>();
		
		h.put(0x2000, "SQL Initialization: SQL object cannot be initailed");
		h.put(0x2001, "SQL Initialization: MySQL driver not found");
		h.put(0x2002, "SQL Initialization: Unknow error, cannot connect to the server");
		h.put(0x2003, "SQL Connection: Invalid connection");
		h.put(0x2004, "SQL Connection: Table not exist");
		h.put(0x2100, "SQL Read: Unkown Error");
		h.put(0x2101, "SQL Read: Result not found");
		h.put(0x2102, "SQL Read: Last element not found");
		h.put(0x2201, "SQL Update: SQL update failed");
		h.put(0x2200, "SQL Update: Hard SQL error.");
		h.put(0x2301, "SQL Table Initailization: Create base error");
		h.put(0x2302, "SQL Table Initailization: Create user info table error");
		h.put(0x2303, "SQL Table Initailization: Create file info table error");
		h.put(0x2304, "SQL Table Initailization: Change base collection to UTF-8 error");
		h.put(0x2305, "SQL Table Initailization: Create waiting file table error");
		h.put(0x3000, "General Core Error: Illegal head node");
		h.put(0x3101, "User Register Error: User exist");
		h.put(0x3102, "User Register Error: Illegal domain");
		h.put(0x3103, "User Register Error: No usable domain code");
		h.put(0x3104, "User Register Error: Illegal Email");
		h.put(0x3105, "User Register Error: Check user status code");
		h.put(0x3201, "User Login Error: Illegal username or passcode");
		h.put(0x3202, "User Login Error: User not exist");
		
		
		h.put(0x2F01, "SQL Update: Success");
		h.put(0x2F02, "SQL Table Initailization: Success");
		h.put(0x2F03, "User Register: Success");
		h.put(0x2F04, "User Login: Success");
		
		return true;
	}
	
	public static String getCodeType (int e) {
		setReturnCodeLib();
		String str = h.get(e);
		if (str == null) return "Unknown Error";
		else return str;
	}
}
