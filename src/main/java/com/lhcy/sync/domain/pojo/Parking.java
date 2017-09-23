package com.lhcy.sync.domain.pojo;

import java.io.Serializable;
import java.util.Date;

public class Parking implements Serializable {
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
    private Date createdts;
    private Date lastupdatedts;
    private String creator;
    private String customermobile;
    
    
	public String getCustomermobile() {
		return customermobile;
	}
	public void setCustomermobile(String customermobile) {
		this.customermobile = customermobile;
	}
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
	public Date getCreatedts() {
		return createdts;
	}
	public void setCreatedts(Date createdts) {
		this.createdts = createdts;
	}
	public Date getLastupdatedts() {
		return lastupdatedts;
	}
	public void setLastupdatedts(Date lastupdatedts) {
		this.lastupdatedts = lastupdatedts;
	}
    
}
