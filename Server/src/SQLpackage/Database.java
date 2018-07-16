package SQLpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

	Connection conn = null;
	Statement stmt = null;
	String id = "root";
	String pw = "cofls8680*"; //"dl938271";
	String dbName;
	
	public Database(String dbName) {
		this.dbName = dbName;
	}
	
	public void connectDB() {
		
		/**
		 * Purpose: Load SQL driver and connect to SQL server. 
		 * 			Create a kind of truck or train to convey information between the connection.
		 * 			Other DB methods then can use the truck to deliver a request to SQL server.
		 * 			At the end, closeDB() function MUST be called.
		 * Input requirement: None
		 * Output: None
		 */
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/" + this.dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"; // "https://67.180.244.170:3306"
		
		try {
			/*
			 * In the case of "No driver" error,
			 * You should download SQL driver called "Connector/J"
			 * Link is here: https://dev.mysql.com/downloads/connector/j/
			 * For further information, icho4@ucsc.edu (In Young Cho)
			 */
			
			/*
			 * 1. forName() - SQL-java driver loading for using SQL in Java language
			 * 2. getConnection() - connect to SQL server
			 * 3. createStatement() - this "truck" will deliver requests (or queries)
			 */
			System.out.print("Driver loading... ");
			Class.forName(driver);
			System.out.println("Succeed!");
			
			System.out.print("Connecting DB... ");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("succeed!\n");
			
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("No driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB() {
		
		/**
		 * Purpose:
		 * Input requirement: None
		 * Output: None
		 */
		
		try { 
			if (stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet readDB(String query) {
		
		/**
		 * Purpose:
		 * Input requirement: None
		 * Output: ResultSet
		 */
		
		if (conn == null || stmt == null)
			return null;
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateDB(String query) {
		if (conn == null || stmt == null)
			return null;
		
		try {
			if (stmt.executeUpdate(query) != 0) return "UPS"; // update succeed
			else return "UPDATEFAIL";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "UPDATEFAIL";
	}
}