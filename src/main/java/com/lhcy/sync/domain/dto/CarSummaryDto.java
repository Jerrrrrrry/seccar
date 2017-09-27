package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class CarSummaryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563360880780754475L;
	private int inStockCarsAmount;
    private int outStockCarsAmount;
    private double totalprofitself;
    private double totalprofitthird;
    private double loaninterestout;
    private double loaninterestin;
    private double tradeinterestoutthird;
    private double tradeinterestinthird;
    private double tradeinterestoutself;
    private double tradeinterestinself;
    
    
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
