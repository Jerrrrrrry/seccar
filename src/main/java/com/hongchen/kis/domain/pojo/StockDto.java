package com.hongchen.kis.domain.pojo;

import java.io.Serializable;

public class StockDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6080291454184016709L;
	private int id;
    private String number;
    private String name;

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

}
