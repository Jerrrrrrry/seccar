package com.hongchen.sync.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hongchen.kis.domain.pojo.ICStockBillEntry;
import com.lhcy.kis.domain.pojo.VoucherEntry;

public class ExcelDataDto {

	private Map<String, ExcelBillDto> sumBills = new HashMap<String, ExcelBillDto>();
	
	private Map<String, String> billMapToDepart = new ConcurrentHashMap<String, String>();;

	private Map<String, VoucherEntry> sumPayTypes = new HashMap<String, VoucherEntry>();
	
	private Map<String, VoucherEntry> sumOrderDetailTypes = new HashMap<String, VoucherEntry>();
	
	private Map<String, List<ICStockBillEntry>> stockData = new HashMap<String, List<ICStockBillEntry>>();
	
	public Map<String, ExcelBillDto> getSumBills() {
		return sumBills;
	}

	public void setSumBills(Map<String, ExcelBillDto> sumBills) {
		this.sumBills = sumBills;
	}

	public Map<String, String> getBillMapToDepart() {
		return billMapToDepart;
	}

	public void setBillMapToDepart(Map<String, String> billMapToDepart) {
		this.billMapToDepart = billMapToDepart;
	}

	public Map<String, VoucherEntry> getSumPayTypes() {
		return sumPayTypes;
	}

	public void setSumPayTypes(Map<String, VoucherEntry> sumPayTypes) {
		this.sumPayTypes = sumPayTypes;
	}

	public Map<String, VoucherEntry> getSumOrderDetailTypes() {
		return sumOrderDetailTypes;
	}

	public void setSumOrderDetailTypes(
			Map<String, VoucherEntry> sumOrderDetailTypes) {
		this.sumOrderDetailTypes = sumOrderDetailTypes;
	}

	public Map<String, List<ICStockBillEntry>> getStockData() {
		return stockData;
	}

	public void setStockData(Map<String, List<ICStockBillEntry>> stockData) {
		this.stockData = stockData;
	}
	
}
