package SQLpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

	String id, pw, dbName; 
	
	public SQL() {
		this.id = "amaMusic";
		this.pw = "loveCS115.";
		this.dbName = "amazingmusicdb";
	}
	
	Connection connect() {

		/**
		 * Purpose: Connect to DB server (need to run connect() after manually login into SQL server)
		 * Input Requirement: nothing
		 * Output: nothing
		 */
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://yg-home.site:3306/" + "?verifyServerCertificate=true&useSSL=false"; 
		Connection conn;
		
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
			 */
			System.out.print("Driver loading... ");
			Class.forName(driver);
			System.out.println("Succeed!");
			
			System.out.print("Connecting DB... ");
			conn = DriverManager.getConnection(url, this.id, this.pw);
			if (!conn.isClosed()) {
				System.out.println("succeed!\n");
				return conn;
			}
			else {
				System.out.println("fail!\n");
				return null;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
