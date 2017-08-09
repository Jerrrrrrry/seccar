package com.lhcy.sync.domain.dto;

import java.io.Serializable;

public class LoanDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 161151567152150153L;

	private String id;
    
        private String seqNum;
    
      private String bizDate;
    
        private String fullName;
     
        private String carName;
    
        private String loanPeriod;
    
        private String netLoanAmount;
    
        private String paymentPerMonth;
    
        private String interestRate;
         private String paybackRate;
        private String parkingFee;
         private String others;
       
        private String arrcuredPayAmount;
        private String comments;
         private String allInterestAmount;
         private String temperaryMaterils;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSeqNum() {
			return seqNum;
		}
		public void setSeqNum(String seqNum) {
			this.seqNum = seqNum;
		}
		public String getBizDate() {
			return bizDate;
		}
		public void setBizDate(String bizDate) {
			this.bizDate = bizDate;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getCarName() {
			return carName;
		}
		public void setCarName(String carName) {
			this.carName = carName;
		}
		public String getLoanPeriod() {
			return loanPeriod;
		}
		public void setLoanPeriod(String loanPeriod) {
			this.loanPeriod = loanPeriod;
		}
		public String getNetLoanAmount() {
			return netLoanAmount;
		}
		public void setNetLoanAmount(String netLoanAmount) {
			this.netLoanAmount = netLoanAmount;
		}
		public String getPaymentPerMonth() {
			return paymentPerMonth;
		}
		public void setPaymentPerMonth(String paymentPerMonth) {
			this.paymentPerMonth = paymentPerMonth;
		}
		public String getInterestRate() {
			return interestRate;
		}
		public void setInterestRate(String interestRate) {
			this.interestRate = interestRate;
		}
		public String getPaybackRate() {
			return paybackRate;
		}
		public void setPaybackRate(String paybackRate) {
			this.paybackRate = paybackRate;
		}
		public String getParkingFee() {
			return parkingFee;
		}
		public void setParkingFee(String parkingFee) {
			this.parkingFee = parkingFee;
		}
		public String getOthers() {
			return others;
		}
		public void setOthers(String others) {
			this.others = others;
		}
		public String getArrcuredPayAmount() {
			return arrcuredPayAmount;
		}
		public void setArrcuredPayAmount(String arrcuredPayAmount) {
			this.arrcuredPayAmount = arrcuredPayAmount;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public String getAllInterestAmount() {
			return allInterestAmount;
		}
		public void setAllInterestAmount(String allInterestAmount) {
			this.allInterestAmount = allInterestAmount;
		}
		public String getTemperaryMaterils() {
			return temperaryMaterils;
		}
		public void setTemperaryMaterils(String temperaryMaterils) {
			this.temperaryMaterils = temperaryMaterils;
		}
       
}
