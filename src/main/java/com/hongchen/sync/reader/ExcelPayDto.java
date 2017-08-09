package com.hongchen.sync.reader;


public class ExcelPayDto {
	private String bizNum;
	private String bizDate;
	private String paySerialNum;
	private String payTypeCode;
	private String payTypeName;
	private double settleAmount;
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
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	public void add(ExcelPayDto dto) {
		this.settleAmount = this.settleAmount+dto.getSettleAmount();
	}
	public String getSumKey()
	{
		return this.bizNum + "+" + this.payTypeCode;
	}
	
}
