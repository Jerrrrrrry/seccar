package com.lhcy.platform.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.SysConstant;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KisUserDao {
    private Logger logger = Logger.getLogger(KisUserDao.class);

    public int get(String nm) throws Exception {

        int result = SysConstant.CREATE_USER_ID;
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.FUserID ");
        sql.append("   from t_User a ");
        sql.append("  where a.FName = '" + nm + "' ");

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
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    }
}

