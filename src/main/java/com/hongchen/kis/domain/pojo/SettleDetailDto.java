package com.hongchen.kis.domain.pojo;

import java.io.Serializable;

public class SettleDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8234025027390340264L;

	public SettleDetailDto(String bizDate, String deptNum)
	{
		this.bizDate = bizDate;
		this.deptNum = deptNum;
		this.bizNumber = (bizDate+"+"+deptNum);
	}
	private String bizDate;
	private String deptNum;
    private String bizNumber;
    private String materialNumber;              // 品项编码
    private String materialName;                // 品项名称
    private double qty;                         // 数量
    private double price;                       // 单价
    private double amount;                      // 金额
    private int materialId;
    private int materialUnitID;
    private int stockId;
    private int deptId;
    
    
    public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getMaterialUnitID() {
		return materialUnitID;
	}

	public void setMaterialUnitID(int materialUnitID) {
		this.materialUnitID = materialUnitID;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
