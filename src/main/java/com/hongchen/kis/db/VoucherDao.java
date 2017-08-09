package com.hongchen.kis.db;


import java.util.ArrayList;
import java.util.List;

import com.hongchen.kis.domain.pojo.Voucher;

public class VoucherDao {


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void create(Voucher vo) throws Exception {

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO t_Voucher ( ");
        sql.append("     FBrNo ");
        sql.append("    ,FVoucherID ");
        sql.append("    ,FDate ");
        sql.append("    ,FYear ");
        sql.append("    ,FPeriod ");
        sql.append("    ,FGroupID ");
        sql.append("    ,FNumber ");
        sql.append("    ,FExplanation ");
        sql.append("    ,FAttachments ");
        sql.append("    ,FEntryCount ");
        sql.append("    ,FDebitTotal ");
        sql.append("    ,FCreditTotal ");
        sql.append("    ,FChecked ");
        sql.append("    ,FPosted ");
        sql.append("    ,FPreparerID ");
        sql.append("    ,FCheckerID ");
        sql.append("    ,FPosterID ");
        sql.append("    ,FCashierID ");
        sql.append("    ,FOwnerGroupID ");
        sql.append("    ,FSerialNum ");
        sql.append("    ,FTranType ");
        sql.append("    ,FTransDate ");
        sql.append("    ,FFrameWorkID ");
        sql.append("    ,FApproveID ");
        sql.append("    ,FFootNote ");
        sql.append("    ,UUID ");
        sql.append(" )VALUES(");
        sql.append(DbSqlHelper.getSqlPlaceholder(25));
        sql.append(" ,NEWID())");

        List args = new ArrayList();
        args.add(vo.getBrNo());
        args.add(vo.getVoucherID());
        args.add(vo.getDate());
        args.add(vo.getYear());
        args.add(vo.getPeriod());
        args.add(vo.getGroupID());
        args.add(vo.getNumber());
        args.add(vo.getExplanation());
        args.add(vo.getAttachments());
        args.add(vo.getEntryCount());
        args.add(vo.getDebitTotal());
        args.add(vo.getCreditTotal());
        args.add(vo.getChecked());
        args.add(vo.getPosted());
        args.add(vo.getPreparerID());
        args.add(vo.getCheckerID());
        args.add(vo.getPosterID());
        args.add(vo.getCashierID());
        args.add(vo.getOwnerGroupID());
        args.add(vo.getSerialNum());
        args.add(vo.getTranType());
        args.add(vo.getTransDate());
        args.add(vo.getFrameWorkID());
        args.add(vo.getApproveID());
        args.add(vo.getFootNote());

        try {
            DbSqlHelper.executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getNextNumber(int grpId, int year, int period) throws Exception {
        int result = 0;

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ISNULL(Max(t.FNumber), 0) + 1 as FNumber ");
        sql.append("   FROM t_Voucher t ");
        sql.append("  WHERE t.FGroupID = ? ");
        sql.append("    AND t.FYear = ? ");
        sql.append("    AND t.FPeriod = ? ");

        List args = new ArrayList();
        args.add(grpId);
        args.add(year);
        args.add(period);

        try {
            result = DbSqlHelper.executeQueryResultInt(sql.toString(), args);
        }catch (Exception e){
            throw e;
        }

        return result;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getNextSerialNum(int year) throws Exception {
        int result = 0;

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ISNULL(MAX(FSerialNum),0) + 1 as FSerialNum FROM ( ");
        sql.append("      select * from t_Voucher  ");
        sql.append("      union all  ");
        sql.append("      select * from t_VoucherBlankout ");
        sql.append("      union all  ");
        sql.append("      select * from t_VoucherAdjust) v ");
        sql.append("   Where FYear = ? ");

        List args = new ArrayList();
        args.add(year);

        try {
            result = DbSqlHelper.executeQueryResultInt(sql.toString(), args);
        }catch (Exception e){
            throw new Exception(e);
        }

        return result;
    }

    public void updateEntryCount() throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t ");
        sql.append("    SET t.FEntryCount = ( ");
        sql.append("       SELECT count(1) ");
        sql.append("         FROM t_VoucherEntry v ");
        sql.append("        WHERE v.FVoucherID = t.FVoucherID ");
        sql.append("    ) ");
        sql.append("  FROM t_Voucher t ");
        sql.append(" WHERE t.FEntryCount = 0 ");

        try {
            DbSqlHelper.executeNonQuery(sql.toString());

        } catch (Exception e) {
            throw e;
        }

    }
}
