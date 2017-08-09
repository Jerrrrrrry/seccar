package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class VoucherTmpListDto implements Serializable {

    private String id;
    private String departmentNumber;        // 门店编码
    private String departmentName;          // 门店名称
    private String bizDate;                 // 销售日期
    private String settleAmount;            // 结算金额
    private String discountAmount;          // 优惠金额
    private String wipeTheZeroAmount;       // 抹零金额
    private String quotaAmount;             // 定额优惠
    private String giftAmount;              // 赠单金额
    private String serviceAmount;           // 服务费
    private String roomsAmount;             // 包房费
    private String debitAmount;             // 借方金额
    private String creditAmount;            // 贷方金额
    private String diffAmount;              // 借贷差额
    private String voucherNumber;           // 凭证号
    private String syncUser;                // 同步人
    private String syncTime;                // 同部时间
    private String status;                  // 同步状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getWipeTheZeroAmount() {
        return wipeTheZeroAmount;
    }

    public void setWipeTheZeroAmount(String wipeTheZeroAmount) {
        this.wipeTheZeroAmount = wipeTheZeroAmount;
    }

    public String getQuotaAmount() {
        return quotaAmount;
    }

    public void setQuotaAmount(String quotaAmount) {
        this.quotaAmount = quotaAmount;
    }

    public String getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(String giftAmount) {
        this.giftAmount = giftAmount;
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

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(String roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(String diffAmount) {
        this.diffAmount = diffAmount;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
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
