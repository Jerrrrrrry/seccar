package com.lhcy.kis.domain.pojo;

import java.io.Serializable;
import java.util.Date;

public class Voucher implements Serializable {

    private String brNo;                    // 公司代码
    private int voucherID;                  // ID
    private Date date;                      // 凭证日期
    private int year;                       // 会计年度
    private int period;                     // 会计期间
    private int groupID;                    // 凭证字内码
    private int number;                     // 凭证号
    private String explanation;             // 备注
    private int attachments;                // 附件数
    private int entryCount;                 // 分录数
    private double debitTotal;              // 借方总金额
    private double creditTotal;             // 贷方总金额
    private int checked;                    // 是否审核
    private int posted;                     // 是否过账
    private int preparerID;                 // 制单人ID
    private int checkerID;                  // 审核人ID
    private int posterID;                   // 记账人ID
    private int cashierID;                  // 出纳人ID
    private int ownerGroupID;               // 制单人所属工作组ID
    private int serialNum;                  // 凭证序号
    private int tranType;                   // 单据类型
    private Date transDate;                 // 业务日期
    private int frameWorkID;                // 集团组织机构ID
    private int approveID;                  // 审批人ID
    private String footNote;                // 批注

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getAttachments() {
        return attachments;
    }

    public void setAttachments(int attachments) {
        this.attachments = attachments;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public double getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(double debitTotal) {
        this.debitTotal = debitTotal;
    }

    public double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(double creditTotal) {
        this.creditTotal = creditTotal;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getPosted() {
        return posted;
    }

    public void setPosted(int posted) {
        this.posted = posted;
    }

    public int getPreparerID() {
        return preparerID;
    }

    public void setPreparerID(int preparerID) {
        this.preparerID = preparerID;
    }

    public int getCheckerID() {
        return checkerID;
    }

    public void setCheckerID(int checkerID) {
        this.checkerID = checkerID;
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }

    public int getOwnerGroupID() {
        return ownerGroupID;
    }

    public void setOwnerGroupID(int ownerGroupID) {
        this.ownerGroupID = ownerGroupID;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getTranType() {
        return tranType;
    }

    public void setTranType(int tranType) {
        this.tranType = tranType;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public int getFrameWorkID() {
        return frameWorkID;
    }

    public void setFrameWorkID(int frameWorkID) {
        this.frameWorkID = frameWorkID;
    }

    public int getApproveID() {
        return approveID;
    }

    public void setApproveID(int approveID) {
        this.approveID = approveID;
    }

    public String getFootNote() {
        return footNote;
    }

    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }
}
