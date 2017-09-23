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
