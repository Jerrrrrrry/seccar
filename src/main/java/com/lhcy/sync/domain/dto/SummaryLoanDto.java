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
    private int year;
    private int month;
    private int sumTotalNewCarLoanAmount;
    private double sumTotalLoan;
    private double sumTotalActualLoan;
    private int sumTotalReturnedCarLoanAmount;
    private double sumTotalPaidInterest;
    private double sumTotalProxyInterest;
    private double sumTotalReturnedPrincipalLoan;
    private double sumTotalReturn;
    private double sumTotalMidinterest;
    
    public double getSumTotalMidinterest() {
		return sumTotalMidinterest;
	}
	public void setSumTotalMidinterest(double sumTotalMidinterest) {
		this.sumTotalMidinterest = sumTotalMidinterest;
	}
	public double getSumTotalReturn() {
		return sumTotalReturn;
	}
	public void setSumTotalReturn(double sumTotalReturn) {
		this.sumTotalReturn = sumTotalReturn;
	}
	private int totalOutStock;
    
	public int getTotalOutStock() {
		return totalOutStock;
	}
	public void setTotalOutStock(int totalOutStock) {
		this.totalOutStock = totalOutStock;
	}
	public int getSumTotalNewCarLoanAmount() {
		return sumTotalNewCarLoanAmount;
	}
	public void setSumTotalNewCarLoanAmount(int sumTotalNewCarLoanAmount) {
		this.sumTotalNewCarLoanAmount = sumTotalNewCarLoanAmount;
	}
	public double getSumTotalLoan() {
		return sumTotalLoan;
	}
	public void setSumTotalLoan(double sumTotalLoan) {
		this.sumTotalLoan = sumTotalLoan;
	}
	public double getSumTotalActualLoan() {
		return sumTotalActualLoan;
	}
	public void setSumTotalActualLoan(double sumTotalActualLoan) {
		this.sumTotalActualLoan = sumTotalActualLoan;
	}
	public int getSumTotalReturnedCarLoanAmount() {
		return sumTotalReturnedCarLoanAmount;
	}
	public void setSumTotalReturnedCarLoanAmount(int sumTotalReturnedCarLoanAmount) {
		this.sumTotalReturnedCarLoanAmount = sumTotalReturnedCarLoanAmount;
	}
	public double getSumTotalPaidInterest() {
		return sumTotalPaidInterest;
	}
	public void setSumTotalPaidInterest(double sumTotalPaidInterest) {
		this.sumTotalPaidInterest = sumTotalPaidInterest;
	}
	public double getSumTotalProxyInterest() {
		return sumTotalProxyInterest;
	}
	public void setSumTotalProxyInterest(double sumTotalProxyInterest) {
		this.sumTotalProxyInterest = sumTotalProxyInterest;
	}
	public double getSumTotalReturnedPrincipalLoan() {
		return sumTotalReturnedPrincipalLoan;
	}
	public void setSumTotalReturnedPrincipalLoan(
			double sumTotalReturnedPrincipalLoan) {
		this.sumTotalReturnedPrincipalLoan = sumTotalReturnedPrincipalLoan;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
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
