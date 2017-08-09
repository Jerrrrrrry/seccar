package com.lhcy.sync.web.form;

import org.apache.struts.action.ActionForm;

public class ICStockForm extends ActionForm {

    // 过滤界面用
    private String bizDateFrom;
    private String bizDateTo;
    private String departmentNumber;
    private String departmentName;

    // 列表
    private int rows;
    private int page;
    private String sort;    // 排序的字段
    private String order;   // 排序方式
    private String id;

    public String getBizDateFrom() {
        return bizDateFrom;
    }

    public void setBizDateFrom(String bizDateFrom) {
        this.bizDateFrom = bizDateFrom;
    }

    public String getBizDateTo() {
        return bizDateTo;
    }

    public void setBizDateTo(String bizDateTo) {
        this.bizDateTo = bizDateTo;
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
