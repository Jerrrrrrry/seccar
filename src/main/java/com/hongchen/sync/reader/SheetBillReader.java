package com.hongchen.sync.reader;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class SheetBillReader {

	private XSSFSheet xssfSheet;
	private String fileType;
	private HSSFSheet hssfSheet;
//	private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
	private ExcelDataDto result;
	
	public SheetBillReader(String fileType, XSSFSheet xssfSheet, HSSFSheet hssfSheet, ExcelDataDto result)
	{
		this.fileType = fileType;
		this.xssfSheet = xssfSheet;
		this.hssfSheet = hssfSheet;
		this.result = result;
	}
	
	public void read() {
		Map<String, ExcelBillDto> map = result.getSumBills();
		Map<String, String> map2 = result.getBillMapToDepart();
		if ("xlsx".equalsIgnoreCase(fileType))
		{
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	        	if (xssfRow == null) {
	                continue;
	            }
	        	
	        	ExcelBillDto dto = new ExcelBillDto(); 
//	        	dto.setBizDate(xssfRow.getCell(0).getStringCellValue());
	            dto.setPaySerialNum(xssfRow.getCell(3).getStringCellValue());
	            dto.setSettleAmount(xssfRow.getCell(4).getNumericCellValue());
	            dto.setDiscountAmount(xssfRow.getCell(5).getNumericCellValue());
	            dto.setWipeTheZeroAmount(xssfRow.getCell(6).getNumericCellValue());
	            dto.setQuotaAmount(xssfRow.getCell(7).getNumericCellValue());
	            dto.setGiftAmount(xssfRow.getCell(8).getNumericCellValue());

	           // String sellDateStr = xssfRow.getCell(7).getStringCellValue();
	            //dto.setSellDate(myFmt2.parse(sellDateStr));
	            dto.setDepartmentNumber(xssfRow.getCell(1).getStringCellValue());
	            dto.setDepartmentName(xssfRow.getCell(2).getStringCellValue());
	            dto.setServiceAmount(xssfRow.getCell(9).getNumericCellValue());
	            dto.setRoomsAmount(xssfRow.getCell(10).getNumericCellValue());
	            
	            map2.put(dto.getPaySerialNum(), dto.getDepartmentNumber());
	            ExcelBillDto one = map.get(dto.getDepartmentNumber());
	            if (one == null)
	            {
	            	map.put(dto.getDepartmentNumber(), dto);
	            } else {
	            	one.add(dto);
	            }
	           
	            //System.out.println("处理到第" +rowNum+"行数据:" + dto.toString());
	        }
		} else if ("xls".equalsIgnoreCase(fileType)) 
		{
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            	if (hssfRow == null) {
                    continue;
                }
            	ExcelBillDto dto = new ExcelBillDto(); 
//	        	dto.setBizDate(xssfRow.getCell(0).getStringCellValue());
	            dto.setPaySerialNum(hssfRow.getCell(3).getStringCellValue());
	            dto.setSettleAmount(hssfRow.getCell(4).getNumericCellValue());
	            dto.setDiscountAmount(hssfRow.getCell(5).getNumericCellValue());
	            dto.setWipeTheZeroAmount(hssfRow.getCell(6).getNumericCellValue());
	            dto.setQuotaAmount(hssfRow.getCell(7).getNumericCellValue());
	            dto.setGiftAmount(hssfRow.getCell(8).getNumericCellValue());

	           // String sellDateStr = hssfRow.getCell(7).getStringCellValue();
	            //dto.setSellDate(myFmt2.parse(sellDateStr));
	            dto.setDepartmentNumber(hssfRow.getCell(1).getStringCellValue());
	            dto.setDepartmentName(hssfRow.getCell(2).getStringCellValue());
	            dto.setServiceAmount(hssfRow.getCell(9).getNumericCellValue());
	            dto.setRoomsAmount(hssfRow.getCell(10).getNumericCellValue());
	            
	            map2.put(dto.getPaySerialNum(), dto.getDepartmentNumber());
	            ExcelBillDto one = map.get(dto.getDepartmentNumber());
	            if (one == null)
	            {
	            	map.put(dto.getDepartmentNumber(), dto);
	            } else {
	            	one.add(dto);
	            }
	            //System.out.println("处理到第" +rowNum+"行数据:" + dto.toString());
	        }
		} else {
			System.out.println("未知文件类型" +fileType);
		}
	}
}
