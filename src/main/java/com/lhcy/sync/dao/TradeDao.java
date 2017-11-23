package com.lhcy.sync.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.StringUtils;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.SummaryTradeDto;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.domain.pojo.Trade;
import com.lhcy.sync.web.form.TradeForm;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeDao {
    private Logger logger = Logger.getLogger(TradeDao.class);

    /***********************************************/
    // 列表的总数量
    /***********************************************/
    public int count(TradeForm form, String accessType) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) as cnt ");
        sql.append("   from SecCarTrade a ");
        sql.append("  WHERE 1=1  ");
//        sql.append("  WHERE 1=1 and isdeleted !='1' ");
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
    public List<TradeDto> list(int rowBegin, int rowEnd, TradeForm form, String accessType) throws Exception{

        List args = new ArrayList();

        String order = "desc";
        if (form.getOrder() != null && form.getOrder().length() > 0){
            order = form.getOrder();
        }

        String sort = "createdts";
        if (form.getSort() != null && form.getSort().length() > 0){
            sort = form.getSort();
        }


        ArrayList<TradeDto> result = new ArrayList<TradeDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH temp AS ( ");
        sql.append(" SELECT top 100 percent" ); 
        sql.append("     a.* ");
//        sql.append("     a.vehicleid ");
//        sql.append("    ,a.licenseno ");
//        sql.append("    ,a.vehicledesc ");
//        sql.append("    ,a.traderid ");
//        sql.append("    ,a.tradername ");
//        sql.append("    ,a.purchaseprice ");
//        sql.append("    ,a.purchasedate ");
//        sql.append("    ,a.ownerid ");
//        sql.append("    ,a.ownername ");
//        sql.append("    ,a.ownerdesc ");
//        sql.append("    ,a.interestrate ");
//        sql.append("    ,a.interest ");
//        sql.append("    ,a.actualloan ");
//        sql.append("    ,a.spareloan ");
//        sql.append("    ,a.earnest ");
//        sql.append("    ,a.sellprice ");
//        sql.append("    ,a.selldate ");
//        sql.append("    ,a.pricediff ");
//        sql.append("    ,a.tradecost ");
//        sql.append("    ,a.profit ");
//        sql.append("    ,a.vehicletype ");
//        sql.append("    ,a.settlement ");
//        sql.append("    ,a.settlementdate ");
//        sql.append("    ,a.totalprofit ");
//        sql.append("    ,a.traderprofit ");
//        sql.append("    ,a.picturepath ");
//        sql.append("    ,a.isdeleted ");
//        sql.append("    ,a.issold ");
//        sql.append("    ,a.comments ");
//        sql.append("    ,a.createdts ");
//        sql.append("    ,a.lastupdatedts ");
//        sql.append("    ,a.interestcost ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowTrade'");
        sql.append("   FROM SecCarTrade a ");
        sql.append("  WHERE 1=1  ");
//        sql.append("  WHERE 1=1 and isdeleted !='1'");
        if (!"管理员".equalsIgnoreCase(accessType))
        {
        	sql.append(" AND (a.traderid =?) ");
            args.add(form.getTraderid());
        }
        sql.append(getWhere(form, args));
        sql.append(" ) ");
        sql.append(" SELECT * FROM temp ");
        sql.append(" WHERE RowTrade BETWEEN " + rowBegin + " AND " + rowEnd + " ");
        System.out.println("list SQL: "+sql);
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
                TradeDto vo = new TradeDto();
                vo.setVehicleid(rs.getString("vehicleid"));
                vo.setVIN(rs.getString("VIN"));
                vo.setLicenseno(rs.getString("licenseno"));
                vo.setVehicledesc(rs.getString("vehicledesc"));
                vo.setTraderid(rs.getString("traderid"));
                vo.setTradername(rs.getString("tradername"));
                vo.setPurchaseprice(rs.getDouble("purchaseprice"));
                vo.setPurchasedate(rs.getString("purchasedate"));
                vo.setOwnerid(rs.getString("ownerid"));
                vo.setOwnername(rs.getString("ownername"));
                vo.setOwnerdesc(rs.getString("ownerdesc"));
                vo.setOwnermobile(rs.getString("ownermobile"));
                vo.setInterestrate(rs.getDouble("interestrate"));
                vo.setInterest(rs.getDouble("interest"));
                vo.setActualloan(rs.getDouble("actualloan"));
                vo.setSpareloan(rs.getDouble("spareloan"));
                vo.setEarnest(rs.getDouble("earnest"));
                vo.setSellprice(rs.getDouble("sellprice"));
                vo.setSelldate(rs.getString("selldate"));
                vo.setPricediff(rs.getDouble("pricediff"));
                vo.setTradecost(rs.getDouble("tradecost"));
                vo.setProfit(rs.getDouble("profit"));
                vo.setVehicletype(rs.getString("vehicletype"));
                vo.setSettlement(rs.getString("settlement"));
                vo.setSelldate(rs.getString("selldate"));
                vo.setTotalprofit(rs.getDouble("totalprofit"));
                vo.setTraderprofit(rs.getDouble("traderprofit"));
                vo.setPicturepath(rs.getString("picturepath"));
                vo.setIsdeleted(rs.getString("isdeleted"));
                vo.setIssold(rs.getString("issold"));
                vo.setComments(rs.getString("comments"));
                vo.setCreatedts(rs.getString("createdts"));
                vo.setLastupdatedts(rs.getString("lastupdatedts"));
                vo.setInterestcost(rs.getDouble("interestcost"));
                vo.setBuyername(rs.getString("buyername"));
                vo.setBuyerid(rs.getString("buyerid"));
                vo.setBuyermobile(rs.getString("buyermobile"));
                vo.calculateLiXiChengBen();
//                vo.setVehicleid(rs.getString("vehicleid"));
//                vo.setLicenseno(rs.getString("licenseno"));
//                vo.setVehicledesc(rs.getString("vehicledesc"));
//                vo.setTradername(rs.getString("tradername"));
//                vo.setPurchaseprice(rs.getDouble("purchaseprice"));
//                vo.setPurchasedate(rs.getString("purchasedate"));
//                vo.setOwnerid(rs.getString("ownerid"));
//                vo.setOwnername(rs.getString("ownername"));
//                vo.setInterestrate(rs.getDouble("interestrate"));
//                vo.setPeriod(rs.getString("period"));
//                vo.setCustomer(rs.getString("customer"));
//                vo.setCardescription(rs.getString("cardescription"));
//                vo.setLicenseno(rs.getString("licenseno"));
//                vo.setInventoryints(rs.getString("inventoryints"));
//                vo.setInventoryoutts(rs.getString("inventoryoutts"));
//                vo.setTradefee(rs.getDouble("Tradefee"));
//                vo.setComments(rs.getString("comments"));

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
    public TradeDto query(String id) throws Exception{

        TradeDto result = new TradeDto();
        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT * ");
        sql.append(" SELECT b.spareloan ");
        sql.append("    ,a.vehicleid ");
        sql.append("    ,a.licenseno ");
        sql.append("    ,a.vehicledesc ");
        sql.append("    ,a.traderid ");
        sql.append("    ,a.tradername ");
        sql.append("    ,a.purchaseprice ");
        sql.append("    ,a.purchasedate ");
        sql.append("    ,a.ownerid ");
        sql.append("    ,a.ownername ");
        sql.append("    ,a.ownerdesc ");
        sql.append("    ,a.ownermobile ");
        sql.append("    ,a.interestrate ");
        sql.append("    ,a.interest ");
        sql.append("    ,a.actualloan ");
//        sql.append("    ,a.spareloan ");
        sql.append("    ,a.earnest ");
        sql.append("    ,a.sellprice ");
        sql.append("    ,a.selldate ");
        sql.append("    ,a.pricediff ");
        sql.append("    ,a.tradecost ");
        sql.append("    ,a.profit ");
        sql.append("    ,a.vehicletype ");
        sql.append("    ,a.settlement ");
        sql.append("    ,a.settlementdate ");
        sql.append("    ,a.totalprofit ");
        sql.append("    ,a.traderprofit ");
        sql.append("    ,a.picturepath ");
        sql.append("    ,a.isdeleted ");
        sql.append("    ,a.issold ");
        sql.append("    ,a.comments ");
        sql.append("    ,a.createdts ");
        sql.append("    ,a.lastupdatedts ");
        sql.append("    ,a.interestcost ");
        sql.append("    ,a.buyerid ");
        sql.append("    ,a.buyername ");
        sql.append("    ,a.buyermobile ");
        sql.append("   FROM SecCarTrade a,  SpareLoan b");
        sql.append("  WHERE a.vehicleid = ? and a.traderid = b.userid");
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
//                result.setVIN(rs.getString("VIN"));
                result.setLicenseno(rs.getString("licenseno"));
                result.setVehicledesc(rs.getString("vehicledesc"));
                result.setTraderid(rs.getString("traderid"));
                result.setTradername(rs.getString("tradername"));
                result.setPurchaseprice(rs.getDouble("purchaseprice"));
                result.setPurchasedate(rs.getString("purchasedate"));
                result.setOwnerid(rs.getString("ownerid"));
                result.setOwnername(rs.getString("ownername"));
                result.setOwnerdesc(rs.getString("ownerdesc"));
                result.setOwnermobile(rs.getString("ownermobile"));
                result.setInterestrate(rs.getDouble("interestrate"));
                result.setInterest(rs.getDouble("interest"));
                result.setActualloan(rs.getDouble("actualloan"));
                result.setSpareloan(rs.getDouble("spareloan"));
                result.setEarnest(rs.getDouble("earnest"));
                result.setSellprice(rs.getDouble("sellprice"));
                result.setSelldate(rs.getString("selldate"));
                result.setPricediff(rs.getDouble("pricediff"));
                result.setTradecost(rs.getDouble("tradecost"));
                result.setProfit(rs.getDouble("profit"));
                result.setVehicletype(rs.getString("vehicletype"));
                result.setSettlement(rs.getString("settlement"));
                result.setSettlementdate(rs.getString("settlementdate"));
                result.setTotalprofit(rs.getDouble("totalprofit"));
                result.setTraderprofit(rs.getDouble("traderprofit"));
                result.setPicturepath(rs.getString("picturepath"));
                result.setIsdeleted(rs.getString("isdeleted"));
                result.setIssold(rs.getString("issold"));
                result.setComments(rs.getString("comments"));
                result.setCreatedts(rs.getString("createdts"));
                result.setLastupdatedts(rs.getString("lastupdatedts"));
                result.setInterestcost(rs.getDouble("interestcost"));
                result.setBuyername(rs.getString("buyername"));
                result.setBuyerid(rs.getString("buyerid"));
                result.setBuyermobile(rs.getString("buyermobile"));
                result.calculateLiXiChengBen();
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
    
    public List<TradeDto> getTradeCarsSellInPeriod(String startDate, String endDate) throws Exception{
    	List<TradeDto> list = new ArrayList<TradeDto>();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select * from SecCarTrade where isdeleted<>'1' and issold='1' and selldate>=? and selldate <=? ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }
        List args = new ArrayList();
        args.add(startDate);
        args.add(endDate);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);
            if (rs == null){
                return list;
            }
            while(rs.next()){
            	TradeDto result = new TradeDto();
            	result.setVehicleid(rs.getString("vehicleid"));
//                result.setVIN(rs.getString("VIN"));
                result.setLicenseno(rs.getString("licenseno"));
                result.setVehicledesc(rs.getString("vehicledesc"));
                result.setTraderid(rs.getString("traderid"));
                result.setTradername(rs.getString("tradername"));
                result.setPurchaseprice(rs.getDouble("purchaseprice"));
                result.setPurchasedate(rs.getString("purchasedate"));
                result.setOwnerid(rs.getString("ownerid"));
                result.setOwnername(rs.getString("ownername"));
                result.setOwnerdesc(rs.getString("ownerdesc"));
                result.setOwnermobile(rs.getString("ownermobile"));
                result.setInterestrate(rs.getDouble("interestrate"));
                result.setInterest(rs.getDouble("interest"));
                result.setActualloan(rs.getDouble("actualloan"));
                result.setSpareloan(rs.getDouble("spareloan"));
                result.setEarnest(rs.getDouble("earnest"));
                result.setSellprice(rs.getDouble("sellprice"));
                result.setSelldate(rs.getString("selldate"));
                result.setPricediff(rs.getDouble("pricediff"));
                result.setTradecost(rs.getDouble("tradecost"));
                result.setProfit(rs.getDouble("profit"));
                result.setVehicletype(rs.getString("vehicletype"));
                result.setSettlement(rs.getString("settlement"));
                result.setSettlementdate(rs.getString("settlementdate"));
                result.setTotalprofit(rs.getDouble("totalprofit"));
                result.setTraderprofit(rs.getDouble("traderprofit"));
                result.setPicturepath(rs.getString("picturepath"));
                result.setIsdeleted(rs.getString("isdeleted"));
                result.setIssold(rs.getString("issold"));
                result.setComments(rs.getString("comments"));
                result.setCreatedts(rs.getString("createdts"));
                result.setLastupdatedts(rs.getString("lastupdatedts"));
                result.setInterestcost(rs.getDouble("interestcost"));
                result.setBuyername(rs.getString("buyername"));
                result.setBuyerid(rs.getString("buyerid"));
                result.setBuyermobile(rs.getString("buyermobile"));
                result.calculateLiXiChengBen();
                list.add(result);
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

        return list;
    }
    /***********************************************/
    // 查询卖出的车辆数量和未卖出的车辆数量
    /***********************************************/
    public List<CarSummaryDto> getSummaryForCarTrade() throws Exception{
    	List<CarSummaryDto> list = new ArrayList<CarSummaryDto>();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select issold,vehicletype,COUNT(*) as number,sum(purchaseprice) as amount from SecCarTrade where isdeleted<>'1' group by issold, vehicletype ");
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
                return list;
            }
            while(rs.next()){
            	CarSummaryDto result = new CarSummaryDto();
            	result.setCarType(rs.getString("vehicletype"));
                if ("1".equalsIgnoreCase(rs.getString("issold")))//卖出去的车的数量
           		{
                	result.setSold(true);
                	result.setOutStockCarsAmount(rs.getInt("number"));
                	result.setOutStockCarsMoney(rs.getDouble("amount"));
                } else
                {
                	//库存车辆数量
                	result.setSold(false);
                	result.setInStockCarsAmount(rs.getInt("number"));
                	result.setInStockCarMoney(rs.getDouble("amount"));
                }
                list.add(result);
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

        return list;
    }
    /***********************************************/
    // 查询卖车总差价
    /***********************************************/
    public CarSummaryDto getSummaryTradeProfit() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select vehicletype,SUM(sellprice-purchaseprice) as  totalprofit from SecCarTrade where isdeleted<>'1' and issold=1 group by vehicletype ");
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
            double totalprofitself =0;
            double totalprofitthird = 0;
            while(rs.next()){
                if ("第三方".equalsIgnoreCase(rs.getString("vehicletype")))//抵押完毕的车辆
           		{
                	totalprofitthird =rs.getDouble("totalprofit");
                } else
                {
                	totalprofitself = rs.getDouble("totalprofit");
                }
            }

        	result.setTotalprofitself(totalprofitself);
        	result.setTotalprofitthird(totalprofitthird);

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
    // 查询三方未售车利息成本
    /***********************************************/
    public CarSummaryDto getSumTradeInterestInThird() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" declare @td  datetime; ");
        sql.append(" select @td = GETDATE(); ");
        sql.append(" WITH TMP AS (select CASE WHEN DATEDIFF(Month,purchasedate,@td)=0 THEN 1.5/100*(purchaseprice+tradecost)*1  ");
        sql.append(" WHEN DATEDIFF(DAY,purchasedate,@td)>DATEDIFF(Month,purchasedate,@td)*31 THEN 1.5/100*(purchaseprice+tradecost)*(DATEDIFF(Month,purchasedate,@td)+1)  ");
        sql.append(" ELSE 1.5/100*(purchaseprice+tradecost)*DATEDIFF(Month,purchasedate,@td) END AS interestcost  ");
        sql.append(" from SecCarTrade where isdeleted<>'1' and issold!=1 and vehicletype='第三方') ");
        sql.append(" SELECT SUM(interestcost) as interestcost FROM TMP  ");
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
            double tradeinterestinthird =0;
            while(rs.next()){
            	tradeinterestinthird = rs.getDouble("interestcost");
            }

        	result.setTradeinterestinthird(tradeinterestinthird);

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
    // 查询三方已售车利息成本
    /***********************************************/
    public CarSummaryDto getSumTradeInterestOutThird() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" WITH TMP AS (select CASE WHEN DATEDIFF(Month,purchasedate,selldate)=0 THEN 1.5/100*(purchaseprice+tradecost)*1  ");
        sql.append(" WHEN DATEDIFF(DAY,purchasedate,selldate)>DATEDIFF(Month,purchasedate,selldate)*31 THEN 1.5/100*(purchaseprice+tradecost)*(DATEDIFF(Month,purchasedate,selldate)+1) ");
        sql.append(" ELSE 1.5/100*(purchaseprice+tradecost)*DATEDIFF(Month,purchasedate,selldate) END AS interestcost from SecCarTrade where isdeleted<>'1' and issold=1 and vehicletype='第三方')  ");
        sql.append(" SELECT SUM(interestcost) as interestcost FROM TMP  ");
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
            double tradeinterestoutthird =0;
            while(rs.next()){
            	tradeinterestoutthird = rs.getDouble("interestcost");
            }

        	result.setTradeinterestoutthird(tradeinterestoutthird);

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
    // 查询自收未售车利息成本
    /***********************************************/
    public CarSummaryDto getSumTradeInterestInSelf() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" declare @td  datetime; ");
        sql.append(" select @td = GETDATE(); ");
        sql.append(" select SUM(1.5/100/30*(purchaseprice+tradecost)*DATEDIFF(day,purchasedate,@td)) as interestcost from SecCarTrade where isdeleted<>'1' and issold!=1 and vehicletype='自收车'  ");
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
            double tradeinterestinself =0;
            while(rs.next()){
            	tradeinterestinself = rs.getDouble("interestcost");
            }

        	result.setTradeinterestinself(tradeinterestinself);;

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
    // 查询自收未售车利息成本
    /***********************************************/
    public CarSummaryDto getSumTradeInterestOutSelf() throws Exception{
    	CarSummaryDto result = new CarSummaryDto();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select SUM(1.5/100/30*(purchaseprice+tradecost)*DATEDIFF(DAY,purchasedate,selldate)) as interestcost from SecCarTrade where isdeleted<>'1' and issold=1 and vehicletype='自收车'  ");
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
            double tradeinterestoutself =0;
            while(rs.next()){
            	tradeinterestoutself = rs.getDouble("interestcost");
            }

        	result.setTradeinterestoutself(tradeinterestoutself);;

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
    public List<SummaryTradeDto> listTrade() throws Exception{
    	List<SummaryTradeDto> result = new ArrayList<SummaryTradeDto>();
        StringBuilder sql = new StringBuilder();
        //删除的车辆不考虑
        sql.append(" select vehicletype,CASE  WHEN issold = '1' THEN '已售' ELSE '未售' END AS issold, CASE  WHEN settlement = '1' THEN '已结算' ELSE '未结算' END AS settlement, ");
        sql.append(" SUM(purchaseprice+tradecost) as purchaseprice,SUM(actualloan) as actualloan,SUM(earnest) as earnest,SUM(sellprice) as sellprice,SUM(tradecost) as tradecost, ");
        sql.append(" SUM(InterestCost) as InterestCost,SUM(pricediff) as pricediff,SUM(totalprofit) as totalprofit,SUM(profit)as profit,SUM(traderprofit) as traderprofit from SecCarTrade where isdeleted<>'1' ");
        sql.append(" group by vehicletype,ISSOLD,settlement ");
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
            	SummaryTradeDto vo = new SummaryTradeDto();
            	vo.setVehicletype(rs.getString("vehicletype"));
            	vo.setIssold(rs.getString("issold"));
            	vo.setSettlement(rs.getString("settlement"));
            	vo.setPurchaseprice(rs.getString("purchaseprice"));
            	vo.setActualloan(rs.getString("actualloan"));
            	vo.setEarnest(rs.getString("earnest"));
            	vo.setSellprice(rs.getString("sellprice"));
            	vo.setTradecost(rs.getString("tradecost"));
            	vo.setInterestcost(rs.getString("interestcost"));
            	vo.setPricediff(rs.getString("pricediff"));
            	vo.setTotalprofit(rs.getString("totalprofit"));
            	vo.setProfit(rs.getString("profit"));
            	vo.setTraderprofit(rs.getString("traderprofit"));
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
    
    /***********************************************/
    // 查询余量
    /***********************************************/
    public double getspare(String userId) throws Exception{

    	double result = 0.00;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  spareloan ");
        sql.append("   FROM SpareLoan ");
        sql.append("  WHERE userid = ? ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        List args = new ArrayList();
        args.add(userId);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            rs.next();
            result = rs.getDouble("spareloan");

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
    
    public boolean spareExists(String userId) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  1 ");
        sql.append("   FROM SpareLoan ");
        sql.append("  WHERE userid = ? ");
        System.out.println("query sql: "+sql);
        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            throw new Exception("无法获取数据库连接！");
        }

        List args = new ArrayList();
        args.add(userId);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return false;
            }
            while(rs.next())
            {
            	int result = rs.getInt(1);
            	if (result == 1)
            	{
            		return true;
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
                logger.error(e.getMessage());
            }
        }

        return false;
    }
    /***********************************************/
    // 创建一个
    /***********************************************/
    public void create(Trade vo) throws Exception{

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO SecCarTrade ( ");
        sql.append("     vehicleid ");
        sql.append("    ,licenseno ");
        sql.append("    ,vehicledesc ");
        sql.append("    ,traderid ");
        sql.append("    ,tradername ");
        sql.append("    ,purchaseprice ");
        sql.append("    ,purchasedate ");
        sql.append("    ,ownerid ");
        sql.append("    ,ownername ");
        sql.append("    ,ownerdesc ");
        sql.append("    ,ownermobile ");
        sql.append("    ,interestrate ");
        sql.append("    ,interest ");
        sql.append("    ,actualloan ");
        sql.append("    ,spareloan ");
        sql.append("    ,earnest ");
        sql.append("    ,vehicletype ");
        sql.append("    ,comments ");
        sql.append("    ,tradecost ");
        sql.append("    ,settlement ");
        sql.append("    ,issold ");
        sql.append("    ,isdeleted ");
        sql.append("    ,picturepath ");
        sql.append("    ,createdts ");
        sql.append("    ,lastupdatedts ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(25));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getVehicleid());
        args.add(vo.getLicenseno());
        args.add(vo.getVehicledesc());
        args.add(vo.getTraderid());
        args.add(vo.getTradername());
        args.add(vo.getPurchaseprice());
        args.add(vo.getPurchasedate());
        args.add(vo.getOwnerid());
        args.add(vo.getOwnername());
        args.add(vo.getOwnerdesc());
        args.add(vo.getOwnermobile());
        args.add(vo.getInterestrate());
        args.add(vo.getInterest());
        args.add(vo.getActualloan());
        args.add(vo.getSpareloan());
        args.add(vo.getEarnest());
        args.add(vo.getVehicletype());
        args.add(vo.getComments());
        args.add(vo.getTradecost());
        args.add("0");
        args.add("0");
        args.add("0");
        args.add(vo.getPicturepath());
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
    public void update(Trade vo) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE SecCarTrade SET ");
        sql.append("   isdeleted = ? ");
        sql.append("   ,issold = ? ");
        sql.append("   ,traderid = ? ");
        sql.append("   ,licenseno = ? ");
        sql.append("   ,vehicledesc = ? ");
        sql.append("   ,tradername = ? ");
        sql.append("   ,purchaseprice = ? ");
        sql.append("   ,ownername = ? ");
        sql.append("   ,ownerid = ? ");
        sql.append("   ,ownermobile = ? ");
        sql.append("   ,purchasedate = ? ");
        sql.append("   ,interestrate = ? ");
        sql.append("   ,actualloan = ? ");
        sql.append("   ,spareloan = ? ");
        sql.append("   ,vehicletype = ? ");
        sql.append("   ,comments = ? ");
        sql.append("   ,earnest = ? ");
        sql.append("   ,tradecost = ? ");
        sql.append("   ,sellprice = ? ");
        sql.append("   ,selldate = ? ");
        sql.append("   ,settlement = ? ");
        sql.append("   ,settlementdate = ? ");
        sql.append("   ,lastupdatedts = ? ");
        sql.append("   ,interest = ? ");
        sql.append("   ,interestcost = ? ");
        sql.append("   ,pricediff = ? ");
        sql.append("   ,totalprofit = ? ");
        sql.append("   ,profit = ? ");
        sql.append("   ,traderprofit = ? ");
        sql.append("   ,buyerid = ? ");
        sql.append("   ,buyername = ? ");
        sql.append("   ,buyermobile = ? ");
        sql.append("   ,picturepath = ? ");
        sql.append(" WHERE vehicleid = ? ");

        List args = new ArrayList();
        args.add(vo.getIsdeleted());
        args.add(vo.getIssold());
        args.add(vo.getTraderid());
        args.add(vo.getLicenseno());
        args.add(vo.getVehicledesc());
        args.add(vo.getTradername());
        args.add(vo.getPurchaseprice());
        args.add(vo.getOwnername());
        args.add(vo.getOwnerid());
        args.add(vo.getOwnermobile());
        args.add(vo.getPurchasedate());
        args.add(vo.getInterestrate());
        args.add(vo.getActualloan());
        args.add(vo.getSpareloan());
        args.add(vo.getVehicletype());
        args.add(vo.getComments());
        args.add(vo.getEarnest());
        args.add(vo.getTradecost());
        args.add(vo.getSellprice());
        args.add(vo.getSelldate());
        args.add(vo.getSettlement());
        args.add(vo.getSettlementdate());
        args.add(new Date());
        args.add(vo.getInterest());
        args.add(vo.getInterestcost());
        args.add(vo.getPricediff());
        args.add(vo.getTotalprofit());
        args.add(vo.getProfit());
        args.add(vo.getTraderprofit());
        args.add(vo.getBuyerid());
        args.add(vo.getBuyername());
        args.add(vo.getBuyermobile());
        args.add(vo.getPicturepath());
        args.add(vo.getVehicleid());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /***********************************************/
    // 结算一个
    /***********************************************/
    public void settle(Trade vo) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE SecCarTrade SET ");
//        sql.append("   isdeleted = ? ");
//        sql.append("   ,issold = ? ");
//        sql.append("   ,traderid = ? ");
////        sql.append("   ,vehicleid = ? ");
//        sql.append("   ,licenseno = ? ");
//        sql.append("   ,vehicledesc = ? ");
//        sql.append("   ,tradername = ? ");
//        sql.append("   ,purchaseprice = ? ");
//        sql.append("   ,ownername = ? ");
//        sql.append("   ,ownerid = ? ");
//        sql.append("   ,purchasedate = ? ");
//        sql.append("   ,interestrate = ? ");
//        sql.append("   ,actualloan = ? ");
//        sql.append("   ,spareloan = ? ");
//        sql.append("   ,vehicletype = ? ");
//        sql.append("   ,comments = ? ");
//        sql.append("   ,earnest = ? ");
//        sql.append("   ,tradecost = ? ");
//        sql.append("   ,sellprice = ? ");
//        sql.append("   ,selldate = ? ");
        sql.append("   settlement = ? ");
        sql.append("   ,settlementdate = ? ");
        sql.append("   ,lastupdatedts = ? ");
//        sql.append("   ,interest = ? ");
//        sql.append("   ,interestcost = ? ");
//        sql.append("   ,pricediff = ? ");
//        sql.append("   ,totalprofit = ? ");
//        sql.append("   ,profit = ? ");
//        sql.append("   ,traderprofit = ? ");
        sql.append(" WHERE vehicleid = ? ");

        List args = new ArrayList();
//        args.add(vo.getIsdeleted());
//        args.add(vo.getIssold());
//        args.add(vo.getTraderid());
//        args.add(vo.getLicenseno());
//        args.add(vo.getVehicledesc());
//        args.add(vo.getTradername());
//        args.add(vo.getPurchaseprice());
//        args.add(vo.getOwnername());
//        args.add(vo.getOwnerid());
//        args.add(vo.getPurchasedate());
//        args.add(vo.getInterestrate());
//        args.add(vo.getActualloan());
//        args.add(vo.getSpareloan());
//        args.add(vo.getVehicletype());
//        args.add(vo.getComments());
//        args.add(vo.getEarnest());
//        args.add(vo.getTradecost());
//        args.add(vo.getSellprice());
//        args.add(vo.getSelldate());
        args.add(vo.getSettlement());
        args.add(vo.getSettlementdate());
        args.add(new Date());
//        args.add(vo.getInterest());
//        args.add(vo.getInterestcost());
//        args.add(vo.getPricediff());
//        args.add(vo.getTotalprofit());
//        args.add(vo.getProfit());
//        args.add(vo.getTraderprofit());
        args.add(vo.getVehicleid());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /***********************************************/
    // 更新余量
    /***********************************************/
    public void updateSpare(String userId, double sloan) throws Exception{

    	StringBuilder sql = new StringBuilder();

      
    	sql.append(" UPDATE SpareLoan SET ");
    	sql.append("   spareloan = ?"
    			+ " where userid=? ");
    	Connection conn = DbConnectionFactory.createHonchenConnection();
    	if (conn == null){
    		return;
    	}

    	PreparedStatement ps = null;
    	String pk = null;
    	try {
          conn.setAutoCommit(false);
          ps = conn.prepareStatement(sql.toString());

              List args = new ArrayList();
              args.add(sloan);
              args.add(userId);
              DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
	          ps.executeBatch();
	          conn.commit();
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
    	 public void insertSpare(String userId, double sloan) throws Exception{

    	    	StringBuilder sql = new StringBuilder();

    	      
    	    	sql.append(" insert SpareLoan values(?,?) ");
    	    	Connection conn = DbConnectionFactory.createHonchenConnection();
    	    	if (conn == null){
    	    		return;
    	    	}

    	    	PreparedStatement ps = null;
    	    	String pk = null;
    	    	try {
    	          conn.setAutoCommit(false);
    	          ps = conn.prepareStatement(sql.toString());

    	              List args = new ArrayList();
    	              args.add(sloan);
    	              args.add(userId);
    	              DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
    		          ps.executeBatch();
    		          conn.commit();
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
//
//        StringBuilder sql = new StringBuilder();
//
//        
//        sql.append(" UPDATE SpareLoan SET ");
//        sql.append("   spareloan = ? ");
//
//        List args = new ArrayList();
//        args.add(sloan);
//
//        try {
//            executeNonQuery(sql.toString(), args);
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
    }
////
////    /***********************************************/
////    // 移动至分类多个
////    /***********************************************/
////    public void move(String[] list, String tree, String user) throws Exception{
////
////        if (tree == null || tree.length() == 0){
////            return;
////        }
////
////        if (list == null || list.length == 0){
////            return;
////        }
////
////        StringBuilder sql = new StringBuilder();
////        sql.append(" UPDATE SecCarTrade SET ");
////        sql.append("     FTreeID = ? ");
////        sql.append("    ,FLastUpdateUserID = ? ");
////        sql.append("    ,FLastUpdateTime = ? ");
////        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
////
////        List args = new ArrayList();
////        args.add(tree);
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
//
    /***********************************************/
    // 删除一个
    /***********************************************/
    public void deletesingle(String vehicleid) throws Exception{


        StringBuilder sql = new StringBuilder();
//        sql.append(" DELETE FROM SecCarTrade ");
        sql.append("UPDATE SecCarTrade SET ISDELETED=1 ");
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
//            TradeDto td = new TradeDto();
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

            TradeDto dto = new TradeDto();
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
    /***********************************************/
    // 删除多个
    /***********************************************/
    public void delete(String[] list) throws Exception{

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
//        sql.append(" DELETE FROM SecCarTrade ");
        sql.append("UPDATE SecCarTrade SET ISDELETED=1 ");
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

            for(String vehicleid : list){

                TradeDto td = new TradeDto();
                td = this.query(vehicleid);
                if(td.getSettlement().equals("1") || td.getIsdeleted().equals("1")){
                	throw new Exception("车辆：" + td.getLicenseno() + SysConstant.M_SETTLEMENT_ERROR);
                }
                List args = new ArrayList();
                args.add(vehicleid);
                pk = vehicleid;
                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            }
            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {

            TradeDto dto = new TradeDto();
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
//
//    /***********************************************/
//    // 启用或禁用多个
//    /***********************************************/
//    public void enable(String[] list, int status, String user) throws Exception{
//
//        if (list == null || list.length == 0){
//            return;
//        }
//
//        StringBuilder sql = new StringBuilder();
//        sql.append(" UPDATE SecCarTrade SET ");
//        sql.append("      FEnable = ? ");
//        sql.append("     ,FLastUpdateUserID = ? ");
//        sql.append("     ,FLastUpdateTime = ? ");
//        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
//
//        List args = new ArrayList();
//        args.add(status);
//        args.add(user);
//        args.add(new Date());
//
//        try {
//            executeNonQuery(sql.toString(), args);
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
//
    /***********************************************/
    // 获得相同编号的数据
    /***********************************************/
    public TradeDto getEquals(String licenseno) throws Exception{

        TradeDto result = new TradeDto();
        if (licenseno == null || licenseno.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("   	vehicleid ");
        sql.append("   ,licenseno ");
        sql.append("   ,purchaseprice ");
        sql.append("   ,actualloan ");
        sql.append("   ,spareloan ");
        sql.append("   ,tradecost ");
//        sql.append("   ,ownerid ");
//        sql.append("   ,traderid ");
        sql.append(" FROM SecCarTrade ");
        sql.append("WHERE licenseno = ? and isdeleted != '1' and settlement !='1' ");

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
                result.setPurchaseprice(rs.getDouble("purchaseprice"));
                result.setActualloan(rs.getDouble("actualloan"));
                result.setSpareloan(rs.getDouble("spareloan"));
                result.setTradecost(rs.getDouble("tradecost"));
//                result.setOwnerid(rs.getString("ownerid"));
//                result.setTraderid(rs.getString("traderid"));
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
    private String getWhere(TradeForm form, List args) throws Exception{

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
            	if(form.getFilterissold() != null && form.getFilterissold().length() > 0){
                    result.append(" AND (a.issold like ? ) ");
                    args.add("%" + form.getFilterissold().trim() + "%");
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
            	if(form.getFiltertradername() != null && form.getFiltertradername().length() > 0){
                    result.append(" AND (a.tradername like ? ) ");
                    args.add("%" + form.getFiltertradername().trim() + "%");
                	}
            	if(form.getFiltercustomer() != null && form.getFiltercustomer().length() > 0){
                    result.append(" AND (a.ownername like ? ) ");
                    args.add("%" + form.getFiltercustomer().trim() + "%");
                	}
            	if(form.getFiltervehicletype() != null && form.getFiltervehicletype().length() > 0){
                    result.append(" AND (a.vehicletype like ? ) ");
                    args.add("%" + form.getFiltervehicletype().trim() + "%");
                	}
            	if(form.getPurchasestart() != null && form.getPurchasestart().length() > 0){
                    result.append(" AND (a.purchasedate >= ? ) ");
                    args.add(form.getPurchasestart().trim());
                	}
            	if(form.getPurchaseend() != null && form.getPurchaseend().length() > 0){
                    result.append(" AND (a.purchasedate <= ? ) ");
                    args.add(form.getPurchaseend().trim());
                	}
            	if(form.getSoldstart() != null && form.getSoldstart().length() > 0){
                    result.append(" AND (a.selldate >= ? ) ");
                    args.add(form.getSoldstart().trim());
                	}
            	if(form.getSoldend() != null && form.getSoldend().length() > 0){
                    result.append(" AND (a.selldate <= ? ) ");
                    args.add(form.getSoldend().trim());
                	}
                		 	
            }
        }else{
        	result.append(" AND (a.isdeleted !='1' ) ");
//            args.add("%" + form.getFilterisdeleted().trim() + "%");
        }

//        if (form.getEnable() == 1){
//            result.append(" AND a.FEnable = 1 ");
//        }

        return result.toString();
    }
}
