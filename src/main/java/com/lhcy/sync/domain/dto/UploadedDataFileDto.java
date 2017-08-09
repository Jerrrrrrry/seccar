package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class UploadedDataFileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1758009899868427754L;
	private String id;
    private String fileName;        // 文件名
    private String uploadDate;          // 上传日期
    private String user;                 // 用户
    private String size;            // 大小
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	    
	 
}
