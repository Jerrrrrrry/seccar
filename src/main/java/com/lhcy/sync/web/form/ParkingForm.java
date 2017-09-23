package com.lhcy.sync.web.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ParkingForm extends ActionForm {

    // 过滤界面用
    private String filterField;
    private String filterValue;
    
    // 过滤结果
    private String filtercustomer;
    private String filterlicenseno;
    private String filtercardescription;
    private String filterinventoryints;
    private String filterinventoryoutts;
    
    // 列表
    private int rows;
    private int page;
    private String sort;    // 排序的字段
    private String order;   // 排序方式

    // 编辑界面用
    private String vehicleid;
    private String period;
    private String customer;
    private String cardescription;
    private String licenseno;
    private String inventoryints;
    private String inventoryoutts;
    private double parkingfee;
    private String comments;
    private String creator;
    private String customermobile;
    
    
	public String getCustomermobile() {
		return customermobile;
	}
	public void setCustomermobile(String customermobile) {
		this.customermobile = customermobile;
	}
	public String getFilterField() {
		return filterField;
	}
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	public String getFilterValue() {
		return filterValue;
	}
	
	public String getFiltercustomer() {
		return filtercustomer;
	}
	public void setFiltercustomer(String filtercustomer) {
		this.filtercustomer = filtercustomer;
	}
	public String getFilterlicenseno() {
		return filterlicenseno;
	}
	public void setFilterlicenseno(String filterlicenseno) {
		this.filterlicenseno = filterlicenseno;
	}
	public String getFiltercardescription() {
		return filtercardescription;
	}
	public void setFiltercardescription(String filtercardescription) {
		this.filtercardescription = filtercardescription;
	}
	public String getFilterinventoryints() {
		return filterinventoryints;
	}
	public void setFilterinventoryints(String filterinventoryints) {
		this.filterinventoryints = filterinventoryints;
	}
	public String getFilterinventoryoutts() {
		return filterinventoryoutts;
	}
	public void setFilterinventoryoutts(String filterinventoryoutts) {
		this.filterinventoryoutts = filterinventoryoutts;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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

}
