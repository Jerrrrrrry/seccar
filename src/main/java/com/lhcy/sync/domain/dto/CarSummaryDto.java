package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class CarSummaryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563360880780754475L;
	private int inStockCarsAmount;
    private int outStockCarsAmount;
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
    
    

}
