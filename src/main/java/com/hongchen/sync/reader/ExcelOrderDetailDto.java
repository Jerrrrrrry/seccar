package com.hongchen.sync.reader;


public class ExcelOrderDetailDto {
	private String bizNum;
	private String bizDate;
	private String paySerialNum;
	private String itemCategory;
	private String itemCategoryName;
	private double settleAmount;
	private double quantity;
	private double price;
	public String getBizNum() {
		return bizNum;
	}
	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}
	
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	public String getPaySerialNum() {
		return paySerialNum;
	}
	public void setPaySerialNum(String paySerialNum) {
		this.paySerialNum = paySerialNum;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	public void add(ExcelOrderDetailDto dto) {
		this.quantity = this.quantity + dto.getQuantity();
		this.settleAmount = this.settleAmount+dto.getSettleAmount();
	}
	public String getSumKey()
	{
		return this.bizNum + "+" + this.itemCategory;
	}
	
}
