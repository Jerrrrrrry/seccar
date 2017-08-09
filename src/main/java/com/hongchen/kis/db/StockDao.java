package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.hongchen.kis.domain.pojo.StockDto;

public class StockDao {



    public Map<String, StockDto> list() throws Exception {

        Map<String, StockDto> result = new HashMap<String, StockDto> ();
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.FItemID ");
        sql.append("       ,a.FNumber ");
        sql.append("       ,a.FName ");
        sql.append("   from t_Stock a ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
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
                StockDto vo = new StockDto();
                vo.setId(rs.getInt("FItemID"));
                vo.setNumber(rs.getString("FNumber"));
                vo.setName(rs.getString("FName"));

                if (!result.containsKey(vo.getNumber())){
                    result.put(vo.getNumber(), vo);
                }
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
            }
        }

        return result;
    }
}
