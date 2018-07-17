package SQLpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

	Connection conn;
	SQL sql;
	
	public Database() {
		this.sql = new SQL();
		this.conn = sql.connect();
	}
	
	private boolean isValidConnection() {
		if (this.conn != null)
			return true;
		else
			return false;
	}
	
//	public String initDB() {
//		
//		String result;
//		String query;
//		
//		if (!isValidConnection()) {
//			return "INVALIDCONNECTION";
//		}
////		
////		query = "use amazingmusicdb;";
////		result = updateDB(query);
////		if (result != "UPS")
////			return "USEFAIL";
////		
////		query = "CREATE TABLE `amazingmusicdb`.`user` (\n" + 
////				"  `emailUsername` CHAR(255) NOT NULL,\n" + 
////				"  `emailDomain` CHAR(255) NOT NULL,\n" + 
////				"  `uid` CHAR(10) NOT NULL,\n" + 
////				"  `pw` CHAR(32) NOT NULL,\n" + 
////				"  PRIMARY KEY (`uid`),\n" + 
////				"  UNIQUE INDEX `uid_UNIQUE` (`uid` ASC));";
////		result = updateDB(query);
////		if (result != "UPS")
////			return "USEFAIL";
////		
////		query = "CREATE TABLE `amazingmusicdb`.`waitingFile` (\n" + 
////				"  `MD5` CHAR(32) NOT NULL,\n" + 
////				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
////				"  `uid` CHAR(10) NOT NULL,\n" + 
////				"  `oriName` CHAR(255) NOT NULL,\n" + 
////				"  UNIQUE INDEX `MD5_UNIQUE` (`MD5` ASC),\n" + 
////				"  PRIMARY KEY (`MD5`));";
////		result = updateDB(query);
////		if (result != "UPS")
////			return "USEFAIL";
//		
//		query = "CREATE TABLE `amazingmusicdb`.`postFile` (\n" + 
//				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
//				"  `uid` CHAR(10) NOT NULL,\n" + 
//				"  `oriName` CHAR(255) NOT NULL,\n" + 
//				"  UNIQUE INDEX `fileSerial_UNIQUE` (`fileSerial` ASC),\n" + 
//				"  PRIMARY KEY (`fileSerial`));";
//		result = updateDB(query);
//		if (result != "UPS")
//			return "USEFAIL";
//	
//		return "INITSUCCEED";
//	}
	
	public ResultSet readDB(String query) {
		
		/**
		 * Purpose:
		 * Input requirement: None
		 * Output: ResultSet
		 */
		
		if (!isValidConnection()) {
			return null;
		}
		
		try {
			ResultSet rs = this.conn.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
/*		if (conn == null || stmt == null)
			return null;
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;*/
	}
	
	public String updateDB(String query) {
		
		if (!isValidConnection())
			return "INVALIDCONNECTION";
		
		try {
			if (this.conn.createStatement().executeUpdate(query) != 0) 
				return "UPS"; // update succeed
			else return "UPDATEFAIL";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "UPDATEFAIL";
/*		if (conn == null || stmt == null)
			return null;
		
		try {
			if (stmt.executeUpdate(query) != 0) return "UPS"; // update succeed
			else return "UPDATEFAIL";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "UPDATEFAIL";*/
	}
}