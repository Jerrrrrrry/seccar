package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hongchen.kis.domain.pojo.DepartmentDto;

// 核算项目横表
public class ItemDetailDao {


    /***********************************************/
    // 根据部门，取得核算项目横表的ID
    // 返回值：核算项目<KIS部门ID,核算项目横表ID>
    /***********************************************/
    public HashMap<Integer, Integer> getDetailByDepartment(List<DepartmentDto> departmentList) throws Exception {

        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        if (departmentList == null || departmentList.size() == 0){
            return result;
        }

        List<Integer> dept = new ArrayList<Integer>();
        for(DepartmentDto dto : departmentList){
            dept.add(dto.getId());
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT a.FDetailID ");
        sql.append("       ,a.F2 ");
        sql.append("   FROM t_ItemDetail a ");
        sql.append("  WHERE a.FDetailCount = 1 ");
        sql.append("    AND a.F2 in (" + DbSqlHelper.intListToCommaString(dept) + ")");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            if (rs == null){
                return result;
            }

            while (rs.next()){

                int detailID = rs.getInt("FDetailID");
                int deptID = rs.getInt("F2");

                if (!result.containsKey(deptID)){
                    result.put(deptID, detailID);
                }
            }

        }catch (Exception e){
            throw new Exception(e);
        }finally {
            try{
                rs.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
