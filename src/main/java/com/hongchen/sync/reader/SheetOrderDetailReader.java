package com.hongchen.sync.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hongchen.kis.db.DbSqlHelper;
import com.hongchen.kis.domain.pojo.ICStockBillEntry;
import com.hongchen.kis.domain.pojo.MaterialDto;
import com.hongchen.kis.domain.pojo.StockDto;
import com.lhcy.kis.domain.pojo.VoucherEntry;

public class SheetOrderDetailReader {

		private XSSFSheet xssfSheet;
		private String fileType;
		private HSSFSheet hssfSheet;
//		private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
		private ExcelDataDto result;
		
		public SheetOrderDetailReader(String fileType, XSSFSheet xssfSheet, HSSFSheet hssfSheet, ExcelDataDto result)
		{
			this.fileType = fileType;
			this.xssfSheet = xssfSheet;
			this.hssfSheet = hssfSheet;
			this.result = result;
		}
		
		public void read() throws Exception {
			Map<String, VoucherEntry> sumDetailTypes = result.getSumOrderDetailTypes();
			 Map<String, String> billMapToDepart = result.getBillMapToDepart();
			if ("xlsx".equalsIgnoreCase(fileType))
			{
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
		            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
		        	if (xssfRow == null) {
		                continue;
		            }
		        	String paySerialNum = xssfRow.getCell(3).getStringCellValue();
		            String departId = null;
		            try
		            {
		            	departId = billMapToDepart.get(paySerialNum);
		            } catch (Exception e)
		            {
		            	String errorMessage = "消费明细页面(第"+rowNum+"行),结算识别码 ("+paySerialNum + ") 在结账流水中不能找到，请检查文件统一性";
		              	 System.out.println(errorMessage);
		              	 throw new Exception(errorMessage);
		            }
		            String itemCategory = xssfRow.getCell(4).getStringCellValue();
		            String itemCategoryName = xssfRow.getCell(5).getStringCellValue();
		            int accountId;
		            try{
		            	accountId = DbSqlHelper.accountsMap.get(itemCategory);
	               } catch (NullPointerException e)
	               {
	              	 String errorMessage = "消费明细页面(第"+rowNum+"行),　品项大类 ("+itemCategoryName + ") (" + itemCategory + ") 在KIS科目中没有维护！";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	               }
		            BigDecimal quantity = new BigDecimal(xssfRow.getCell(10).getNumericCellValue());
		            BigDecimal price = new BigDecimal(xssfRow.getCell(11).getNumericCellValue());
		            BigDecimal amount=quantity.multiply(price);
		            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
//		            String sellDateStr = xssfRow.getCell(7).getStringCellValue();
//		            Date sellDate = myFmt2.parse(sellDateStr);
		           if (itemCategoryName!=null && itemCategoryName.trim().equalsIgnoreCase("酒水"))
		            {
		            	String sellDateStr = xssfRow.getCell(0).getStringCellValue();
		            	String bizNum = sellDateStr +"+"+departId;
		            	List<ICStockBillEntry> list = result.getStockData().get(bizNum);
	                    if (list == null)
	                    {
	                    	result.getStockData().put(bizNum, new ArrayList<ICStockBillEntry>());
	                    	list = result.getStockData().get(bizNum);
	                    }
	                    ICStockBillEntry vo = new ICStockBillEntry();
	        			vo.setBrNo("0");
	        			//vo.setInterID(pks.get(dto.getBizNumber()));
	        			vo.setQty(quantity.doubleValue());
	        			vo.setAuxQty(quantity.doubleValue());
	        			vo.setConsignPrice(price.doubleValue());
	        			vo.setConsignAmount(amount.doubleValue());
	        			String materialNumber = xssfRow.getCell(8).getStringCellValue();
	        			String materialName = xssfRow.getCell(9).getStringCellValue();
	        			try{
	        	        	 MaterialDto material = DbSqlHelper.materialMap.get(materialNumber);
	        	        	 vo.setItemID(material.getId());         // 物料ID
	        	        	 vo.setUnitID(material.getUnitID());
	        	         } catch (Exception e)
	        	         {
	        	        	 String errorMessage = "消费明细页面(第"+rowNum+"行),　品项名称 ("+materialNumber + ") (" + materialName + ") 在KIS科目中没有维护！";
	        	        	 throw new Exception(errorMessage);
	        	         };
	        			String depFNum = null;
	        			 try{
	        	        	 depFNum = DbSqlHelper.departmentMap.get(departId).getNumber();
	        	        	 StockDto stock = DbSqlHelper.stockMap.get(depFNum + ".01");
	        	        	 vo.setDcStockID(stock.getId());
	        	         } catch (Exception e)
	        	         {
	        	        	 String errorMessage = "消费明细页面(第"+rowNum+"行),　部门("+depFNum + ".01) 在KIS科目中没有维护！";
	        	        	 throw new Exception(errorMessage);
	        	         }
	        		
	        			vo.setEntryID(-1);
	        			vo.setDcSPID(0);
	        			vo.setSnListID(0);
	        			boolean exists = false;
	                    for (ICStockBillEntry dto: list)
	                    {
	                    	if (dto.getItemID() == vo.getItemID()&& dto.getUnitID() == vo.getUnitID() && dto.getConsignPrice() == vo.getConsignPrice())
	                    	{
	                    		exists=true;
	                    		dto.setQty(dto.getQty() + vo.getQty());
	                    		break;
	                    	}
	                    }
	                    if (!exists)
	                    {
	                    	list.add(vo);
	                    }
		            }
		            int dc = 0;
			           String key = departId + "_" + accountId + "_" + dc;
			           if (sumDetailTypes.containsKey(key)){
			        	   VoucherEntry vo =  sumDetailTypes.get(key);
			        	   vo.setQuantity(vo.getQuantity().add(quantity));
			        	   vo.setAmount(vo.getAmount().add(amount));
			           } else {
			        	   VoucherEntry vo = new VoucherEntry(); 
			        	   vo.setBrNo("0");
			               vo.setVoucherID(-1);
			               vo.setEntryID(-1);      // 临时设置，后续过程将重新设置
			               vo.setExplanation(itemCategoryName);
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
			               sumDetailTypes.put(key, vo);
			           }
		        }
			} else if ("xls".equalsIgnoreCase(fileType)) 
			{
				for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	            	if (hssfRow == null) {
	                    continue;
	                }
	            	String paySerialNum = hssfRow.getCell(3).getStringCellValue();
		            String departId = null;
		            try
		            {
		            	departId = billMapToDepart.get(paySerialNum);
		            } catch (Exception e)
		            {
		            	String errorMessage = "消费明细页面(第"+rowNum+"行),结算识别码 ("+paySerialNum + ") 在结账流水中不能找到，请检查文件统一性";
		              	 System.out.println(errorMessage);
		              	 throw new Exception(errorMessage);
		            }
		            String itemCategory = hssfRow.getCell(4).getStringCellValue();
		            String itemCategoryName = hssfRow.getCell(5).getStringCellValue();
		            int accountId;
		            try{
		            	accountId = DbSqlHelper.accountsMap.get(itemCategory);
	               } catch (NullPointerException e)
	               {
	              	 String errorMessage = "消费明细页面(第"+rowNum+"行),　品项大类 ("+itemCategoryName + ") (" + itemCategory + ") 在KIS科目中没有维护！";
	              	 System.out.println(errorMessage);
	              	 throw new Exception(errorMessage);
	               }
		            BigDecimal quantity = new BigDecimal(hssfRow.getCell(10).getNumericCellValue());
		            BigDecimal price = new BigDecimal(hssfRow.getCell(11).getNumericCellValue());
		            BigDecimal amount=quantity.multiply(price);
//		            String sellDateStr = xssfRow.getCell(7).getStringCellValue();
//		            Date sellDate = myFmt2.parse(sellDateStr);
		           if (itemCategoryName!=null && itemCategoryName.trim().equalsIgnoreCase("酒水"))
		            {
		            	String sellDateStr = hssfRow.getCell(0).getStringCellValue();
		            	String bizNum = sellDateStr +"+"+departId;
		            	List<ICStockBillEntry> list = result.getStockData().get(bizNum);
	                    if (list == null)
	                    {
	                    	result.getStockData().put(bizNum, new ArrayList<ICStockBillEntry>());
	                    	list = result.getStockData().get(bizNum);
	                    }
	                    ICStockBillEntry vo = new ICStockBillEntry();
	        			vo.setBrNo("0");
	        			//vo.setInterID(pks.get(dto.getBizNumber()));
	        			vo.setQty(quantity.doubleValue());
	        			vo.setAuxQty(quantity.doubleValue());
	        			vo.setConsignPrice(price.doubleValue());
	        			vo.setConsignAmount(amount.doubleValue());
	        			String materialNumber = hssfRow.getCell(8).getStringCellValue();
	        			String materialName = hssfRow.getCell(9).getStringCellValue();
	        			try{
	        	        	 MaterialDto material = DbSqlHelper.materialMap.get(materialNumber);
	        	        	 vo.setItemID(material.getId());         // 物料ID
	        	        	 vo.setUnitID(material.getUnitID());
	        	         } catch (Exception e)
	        	         {
	        	        	 String errorMessage = "消费明细页面(第"+rowNum+"行),　品项名称 ("+materialNumber + ") (" + materialName + ") 在KIS科目中没有维护！";
	        	        	 throw new Exception(errorMessage);
	        	         };
	        			String depFNum = null;
	        			 try{
	        	        	 depFNum = DbSqlHelper.departmentMap.get(departId).getNumber();
	        	        	 StockDto stock = DbSqlHelper.stockMap.get(depFNum + ".01");
	        	        	 vo.setDcStockID(stock.getId());
	        	         } catch (Exception e)
	        	         {
	        	        	 String errorMessage = "消费明细页面(第"+rowNum+"行),　部门("+depFNum + ".01) 在KIS科目中没有维护！";
	        	        	 throw new Exception(errorMessage);
	        	         }
	        		
	        			vo.setEntryID(-1);
	        			vo.setDcSPID(0);
	        			vo.setSnListID(0);
	        			boolean exists = false;
	                    for (ICStockBillEntry dto: list)
	                    {
	                    	if (dto.getItemID() == vo.getItemID()&& dto.getUnitID() == vo.getUnitID() && dto.getConsignPrice() == vo.getConsignPrice())
	                    	{
	                    		exists=true;
	                    		dto.setQty(dto.getQty() + vo.getQty());
	                    		break;
	                    	}
	                    }
	                    if (!exists)
	                    {
	                    	list.add(vo);
	                    }
		            }
		            int dc = 0;
			           String key = departId + "_" + accountId + "_" + dc;
			           if (sumDetailTypes.containsKey(key)){
			        	   VoucherEntry vo =  sumDetailTypes.get(key);
			        	   vo.setQuantity(vo.getQuantity().add(quantity));
			        	   vo.setAmount(vo.getAmount().add(amount));
			           } else {
			        	   VoucherEntry vo = new VoucherEntry(); 
			        	   vo.setBrNo("0");
			               vo.setVoucherID(-1);
			               vo.setEntryID(-1);      // 临时设置，后续过程将重新设置
			               vo.setExplanation(itemCategoryName);
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
			               sumDetailTypes.put(key, vo);
			           }
		        }
			} else {
				System.out.println("未知文件类型" +fileType);
			}
		}

}
