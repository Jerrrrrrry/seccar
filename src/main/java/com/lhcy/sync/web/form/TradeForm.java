package com.lhcy.sync.web.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TradeForm extends ActionForm {

    // 过滤界面用
    private String filterField;
    private String filterValue;
    
    // 过滤结果
    private String purchasestart;
    private String purchaseend;
    private String soldstart;
    private String soldend;
    private String filterlicenseno;
    private String filtertradername;
    private String filtervehicletype;
    private String filtersettlement;
    
    // 列表
    private int rows;
    private int page;
    private String sort;    // 排序的字段
    private String order;   // 排序方式

    // 编辑界面用
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
    private String totalprofit;
    private String traderprofit;
    private String picturepath;
    private String isdeleted;
    private String issold;
    private String comments;
    private String interestcost;
    private String operation;
    private String buyerid;
    private String buyername;
    
    
	public String getFilterField() {
		return filterField;
	}
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public String getPurchasestart() {
		return purchasestart;
	}
	public void setPurchasestart(String purchasestart) {
		this.purchasestart = purchasestart;
	}
	public String getPurchaseend() {
		return purchaseend;
	}
	public void setPurchaseend(String purchaseend) {
		this.purchaseend = purchaseend;
	}
	public String getSoldstart() {
		return soldstart;
	}
	public void setSoldstart(String soldstart) {
		this.soldstart = soldstart;
	}
	public String getSoldend() {
		return soldend;
	}
	public void setSoldend(String soldend) {
		this.soldend = soldend;
	}
	public String getFilterlicenseno() {
		return filterlicenseno;
	}
	public void setFilterlicenseno(String filterlicenseno) {
		this.filterlicenseno = filterlicenseno;
	}
	public String getFiltertradername() {
		return filtertradername;
	}
	public void setFiltertradername(String filtertradername) {
		this.filtertradername = filtertradername;
	}
	public String getFiltervehicletype() {
		return filtervehicletype;
	}
	public void setFiltervehicletype(String filtervehicletype) {
		this.filtervehicletype = filtervehicletype;
	}
	public String getFiltersettlement() {
		return filtersettlement;
	}
	public void setFiltersettlement(String filtersettlement) {
		this.filtersettlement = filtersettlement;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	public String getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(String totalprofit) {
		this.totalprofit = totalprofit;
	}
	public String getTraderprofit() {
		return traderprofit;
	}
	public void setTraderprofit(String traderprofit) {
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
	public String getInterestcost() {
		return interestcost;
	}
	public void setInterestcost(String interestcost) {
		this.interestcost = interestcost;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
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
