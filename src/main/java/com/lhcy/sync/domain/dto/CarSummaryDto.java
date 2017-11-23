package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class CarSummaryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563360880780754475L;
	private int inStockCarsAmount;
	private double inStockCarMoney;
    private int outStockCarsAmount;
    private double outStockCarsMoney;
    private String carType;
    private boolean isSold;
    private double totalprofitself;
    private double totalprofitthird;
    private double loaninterestout;
    private double loaninterestin;
    private double tradeinterestoutthird;
    private double tradeinterestinthird;
    private double tradeinterestoutself;
    private double tradeinterestinself;
    private double totalSellPrice;
    private double totalPuchasePrice;
    private double totalProfit;
    private double accruedTotalProfit;
    
    private double previousMonthCost;
    private double currentMonthCost;
    private double accruedTotalCost;
    
    
    
	public double getAccruedTotalProfit() {
		return accruedTotalProfit;
	}
	public void setAccruedTotalProfit(double accruedTotalProfit) {
		this.accruedTotalProfit = accruedTotalProfit;
	}
	public double getPreviousMonthCost() {
		return previousMonthCost;
	}
	public void setPreviousMonthCost(double previousMonthCost) {
		this.previousMonthCost = previousMonthCost;
	}
	public double getCurrentMonthCost() {
		return currentMonthCost;
	}
	public void setCurrentMonthCost(double currentMonthCost) {
		this.currentMonthCost = currentMonthCost;
	}
	public double getAccruedTotalCost() {
		return accruedTotalCost;
	}
	public void setAccruedTotalCost(double accruedTotalCost) {
		this.accruedTotalCost = accruedTotalCost;
	}
	public double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public double getTotalSellPrice() {
		return totalSellPrice;
	}
	public void setTotalSellPrice(double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}
	public double getTotalPuchasePrice() {
		return totalPuchasePrice;
	}
	public void setTotalPuchasePrice(double totalPuchasePrice) {
		this.totalPuchasePrice = totalPuchasePrice;
	}
	public boolean isSold() {
		return isSold;
	}
	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public double getInStockCarMoney() {
		return inStockCarMoney;
	}
	public void setInStockCarMoney(double inStockCarMoney) {
		this.inStockCarMoney = inStockCarMoney;
	}

	
	public double getOutStockCarsMoney() {
		return outStockCarsMoney;
	}
	public void setOutStockCarsMoney(double outStockCarsMoney) {
		this.outStockCarsMoney = outStockCarsMoney;
	}
	public int getInStockCarsAmount() {
		return inStockCarsAmount;
	}
	public void setInStockCarsAmount(int inStockCarsAmount) {
		this.inStockCarsAmount = inStockCarsAmount;
	}
	public int getOutStockCarsAmount() {
		return outStockCarsAmount;
	}
	public void setOutStockCarsAmount(int outStockCarsAmount) {
		this.outStockCarsAmount = outStockCarsAmount;
	}
	public double getTotalprofitself() {
		return totalprofitself;
	}
	public void setTotalprofitself(double totalprofitself) {
		this.totalprofitself = totalprofitself;
	}
	public double getTotalprofitthird() {
		return totalprofitthird;
	}
	public void setTotalprofitthird(double totalprofitthird) {
		this.totalprofitthird = totalprofitthird;
	}
	public double getLoaninterestout() {
		return loaninterestout;
	}
	public void setLoaninterestout(double loaninterestout) {
		this.loaninterestout = loaninterestout;
	}
	public double getLoaninterestin() {
		return loaninterestin;
	}
	public void setLoaninterestin(double loaninterestin) {
		this.loaninterestin = loaninterestin;
	}
	public double getTradeinterestoutthird() {
		return tradeinterestoutthird;
	}
	public void setTradeinterestoutthird(double tradeinterestoutthird) {
		this.tradeinterestoutthird = tradeinterestoutthird;
	}
	public double getTradeinterestinthird() {
		return tradeinterestinthird;
	}
	public void setTradeinterestinthird(double tradeinterestinthird) {
		this.tradeinterestinthird = tradeinterestinthird;
	}
	public double getTradeinterestoutself() {
		return tradeinterestoutself;
	}
	public void setTradeinterestoutself(double tradeinterestoutself) {
		this.tradeinterestoutself = tradeinterestoutself;
	}
	public double getTradeinterestinself() {
		return tradeinterestinself;
	}
	public void setTradeinterestinself(double tradeinterestinself) {
		this.tradeinterestinself = tradeinterestinself;
	}


}
