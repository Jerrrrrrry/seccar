package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class SystemProfileDao {

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public String getProfileValue(String category, String key) throws Exception{
        String result = "";

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.FValue ");
        sql.append("   from t_SystemProfile t ");
        sql.append("  where t.FCategory = ? ");
        sql.append("    and t.FKEY = ? ");

        List args = new ArrayList();
        args.add(category);
        args.add(key);

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;

        try{
            result = DbSqlHelper.executeQueryResultString(sql.toString(), args);

        }catch (Exception e){
            throw e;
        }

        return result;
    }

}
