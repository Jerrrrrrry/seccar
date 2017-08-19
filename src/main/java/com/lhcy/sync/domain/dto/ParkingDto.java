package com.lhcy.sync.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkingDto implements Serializable {
	/**
	 * 
	 */
	private String vehicleid;
    private String period;
    private String customer;
    private String cardescription;
    private String licenseno;
    private String inventoryints;
    private String inventoryoutts;
    private double parkingfee;
    private String comments;
    private String createdts;
    private String lastupdatedts;
    private String creator;
    
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCardescription() {
		return cardescription;
	}
	public void setCardescription(String cardescription) {
		this.cardescription = cardescription;
	}
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	public double getParkingfee() {
		return parkingfee;
	}
	public void setParkingfee(double parkingfee) {
		this.parkingfee = parkingfee;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getInventoryints() {
		return inventoryints;
	}
	public void setInventoryints(String inventoryints) {
		this.inventoryints = inventoryints;
	}
	public String getInventoryoutts() {
		return inventoryoutts;
	}
	public void setInventoryoutts(String inventoryoutts) {
		this.inventoryoutts = inventoryoutts;
	}
	public String getCreatedts() {
		return createdts;
	}
	public void setCreatedts(String createdts) {
		this.createdts = createdts;
	}
	public String getLastupdatedts() {
		return lastupdatedts;
	}
	public void setLastupdatedts(String lastupdatedts) {
		this.lastupdatedts = lastupdatedts;
	}
    
}
