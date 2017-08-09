package com.lhcy.core.bo;

public class EntryTotal {

    private String qty;
    private String amount;
    private String tag;

    public EntryTotal(){

        this.qty = "0";
        this.amount = "¥0";
        this.tag = "";
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

