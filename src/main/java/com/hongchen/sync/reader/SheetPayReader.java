package com.hongchen.sync.reader;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.kis.domain.pojo.VoucherEntry;


public class SheetPayReader {

	private XSSFSheet xssfSheet;
	private String fileType;
	private HSSFSheet hssfSheet;
//	private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
	private ExcelDataDto result;
	
	public SheetPayReader(String fileType, XSSFSheet xssfSheet, HSSFSheet hssfSheet, ExcelDataDto result)
	{
		this.fileType = fileType;
		this.xssfSheet = xssfSheet;
		this.hssfSheet = hssfSheet;
		this.result = result;
	}
	
	public void read() throws Exception {
		Map<String, VoucherEntry> sumPayTypes = result.getSumPayTypes();
		 Map<String, String> billMapToDepart = result.getBillMapToDepart();
		if ("xlsx".equalsIgnoreCase(fileType))
		{
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	        	if (xssfRow == null) {
	                continue;
	            }
	        	//dto.setBizDate(xssfRow.getCell(0).getStringCellValue());
	            String paySerialNum = xssfRow.getCell(3).getStringCellValue();
	            String departId = null;
	            try
	            {
	            	departId = billMapToDepart.get(paySerialNum);
	            } catch (Exception e)
	            {
	            	String errorMessage = "结算明细页面(第"+rowNum+"行),结算识别码 ("+paySerialNum + ") 在结账流水中不能找到，请检查文件统一性";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	            }
	            String payTypeCode = xssfRow.getCell(6).getStringCellValue();
	            String payTypeName = xssfRow.getCell(7).getStringCellValue();
	            int accountId;
	            try{
	            	accountId = DbSqlHelper.accountsMap.get(payTypeCode);
	               } catch (NullPointerException e)
	               {
	              	 String errorMessage = "结算明细页面(第"+rowNum+"行),"+payTypeName + " " + payTypeCode + " 在KIS科目中没有维护！";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	               }
	            
	            BigDecimal amount = new BigDecimal(xssfRow.getCell(8).getNumericCellValue());
//	            String sellDateStr = xssfRow.getCell(7).getStringCellValue();
//	            Date sellDate = myFmt2.parse(sellDateStr);
//	            dto.setSellDate(sellDate);
	            int dc = 1;
	           String key = departId + "_" + accountId + "_" + dc;
	           if (sumPayTypes.containsKey(key)){
	        	   VoucherEntry vo =  sumPayTypes.get(key);
	        	   vo.setAmount(vo.getAmount().add(amount));
	           } else {
	        	   VoucherEntry vo = new VoucherEntry(); 
	        	   vo.setBrNo("0");
	               vo.setVoucherID(-1);
	               vo.setEntryID(-1);      // 临时设置，后续过程将重新设置
	               vo.setExplanation(payTypeName);
	               vo.setAccountID(accountId);
	               vo.setAccountID2(vo.getAccountID());
	               vo.setDetailID(0);      // 核算项目ID，临时设置，后面还有重新赋值
	               vo.setCurrencyID(1);
	               vo.setExchangeRate(1);
	               vo.setDc(dc);            // 
	               vo.setAmountFor(amount);
	               vo.setAmount(amount);
	               vo.setQuantity(BigDecimal.ZERO);
	               vo.setMeasureUnitID(0);
	               vo.setUnitPrice(BigDecimal.ZERO);
	               vo.setSettleTypeID(0);
	               vo.setCashFlowItem(0);
	               vo.setTaskID(0);
	               vo.setResourceID(0);

	               // 设置核算项目ID
	               if (DbSqlHelper.departmentMap.containsKey(departId)){
	                   int dept = DbSqlHelper.departmentMap.get(departId).getId();

	                   if (DbSqlHelper.itemDetailMap.containsKey(dept)){
	                       vo.setDetailID(DbSqlHelper.itemDetailMap.get(dept));
	                   }
	               }
	               sumPayTypes.put(key, vo);
	           }
	        }
		} else if ("xls".equalsIgnoreCase(fileType)) 
		{
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            	if (hssfRow == null) {
                    continue;
                }
            	//dto.setBizDate(xssfRow.getCell(0).getStringCellValue());
	            String paySerialNum = hssfRow.getCell(3).getStringCellValue();
	            String departId = null;
	            try
	            {
	            	departId = billMapToDepart.get(paySerialNum);
	            } catch (Exception e)
	            {
	            	String errorMessage = "结算明细页面(第"+rowNum+"行),结算识别码 ("+paySerialNum + ") 在结账流水中不能找到，请检查文件统一性";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	            }
	            String payTypeCode = hssfRow.getCell(6).getStringCellValue();
	            String payTypeName = hssfRow.getCell(7).getStringCellValue();
	            int accountId;
	            try{
	            	accountId = DbSqlHelper.accountsMap.get(payTypeCode);
	               } catch (NullPointerException e)
	               {
	              	 String errorMessage = "结算明细页面(第"+rowNum+"行),"+payTypeName + " " + payTypeCode + " 在KIS科目中没有维护！";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	               }
	            
	            BigDecimal amount = new BigDecimal(hssfRow.getCell(8).getNumericCellValue());
//	            String sellDateStr = xssfRow.getCell(7).getStringCellValue();
//	            Date sellDate = myFmt2.parse(sellDateStr);
//	            dto.setSellDate(sellDate);
	            int dc = 1;
	           String key = departId + "_" + accountId + "_" + dc;
	           if (sumPayTypes.containsKey(key)){
	        	   VoucherEntry vo =  sumPayTypes.get(key);
	        	   vo.setAmount(vo.getAmount().add(amount));
	           } else {
	        	   VoucherEntry vo = new VoucherEntry(); 
	        	   vo.setBrNo("0");
	               vo.setVoucherID(-1);
	               vo.setEntryID(-1);      // 临时设置，后续过程将重新设置
	               vo.setExplanation(payTypeName);
	               vo.setAccountID(accountId);
	               vo.setAccountID2(vo.getAccountID());
	               vo.setDetailID(0);      // 核算项目ID，临时设置，后面还有重新赋值
	               vo.setCurrencyID(1);
	               vo.setExchangeRate(1);
	               vo.setDc(dc);            // 
	               vo.setAmountFor(amount);
	               vo.setAmount(amount);
	               vo.setQuantity(BigDecimal.ZERO);
	               vo.setMeasureUnitID(0);
	               vo.setUnitPrice(BigDecimal.ZERO);
	               vo.setSettleTypeID(0);
	               vo.setCashFlowItem(0);
	               vo.setTaskID(0);
	               vo.setResourceID(0);

	               // 设置核算项目ID
	               if (DbSqlHelper.departmentMap.containsKey(departId)){
	                   int dept = DbSqlHelper.departmentMap.get(departId).getId();

	                   if (DbSqlHelper.itemDetailMap.containsKey(dept)){
	                       vo.setDetailID(DbSqlHelper.itemDetailMap.get(dept));
	                   }
	               }
	               sumPayTypes.put(key, vo);
	           }
	        }
		} else {
			System.out.println("未知文件类型 " +fileType);
		}
	}
}
