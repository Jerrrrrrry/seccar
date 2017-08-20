package com.lhcy.platform.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {
	private String userid;
    private String username;
    private String password;
    private String accesstype;
    private String islocked;
    private int creator;
    private String createdts;
    private String lastupdatedts;
    private String userdesc;
    private String comments;
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
