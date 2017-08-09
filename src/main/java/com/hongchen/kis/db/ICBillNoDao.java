package com.hongchen.kis.db;


import java.util.ArrayList;
import java.util.List;

public class ICBillNoDao {


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getNextNumber(int billTypeID) throws Exception{

        String result = "";
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT REPLACE(FDESC,'+','') AS FNumber ");
        sql.append("   FROM ICBILLNO ");
        sql.append("  WHERE FBILLID = ? ");

        List args = new ArrayList();
        args.add(billTypeID);

        try{
            result = DbSqlHelper.executeQueryResultString(sql.toString(), args);
        }catch (Exception e){
            throw new Exception(e);
        }

        return result;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void updateNextNumber(int billTypeID) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ICBILLNO ");
        sql.append("    SET FCurNo = FCurNo + 1 ");
        sql.append("      , FDesc = FPreLetter + '+' + REPLICATE('0',LEN(FFormat) - len(FCurNo + 2)) + cast((FCurNo + 2) as varchar) ");
        sql.append("  WHERE FBillID = ? ");

        List args = new ArrayList();
        args.add(billTypeID);

        try{
            DbSqlHelper.executeNonQuery(sql.toString(), args);
        }catch (Exception e){
            throw new Exception(e);
        }

    }
}
