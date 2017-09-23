package com.lhcy.sync.web.form;

import org.apache.struts.action.ActionForm;

public class SumSummaryForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3461096808243845722L;
	private String filterField;
    private String filterValue;
    
    private String filterisdeleted;
    private String filterissold;
    private String filtersettlement;
    
    private String filterlicenseno;
    private String filtercardescription;
    private String filtertradername;
    private String filtercustomer;
    private String filtervehicletype;
    
    private String purchasestart;
    private String purchaseend;
    private String soldstart;
    private String soldend;
	public String getFilterField() {
		return filterField;
	}
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public String getFilterisdeleted() {
		return filterisdeleted;
	}
	public void setFilterisdeleted(String filterisdeleted) {
		this.filterisdeleted = filterisdeleted;
	}
	public String getFilterissold() {
		return filterissold;
	}
	public void setFilterissold(String filterissold) {
		this.filterissold = filterissold;
	}
	public String getFiltersettlement() {
		return filtersettlement;
	}
	public void setFiltersettlement(String filtersettlement) {
		this.filtersettlement = filtersettlement;
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
	public String getFiltertradername() {
		return filtertradername;
	}
	public void setFiltertradername(String filtertradername) {
		this.filtertradername = filtertradername;
	}
	public String getFiltercustomer() {
		return filtercustomer;
	}
	public void setFiltercustomer(String filtercustomer) {
		this.filtercustomer = filtercustomer;
	}
	public String getFiltervehicletype() {
		return filtervehicletype;
	}
	public void setFiltervehicletype(String filtervehicletype) {
		this.filtervehicletype = filtervehicletype;
	}
	public String getPurchasestart() {
		return purchasestart;
	}
	public void setPurchasestart(String purchasestart) {
		this.purchasestart = purchasestart;
	}
	public String getPurchaseend() {
		return purchaseend;
	}
	public void setPurchaseend(String purchaseend) {
		this.purchaseend = purchaseend;
	}
	public String getSoldstart() {
		return soldstart;
	}
	public void setSoldstart(String soldstart) {
		this.soldstart = soldstart;
	}
	public String getSoldend() {
		return soldend;
	}
	public void setSoldend(String soldend) {
		this.soldend = soldend;
	}

}
