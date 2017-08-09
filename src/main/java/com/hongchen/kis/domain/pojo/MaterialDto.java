package com.hongchen.kis.domain.pojo;

import java.io.Serializable;

public class MaterialDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8823188308066624086L;
	private int id;
    private String number;
    private String name;
    private String targetNumber;
    private int unitID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
