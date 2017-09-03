package com.lhcy.sync.domain.dto;

import java.io.Serializable;
import java.util.Date;

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
