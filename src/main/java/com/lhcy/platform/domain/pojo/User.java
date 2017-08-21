package com.lhcy.platform.domain.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private String id;
    private String userid;
    private String username;
    private String password;
    private String accesstype;
    private String islocked;
    private int creator;
    private Date createdts;
    private Date lastupdatedts;
    private String userdesc;
    private String comments;
    private String sbcol1;
    private String sbcol2;
    private String sbcol3;
    
    
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
	public String getSbcol1() {
		return sbcol1;
	}
	public void setSbcol1(String sbcol1) {
		this.sbcol1 = sbcol1;
	}
	public String getSbcol2() {
		return sbcol2;
	}
	public void setSbcol2(String sbcol2) {
		this.sbcol2 = sbcol2;
	}
	public String getSbcol3() {
		return sbcol3;
	}
	public void setSbcol3(String sbcol3) {
		this.sbcol3 = sbcol3;
	}

}
