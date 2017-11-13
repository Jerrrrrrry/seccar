package com.hongchen.kis.domain.pojo;

import java.util.Date;

public class ICStockBill {

    private String brNo;
    private int interID;
    private int tranType;                   // 单据类型
    private Date date;                      // 单据日期
    private String billNo;                  // 单据编号
    private int deptID;                     // 部门ID
    private int empID;                      // 业务员ID
    private int supplyID;                   // 供应商ID
    private int fManagerID;                 // 验收人ID
    private int sManagerID;                 // 保管人ID
    private int billerID;                   // 制单人ID
    private int vchInterID;                 // 凭证ID
    private int saleStyle;                  // 售方
    private int relateBrID;                 // 订货机构
    private String explanation;             // 摘要
    private int brID;                       // 制单机构
    private String poOrdBillNo;             // 对方单据
    private Date settleDate;                // 收款日期
    private int manageType;                 // 税管类型
    private int consignee;                  // 收货
    private int wlCompany;                  // 物流公司

    public String getBrNo() {
        return brNo;
    }

    public void setBrNo(String brNo) {
        this.brNo = brNo;
    }

    public int getInterID() {
        return interID;
    }

    public void setInterID(int interID) {
        this.interID = interID;
    }

    public int getTranType() {
        return tranType;
    }

    public void setTranType(int tranType) {
        this.tranType = tranType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(int supplyID) {
        this.supplyID = supplyID;
    }

    public int getfManagerID() {
        return fManagerID;
    }

    public void setfManagerID(int fManagerID) {
        this.fManagerID = fManagerID;
    }

    public int getsManagerID() {
        return sManagerID;
    }

    public void setsManagerID(int sManagerID) {
        this.sManagerID = sManagerID;
    }

    public int getBillerID() {
        return billerID;
    }

    public void setBillerID(int billerID) {
        this.billerID = billerID;
    }

    public int getVchInterID() {
        return vchInterID;
    }

    public void setVchInterID(int vchInterID) {
        this.vchInterID = vchInterID;
    }

    public int getSaleStyle() {
        return saleStyle;
    }

    public void setSaleStyle(int saleStyle) {
        this.saleStyle = saleStyle;
    }

    public int getRelateBrID() {
        return relateBrID;
    }

    public void setRelateBrID(int relateBrID) {
        this.relateBrID = relateBrID;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getBrID() {
        return brID;
    }

    public void setBrID(int brID) {
        this.brID = brID;
    }

    public String getPoOrdBillNo() {
        return poOrdBillNo;
    }

    public void setPoOrdBillNo(String poOrdBillNo) {
        this.poOrdBillNo = poOrdBillNo;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public int getManageType() {
        return manageType;
    }

    public void setManageType(int manageType) {
        this.manageType = manageType;
    }

    public int getConsignee() {
        return consignee;
    }

    public void setConsignee(int consignee) {
        this.consignee = consignee;
    }

    public int getWlCompany() {
        return wlCompany;
    }

    public void setWlCompany(int wlCompany) {
        this.wlCompany = wlCompany;
    }
}
