package Object;

import Object.SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	
	Connection c;
	SQL s;

	public Conn () {
		Object o;
		this.s = new SQL();
		o = this.s.connect();
		if (o.getClass().equals(Integer.class)) {
			this.c = null;
			return;
		} else this.c = (Connection) o;
	}
	
	public Object getFirstCol (String sql) {
		if (!validateConn()) return 0x2003;
		try {
			ResultSet rs = this.c.createStatement().executeQuery(sql);
			if (rs.next()) return rs.getString(1);
		} catch (SQLException e) {
			return 0x2101;
		}
		return 0x2100;
	}
	
	public Object getLastCol (String sql) {
		if (!validateConn()) return 0x2003;
		try {
			ResultSet rs = this.c.createStatement().executeQuery(sql);
			while (!rs.last()) {
				rs.next();
			} return rs.getString(1);
		} catch (SQLException e) {
			return 0x2102;
		}
	}
	
	public Object readData (String sql) {
		if (!validateConn()) return 0x2003;
		try {
			return this.c.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			return 0x2101;
		}
	}
	
	public Object updateData (String sql) {
		if (!validateConn()) return 0x2003;
		try {
			if (this.c.createStatement().executeUpdate(sql) >= 0) return 0x2F01;
			else return 0x2201;
		} catch (SQLException e) {
			return 0x2200;
		}
	}
	
	public Object iniDatabase () {
		if (!validateConn()) return 0x2003;
		Object o;
		String sql;
		
		sql = "CREATE SCHEMA `user`;";
		o = updateData(sql);
		System.out.println(o + " " + sql);
		if (!o.equals(0x2F01)) return 0x2301;
		
		sql = "ALTER SCHEMA `user`  DEFAULT CHARACTER SET utf8 ;";
		o = updateData(sql);
		System.out.println(o + " " + sql);
		if (!o.equals(0x2F01)) return 0x2304;
		
		sql = "CREATE TABLE `user`.`info` (\n" + 
				"	`index` INT NOT NULL AUTO_INCREMENT,\n" +
				"  `emailUser` CHAR(255) NOT NULL,\n" + 
				"  `emailDomain` CHAR(255) NOT NULL,\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `password` CHAR(32) NOT NULL,\n" + 
				"  PRIMARY KEY (`index`));";
		o = updateData(sql);
		System.out.println(o + " " + sql);
		if (!o.equals(0x2F01)) return 0x2302;
		
		sql = "CREATE TABLE `user`.`fileList` (\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
				"  `oriFileName` CHAR(255) NOT NULL,\n" + 
				"  PRIMARY KEY (`fileSerial`),\n" + 
				"  UNIQUE INDEX `fileSerial_UNIQUE` (`fileSerial`));";
		o = updateData(sql);
		System.out.println(o + " " + sql);
		if (!o.equals(0x2F01)) return 0x2303;
		
		sql = "CREATE TABLE `user`.`fileWait` (\n" + 
				"  `MD5` CHAR(32) NOT NULL,\n" + 
				"  `fileSerial` CHAR(32) NOT NULL,\n" + 
				"  `uid` CHAR(10) NOT NULL,\n" + 
				"  `oriName` CHAR(255) NOT NULL,\n" + 
				"  PRIMARY KEY (`MD5`));";
		o = updateData(sql);
		System.out.println(o + " " + sql);
		if (!o.equals(0x2F01)) return 0x2305;
		
		return 0x2F02;
	}
	
	public int countLine(String tableName) throws SQLException {
		if (!validateConn()) return 0x2003;
		String sql = "select count(*) as rowCount from " + tableName;
		if (!tableExist(tableName)) return 0x2004;
		ResultSet rset = c.createStatement().executeQuery(sql);
		rset.next();
		int rtn = rset.getInt("rowCount");
		return rtn;
	}
	
	private boolean validateConn () {
		if (this.c == null) return false;
		return true;
	}
	
	public Connection getConn () {
		return this.c;
	}
	
	public boolean tableExist (String tableName) throws SQLException {
		if (this.c.getMetaData().getTables(null, null, tableName, null).next()) return true;
		else return false;
	}
}
