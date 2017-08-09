package com.lhcy.zt.domain.dto;

import java.io.Serializable;

public class SettleDetailClassDto implements Serializable {
    
    private String bizNumber;
    private String materialClass1Number;        // 品项大类编码
    private String materialClass1Name;          // 品项大类名称
    private double qty;                         // 数量
    private double amount;                      // 金额

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }

    public String getMaterialClass1Number() {
        return materialClass1Number;
    }

    public void setMaterialClass1Number(String materialClass1Number) {
        this.materialClass1Number = materialClass1Number;
    }

    public String getMaterialClass1Name() {
        return materialClass1Name;
    }

    public void setMaterialClass1Name(String materialClass1Name) {
        this.materialClass1Name = materialClass1Name;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
