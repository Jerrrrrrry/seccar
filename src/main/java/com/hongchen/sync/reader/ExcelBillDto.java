package com.hongchen.sync.reader;

import java.util.Date;

public class ExcelBillDto {
	 private String bizDate;
	 private String paySerialNum;
	 private double settleAmount;
	 private double discountAmount;
	 private double wipeTheZeroAmount;
	 private double quotaAmount;
	 private double giftAmount;

	 private String sellDateStr;
	 private Date sellDate;
	 private String departmentNumber;
	 private String departmentName;
	 private double serviceAmount;
	 private double roomsAmount;
	 
	 
//	public String getBizNum() {
//		return this.bizDate + "+" + departmentNumber;
//	}

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
	public double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getWipeTheZeroAmount() {
		return wipeTheZeroAmount;
	}
	public void setWipeTheZeroAmount(double wipeTheZeroAmount) {
		this.wipeTheZeroAmount = wipeTheZeroAmount;
	}
	public double getQuotaAmount() {
		return quotaAmount;
	}
	public void setQuotaAmount(double quotaAmount) {
		this.quotaAmount = quotaAmount;
	}
	public double getGiftAmount() {
		return giftAmount;
	}
	public void setGiftAmount(double giftAmount) {
		this.giftAmount = giftAmount;
	}
	public String getSellDateStr() {
		return sellDateStr;
	}
	public void setSellDateStr(String sellDateStr) {
		this.sellDateStr = sellDateStr;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public String getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public double getServiceAmount() {
		return serviceAmount;
	}
	public void setServiceAmount(double serviceAmount) {
		this.serviceAmount = serviceAmount;
	}
	public double getRoomsAmount() {
		return roomsAmount;
	}
	public void setRoomsAmount(double roomsAmount) {
		this.roomsAmount = roomsAmount;
	}
	 
	 public String toString()
	 {
		 return bizDate + " " + paySerialNum + " " + settleAmount + " " + discountAmount + " " + wipeTheZeroAmount + " " + quotaAmount + " " + giftAmount+ " " + sellDate + " " + departmentNumber + " " + departmentName+ " " + serviceAmount + " " + roomsAmount;
	 }

	public void add(ExcelBillDto dto) {
		this.settleAmount = this.settleAmount+dto.getSettleAmount();
		this.discountAmount = this.discountAmount+dto.getDiscountAmount();
		this.wipeTheZeroAmount = this.wipeTheZeroAmount + dto.getWipeTheZeroAmount();
		this.quotaAmount = this.quotaAmount  + dto.getQuotaAmount();
		this.giftAmount = this.giftAmount + dto.getGiftAmount();
		this.serviceAmount = this.serviceAmount + dto.getServiceAmount();
		this.roomsAmount = this.roomsAmount + dto.getRoomsAmount();
	}
}
