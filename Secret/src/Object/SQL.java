package Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
	
	private String u, p;
	
	public SQL(){
		this.u = "amaMusic";
		this.p = "loveCS115.";
	}
	
	Object connect() {
		Connection conn;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://yg-home.site:3306/?verifyServerCertificate=true&useSSL=true&useUnicode=true&characterEncoding=UTF-8";
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,this.u,this.p);
			if(!conn.isClosed()) {
				return conn;
			}
		}catch(ClassNotFoundException e){
			return 0x2001;
		}catch(SQLException e){
			return 0x2002;
		}
		
		return 0x2000;
	}
	
}