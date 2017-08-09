package com.lhcy.kis.domain.pojo;

public class ICStockBillEntry {

    private String brNo;
    private int interID;
    private int entryID;
    private int itemID;
    private double qty;                 // 实际数量
    private int unitID;                 // 计量单位
    private double auxQty;              // 数量
    private int dcSPID;                 // 目标仓位
    private double consignPrice;        // 代销单价
    private double consignAmount;       // 代销金额
    private int snListID;               // 序列号外键
    private int dcStockID;              // 调入仓库
    private boolean isSum;
    private boolean isDel;

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

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public double getAuxQty() {
        return auxQty;
    }

    public void setAuxQty(double auxQty) {
        this.auxQty = auxQty;
    }

    public int getDcSPID() {
        return dcSPID;
    }

    public void setDcSPID(int dcSPID) {
        this.dcSPID = dcSPID;
    }

    public double getConsignPrice() {
        return consignPrice;
    }

    public void setConsignPrice(double consignPrice) {
        this.consignPrice = consignPrice;
    }

    public double getConsignAmount() {
        return consignAmount;
    }

    public void setConsignAmount(double consignAmount) {
        this.consignAmount = consignAmount;
    }

    public int getSnListID() {
        return snListID;
    }

    public void setSnListID(int snListID) {
        this.snListID = snListID;
    }

    public int getDcStockID() {
        return dcStockID;
    }

    public void setDcStockID(int dcStockID) {
        this.dcStockID = dcStockID;
    }

    public boolean isSum() {
        return isSum;
    }

    public void setIsSum(boolean isSum) {
        this.isSum = isSum;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setIsDel(boolean isDel) {
        this.isDel = isDel;
    }
}
