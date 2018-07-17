package SQLpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

	Connection conn;
	SQL sql;
	
	public Database(String dbName) {
		this.sql = new SQL(dbName);
		this.conn = sql.connect();
	}
	
	private boolean isValidConnection() {
		if (this.conn != null)
			return true;
		else
			return false;
	}
	
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