package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class ParkingSummaryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6546080541517818988L;
	private int inStockCarsAmount;
    private int outStockCarsAmount;
    
    private double inStockTotalFee;
    private double outStockTotalFee;
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
	public double getInStockTotalFee() {
		return inStockTotalFee;
	}
	public void setInStockTotalFee(double inStockTotalFee) {
		this.inStockTotalFee = inStockTotalFee;
	}
	public double getOutStockTotalFee() {
		return outStockTotalFee;
	}
	public void setOutStockTotalFee(double outStockTotalFee) {
		this.outStockTotalFee = outStockTotalFee;
	}
    
    
    
}
