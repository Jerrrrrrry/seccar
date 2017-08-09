package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hongchen.kis.domain.pojo.DepartmentDto;

public class DepartmentDao{


    public List<DepartmentDto> list() throws Exception  {

        List<DepartmentDto> result = new ArrayList<DepartmentDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT a.FItemID ");
        sql.append("       ,a.FNumber ");
        sql.append("       ,a.FName ");
        sql.append("       ,a.F_102 ");
        sql.append("   FROM t_Department a ");
        sql.append("  WHERE a.F_102 is not null ");

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
                DepartmentDto vo = new DepartmentDto();
                vo.setId(rs.getInt("FItemID"));
                vo.setNumber(rs.getString("FNumber"));
                vo.setName(rs.getString("FName"));
                vo.setTargetNumber(rs.getString("F_102"));

                result.add(vo);
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
