package com.lhcy.kis.domain.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class VoucherEntry implements Serializable {

    private String brNo;                    // 公司代码
    private int voucherID;                  // ID
    private int entryID;                    // 分录ID
    private String explanation;             // 摘要
    private int accountID;                  // 科目ID
    private int accountID2;                 // 对方科目ID
    private int detailID;                   // 核算项目ID
    private int currencyID;                 // 币别ID
    private double exchangeRate;            // 汇率
    private int dc;                         // 余额方向 0-贷方,1-借方
    private BigDecimal amountFor;           // 原币金额
    private BigDecimal amount;              // 本位币金额
    private BigDecimal quantity;            // 数量
    private int measureUnitID;              // 计量单位ID
    private BigDecimal unitPrice;           // 单价
    private int settleTypeID;               // 结算方式ID
    private int cashFlowItem;               // 现金流量
    private int taskID;                     // 项目任务ID
    private int resourceID;                 // 项目资源ID
    private boolean isSum;
    private boolean isDel;

    public String getBrNo() {
        return brNo;
    }

    public void setBrNo(String brNo) {
        this.brNo = brNo;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getAccountID2() {
        return accountID2;
    }

    public void setAccountID2(int accountID2) {
        this.accountID2 = accountID2;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }

    public BigDecimal getAmountFor() {
        return amountFor;
    }

    public void setAmountFor(BigDecimal amountFor) {
        this.amountFor = amountFor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getMeasureUnitID() {
        return measureUnitID;
    }

    public void setMeasureUnitID(int measureUnitID) {
        this.measureUnitID = measureUnitID;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSettleTypeID() {
        return settleTypeID;
    }

    public void setSettleTypeID(int settleTypeID) {
        this.settleTypeID = settleTypeID;
    }

    public int getCashFlowItem() {
        return cashFlowItem;
    }

    public void setCashFlowItem(int cashFlowItem) {
        this.cashFlowItem = cashFlowItem;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
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
