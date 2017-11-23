package com.lhcy.sync.domain.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class TradeDto implements Serializable {
	private String vehicleid;
    private String VIN;
    private String licenseno;
    private String vehicledesc;
    private String traderid;
    private String tradername;
    private double purchaseprice;
    private String purchasedate;
    private String ownerid;
    private String ownername;
    private String ownerdesc;
    private double interestrate;
    private double interest;
    private double actualloan;
    private double spareloan;
    private double earnest;
    private double sellprice;
    private String selldate;
    private double pricediff;
    private double tradecost;
    private double profit;
    private String vehicletype;
    private String settlement;
    private String settlementdate;
    private double totalprofit;
    private double traderprofit;
    private String picturepath;
    private String isdeleted;
    private String issold;
    private String comments;
    private String createdts;
    private String lastupdatedts;
    private double interestcost;
    private String buyerid;
    private String buyername;
    private String ownermobile;
    private String buyermobile;
    private String lixichengben;
    private Map<String, Double> monthAndCost = new HashMap<String, Double>();
    
    
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
    		if ("第三方".equalsIgnoreCase(vehicletype)) {

    			Calendar today = getDate(new Date());
    			Calendar borrowDateC = formatDate(purchasedate);
    			Calendar actualreturndateC = formatDate(selldate);
    			Calendar borrowDateCopy = formatDate(purchasedate);
    			if (borrowDateC != null)
    			{
    				if(actualreturndateC!= null)
    				{
    					if (borrowDateCopy.before(actualreturndateC))
    					{
    						borrowDateCopy.set(Calendar.MONTH, borrowDateCopy.get(Calendar.MONTH) + 1);
    						if (!borrowDateCopy.before(actualreturndateC))
    						{
    							String start = toDateStr(borrowDateC);
    							String end= toDateStr(actualreturndateC);
    							sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    							monthAndCost.put(String.valueOf(borrowDateCopy.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
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
    								String end= toDateStr(borrowDateC);
    								int endMonth = borrowDateC.get(Calendar.MONTH);
    								borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    								String start = toDateStr(borrowDateC);
    								sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(endMonth), Double.valueOf(actualloan * 1.5/100));
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
    						if (!borrowDateCopy.before(today))
    						{
    							String start = toDateStr(borrowDateC);
    							String end= toDateStr(today);
    							sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    							monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
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
    								sb.append(start+"到"+end+ "利息 	" + (actualloan * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(actualloan * 1.5/100));
    								
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
    		
    		} else if ("自收车".equalsIgnoreCase(vehicletype))
    		{
    			Calendar today = getDate(new Date());
    			Calendar borrowDateC = formatDate(purchasedate);
    			Calendar actualreturndateC = formatDate(selldate);
    			Calendar borrowDateCopy = formatDate(purchasedate);
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
    							sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    							monthAndCost.put(String.valueOf(borrowDateCopy.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
    						} else if (borrowDateCopy.after(actualreturndateC)){
    							String start = toDateStr(borrowDateC);
    							String end= toDateStr(actualreturndateC);
    							long time1 = borrowDateC.getTimeInMillis();                  
    							long time2 = actualreturndateC.getTimeInMillis();          
    							int between_days=Integer.parseInt(String.valueOf((time2-time1)/(1000*3600*24)));     
    							
    							sb.append(start+"到"+end+ "利息 	" + ((purchaseprice * 1.5 * between_days)/(30*100))+"元</br>");
    							monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf((purchaseprice * 1.5 * between_days)/(30*100)));
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
    								sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(borrowDateC.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
    							}
    							if (borrowDateC.equals(actualreturndateC))
    							{
    								borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    								String start = toDateStr(borrowDateC);
    								String end= toDateStr(actualreturndateC);
    								sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
    							} else 
    							{
    								borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    								String start = toDateStr(borrowDateC);
    								String end= toDateStr(actualreturndateC);
    								long time1 = borrowDateC.getTimeInMillis();                  
    								long time2 = actualreturndateC.getTimeInMillis();          
    								int between_days=Integer.parseInt(String.valueOf((time2-time1)/(1000*3600*24)));     
    								
    								sb.append(start+"到"+end+ "利息 	" + ((purchaseprice * 1.5 * between_days)/(30*100))+"元</br>");
    								monthAndCost.put(String.valueOf(actualreturndateC.get(Calendar.MONTH)), Double.valueOf((purchaseprice * 1.5 * between_days)/(30*100)));
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
    							sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    							monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
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
    								sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(borrowDateC.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
    							}
    							if (borrowDateC.equals(today))
    							{
    								borrowDateC.set(Calendar.MONTH, borrowDateC.get(Calendar.MONTH) - 1);
    								String start = toDateStr(borrowDateC);
    								String end= toDateStr(today);
    								sb.append(start+"到"+end+ "利息 	" + (purchaseprice * 1.5/100)+"元</br>");
    								monthAndCost.put(String.valueOf(today.get(Calendar.MONTH)), Double.valueOf(purchaseprice * 1.5/100));
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
    		} else {
    			sb.append("不支持车辆类型");
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
	public Map<String, Double> getMonthAndCost() {
		return monthAndCost;
	}
	public void setMonthAndCost(Map<String, Double> monthAndCost) {
		this.monthAndCost = monthAndCost;
	}
	public String getOwnermobile() {
		return ownermobile;
	}
	public void setOwnermobile(String ownermobile) {
		this.ownermobile = ownermobile;
	}
	public String getBuyermobile() {
		return buyermobile;
	}
	public void setBuyermobile(String buyermobile) {
		this.buyermobile = buyermobile;
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
	public double getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}
	public String getPurchasedate() {
		return purchasedate;
	}
	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
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
	public double getActualloan() {
		return actualloan;
	}
	public void setActualloan(double actualloan) {
		this.actualloan = actualloan;
	}
	public double getSpareloan() {
		return spareloan;
	}
	public void setSpareloan(double spareloan) {
		this.spareloan = spareloan;
	}
	public double getEarnest() {
		return earnest;
	}
	public void setEarnest(double earnest) {
		this.earnest = earnest;
	}
	public double getSellprice() {
		return sellprice;
	}
	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}
	public String getSelldate() {
		return selldate;
	}
	public void setSelldate(String selldate) {
		this.selldate = selldate;
	}
	public double getPricediff() {
		return pricediff;
	}
	public void setPricediff(double pricediff) {
		this.pricediff = pricediff;
	}
	public double getTradecost() {
		return tradecost;
	}
	public void setTradecost(double tradecost) {
		this.tradecost = tradecost;
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
	public String getIssold() {
		return issold;
	}
	public void setIssold(String issold) {
		this.issold = issold;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCreatedts() {
		return createdts;
	}
	public void setCreatedts(String createdts) {
		this.createdts = createdts;
	}
	public String getLastupdatedts() {
		return lastupdatedts;
	}
	public void setLastupdatedts(String lastupdatedts) {
		this.lastupdatedts = lastupdatedts;
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
	public double getInterestcost() {
		return interestcost;
	}
	public void setInterestcost(double interestcost) {
		this.interestcost = interestcost;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	
    
    
    
}
