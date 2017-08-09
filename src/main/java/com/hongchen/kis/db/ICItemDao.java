package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.hongchen.kis.domain.pojo.MaterialDto;

public class ICItemDao {


    public Map<String, MaterialDto> list() throws Exception {

        Map<String, MaterialDto> result = new HashMap<String, MaterialDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.FItemID ");
        sql.append("       ,a.FNumber ");
        sql.append("       ,a.FName ");
        sql.append("       ,a.FSaleUnitID ");
        sql.append("       ,b.F_111 as FTargetNumber ");
        sql.append("   from t_ICItem a ");
        sql.append("   inner join t_ICItemCustom b on b.FItemID = a.FItemID ");
        sql.append("   where b.F_111 is not null ");

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
                MaterialDto vo = new MaterialDto();
                vo.setId(rs.getInt("FItemID"));
                vo.setNumber(rs.getString("FNumber"));
                vo.setName(rs.getString("FName"));
                vo.setUnitID(rs.getInt("FSaleUnitID"));
                vo.setTargetNumber(rs.getString("FTargetNumber"));

                String[] cds = vo.getTargetNumber().split(",");

                for (int i = 0; i < cds.length; i ++){
                    if (!result.containsKey(cds[i])){
                        result.put(cds[i], vo);
                    }
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
