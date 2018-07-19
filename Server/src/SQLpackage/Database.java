package SQLpackage;

import java.sql.Connection;
import java.sql.SQLException;
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
	
	public String initDB() {
		
		/**
		 * Purpose: Initialize DB tables (userinfo, waitingfile, and postFile)
		 * Input Requirement: nothing
		 * Output: nothing
		 */
		
		String result;
		String query;
		
		if (!isValidConnection()) {
			return "INVALIDCONNECTION";
		}
		
		query = "CREATE DATABASE IF NOT EXISTS `amazingmusicdb`";
		result = updateDB(query);
		if (result != "UPS")
			return "USEFAIL";
		
/*		query = "USE `amazingmusicdb`;";
		result = updateDB(query);
		if (result != "UPS")
			return "USEFAIL";*/
				
		query = "CREATE TABLE IF NOT EXISTS `amazingmusicdb`.`userinfo` (\n" + 
				"  `emailUsername` CHAR(255) NOT NULL,\n" + 
				"  `emailDomain` CHAR(255) NOT NULL,\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `pw` CHAR(32) NOT NULL,\n" + 
				"  PRIMARY KEY (`uid`),\n" + 
				"  UNIQUE INDEX `uid_UNIQUE` (`uid` ASC));";
		result = updateDB(query);
		//System.out.println(result);
		/*if (result != "UPS")
			return "USEFAIL";*/
		
		query = "CREATE TABLE IF NOT EXISTS `amazingmusicdb`.`waitingFile` (\n" + 
				"  `MD5` CHAR(32) NOT NULL,\n" + 
				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `oriName` CHAR(255) NOT NULL,\n" + 
				"  UNIQUE INDEX `MD5_UNIQUE` (`MD5` ASC),\n" + 
				"  PRIMARY KEY (`MD5`));";
		result = updateDB(query);
		//System.out.println(result);
		/*if (result != "UPS")
			return "USEFAIL";*/
		
		query = "CREATE TABLE IF NOT EXISTS `amazingmusicdb`.`postFile` (\n" + 
				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `oriName` CHAR(255) NOT NULL,\n" + 
				"  UNIQUE INDEX `fileSerial_UNIQUE` (`fileSerial` ASC),\n" + 
				"  PRIMARY KEY (`fileSerial`));";
		result = updateDB(query);
		//System.out.println(result);
		/*if (result != "UPS")
			return "USEFAIL";*/
	
		return "INITSUCCEED";
	}
	
	public ResultSet readDB(String query) {
		
		/**
		 * Purpose: read database with input query(=instruction or order)
		 * Input requirement: None
		 * Output: ResultSet object (similar with an excel table)
		 */
		
		if (!isValidConnection()) {
			return null;
		}
		
		try {
			
			/*
			 * Statement object plays a role of a freight truck 
			 * to carry requests(or query) between the connection(role of highway)
			 */
			ResultSet rs = this.conn.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateDB(String query) {
		
		/**
		 * Purpose: update database with input query(=instruction or order)
		 * Input requirement: None
		 * Output: update status
		 */
		
		if (!isValidConnection())
			return "INVALIDCONNECTION";
		
		try {
			if (this.conn.createStatement().executeUpdate(query) != 0) 
				return "UPS"; // update succeed
			else 
				return "UPDATEFAIL";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "UPDATEFAIL";
	}
}