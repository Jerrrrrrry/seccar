package com.hongchen.kis.db;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class InnerDao {


    public int getNextInterID(String bill)throws Exception {

        int result = 0;
        Connection conn = DbConnectionFactory.createKisConnection();
        if (conn == null){
            return result;
        }

        CallableStatement call = null ;
        try {

            call = conn. prepareCall("{call GetICMaxNum(?, ?)}");

            // 返回值
            call.setString(1, bill);
            call.registerOutParameter(2, Types.INTEGER);
            call.execute();

            // 取得返回值
            result = call.getInt(2);

        }catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                call.clearParameters();
                call.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
