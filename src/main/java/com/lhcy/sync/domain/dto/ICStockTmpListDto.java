package com.lhcy.sync.domain.dto;

public class ICStockTmpListDto {

    private String id;
    private String bizDate;                 // 销售日期
    private String departmentNumber;        // 门店编码
    private String departmentName;          // 门店名称
    private String beerAmount;              // 啤酒
    private String spiritAmount;            // 白酒
    private String juiceAmount;             // 饮料
    private String totalAmount;             // 合计
    private String billNumber;              // 单据编号
    private String syncUser;                // 同步人
    private String syncTime;                // 同部时间
    private String status;                  // 同步状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
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

    public String getBeerAmount() {
        return beerAmount;
    }

    public void setBeerAmount(String beerAmount) {
        this.beerAmount = beerAmount;
    }

    public String getSpiritAmount() {
        return spiritAmount;
    }

    public void setSpiritAmount(String spiritAmount) {
        this.spiritAmount = spiritAmount;
    }

    public String getJuiceAmount() {
        return juiceAmount;
    }

    public void setJuiceAmount(String juiceAmount) {
        this.juiceAmount = juiceAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getSyncUser() {
        return syncUser;
    }

    public void setSyncUser(String syncUser) {
        this.syncUser = syncUser;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
