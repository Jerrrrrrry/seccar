package com.hongchen.kis.db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class KisAccountDao {

    public Map<String, Integer> list(String[] ids) throws Exception  {
    	Map<String, Integer> map = new HashMap<String, Integer>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select b.FAccountID ");
        sql.append("       ,b.FNumber ");
        sql.append("       ,b.FName ");
        sql.append("   from  t_Account b ");
        sql.append("  where b.FNumber in (").append(DbSqlHelper.listToCommaString(ids)+")");

        Connection conn = DbConnectionFactory.createKisConnection();
        if (conn == null){
            throw new Exception("数据库连接失败！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);
          
            while(rs.next()){
            	map.put(rs.getString("FNumber"), rs.getInt("FAccountID"));
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
    
    public int get(String cd) throws Exception {

        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.FAccountID ");
        sql.append("   from t_account a ");
        sql.append("  where a.FNumber = '" + cd + "' ");

        Connection conn = DbConnectionFactory.createKisConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result = rs.getInt(1);
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

        return result;
    }
}
