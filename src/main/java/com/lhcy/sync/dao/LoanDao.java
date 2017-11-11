package com.lhcy.sync.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.ConvertUtils;
import com.lhcy.core.util.StringUtils;
import com.lhcy.core.util.TreeListUtils;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.dto.SummaryLoanDto;
import com.lhcy.sync.domain.dto.SummaryTradeDto;
import com.lhcy.sync.domain.pojo.Loan;
import com.lhcy.sync.web.form.LoanForm;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDao {
    private Logger logger = Logger.getLogger(LoanDao.class);

    /***********************************************/
    // 列表的总数量
    /***********************************************/
    public int count(LoanForm form, String accessType) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) as cnt ");
        sql.append("   from SecCarLoan a ");
        sql.append("  WHERE 1=1  ");
//        sql.append("  WHERE 1=1 and isdeleted !='1' and settlement !='1' ");

        List args = new ArrayList();
        if (!"管理员".equalsIgnoreCase(accessType))
        {
        	sql.append(" AND (a.traderid =?) ");
            args.add(form.getTraderid());
        }
        sql.append(getWhere(form, args));

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result = rs.getInt(1);
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
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /***********************************************/
    // 列表
    /***********************************************/
    public List<LoanDto> list(int rowBegin, int rowEnd, LoanForm form, String accessType) throws Exception{

        List args = new ArrayList();
        StringBuilder pre = new StringBuilder();
        if (!"管理员".equalsIgnoreCase(accessType))
        {
        	pre.append(" AND (a.traderid =?) ");
            args.add(form.getTraderid());
        }
        pre.append(getWhere(form, args));
        String where = pre.toString();

        String order = "desc";
        if (form.getOrder() != null && form.getOrder().length() > 0){
            order = form.getOrder();
        }

        String sort = "purchasedate";
        if (form.getSort() != null && form.getSort().length() > 0){
            sort = form.getSort();
        }


        ArrayList<LoanDto> result = new ArrayList<LoanDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH temp AS ( ");
        sql.append(" SELECT top 100 percent" ); 
        sql.append("     a.* ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowLoan'");
        sql.append("   FROM SecCarLoan a ");
        sql.append("  WHERE 1=1  ");
//        sql.append("  WHERE 1=1 and isdeleted !='1' and settlement !='1' ");
        sql.append(where);
        sql.append(" ) ");
        sql.append(" SELECT * FROM temp ");
        sql.append(" WHERE RowLoan BETWEEN " + rowBegin + " AND " + rowEnd + " ");
        System.out.println("Loan list SQL: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                LoanDto vo = new LoanDto();
                vo.setVehicleid(rs.getString("vehicleid"));
                vo.setVIN(rs.getString("VIN"));
                vo.setLicenseno(rs.getString("licenseno"));
                vo.setVehicledesc(rs.getString("vehicledesc"));
                vo.setOwnerid(rs.getString("ownerid"));
                vo.setOwnername(rs.getString("ownername"));
                vo.setOwnerdesc(rs.getString("ownerdesc"));
                vo.setBorrowdate(rs.getString("borrowdate"));
                vo.setReturndate(rs.getString("returndate"));
                vo.setPeriodmonths(rs.getDouble("periodmonths"));
                vo.setTraderid(rs.getString("traderid"));
                vo.setTradername(rs.getString("tradername"));
                vo.setBorrowamount(rs.getDouble("borrowamount"));
                vo.setInterestrate(rs.getDouble("interestrate"));
                vo.setInterest(rs.getDouble("interest"));
                vo.setInterestpaid(rs.getDouble("interestpaid"));
                vo.setTotalinterest(rs.getDouble("totalinterest"));
                vo.setMidinterestrate(rs.getDouble("midinterestrate"));
                vo.setMidinterest(rs.getDouble("midinterest"));
                vo.setParkingfee(rs.getDouble("parkingfee"));
                vo.setOtherfee(rs.getDouble("otherfee"));
                vo.setComments(rs.getString("comments"));
                vo.setActualloan(rs.getDouble("actualloan"));
                vo.setActualreturn(rs.getDouble("actualreturn"));
                if(rs.getDate("actualreturndate")!=null){
                	vo.setActualreturndate(rs.getDate("actualreturndate").toString());
                }else{
                	vo.setActualreturndate(null);
                }
                	
                vo.setOthercost(rs.getDouble("othercost"));
                vo.setVehicletype(rs.getString("vehicletype"));
                vo.setSettlement(rs.getString("settlement"));
                vo.setSettlementdate(rs.getString("settlementdate"));
                vo.setTotalprofit(rs.getDouble("totalprofit"));
                vo.setPicturepath(rs.getString("picturepath"));
                vo.setIsdeleted(rs.getString("isdeleted"));
                vo.setIsreturned(rs.getString("isreturned"));
                vo.setIsabandon(rs.getString("isabandon"));
                vo.setComments2(rs.getString("comments2"));
                vo.setInterestpaidto(rs.getString("interestpaidto"));
                vo.setNextpaymentdate(rs.getString("nextpaymentdate"));
                vo.setInterestcost(rs.getDouble("interestcost"));
                vo.setActualmonths(rs.getDouble("actualmonths"));
                vo.setMobileno(rs.getString("mobileno"));
                vo.setEarnest(rs.getDouble("earnest"));
                result.add(vo);
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
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /***********************************************/
    // 查询一个
    /***********************************************/
    public LoanDto query(String id) throws Exception{

        LoanDto result = new LoanDto();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT a.* "); 
//        sql.append(" SELECT (select top 1 spareloan from SpareLoan) as spareloan ");
//        sql.append("    ,a.vehicleid ");
//        sql.append("    ,a.licenseno ");
//        sql.append("    ,a.vehicledesc ");
//        sql.append("    ,a.Loanrid ");
//        sql.append("    ,a.Loanrname ");
//        sql.append("    ,a.purchaseprice ");
//        sql.append("    ,a.purchasedate ");
//        sql.append("    ,a.ownerid ");
//        sql.append("    ,a.ownername ");
//        sql.append("    ,a.ownerdesc ");
//        sql.append("    ,a.interestrate ");
//        sql.append("    ,a.interest ");
//        sql.append("    ,a.actualloan ");
//        sql.append("    ,a.earnest ");
//        sql.append("    ,a.sellprice ");
//        sql.append("    ,a.selldate ");
//        sql.append("    ,a.pricediff ");
//        sql.append("    ,a.Loancost ");
//        sql.append("    ,a.profit ");
//        sql.append("    ,a.vehicletype ");
//        sql.append("    ,a.settlement ");
//        sql.append("    ,a.settlementdate ");
//        sql.append("    ,a.totalprofit ");
//        sql.append("    ,a.Loanrprofit ");
//        sql.append("    ,a.picturepath ");
//        sql.append("    ,a.isdeleted ");
//        sql.append("    ,a.issold ");
//        sql.append("    ,a.comments ");
//        sql.append("    ,a.createdts ");
//        sql.append("    ,a.lastupdatedts ");
//        sql.append("    ,a.interestcost ");
//        sql.append("    ,a.buyerid ");
//        sql.append("    ,a.buyername ");
        sql.append("   FROM SecCarLoan a ");
        sql.append("  WHERE a.vehicleid = ? ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        List args = new ArrayList();
        args.add(id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
            	result.setVehicleid(rs.getString("vehicleid"));
                result.setVIN(rs.getString("VIN"));
                result.setLicenseno(rs.getString("licenseno"));
                result.setVehicledesc(rs.getString("vehicledesc"));
                result.setOwnerid(rs.getString("ownerid"));
                result.setOwnername(rs.getString("ownername"));
                result.setOwnerdesc(rs.getString("ownerdesc"));
                result.setMobileno(rs.getString("mobileno"));
                result.setBorrowdate(rs.getString("borrowdate"));
                result.setReturndate(rs.getString("returndate"));
                result.setPeriodmonths(rs.getDouble("periodmonths"));
                result.setTraderid(rs.getString("traderid"));
                result.setTradername(rs.getString("tradername"));
                result.setBorrowamount(rs.getDouble("borrowamount"));
                result.setInterestrate(rs.getDouble("interestrate"));
                result.setInterest(rs.getDouble("interest"));
                result.setInterestpaid(rs.getDouble("interestpaid"));
                result.setTotalinterest(rs.getDouble("totalinterest"));
                result.setActualmonths(rs.getDouble("actualmonths"));
                result.setEarnest(rs.getDouble("earnest"));
                result.setMidinterestrate(rs.getDouble("midinterestrate"));
                result.setMidinterest(rs.getDouble("midinterest"));
                result.setParkingfee(rs.getDouble("parkingfee"));
                result.setOtherfee(rs.getDouble("otherfee"));
                result.setComments(rs.getString("comments"));
                result.setActualloan(rs.getDouble("actualloan"));
                result.setActualreturn(rs.getDouble("actualreturn"));
                result.setActualreturndate(rs.getString("actualreturndate"));
                result.setOthercost(rs.getDouble("othercost"));
                result.setVehicletype(rs.getString("vehicletype"));
                result.setSettlement(rs.getString("settlement"));
                result.setSettlementdate(rs.getString("settlementdate"));
                result.setTotalprofit(rs.getDouble("totalprofit"));
                result.setPicturepath(rs.getString("picturepath"));
                result.setIsdeleted(rs.getString("isdeleted"));
                result.setIsreturned(rs.getString("isreturned"));
                result.setIsabandon(rs.getString("isabandon"));
                result.setComments2(rs.getString("comments2"));
                result.setInterestpaidto(rs.getString("interestpaidto"));
                result.setNextpaymentdate(rs.getString("nextpaymentdate"));
                result.setInterestcost(rs.getDouble("interestcost"));
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
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    /***********************************************/
    // 查询车贷的车辆库存车辆和已抵押完毕的车辆
    /***********************************************/
    public CarSummaryDto getSummaryForCarLoan() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select case when isreturned =1 then 1 when isabandon=1 then 2 else 0  end as isreturned,COUNT(*) as num from SecCarLoan where isdeleted<>'1' group by isreturned,isabandon ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return result;
            }
            int out =0;
            int in = 0;
            while(rs.next()){
                if ("0".equalsIgnoreCase(rs.getString("isreturned")))//抵押完毕的车辆
           		{
                	in =rs.getInt("num");
                } else
                {
                	out = out + rs.getInt("num");
                }
            }

        	result.setInStockCarsAmount(in);
        	result.setOutStockCarsAmount(out);//库存车辆数量

        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    } 
    
    

    /***********************************************/
    // 押车利息已出库
    /***********************************************/
    public CarSummaryDto getSummaryLoanOutInterest() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
//        sql.append(" declare @td  datetime; ");
//        sql.append(" select @td = GETDATE(); ");
        sql.append(" select sum(1.5/100/30*borrowamount*DATEDIFF(DAY,borrowdate,returndate)) as totalinterest from SecCarLoan where isdeleted<>'1' and (isabandon=1 or isreturned=1) ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return result;
            }
            double loaninterestout =0;
//            int totalprofitthird = 0;
            while(rs.next()){
            	loaninterestout = rs.getDouble("totalinterest");
//                if ("第三方".equalsIgnoreCase(rs.getString("vehicletype")))//抵押完毕的车辆
//           		{
//                	totalprofitthird =rs.getInt("totalprofit");
//                } else
//                {
//                	totalprofitself = rs.getInt("totalprofit");
//                }
            }

        	result.setLoaninterestout(loaninterestout);

        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    

    /***********************************************/
    // 押车利息在库
    /***********************************************/
    public CarSummaryDto getSummaryLoanInInterest() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" declare @td  datetime; ");
        sql.append(" select @td = GETDATE(); ");
        sql.append(" select sum(1.5/100/30*borrowamount*DATEDIFF(DAY,borrowdate,@td)) as totalinterest from SecCarLoan where isdeleted<>'1' and isabandon !=1 and isreturned!=1 ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return result;
            }
            double loaninterestin =0;
//            int totalprofitthird = 0;
            while(rs.next()){
            	loaninterestin = rs.getDouble("totalinterest");
//                if ("第三方".equalsIgnoreCase(rs.getString("vehicletype")))//抵押完毕的车辆
//           		{
//                	totalprofitthird =rs.getInt("totalprofit");
//                } else
//                {
//                	totalprofitself = rs.getInt("totalprofit");
//                }
            }

        	result.setLoaninterestin(loaninterestin);

        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    /***********************************************/
    // 查询二手车交易汇总
    /***********************************************/
    public List<SummaryLoanDto> listLoan() throws Exception{
    	List<SummaryLoanDto> result = new ArrayList<SummaryLoanDto>();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select CASE WHEN isreturned=1 THEN '已归还' ELSE '未归还' END AS isreturned,   ");
        sql.append(" CASE WHEN isabandon=1 THEN '已弃车' END AS isabandon,  CASE  WHEN settlement = '1' THEN '已结算' ELSE '未结算' END AS settlement,  ");
        sql.append(" SUM(borrowamount) AS borrowamount,SUM(interestpaid) AS interestpaid, SUM(totalinterest) AS totalinterest,SUM(parkingfee) AS parkingfee, SUM(otherfee) AS othercost, ");
        sql.append(" SUM(midinterest) AS midinterest, SUM(actualloan) AS actualloan,SUM(actualreturn) AS actualreturn,SUM(totalprofit) AS totalprofit   from SecCarLoan where isdeleted<>'1'  ");
        sql.append(" GROUP BY isreturned,ISABANDON,settlement ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
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
            	SummaryLoanDto vo = new SummaryLoanDto();
            	vo.setIsreturned(rs.getString("isreturned"));
            	vo.setIsabandon(rs.getString("isabandon"));
            	vo.setSettlement(rs.getString("settlement"));
            	vo.setBorrowamount(rs.getString("borrowamount"));
            	vo.setParkingfee(rs.getString("parkingfee"));
            	vo.setOthercost(rs.getString("othercost"));
            	vo.setInterestpaid(rs.getString("interestpaid"));
            	vo.setMidinterest(rs.getString("midinterest"));
            	vo.setActualreturn(rs.getString("actualreturn"));
            	vo.setTotalprofit(rs.getString("totalprofit"));
            	vo.setTotalinterest(rs.getString("totalinterest"));
            	vo.setActualloan(rs.getString("actualloan"));
//                if ("1".equalsIgnoreCase(rs.getString("issold")))//卖出去的车的数量
//           		{
//                	result.setOutStockCarsAmount(rs.getInt("num"));
//                } else
//                {
//                	//库存车辆数量
//                	result.setInStockCarsAmount(rs.getInt("num"));
//                }
            	result.add(vo);
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
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    public SummaryLoanDto listLoanReport() throws Exception{
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select SUM(borrowamount) AS borrowamount,SUM(interestpaid) AS interestpaid, SUM(totalinterest) AS totalinterest,SUM(parkingfee) AS parkingfee,SUM(midinterest) AS midinterest, SUM(midinterestrate) AS midinterestrate,SUM(actualloan) AS actualloan,SUM(actualreturn) AS actualreturn,SUM(totalprofit) AS totalprofit   from SecCarLoan where isdeleted<>'1' ");
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return null;
            }
            SummaryLoanDto vo = new SummaryLoanDto();
            while(rs.next()){
            	vo.setBorrowamount(rs.getString("borrowamount"));//借款金额合计
            	vo.setActualloan(rs.getString("actualloan"));//实际打款金额合计
            	vo.setInterestpaid(rs.getString("interestpaid"));//已付利息合计
            	vo.setMidinterest(rs.getString("midinterest"));//中介返点合计
            	vo.setMidinterestrate(rs.getString("midinterestrate"));//已还本金差合计
            	double b = rs.getDouble("actualreturn") - rs.getDouble("midinterestrate");
            	vo.setActualreturn(toTwoDigits(b));//已还本金合计
//                if ("1".equalsIgnoreCase(rs.getString("issold")))//卖出去的车的数量
//           		{
//                	result.setOutStockCarsAmount(rs.getInt("num"));
//                } else
//                {
//                	//库存车辆数量
//                	result.setInStockCarsAmount(rs.getInt("num"));
//                }
            	return vo;
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
                logger.error(e.getMessage());
            }
        }

        return null;
    }
    private String toTwoDigits(double d){
		DecimalFormat    df   = new DecimalFormat("#################0.00");   
		return df.format(d);
	}
//    /***********************************************/
//    // 查询余量
//    /***********************************************/
//    public double getspare() throws Exception{
//
//    	double result = 0.00;
//        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT  spareloan ");
//        sql.append("   FROM SpareLoan ");
//        sql.append("  WHERE 1 = 1 ");
//        System.out.println("query sql: "+sql);
//        Connection conn = DbConnectionFactory.createHonchenConnection();
//        if (conn == null){
//            throw new Exception("无法获取数据库连接！");
//        }
//
//        List args = new ArrayList();
////        args.add(id);
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            ps = conn.prepareStatement(sql.toString());
//            rs = DbSqlHelper.executeQuery(ps);
//
//            if (rs == null){
//                return result;
//            }
//            rs.next();
//            result = rs.getDouble("spareloan");
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }finally{
//            try {
//                rs.close();
//                ps.close();
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error(e.getMessage());
//            }
//        }
//
//        return result;
//    }
    
    /***********************************************/
    // 创建一个
    /***********************************************/
    public void create(Loan vo) throws Exception{

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO SecCarLoan ( ");
        sql.append("     vehicleid ");
        sql.append("    ,licenseno ");
        sql.append("    ,vehicledesc ");
        sql.append("    ,ownerid ");
        sql.append("    ,ownername ");
        sql.append("    ,borrowdate ");
        sql.append("    ,returndate ");
        sql.append("    ,periodmonths ");
        sql.append("    ,traderid ");
        sql.append("    ,tradername ");        
        sql.append("    ,borrowamount ");
        sql.append("    ,interestrate ");
        sql.append("    ,interestpaid ");
        sql.append("    ,interestpaidto ");
        sql.append("    ,nextpaymentdate ");
        sql.append("    ,midinterestrate ");    
        sql.append("    ,midinterest ");
        sql.append("    ,parkingfee ");
        sql.append("    ,otherfee ");
        sql.append("    ,comments ");
        sql.append("    ,actualloan ");
        sql.append("    ,actualreturn ");
//        sql.append("    ,actualreturndate ");
        sql.append("    ,vehicletype "); 
        sql.append("    ,settlement ");
        sql.append("    ,isdeleted ");
        sql.append("    ,isreturned ");
        sql.append("    ,isabandon ");
        sql.append("    ,picturepath ");
        sql.append("    ,mobileno ");
        sql.append("    ,totalinterest ");
        sql.append("    ,earnest ");
        sql.append("    ,actualmonths ");
        sql.append("    ,interestcost ");
        sql.append("    ,createdts ");
        sql.append("    ,lastupdatedts ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(35));
        sql.append(" )");

        
        List args = new ArrayList();
        args.add(vo.getVehicleid());
        args.add(vo.getLicenseno());
        args.add(vo.getVehicledesc());
        args.add(vo.getOwnerid());
        args.add(vo.getOwnername());
        args.add(vo.getBorrowdate());
        args.add(vo.getReturndate());
        args.add(vo.getPeriodmonths());
        args.add(vo.getTraderid());
        args.add(vo.getTradername());
        args.add(vo.getBorrowamount());
        args.add(vo.getInterestrate());
        args.add(vo.getInterestpaid());
        args.add(vo.getInterestpaidto());
        args.add(vo.getNextpaymentdate());
        args.add(vo.getMidinterestrate());
        args.add(vo.getMidinterest());
        args.add(vo.getParkingfee());
        args.add(vo.getOtherfee());
        args.add(vo.getComments());
        args.add(vo.getActualloan());
        args.add(vo.getActualreturn());
//        args.add(vo.getActualreturndate());
        args.add("贷车");
        args.add("0");
        args.add("0");
        args.add("0");
        args.add("0");
        args.add(vo.getPicturepath());
        args.add(vo.getMobileno());
        args.add(vo.getTotalinterest());
        args.add(vo.getEarnest());
        args.add(vo.getActualmonths());
        args.add(vo.getInterestcost());
        args.add(new Date());
        args.add(new Date());

        try {
            executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 更新一个
    /***********************************************/
    public void update(Loan vo) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE SecCarLoan SET ");
        sql.append("   licenseno = ? ");
        sql.append("   ,vehicledesc = ? ");
        sql.append("   ,ownerid = ? ");
        sql.append("   ,ownername = ? ");
        sql.append("   ,borrowdate = ? ");
        sql.append("   ,returndate = ? ");
        sql.append("   ,periodmonths = ? ");
        sql.append("   ,traderid = ? ");
        sql.append("   ,tradername = ? ");
        sql.append("   ,borrowamount = ? ");
        sql.append("   ,interestrate = ? ");
        sql.append("   ,interestpaid = ? ");
        sql.append("   ,interestpaidto = ? ");
        sql.append("   ,nextpaymentdate = ? ");
        sql.append("   ,midinterestrate = ? ");
        sql.append("   ,midinterest = ? ");
        sql.append("   ,parkingfee = ? ");
        sql.append("   ,otherfee = ? ");
        sql.append("   ,comments = ? ");
        sql.append("   ,actualloan = ? ");
        sql.append("   ,actualreturn = ? ");
        sql.append("   ,actualreturndate = ? ");
        sql.append("   ,vehicletype = ? ");
        sql.append("   ,totalprofit = ? ");
        sql.append("   ,settlement = ? ");
        sql.append("   ,isreturned = ? ");
        sql.append("   ,isabandon = ? ");
        sql.append("   ,picturepath = ? ");
        sql.append("   ,mobileno = ? ");
        sql.append("   ,totalinterest = ? ");
        sql.append("   ,earnest = ? ");
        sql.append("   ,actualmonths = ? ");
        sql.append("   ,interestcost = ? ");
        sql.append("   ,lastupdatedts = ? ");
        sql.append(" WHERE vehicleid = ? ");

        List args = new ArrayList();
        args.add(vo.getLicenseno());
        args.add(vo.getVehicledesc());
        args.add(vo.getOwnerid());
        args.add(vo.getOwnername());
        args.add(vo.getBorrowdate());
        args.add(vo.getReturndate());
        args.add(vo.getPeriodmonths());
        args.add(vo.getTraderid());
        args.add(vo.getTradername());
        args.add(vo.getBorrowamount());
        args.add(vo.getInterestrate());
        args.add(vo.getInterestpaid());
        args.add(vo.getInterestpaidto());
        args.add(vo.getNextpaymentdate());
        args.add(vo.getMidinterestrate());
        args.add(vo.getMidinterest());
        args.add(vo.getParkingfee());
        args.add(vo.getOtherfee());
        args.add(vo.getComments());
        args.add(vo.getActualloan());
        args.add(vo.getActualreturn());
        args.add(vo.getActualreturndate());
        args.add(vo.getVehicletype());
        args.add(vo.getTotalprofit());
        args.add(vo.getSettlement());
        args.add(vo.getIsreturned());
        args.add(vo.getIsabandon());
        args.add(vo.getPicturepath());
        args.add(vo.getMobileno());
        args.add(vo.getTotalinterest());
        args.add(vo.getEarnest());
        args.add(vo.getActualmonths());
        args.add(vo.getInterestcost());
        args.add(new Date());
        args.add(vo.getVehicleid());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
//    /***********************************************/
//    // 结算一个
//    /***********************************************/
//    public void settle(Loan vo) throws Exception{
//
//        StringBuilder sql = new StringBuilder();
//        sql.append(" UPDATE SecCarLoan SET ");
////        sql.append("   isdeleted = ? ");
////        sql.append("   ,issold = ? ");
////        sql.append("   ,Loanrid = ? ");
//////        sql.append("   ,vehicleid = ? ");
////        sql.append("   ,licenseno = ? ");
////        sql.append("   ,vehicledesc = ? ");
////        sql.append("   ,Loanrname = ? ");
////        sql.append("   ,purchaseprice = ? ");
////        sql.append("   ,ownername = ? ");
////        sql.append("   ,ownerid = ? ");
////        sql.append("   ,purchasedate = ? ");
////        sql.append("   ,interestrate = ? ");
////        sql.append("   ,actualloan = ? ");
////        sql.append("   ,spareloan = ? ");
////        sql.append("   ,vehicletype = ? ");
////        sql.append("   ,comments = ? ");
////        sql.append("   ,earnest = ? ");
////        sql.append("   ,Loancost = ? ");
////        sql.append("   ,sellprice = ? ");
////        sql.append("   ,selldate = ? ");
//        sql.append("   settlement = ? ");
//        sql.append("   ,settlementdate = ? ");
//        sql.append("   ,lastupdatedts = ? ");
////        sql.append("   ,interest = ? ");
////        sql.append("   ,interestcost = ? ");
////        sql.append("   ,pricediff = ? ");
////        sql.append("   ,totalprofit = ? ");
////        sql.append("   ,profit = ? ");
////        sql.append("   ,Loanrprofit = ? ");
//        sql.append(" WHERE vehicleid = ? ");
//
//        List args = new ArrayList();
////        args.add(vo.getIsdeleted());
////        args.add(vo.getIssold());
////        args.add(vo.getLoanrid());
////        args.add(vo.getLicenseno());
////        args.add(vo.getVehicledesc());
////        args.add(vo.getLoanrname());
////        args.add(vo.getPurchaseprice());
////        args.add(vo.getOwnername());
////        args.add(vo.getOwnerid());
////        args.add(vo.getPurchasedate());
////        args.add(vo.getInterestrate());
////        args.add(vo.getActualloan());
////        args.add(vo.getSpareloan());
////        args.add(vo.getVehicletype());
////        args.add(vo.getComments());
////        args.add(vo.getEarnest());
////        args.add(vo.getLoancost());
////        args.add(vo.getSellprice());
////        args.add(vo.getSelldate());
//        args.add(vo.getSettlement());
//        args.add(vo.getSettlementdate());
//        args.add(new Date());
////        args.add(vo.getInterest());
////        args.add(vo.getInterestcost());
////        args.add(vo.getPricediff());
////        args.add(vo.getTotalprofit());
////        args.add(vo.getProfit());
////        args.add(vo.getLoanrprofit());
//        args.add(vo.getVehicleid());
//
//        try {
//            executeNonQuery(sql.toString(), args);
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
//    
//    /***********************************************/
//    // 更新余量
//    /***********************************************/
//    public void updateSpare(double sloan) throws Exception{
//
//    	StringBuilder sql = new StringBuilder();
//
//      
//    	sql.append(" UPDATE SpareLoan SET ");
//    	sql.append("   spareloan = ? ");
//    	Connection conn = DbConnectionFactory.createHonchenConnection();
//    	if (conn == null){
//    		return;
//    	}
//
//    	PreparedStatement ps = null;
//    	String pk = null;
//    	try {
//          conn.setAutoCommit(false);
//          ps = conn.prepareStatement(sql.toString());
//
//              List args = new ArrayList();
//              args.add(sloan);
//              DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
//	          ps.executeBatch();
//	          conn.commit();
//      }catch (Exception e){
//
//          try{
//              conn.rollback();
//          }catch (Exception e1){
//              e1.printStackTrace();
//              logger.error(e1.getMessage());
//          }
//
//          throw new Exception(e);
//
//      }finally{
//          try {
//              ps.close();
//              conn.close();
//          } catch (Exception e) {
//              e.printStackTrace();
//              logger.error(e.getMessage());
//          }
//      }
////
////        StringBuilder sql = new StringBuilder();
////
////        
////        sql.append(" UPDATE SpareLoan SET ");
////        sql.append("   spareloan = ? ");
////
////        List args = new ArrayList();
////        args.add(sloan);
////
////        try {
////            executeNonQuery(sql.toString(), args);
////
////        } catch (Exception e) {
////            throw new Exception(e);
////        }
//    }
//////
//////    /***********************************************/
//////    // 移动至分类多个
//////    /***********************************************/
//////    public void move(String[] list, String tree, String user) throws Exception{
//////
//////        if (tree == null || tree.length() == 0){
//////            return;
//////        }
//////
//////        if (list == null || list.length == 0){
//////            return;
//////        }
//////
//////        StringBuilder sql = new StringBuilder();
//////        sql.append(" UPDATE SecCarLoan SET ");
//////        sql.append("     FTreeID = ? ");
//////        sql.append("    ,FLastUpdateUserID = ? ");
//////        sql.append("    ,FLastUpdateTime = ? ");
//////        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
//////
//////        List args = new ArrayList();
//////        args.add(tree);
//////        args.add(user);
//////        args.add(new Date());
//////
//////        try {
//////            executeNonQuery(sql.toString(), args);
//////
//////        } catch (Exception e) {
//////            throw new Exception(e);
//////        }
//////    }
////
    /***********************************************/
    // 删除一个
    /***********************************************/
    public void deletesingle(String vehicleid) throws Exception{


        StringBuilder sql = new StringBuilder();
//        sql.append(" DELETE FROM SecCarLoan ");
        sql.append("UPDATE SecCarLoan SET ISDELETED=1 ");
        sql.append(" WHERE vehicleid = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        String pk = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());
//            LoanDto td = new LoanDto();
//            td = this.query(vehicleid);
//            if(td.getSettlement().equals("1") || td.getIsdeleted().equals("1")){
//            	throw new Exception("车辆：" + td.getLicenseno() + SysConstant.M_SETTLEMENT_ERROR);
//            }
            List args = new ArrayList();
            args.add(vehicleid);
            pk = vehicleid;
            DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {

            LoanDto dto = new LoanDto();
            try {
                dto = this.query(pk);
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            if (dto.getLicenseno() == null || dto.getLicenseno().length() == 0) {
                throw new Exception(e);
            } else {
                throw new Exception(e.getMessage() + "车辆：" + dto.getLicenseno() + "<br/>");
            }
        }catch (Exception e){

            try{
                conn.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            throw new Exception(e);

        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
//    /***********************************************/
//    // 删除多个
//    /***********************************************/
//    public void delete(String[] list) throws Exception{
//
//        if (list == null || list.length == 0){
//            return;
//        }
//
//        StringBuilder sql = new StringBuilder();
////        sql.append(" DELETE FROM SecCarLoan ");
//        sql.append("UPDATE SecCarLoan SET ISDELETED=1 ");
//        sql.append(" WHERE vehicleid = ? ");
//
//        Connection conn = DbConnectionFactory.createHonchenConnection();
//        if (conn == null){
//            return;
//        }
//
//        PreparedStatement ps = null;
//        String pk = null;
//        try {
//            conn.setAutoCommit(false);
//            ps = conn.prepareStatement(sql.toString());
//
//            for(String vehicleid : list){
//
//                LoanDto td = new LoanDto();
//                td = this.query(vehicleid);
//                if(td.getSettlement().equals("1") || td.getIsdeleted().equals("1")){
//                	throw new Exception("车辆：" + td.getLicenseno() + SysConstant.M_SETTLEMENT_ERROR);
//                }
//                List args = new ArrayList();
//                args.add(vehicleid);
//                pk = vehicleid;
//                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
//            }
//            ps.executeBatch();
//            conn.commit();
//
//        } catch (SQLException e) {
//
//            LoanDto dto = new LoanDto();
//            try {
//                dto = this.query(pk);
//                conn.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                logger.error(e1.getMessage());
//            }
//
//            if (dto.getLicenseno() == null || dto.getLicenseno().length() == 0) {
//                throw new Exception(e);
//            } else {
//                throw new Exception(e.getMessage() + "车辆：" + dto.getLicenseno() + "<br/>");
//            }
//        }catch (Exception e){
//
//            try{
//                conn.rollback();
//            }catch (Exception e1){
//                e1.printStackTrace();
//                logger.error(e1.getMessage());
//            }
//
//            throw new Exception(e);
//
//        }finally{
//            try {
//                ps.close();
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error(e.getMessage());
//            }
//        }
//    }
////
////    /***********************************************/
////    // 启用或禁用多个
////    /***********************************************/
////    public void enable(String[] list, int status, String user) throws Exception{
////
////        if (list == null || list.length == 0){
////            return;
////        }
////
////        StringBuilder sql = new StringBuilder();
////        sql.append(" UPDATE SecCarLoan SET ");
////        sql.append("      FEnable = ? ");
////        sql.append("     ,FLastUpdateUserID = ? ");
////        sql.append("     ,FLastUpdateTime = ? ");
////        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
////
////        List args = new ArrayList();
////        args.add(status);
////        args.add(user);
////        args.add(new Date());
////
////        try {
////            executeNonQuery(sql.toString(), args);
////
////        } catch (Exception e) {
////            throw new Exception(e);
////        }
////    }
////
    /***********************************************/
    // 获得相同编号的数据
    /***********************************************/
    public LoanDto getEquals(String licenseno) throws Exception{

        LoanDto result = new LoanDto();
        if (licenseno == null || licenseno.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("   	vehicleid ");
        sql.append("   ,licenseno ");
//        sql.append("   ,ownerid ");
//        sql.append("   ,Loanrid ");
        sql.append(" FROM SecCarLoan ");
        sql.append("WHERE licenseno = ?  and isdeleted != '1' and settlement !='1' ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        List args = new ArrayList();
        args.add(licenseno);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result.setVehicleid(rs.getString("vehicleid"));
                result.setLicenseno(rs.getString("licenseno"));
//                result.setOwnerid(rs.getString("ownerid"));
//                result.setLoanrid(rs.getString("Loanrid"));
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
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    @SuppressWarnings("rawtypes")
   	public static void executeNonQuery(String sql, List args) throws SQLException{
   		
   		Connection conn = DbConnectionFactory.createHonchenConnection();
   		if (conn == null){
   			return;
   		}
   		PreparedStatement ps = null;
   		
   		try {
   			ps = conn.prepareStatement(sql.toString());
   			setParameter(ps, args);
   			ps.execute();
   		} catch (SQLException e) {
   			throw e;
   		}finally{
   			try {
   				ps.close();
   				conn.close();
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		}
   		
   	}
    @SuppressWarnings("rawtypes")
   	private static void setParameter(PreparedStatement ps, List args) throws SQLException{
   		
   		if (ps == null || args == null){
   			return;
   		}
   		
   		for(int i = 1; i < args.size() + 1; i ++){

               Object val = args.get(i - 1);

               if (val instanceof Date){
                   ps.setTimestamp(i, new Timestamp(((Date)val).getTime()));

               }else{
                   ps.setObject(i, val);
               }
   		}
   	}
    /***********************************************/
    // 列表的过滤条件
    /***********************************************/
    private String getWhere(LoanForm form, List args) throws Exception{

        StringBuilder result = new StringBuilder();
        if (form == null){
            return "";
        }


        if (form.getFilterField() != null && form.getFilterField().length() > 0 ){

            if ("nm+cd".equals(form.getFilterField()) && form.getFilterValue() != null && form.getFilterValue().length() > 0){
                result.append(" AND (a.customer like ? OR a.licenseno like ? ) ");
                args.add("%" + form.getFilterValue().trim() + "%");
                args.add("%" + form.getFilterValue().trim() + "%");

            }else{
            	if(form.getFilterisdeleted() != null && form.getFilterisdeleted().length() > 0){
	                result.append(" AND (a.isdeleted like ? ) ");
	                args.add("%" + form.getFilterisdeleted().trim() + "%");
	            	}
            	if(form.getFilterisabandon() != null && form.getFilterisabandon().length() > 0){
                    result.append(" AND (a.isabandon like ? ) ");
                    args.add("%" + form.getFilterisabandon().trim() + "%");
                	}
            	if(form.getFilterisreturned() != null && form.getFilterisreturned().length() > 0){
                    result.append(" AND (a.isreturned like ? ) ");
                    args.add("%" + form.getFilterisreturned().trim() + "%");
                	}
            	if(form.getFiltersettlement() != null && form.getFiltersettlement().length() > 0){
                    result.append(" AND (a.settlement like ? ) ");
                    args.add("%" + form.getFiltersettlement().trim() + "%");
                	}
            	if(form.getFilterlicenseno() != null && form.getFilterlicenseno().length() > 0){
                    result.append(" AND (a.licenseno like ? ) ");
                    args.add("%" + form.getFilterlicenseno().trim() + "%");
                	}
            	if(form.getFiltercardescription() != null && form.getFiltercardescription().length() > 0){
                    result.append(" AND (a.vehicledesc like ? ) ");
                    args.add("%" + form.getFiltercardescription().trim() + "%");
                	}
            	if(form.getFiltercustomer() != null && form.getFiltercustomer().length() > 0){
                    result.append(" AND (a.ownername like ? ) ");
                    args.add("%" + form.getFiltercustomer().trim() + "%");
                	}
            	if(form.getLoanstart() != null && form.getLoanstart().length() > 0){
                    result.append(" AND (a.borrowdate >= ? ) ");
                    args.add(form.getLoanstart().trim());
                	}
            	if(form.getLoanend() != null && form.getLoanend().length() > 0){
                    result.append(" AND (a.borrowdate <= ? ) ");
                    args.add(form.getLoanend().trim());
                	}
            	if(form.getReturnstart() != null && form.getReturnstart().length() > 0){
                    result.append(" AND (a.returndate >= ? ) ");
                    args.add(form.getReturnstart().trim());
                	}
            	if(form.getReturnend() != null && form.getReturnend().length() > 0){
                    result.append(" AND (a.returndate <= ? ) ");
                    args.add(form.getReturnend().trim());
                	}
                			 	
            }
        }else{
        	result.append(" AND (a.isdeleted !='1' ) ");
//          args.add("%" + form.getFilterisdeleted().trim() + "%");
      }

//        if (form.getEnable() == 1){
//            result.append(" AND a.FEnable = 1 ");
//        }

        return result.toString();
    }
}
