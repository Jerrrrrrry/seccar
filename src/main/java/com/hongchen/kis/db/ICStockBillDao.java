package com.hongchen.kis.db;


import java.util.ArrayList;
import java.util.List;

import com.hongchen.kis.domain.pojo.ICStockBill;

public class ICStockBillDao {


    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void create(ICStockBill vo) throws Exception{

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ICStockBill ( ");
        sql.append("  FBrNo ");
        sql.append(" ,FInterID ");
        sql.append(" ,FTranType ");
        sql.append(" ,FDate ");
        sql.append(" ,FBillNo ");
        sql.append(" ,FDeptID ");
        sql.append(" ,FEmpID ");
        sql.append(" ,FSupplyID ");
        sql.append(" ,FFManagerID ");
        sql.append(" ,FSManagerID ");
        sql.append(" ,FBillerID ");
        sql.append(" ,FVchInterID ");
        sql.append(" ,FSaleStyle ");
        sql.append(" ,FRelateBrID ");
        sql.append(" ,FExplanation ");
        sql.append(" ,FBrID ");
        sql.append(" ,FPoOrdBillNo ");
        sql.append(" ,FSettleDate ");
        sql.append(" ,FManageType ");
        sql.append(" ,FConsignee ");
        sql.append(" ,FWlCompany ");
        sql.append(" ,FUUID ");
        sql.append(" )VALUES(");
        sql.append(DbSqlHelper.getSqlPlaceholder(21));
        sql.append(" ,NEWID())");

        List args = new ArrayList();
        args.add(vo.getBrNo());
        args.add(vo.getInterID());
        args.add(vo.getTranType());
        args.add(vo.getDate());
        args.add(vo.getBillNo());
        args.add(vo.getDeptID());
        args.add(vo.getEmpID());
        args.add(vo.getSupplyID());
        args.add(vo.getfManagerID());
        args.add(vo.getsManagerID());
        args.add(vo.getBillerID());
        args.add(vo.getVchInterID());
        args.add(vo.getSaleStyle());
        args.add(vo.getRelateBrID());
        args.add(vo.getExplanation());
        args.add(vo.getBrID());
        args.add(vo.getPoOrdBillNo());
        args.add(vo.getSettleDate());
        args.add(vo.getManageType());
        args.add(vo.getConsignee());
        args.add(vo.getWlCompany());

        try {
            DbSqlHelper.executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
