package DBtester;

import java.sql.ResultSet;
import java.sql.SQLException;

import SQLpackage.Database;

public class DBtester {

	public static void main(String[] args) {
		String dbName = "expriment1";
		String query = "select * from author";
		Database db = new Database(dbName);
		ResultSet rs = null;
		
		try {
			db.connectDB();
			rs = db.readDB(query);
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {}
			db.closeDB();
		}
	}

}
