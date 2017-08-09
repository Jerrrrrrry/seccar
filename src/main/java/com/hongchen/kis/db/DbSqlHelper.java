package com.hongchen.kis.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hongchen.kis.domain.pojo.DepartmentDto;
import com.hongchen.kis.domain.pojo.MaterialDto;
import com.hongchen.kis.domain.pojo.StockDto;


public class DbSqlHelper {
	
	public static Map<String, String> DBConfig = new ConcurrentHashMap<String, String>();
	public static Map<String, Integer> accountsMap = new ConcurrentHashMap<String, Integer>();
	public static List<DepartmentDto> departmentList = new ArrayList<DepartmentDto>();
	public static Map<Integer, Integer> itemDetailMap = new ConcurrentHashMap<Integer, Integer>();
	public static Map<String, DepartmentDto> departmentMap = new ConcurrentHashMap<String, DepartmentDto>();
	public static Map<String, MaterialDto> materialMap = new ConcurrentHashMap<String, MaterialDto>();     // Key: 中天物料编码  Value: KIS物料DTO
	public static Map<String, StockDto> stockMap = new ConcurrentHashMap<String, StockDto>();           // Key: KIS仓库编码  Value: KIS仓库DTO
	public static ResultSet executeQuery(PreparedStatement ps) throws SQLException{
		return executeQuery(ps, null);
	}
	@SuppressWarnings("rawtypes")
	public static ResultSet executeQuery(PreparedStatement ps, List args) throws SQLException{

		ResultSet rs = null;
		setParameter(ps, args);
		rs = ps.executeQuery();
		return rs;
	}
	
	public static int executeQueryResultInt(String sql) throws SQLException{
		return executeQueryResultInt(sql, null);
	}
	@SuppressWarnings("rawtypes")
	public static int executeQueryResultInt(String sql, List args) throws SQLException{

		int result = 0;
		Connection conn = DbConnectionFactory.createKisConnection();
		if (conn == null){
			return result;
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql.toString());
			setParameter(ps, args);
			rs = ps.executeQuery();
			if (rs == null){
				return result;
			}
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
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
	
	public static String executeQueryResultString(String sql) throws SQLException{
		return executeQueryResultString(sql, null);
	}
	
	@SuppressWarnings("rawtypes")
	public static String executeQueryResultString(String sql, List args) throws SQLException{

		String result = "";
		Connection conn = DbConnectionFactory.createKisConnection();
		if (conn == null){
			return result;
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql.toString());
			setParameter(ps, args);
			rs = ps.executeQuery();
			if (rs == null){
				return result;
			}
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
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
	
	public static void executeNonQuery(String sql ) throws SQLException{
		executeNonQuery(sql, null );
	}
	@SuppressWarnings("rawtypes")
	public static void executeNonQuery(String sql, List args) throws SQLException{
		
		Connection conn = DbConnectionFactory.createKisConnection();
		if (conn == null){
			return;
		}
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql.toString());
			setParameter(ps, args);
			ps.execute();
		} catch (SQLException e) {
			throw e;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void executeNonQueryWithBatch(Connection conn, PreparedStatement ps) throws SQLException{
		executeNonQueryWithBatch(conn, ps, null);
	}
	@SuppressWarnings("rawtypes")
	public static void executeNonQueryWithBatch(Connection conn, PreparedStatement ps, List args) throws SQLException{
		setParameter(ps, args);
		ps.addBatch();
	}
	@SuppressWarnings("rawtypes")
	private static void setParameter(PreparedStatement ps, List args) throws SQLException{
		
		if (ps == null || args == null){
			return;
		}
		
		for(int i = 1; i < args.size() + 1; i ++){

            Object val = args.get(i - 1);

            if (val instanceof Date){
                ps.setTimestamp(i, new Timestamp(((Date)val).getTime()));

            }else{
                ps.setObject(i, val);
            }
		}
	}
	public static String listToCommaString(String[] list){

        if (list == null || list.length == 0){
            return "''";
        }

        String result = "";
        for (int i = 0; i < list.length; i ++){
            result += ",'" + list[i] + "' ";
        }

        result = result.substring(1,result.length());
        return result;
    }
	 public static String intListToCommaString(List<Integer> list){

	        if (list == null || list.size() == 0){
	            return "";
	        }

	        String result = "";
	        for (int i = 0; i < list.size(); i ++){
	            result += "," + list.get(i) + " ";
	        }

	        result = result.substring(1,result.length());

	        return result;
	    }
	// SQL占位符
	    public static String getSqlPlaceholder(int count){
	        String result = "";
	        for (int i = 0; i < count; i ++){
	            result += ",?";
	        }

	        result = result.substring(1,result.length());
	        return result;
	    }
}
