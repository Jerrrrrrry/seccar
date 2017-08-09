package com.lhcy.core.bo;

import java.io.Serializable;

public class HttpActionResult implements Serializable {

    private String status;
    private String message;
    private Object dto;

    public HttpActionResult(String status, String message, Object dto){
        this.status = status;
        this.message = message;
        this.dto = dto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }
}
