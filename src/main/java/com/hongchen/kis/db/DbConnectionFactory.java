package com.hongchen.kis.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnectionFactory {

	public static Connection createHonchenConnection(){
		Connection conn = null;
		try {
			 String db_url=DbSqlHelper.DBConfig.get("db_url"); 
			 String db_instance=DbSqlHelper.DBConfig.get("db_instance");
			 String db_user=DbSqlHelper.DBConfig.get("db_user");
			 String db_password=DbSqlHelper.DBConfig.get("db_password");

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(db_url + db_instance, db_user, db_password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return conn;
	}
}