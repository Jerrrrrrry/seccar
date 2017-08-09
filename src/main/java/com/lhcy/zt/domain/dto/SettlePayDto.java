package com.lhcy.zt.domain.dto;

import java.io.Serializable;

public class SettlePayDto implements Serializable {

    private String bizNumber;
    private String settleNumber;                // 结算方式编码
    private String settleName;                  // 结算方式名称
    private double amount;                      // 计算金额

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }

    public String getSettleNumber() {
        return settleNumber;
    }

    public void setSettleNumber(String settleNumber) {
        this.settleNumber = settleNumber;
    }

    public String getSettleName() {
        return settleName;
    }

    public void setSettleName(String settleName) {
        this.settleName = settleName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
