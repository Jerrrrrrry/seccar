package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class SummaryTradeDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1035423497491997258L;
	private String category;
	private String itemType;
    private String details;
    private String vehicletype;
    private String issold;
    private String settlement;
    private String purchaseprice;
    private String actualloan;
    private String earnest;
    private String sellprice;
    private String tradecost;
    private String interestcost;
    private String pricediff;
    private String totalprofit;
    private String profit;
    private String traderprofit;
    
    
    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getIssold() {
		return issold;
	}
	public void setIssold(String issold) {
		this.issold = issold;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(String purchaseprice) {
		this.purchaseprice = purchaseprice;
	}
	public String getActualloan() {
		return actualloan;
	}
	public void setActualloan(String actualloan) {
		this.actualloan = actualloan;
	}
	public String getEarnest() {
		return earnest;
	}
	public void setEarnest(String earnest) {
		this.earnest = earnest;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getTradecost() {
		return tradecost;
	}
	public void setTradecost(String tradecost) {
		this.tradecost = tradecost;
	}
	public String getInterestcost() {
		return interestcost;
	}
	public void setInterestcost(String interestcost) {
		this.interestcost = interestcost;
	}
	public String getPricediff() {
		return pricediff;
	}
	public void setPricediff(String pricediff) {
		this.pricediff = pricediff;
	}
	public String getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(String totalprofit) {
		this.totalprofit = totalprofit;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getTraderprofit() {
		return traderprofit;
	}
	public void setTraderprofit(String traderprofit) {
		this.traderprofit = traderprofit;
	}
    
    
}
