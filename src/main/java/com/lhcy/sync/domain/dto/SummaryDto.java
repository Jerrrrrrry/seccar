package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class SummaryDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1035423497491997258L;
	private String category;
	private String itemType;
    private String details;
    private String carDesc;
    private String instockDate;
    private String actualPaiedToLoaner;
    
	public String getCarDesc() {
		return carDesc;
	}
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}
	public String getInstockDate() {
		return instockDate;
	}
	public void setInstockDate(String instockDate) {
		this.instockDate = instockDate;
	}
	public String getActualPaiedToLoaner() {
		return actualPaiedToLoaner;
	}
	public void setActualPaiedToLoaner(String actualPaiedToLoaner) {
		this.actualPaiedToLoaner = actualPaiedToLoaner;
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
    
    
}
