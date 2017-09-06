package com.lhcy.sync.web.form;

import org.apache.struts.action.ActionForm;

public class LoanForm extends ActionForm {

    // 过滤界面用
    private String filterField;
    private String filterValue;
    
    // 过滤结果
    private String loanstart;
    private String loanend;
    private String returnstart;
    private String returnend;
    private String filterlicenseno;
    private String filtercardescription;
    private String filtercustomer;
    private String filterisdeleted;
    private String filterisreturned;
    private String filterisabandon;
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
    private String operation;
    
    
	public String getFiltercustomer() {
		return filtercustomer;
	}
	public void setFiltercustomer(String filtercustomer) {
		this.filtercustomer = filtercustomer;
	}
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
	
	public String getLoanstart() {
		return loanstart;
	}
	public void setLoanstart(String loanstart) {
		this.loanstart = loanstart;
	}
	public String getLoanend() {
		return loanend;
	}
	public void setLoanend(String loanend) {
		this.loanend = loanend;
	}
	public String getReturnstart() {
		return returnstart;
	}
	public void setReturnstart(String returnstart) {
		this.returnstart = returnstart;
	}
	public String getReturnend() {
		return returnend;
	}
	public void setReturnend(String returnend) {
		this.returnend = returnend;
	}
	public String getFilterlicenseno() {
		return filterlicenseno;
	}
	public void setFilterlicenseno(String filterlicenseno) {
		this.filterlicenseno = filterlicenseno;
	}
	public String getFiltercardescription() {
		return filtercardescription;
	}
	public void setFiltercardescription(String filtercardescription) {
		this.filtercardescription = filtercardescription;
	}
	public String getFilterisdeleted() {
		return filterisdeleted;
	}
	public void setFilterisdeleted(String filterisdeleted) {
		this.filterisdeleted = filterisdeleted;
	}
	public String getFilterisreturned() {
		return filterisreturned;
	}
	public void setFilterisreturned(String filterisreturned) {
		this.filterisreturned = filterisreturned;
	}
	public String getFilterisabandon() {
		return filterisabandon;
	}
	public void setFilterisabandon(String filterisabandon) {
		this.filterisabandon = filterisabandon;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
    
    
	
}
