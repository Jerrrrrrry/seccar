package com.hongchen.kis.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.hongchen.kis.domain.pojo.ICStockBillEntry;

public class ICStockBillEntryDao {


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void create(List<ICStockBillEntry> list) throws  Exception{

        if (list == null || list.size() == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ICStockBillEntry ( ");
        sql.append("  FBrNo ");
        sql.append(" ,FInterID ");
        sql.append(" ,FEntryID ");
        sql.append(" ,FItemID ");
        sql.append(" ,FQty ");
        sql.append(" ,FUnitID ");
        sql.append(" ,FAuxQty ");
        sql.append(" ,FDCSPID ");
        sql.append(" ,FConsignPrice ");
        sql.append(" ,FConsignAmount ");
        sql.append(" ,FSNListID ");
        sql.append(" ,FDCStockID ");
        sql.append(" )VALUES( ");
            sql.append(DbSqlHelper.getSqlPlaceholder(12));
        sql.append(" )");

        Connection conn = DbConnectionFactory.createKisConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());

            for (ICStockBillEntry vo : list){
                List args = new ArrayList();
                args.add(vo.getBrNo());
                args.add(vo.getInterID());
                args.add(vo.getEntryID());
                args.add(vo.getItemID());
                args.add(vo.getQty());
                args.add(vo.getUnitID());
                args.add(vo.getAuxQty());
                args.add(vo.getDcSPID());
                args.add(vo.getConsignPrice());
                args.add(vo.getConsignAmount());
                args.add(vo.getSnListID());
                args.add(vo.getDcStockID());

                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            }

            ps.executeBatch();
            conn.commit();

        }catch (Exception e){
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new Exception(e);
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
