package com.hongchen.kis.db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AccountDao {
	 public Map<String, String> list() throws Exception  {
		 Map<String, String> map = new HashMap<String, String>();
		 StringBuilder sql = new StringBuilder();
		 sql.append(" select a.FNumber ");
	        sql.append("       ,a.FShortNumber ");
	        sql.append("   from t_bd_Account a ");
	        sql.append("  where a.FEnable = 1 ");

	        Connection conn = DbConnectionFactory.createHonchenConnection();
	        if (conn == null){
	            throw new Exception("数据库连接失败！");
	        }

	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            ps = conn.prepareStatement(sql.toString());
	            rs = DbSqlHelper.executeQuery(ps);
	          
	            while(rs.next()){
	            	map.put(rs.getString("FNumber"), rs.getString("FShortNumber"));
	            }
	        } catch (Exception e) {
	            throw e;
	        }finally{
	            try {
	                rs.close();
	                ps.close();
	                conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return map;
    }
}
