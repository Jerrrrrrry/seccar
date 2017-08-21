package com.lhcy.platform.web.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {

    // 过滤界面用
    private String filterField;
    private String filterValue;

    // 列表
    private int rows;
    private int page;
    private String sort;    // 排序的字段
    private String order;   // 排序方式

    // 编辑界面用
    private String id;
    private String userid;
    private String username;
    private String password;
    private String accesstype;
    private String islocked;
    private int creator;
//    private String createdts;
//    private String lastupdatedts;
    private String userdesc;
    private String comments;
    
    
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccesstype() {
		return accesstype;
	}
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	public String getIslocked() {
		return islocked;
	}
	public void setIslocked(String islocked) {
		this.islocked = islocked;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
//	public String getCreatedts() {
//		return createdts;
//	}
//	public void setCreatedts(String createdts) {
//		this.createdts = createdts;
//	}
//	public String getLastupdatedts() {
//		return lastupdatedts;
//	}
//	public void setLastupdatedts(String lastupdatedts) {
//		this.lastupdatedts = lastupdatedts;
//	}
	
	public String getUserdesc() {
		return userdesc;
	}
	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
    
}
