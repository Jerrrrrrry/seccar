package com.hongchen.kis.db;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hongchen.kis.db.DbSqlHelper;
import com.hongchen.kis.db.DepartmentDao;
import com.hongchen.kis.db.ICItemDao;
import com.hongchen.kis.db.ItemDetailDao;
import com.hongchen.kis.db.StockDao;
import com.hongchen.kis.domain.pojo.DepartmentDto;
import com.hongchen.kis.domain.pojo.MaterialDto;
import com.hongchen.kis.domain.pojo.StockDto;


public class ConfigDataInitializer {
	 private static Logger logger = Logger.getLogger(ConfigDataInitializer.class);
	public void initDB() throws Exception{
		try {
			Properties pps = new Properties();
			DbSqlHelper.DBConfig.clear();
		 InputStream in = this.getClass().getResourceAsStream("/db.properties");  
		 pps.load(in);
		 in.close();
		 DbSqlHelper.DBConfig.put("db_url", pps.getProperty("db_url"));
		 DbSqlHelper.DBConfig.put("db_instance", pps.getProperty("db_instance"));
		 DbSqlHelper.DBConfig.put("db_user", pps.getProperty("db_user"));
		 DbSqlHelper.DBConfig.put("db_password", pps.getProperty("db_password"));
		 
		 DbSqlHelper.DBConfig.put("kis_db_url", pps.getProperty("kis_db_url"));
		 DbSqlHelper.DBConfig.put("kis_db_instance", pps.getProperty("kis_db_instance"));
		 DbSqlHelper.DBConfig.put("kis_db_user", pps.getProperty("kis_db_user"));
		 DbSqlHelper.DBConfig.put("kis_db_password", pps.getProperty("kis_db_password"));
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		logger.info("读取DB配置成功");
	}
//	private void outputMap(Map<String, Integer> accountsMap){
//		for (Entry<String, Integer> entry : accountsMap.entrySet()){
//			logger.info(entry.getKey() +"=" + entry.getValue());
//		}
//	}
	private void initAccounts() throws Exception{
		 try {
			 DbSqlHelper.accountsMap.clear();
			 Map<String, String> all= new AccountDao().list();
			 String[] ids= all.keySet().toArray(new String[0]);
			 Map<String, Integer> accounts= new KisAccountDao().list(ids);
			 for (Entry<String, String> entry : all.entrySet()){
		            String[] helperCode = ((String)entry.getValue()).split(",");
		            for(String cd : helperCode){
		            	cd = cd.trim();
		                if (!DbSqlHelper.accountsMap.containsKey(cd)){
		                	DbSqlHelper.accountsMap.put(cd, accounts.get(entry.getKey()));
		                }
		            }
		       }
//			 outputMap(DbSqlHelper.accountsMap);
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		 logger.info("初始化accounts成功");
		 
	}
	private void initDeparts() throws Exception{
		 try {
			 DbSqlHelper.departmentMap.clear();
			 DbSqlHelper.departmentList.clear();
			 List<DepartmentDto> departs= new DepartmentDao().list();
			 DbSqlHelper.departmentList.addAll(departs);
			 for (DepartmentDto dto:departs){
				 DbSqlHelper.departmentMap.put(dto.getTargetNumber(), dto);
			 }
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		 logger.info("初始化departments成功");
	}
	private void initItemDetails() throws Exception{
		 try {
			 DbSqlHelper.itemDetailMap.clear();
			Map<Integer, Integer> map = new ItemDetailDao().getDetailByDepartment(DbSqlHelper.departmentList);
			DbSqlHelper.itemDetailMap.putAll(map);
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		 logger.info("初始化ItemDetails成功");
	}
	private void initStocks() throws Exception{
		 try {
			 DbSqlHelper.stockMap.clear();
			 Map<String, StockDto> map = new StockDao().list();
			DbSqlHelper.stockMap.putAll(map);
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		 logger.info("初始化stocks成功");
	}
	private void initMaterialMap() throws Exception{
		 try {
			 DbSqlHelper.materialMap.clear();
			 Map<String, MaterialDto> map = new ICItemDao().list();
			DbSqlHelper.materialMap.putAll(map);
		}catch (Exception e) {
		 e.printStackTrace();
		 throw e;
		}
		 logger.info("初始化MaterialMap成功");
	}
	public void initAll()
	{
		logger.info("initAll start***************************************");
		try {
			initDB();
			initAccounts();
			initDeparts();
			initItemDetails();
			initStocks();
			initMaterialMap();
		} catch (Exception e)
		{
			logger.error(" an error when initialize the data", e);
			e.printStackTrace();
		}
		logger.info("initAll finished***************************************");
	}
}
