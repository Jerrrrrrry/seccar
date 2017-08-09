package com.lhcy.sync.domain.dto;

public class VoucherTempDto {

    private String bizNumber;
    private String bizDate;                 // 销售日期
    private String departmentNumber;        // 门店编码
    private String departmentName;          // 门店名称
    private double settleAmount;            // 结算金额
    private double discountAmount;          // 优惠金额
    private double wipeTheZeroAmount;       // 抹零金额
    private double quotaAmount;             // 定额优惠
    private double giftAmount;              // 赠单金额
    private double serviceAmount;           // 服务费
    private double roomsAmount;             // 包房费
    private double debitAmount;             // 借方总金额
    private double creditAmount;            // 贷方总金额
    private double diffAmount;              // 借贷差额

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
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

    public double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getWipeTheZeroAmount() {
        return wipeTheZeroAmount;
    }

    public void setWipeTheZeroAmount(double wipeTheZeroAmount) {
        this.wipeTheZeroAmount = wipeTheZeroAmount;
    }

    public double getQuotaAmount() {
        return quotaAmount;
    }

    public void setQuotaAmount(double quotaAmount) {
        this.quotaAmount = quotaAmount;
    }

    public double getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(double giftAmount) {
        this.giftAmount = giftAmount;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public double getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(double roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(double diffAmount) {
        this.diffAmount = diffAmount;
    }
}
