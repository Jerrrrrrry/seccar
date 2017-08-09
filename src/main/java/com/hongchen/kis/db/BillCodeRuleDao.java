package com.hongchen.kis.db;


import java.util.ArrayList;
import java.util.List;

public class BillCodeRuleDao {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void updateNextProjectVal(int billTypeID) throws  Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE T_BILLCODERULE ");
        sql.append("    SET FProjectVal = CAST(FProjectVal as int) + 1 ");
        sql.append("  WHERE FBillTypeID = ? ");
        sql.append("    AND FPROJECTID = 3 ");

        List args = new ArrayList();
        args.add(billTypeID);

        try{
            DbSqlHelper.executeNonQuery(sql.toString(), args );
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
