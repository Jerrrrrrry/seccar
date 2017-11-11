package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class SummaryLoanDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1035423497491997258L;
	private String category;
	private String itemType;
    private String isreturned;
    private String isabandon;
    private String borrowamount;
    private String parkingfee;
    private String othercost;
    private String interestpaid;
    private String midinterest;
    private String midinterestrate;
    private String actualreturn;
    private String totalprofit;
    private String details;
    private String settlement;
    private String totalinterest;
    private String actualloan;
    
	public String getTotalinterest() {
		return totalinterest;
	}
	public void setTotalinterest(String totalinterest) {
		this.totalinterest = totalinterest;
	}
	public String getActualloan() {
		return actualloan;
	}
	public void setActualloan(String actualloan) {
		this.actualloan = actualloan;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
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
	public String getBorrowamount() {
		return borrowamount;
	}
	public void setBorrowamount(String borrowamount) {
		this.borrowamount = borrowamount;
	}
	public String getParkingfee() {
		return parkingfee;
	}
	public void setParkingfee(String parkingfee) {
		this.parkingfee = parkingfee;
	}
	public String getOthercost() {
		return othercost;
	}
	public void setOthercost(String othercost) {
		this.othercost = othercost;
	}
	public String getInterestpaid() {
		return interestpaid;
	}
	public void setInterestpaid(String interestpaid) {
		this.interestpaid = interestpaid;
	}
	public String getMidinterest() {
		return midinterest;
	}
	public void setMidinterest(String midinterest) {
		this.midinterest = midinterest;
	}
	public String getActualreturn() {
		return actualreturn;
	}
	public void setActualreturn(String actualreturn) {
		this.actualreturn = actualreturn;
	}
	public String getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(String totalprofit) {
		this.totalprofit = totalprofit;
	}
	public String getMidinterestrate() {
		return midinterestrate;
	}
	public void setMidinterestrate(String midinterestrate) {
		this.midinterestrate = midinterestrate;
	}
    
    
}
