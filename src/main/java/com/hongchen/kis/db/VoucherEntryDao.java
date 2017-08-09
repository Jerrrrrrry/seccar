package com.hongchen.kis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.lhcy.kis.domain.pojo.VoucherEntry;


public class VoucherEntryDao {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void create(List<VoucherEntry> list) throws Exception {

        if (list == null || list.size() == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO t_VoucherEntry ( ");
        sql.append("    FBrNo ");
        sql.append("   ,FVoucherID ");
        sql.append("   ,FEntryID ");
        sql.append("   ,FExplanation ");
        sql.append("   ,FAccountID ");
        sql.append("   ,FAccountID2 ");
        sql.append("   ,FDetailID ");
        sql.append("   ,FCurrencyID ");
        sql.append("   ,FExchangeRate ");
        sql.append("   ,FDC ");
        sql.append("   ,FAmountFor ");
        sql.append("   ,FAmount ");
        sql.append("   ,FQuantity ");
        sql.append("   ,FMeasureUnitID ");
        sql.append("   ,FUnitPrice ");
        sql.append("   ,FSettleTypeID ");
        sql.append("   ,FCashFlowItem ");
        sql.append("   ,FTaskID ");
        sql.append("   ,FResourceID ");
        sql.append(" )VALUES(" + DbSqlHelper.getSqlPlaceholder(19) + ")");

        Connection conn = DbConnectionFactory.createKisConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());

            for (VoucherEntry vo : list){
                List args = new ArrayList();
                args.add(vo.getBrNo());
                args.add(vo.getVoucherID());
                args.add(vo.getEntryID());
                args.add(vo.getExplanation());
                args.add(vo.getAccountID());
                args.add(vo.getAccountID2());
                args.add(vo.getDetailID());
                args.add(vo.getCurrencyID());
                args.add(vo.getExchangeRate());
                args.add(vo.getDc());
                args.add(vo.getAmountFor());
                args.add(vo.getAmount());
                args.add(vo.getQuantity());
                args.add(vo.getMeasureUnitID());
                args.add(vo.getUnitPrice());
                args.add(vo.getSettleTypeID());
                args.add(vo.getCashFlowItem());
                args.add(vo.getTaskID());
                args.add(vo.getResourceID());

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
