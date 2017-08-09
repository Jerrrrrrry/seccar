package com.hongchen.sync.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.hongchen.sync.model.SyncService;



public class FileProcessor {
	  private Logger logger = Logger.getLogger(FileProcessor.class);

	public String process(File newFile, String userId, int kisUserId)
	{
		logger.info("开始处理 " + newFile.getPath());
		try {
			long begin = System.currentTimeMillis();
			ExcelDataDto result = null;
			if (newFile.getName().toLowerCase().endsWith("xls"))
			{
				result= this.readXLS(newFile);
			} else if (newFile.getName().toLowerCase().endsWith("xlsx"))
			{
				result= this.readXLSX(newFile);
			} else
		 	 {
		 		logger.info("不可识别文件类型"+ newFile.getName());
		 		return "不可识别文件类型";
		 	 }
			SyncService sync = new SyncService(result);
			sync.sync(userId, kisUserId);
			long end = System.currentTimeMillis();
			logger.info("处理文件用时:" +(end-begin) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "成功";
	}
	@SuppressWarnings("resource")
	private ExcelDataDto readXLS(File processingFile) throws Exception
	{
		ExcelDataDto result = new ExcelDataDto();
		try {
			InputStream is = new FileInputStream(processingFile);
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        HSSFSheet hssfSheet1 = hssfWorkbook.getSheet("结账明细");
	        HSSFSheet hssfSheet2 = hssfWorkbook.getSheet("消费明细");
	        HSSFSheet hssfSheet3 = hssfWorkbook.getSheet("结算明细");
	        if (hssfSheet1 == null || hssfSheet2==null ||  hssfSheet3 ==null) {
	        	throw new Exception("数据格式问题, 缺少sheet页");
	        }
	        
	        new SheetBillReader("xls", null, hssfSheet1, result).read();
	        new SheetPayReader("xls", null,hssfSheet3, result).read();
	        new SheetOrderDetailReader("xls", null,hssfSheet2, result).read();
		}  catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	@SuppressWarnings("resource")
	private ExcelDataDto readXLSX(File processingFile) throws Exception
	{
		ExcelDataDto result = new ExcelDataDto();
		try {
			InputStream is = new FileInputStream(processingFile);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			
			XSSFSheet xssfSheet1 = xssfWorkbook.getSheet("结账明细");
			XSSFSheet xssfSheet2 = xssfWorkbook.getSheet("消费明细");
			XSSFSheet xssfSheet3 = xssfWorkbook.getSheet("结算明细");
	        if (xssfSheet1 == null || xssfSheet2==null ||  xssfSheet3 ==null) {
	        	throw new Exception("数据格式问题, 缺少sheet页");
	        }
	        new SheetBillReader("xlsx", xssfSheet1, null, result).read();
	        new SheetPayReader("xlsx", xssfSheet3, null, result).read();
	        new SheetOrderDetailReader("xlsx", xssfSheet2, null, result).read();
	        System.out.println(result);  
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	public static String getNewId() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	public String toProcessingFileName(String origName)
	{
		String uuid = getNewId();
		 SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 String processingTimeStamp = f.format(new Date());
		 String newName=null;
		if (origName.lastIndexOf(".") > 0)
		{
			String oName = origName.substring(0, origName.lastIndexOf("."));
			String extentionName = origName.substring(origName.lastIndexOf("."));
			newName = oName+"_"+processingTimeStamp+"_"+uuid+extentionName;
		} else
		{
			newName = origName+"_"+processingTimeStamp+"_"+uuid;
		}
		return newName;
	}

}
