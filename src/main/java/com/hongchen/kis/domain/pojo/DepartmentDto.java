package com.hongchen.kis.domain.pojo;

import java.io.Serializable;

public class DepartmentDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8036004712257881568L;
	private int id;
    private String number;
    private String name;
    private String targetNumber;

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
}
