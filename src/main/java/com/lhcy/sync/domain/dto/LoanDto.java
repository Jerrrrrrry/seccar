package com.lhcy.sync.domain.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class LoanDto implements Serializable {
	private String vehicleid;
    private String VIN;
    private String licenseno;
    private String vehicledesc;
    private String ownerid;
    private String ownername;
    private String ownerdesc;
    private String borrowdate;
    private String returndate;
    private double periodmonths;
    private String traderid;
    private String tradername;
    private double borrowamount;
    private double interestrate;
    private double interest;
    private double interestpaid;
    private double totalinterest;
    private double midinterestrate;
    private double midinterest;
    private double parkingfee;
    private double otherfee;
    private String comments; 
    private double actualloan;
    private double actualreturn;
    private String actualreturndate;
    private double othercost;
    private double profit;
    private String vehicletype;
    private String settlement;
    private String settlementdate;
    private double totalprofit;
    private double traderprofit;
    private String picturepath;
    private String isdeleted;
    private String isreturned;
    private String isabandon;
    private String comments2;
    private String interestpaidto;
    private String nextpaymentdate;
    private double interestcost;
    private double earnest;
    private String mobileno;
    private double actualmonths;
    private String lixichengben;
    private Map<String, Double> monthAndCost = new HashMap<String, Double>();
    
    public Map<String, Double> getMonthAndCost() {
		return monthAndCost;
	}
	public void setMonthAndCost(Map<String, Double> monthAndCost) {
		this.monthAndCost = monthAndCost;
	}
	private Calendar getDate(Date date)
    {
		Calendar cal2=Calendar.getInstance();  
		cal2.setTime(date);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		return cal2;
    }
    private Calendar formatDate(String dateString){
    	
    	if (!StringUtils.isBlank(dateString))
	    {
    		try
	    	{
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date = sdf.parse(dateString);
	    		return getDate(date);
	    	}
	    	catch (Exception e)
	    	{
	    		System.out.println(e.getMessage());
	    	}
    	}
    	return null;
    }
    private String toDateStr(Calendar cal)
    {
    	int month = cal.get(Calendar.MONTH)+1;
    	int date = cal.get(Calendar.DATE);
    	return cal.get(Calendar.YEAR) + "-" +(month<10?("0"+month):month) +"-"+ (date<10?("0"+date):date);
    }
    public void calculateLiXiChengBen(){
    	StringBuilder sb = new StringBuilder();
    	if (!"1".equalsIgnoreCase(isdeleted)){
    		Calendar today = getDate(new Date());
    		Calendar borrowDateC = formatDate(borrowdate);
    		Calendar actualreturndateC = formatDate(actualreturndate);
    		Calendar borrowDateCopy = formatDate(borrowdate);
    		if (borrowDateC != null)
    		{
    			if(actualreturndateC!= null)
    			{
    				if (borrowDateCopy.before(actualreturndateC))
    				{
    					borrowDateCopy.set(Calendar.MONTH, borrowDateCopy.get(Calendar.MONTH) + 1);
    					if (borrowDateCopy.equals(actualreturndateC))
    					{
    						String start = toDateStr(borrowDateC);
        					String end= toDateStr(actualreturndateC);
        					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
        					monthAndCost.put(String.valueOf(borrowDateCopy.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    					} else if (borrowDateCopy.after(actualreturndateC)){
    						String start = toDateStr(borrowDateC);
        					String end= toDateStr(actualreturndateC);
        					long time1 = borrowDateC.getTimeInMillis();                  
        					long time2 = actualreturndateC.getTimeInMillis();          
        					int between_days=Integer.parseInt(String.valueOf((time2-time1)/(1000*3600*24)));     

        					sb.append(start+"到"+end+ "利息 	" + ((actualloan * 1.5 * between_days)/(30*100))+"元</br>");
        					monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf((actualloan * 1.5 * between_days)/(30*100)));
    					} else {
    						while (borrowDateC.before(actualreturndateC))
    	        			{
    	    					String start = toDateStr(borrowDateC);
    	    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) + 1);
    	    					if (!borrowDateC.before(actualreturndateC))
    	    					{
    	    						break;
    	    					}
    	    					String end= toDateStr(borrowDateC);
    	    					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    	    					monthAndCost.put(String.valueOf(borrowDateC.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    	        			}
    	    				if (borrowDateC.equals(actualreturndateC))
    	    				{
    	    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    	    					String start = toDateStr(borrowDateC);
    	    					String end= toDateStr(actualreturndateC);
    	    					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    	    					monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    	    				} else 
    	    				{
    	    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    	    					String start = toDateStr(borrowDateC);
    	    					String end= toDateStr(actualreturndateC);
    	    					long time1 = borrowDateC.getTimeInMillis();                  
    	    					long time2 = actualreturndateC.getTimeInMillis();          
    	    					int between_days=Integer.parseInt(String.valueOf((time2-time1)/(1000*3600*24)));     

    	    					sb.append(start+"到"+end+ "利息 	" + ((actualloan * 1.5 * between_days)/(30*100))+"元</br>");
    	    					monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf((actualloan * 1.5 * between_days)/(30*100)));
    	    				}
    					}
    				} else {
    					sb.append("无法计算");
    				}
    			}
    			else
    			{
	    			if(borrowDateCopy.before(today))
	    			{
	    				borrowDateCopy.set(Calendar.MONTH, borrowDateCopy.get(Calendar.MONTH) + 1);
	    				if (borrowDateCopy.equals(today))
    					{
    						String start = toDateStr(borrowDateC);
        					String end= toDateStr(today);
        					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
        					monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    					} else if (borrowDateCopy.after(today)){
        					String end= toDateStr(borrowDateCopy);
        					sb.append("目前利息成本为0，到下月"+end+ "才开始计算利息成本</br>");
        					monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(0));
    					} else {
		    				while (borrowDateC.before(today))
		    				{
		    					String start = toDateStr(borrowDateC);
		    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) + 1);
		    					if (!borrowDateC.before(today))
    	    					{
    	    						break;
    	    					}
		    					String end= toDateStr(borrowDateC);
		    					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
		    					monthAndCost.put(String.valueOf(borrowDateC.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
		    				}
		    				if (borrowDateC.equals(today))
    	    				{
    	    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    	    					String start = toDateStr(borrowDateC);
    	    					String end= toDateStr(today);
    	    					sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    	    					monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    	    				} else 
    	    				{
    	    					String end= toDateStr(borrowDateC);
    	    					borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    	    					String start = toDateStr(borrowDateC);
    	    					sb.append(start+"到"+end+ "利息 0元，下个月"+end+"开始计算利息</br>");
    	    					monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(0));
    	    				
    	    				}
    					}
	    			} else 
	    			{
	    				today.set(Calendar.MONTH, today.get(Calendar.MONTH) + 1);
	    				String end= toDateStr(today);
	    				sb.append("目前利息成本为0，到下月"+end+ "才开始计算利息成本</br>");
	    				monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(0));
	    			}
    			}
    		} else
    		{
    			sb.append("无法计算");
    		}
    	} else
    	{
    		sb.append("已删除");
    	}
    	lixichengben = sb.toString();
    }
	public String getLixichengben() {
		return lixichengben;
	}
	public void setLixichengben(String lixichengben) {
		this.lixichengben = lixichengben;
	}
	public double getActualmonths() {
		return actualmonths;
	}
	public void setActualmonths(double actualmonths) {
		this.actualmonths = actualmonths;
	}
	public double getEarnest() {
		return earnest;
	}
	public void setEarnest(double earnest) {
		this.earnest = earnest;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	public String getVehicledesc() {
		return vehicledesc;
	}
	public void setVehicledesc(String vehicledesc) {
		this.vehicledesc = vehicledesc;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getOwnerdesc() {
		return ownerdesc;
	}
	public void setOwnerdesc(String ownerdesc) {
		this.ownerdesc = ownerdesc;
	}
	public String getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public double getPeriodmonths() {
		return periodmonths;
	}
	public void setPeriodmonths(double periodmonths) {
		this.periodmonths = periodmonths;
	}
	public String getTraderid() {
		return traderid;
	}
	public void setTraderid(String traderid) {
		this.traderid = traderid;
	}
	public String getTradername() {
		return tradername;
	}
	public void setTradername(String tradername) {
		this.tradername = tradername;
	}
	public double getBorrowamount() {
		return borrowamount;
	}
	public void setBorrowamount(double borrowamount) {
		this.borrowamount = borrowamount;
	}
	public double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getInterestpaid() {
		return interestpaid;
	}
	public void setInterestpaid(double interestpaid) {
		this.interestpaid = interestpaid;
	}
	public double getTotalinterest() {
		return totalinterest;
	}
	public void setTotalinterest(double totalinterest) {
		this.totalinterest = totalinterest;
	}
	public double getMidinterestrate() {
		return midinterestrate;
	}
	public void setMidinterestrate(double midinterestrate) {
		this.midinterestrate = midinterestrate;
	}
	public double getMidinterest() {
		return midinterest;
	}
	public void setMidinterest(double midinterest) {
		this.midinterest = midinterest;
	}
	public double getParkingfee() {
		return parkingfee;
	}
	public void setParkingfee(double parkingfee) {
		this.parkingfee = parkingfee;
	}
	public double getOtherfee() {
		return otherfee;
	}
	public void setOtherfee(double otherfee) {
		this.otherfee = otherfee;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getActualloan() {
		return actualloan;
	}
	public void setActualloan(double actualloan) {
		this.actualloan = actualloan;
	}
	public double getActualreturn() {
		return actualreturn;
	}
	public void setActualreturn(double actualreturn) {
		this.actualreturn = actualreturn;
	}
	public String getActualreturndate() {
		return actualreturndate;
	}
	public void setActualreturndate(String actualreturndate) {
		this.actualreturndate = actualreturndate;
	}
	public double getOthercost() {
		return othercost;
	}
	public void setOthercost(double othercost) {
		this.othercost = othercost;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getSettlementdate() {
		return settlementdate;
	}
	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}
	public double getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(double totalprofit) {
		this.totalprofit = totalprofit;
	}
	public double getTraderprofit() {
		return traderprofit;
	}
	public void setTraderprofit(double traderprofit) {
		this.traderprofit = traderprofit;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getIsreturned() {
		return isreturned;
	}
	public void setIsreturned(String isreturned) {
		this.isreturned = isreturned;
	}
	public String getIsabandon() {
		return isabandon;
	}
	public void setIsabandon(String isabandon) {
		this.isabandon = isabandon;
	}
	public String getComments2() {
		return comments2;
	}
	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}
	public String getInterestpaidto() {
		return interestpaidto;
	}
	public void setInterestpaidto(String interestpaidto) {
		this.interestpaidto = interestpaidto;
	}
	public String getNextpaymentdate() {
		return nextpaymentdate;
	}
	public void setNextpaymentdate(String nextpaymentdate) {
		this.nextpaymentdate = nextpaymentdate;
	}
	public double getInterestcost() {
		return interestcost;
	}
	public void setInterestcost(double interestcost) {
		this.interestcost = interestcost;
	}
    
    
    
}
