package com.hongchen.sync.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.hongchen.kis.db.BillCodeRuleDao;
import com.hongchen.kis.db.DbSqlHelper;
import com.hongchen.kis.db.ICBillNoDao;
import com.hongchen.kis.db.ICStockBillDao;
import com.hongchen.kis.db.ICStockBillEntryDao;
import com.hongchen.kis.db.InnerDao;
import com.hongchen.kis.db.KisAccountDao;
import com.hongchen.kis.db.SystemProfileDao;
import com.hongchen.kis.db.VoucherDao;
import com.hongchen.kis.db.VoucherEntryDao;
import com.hongchen.kis.domain.pojo.ICStockBill;
import com.hongchen.kis.domain.pojo.ICStockBillEntry;
import com.hongchen.kis.domain.pojo.Voucher;
import com.hongchen.sync.reader.ExcelBillDto;
import com.hongchen.sync.reader.ExcelDataDto;
import com.hongchen.sync.util.ConvertUtils;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.kis.domain.pojo.VoucherEntry;

public class SyncService {
	private Logger logger = Logger.getLogger(SyncService.class);
	private ExcelDataDto result;
	public SyncService(ExcelDataDto result) {
		this.result = result;
	} 

	public void sync(String userID, int kisUserID) throws Exception {
		//SUM(a.结算金额) + SUM(a.抹零金额) + SUM(a.定额优惠) as 贷方金额
		double totalAmount = 0;
		List<VoucherEntry> list = new ArrayList<VoucherEntry>();
		for (Entry<String, ExcelBillDto> entry : result.getSumBills().entrySet())
		{
			ExcelBillDto dto = entry.getValue();
			totalAmount = totalAmount + (dto.getSettleAmount() + dto.getWipeTheZeroAmount()+dto.getQuotaAmount());
			if ( dto.getWipeTheZeroAmount() != 0){
				list.add(this.createWipeZeroEntry(entry.getKey(), dto.getWipeTheZeroAmount()));
			}
			if (dto.getQuotaAmount() != 0)
			{
				list.add(this.createQuotaEntry(entry.getKey(), dto.getQuotaAmount()));
			}
		}
		Voucher voucher = this.syncVoucher(totalAmount, userID, kisUserID);
		
		int entryId = 0;//分录序号
		for (VoucherEntry vo : list)
		{
			vo.setVoucherID(voucher.getVoucherID());
			vo.setEntryID(entryId);
			entryId++;
			
		}
		
		BigDecimal dAmount = BigDecimal.ZERO;
	    BigDecimal cAmount = BigDecimal.ZERO;
		
	    for (Entry<String, VoucherEntry> entry : result.getSumPayTypes().entrySet())
	    {
	    	VoucherEntry dto = entry.getValue();
	    	dto.setVoucherID(voucher.getVoucherID());
	    	dto.setEntryID(entryId);
	    	dAmount.add(dto.getAmount());
	    	list.add(dto);
	    	dto.setAmountFor(dto.getAmount());
	    	entryId++;
	    }
		for (Entry<String, VoucherEntry> entry : result.getSumOrderDetailTypes().entrySet())
		{
			VoucherEntry dto = entry.getValue();
			dto.setVoucherID(voucher.getVoucherID());
			dto.setEntryID(entryId);
			cAmount.add(dto.getAmount());
			dto.setAmountFor(dto.getAmount());
			list.add(dto);
			entryId++;
		}
		
		
		this.syncVoucherEntry(list, voucher, dAmount, cAmount);
		this.syncStockData( userID, kisUserID);
	}

	private void syncStockData(String userID, int kisUserID) throws Exception {
		Map<String, List<ICStockBillEntry>>  map = this.result.getStockData();
		if (map.isEmpty()){
			logger.info("此次上传无酒水出库信息");
			return;
		}
		Map<String, Integer> pks = new HashMap<String, Integer>();  
		for (String bizNumber : map.keySet()){
        	String date = bizNumber.split("\\+")[0];
        	String deptNum = bizNumber.split("\\+")[1];
            ICStockBill vo = new ICStockBill();
            vo.setBrNo("0");
            vo.setInterID(new InnerDao().getNextInterID("ICStockBill"));
            vo.setTranType(21);
            vo.setDate(ConvertUtils.stringToDate(date));
            vo.setBillNo(new ICBillNoDao().getNextNumber(21));
            vo.setDeptID(DbSqlHelper.departmentMap.get(deptNum).getId());
            vo.setEmpID(0);
            vo.setSupplyID(5539);       // 消费客户
            vo.setfManagerID(4822);     // 默认
            vo.setsManagerID(4822);     // 默认
            vo.setBillerID(kisUserID);
            vo.setVchInterID(0);
            vo.setSaleStyle(100);       // 现销
            vo.setRelateBrID(0);
            vo.setExplanation(bizNumber);
            vo.setBrID(0);
            vo.setPoOrdBillNo("");
            vo.setSettleDate(vo.getDate());
            vo.setManageType(0);
            vo.setConsignee(0);
            vo.setWlCompany(0);

            // 持久化
            new ICStockBillDao().create(vo);

            // 更新下一个编号
            new ICBillNoDao().updateNextNumber(21);
            new BillCodeRuleDao().updateNextProjectVal(21);


            if (!pks.containsKey(bizNumber)){
                pks.put(bizNumber ,(Integer)vo.getInterID());
            }
        }
		logger.info(pks);
		 List<ICStockBillEntry> dbList = new ArrayList<ICStockBillEntry>();
		 int i = 0;
        for (Entry<String, List<ICStockBillEntry>> entry: map.entrySet()){
        	List<ICStockBillEntry> list = entry.getValue();
        	if (list != null)
        	{
        		for (ICStockBillEntry vo : list)
        		{
        			vo.setInterID(pks.get(entry.getKey()));
        			 vo.setEntryID(i);
        			vo.setAuxQty(vo.getQty());
        			vo.setConsignPrice(vo.getConsignPrice());
        			vo.setConsignAmount(vo.getQty() * vo.getConsignPrice());
        			i++;
        			dbList.add(vo);
        		}
        	}
        }
     // 持久化分录
        new ICStockBillEntryDao().create(dbList);
	}

	private VoucherEntry createWipeZeroEntry(String departId, double totalWipeZeroAmount) throws Exception {
		int acctWZ = new KisAccountDao().get(SysConstant.WIPE_ZERO_ACCOUNT);
		VoucherEntry vo = new VoucherEntry();
		vo.setBrNo("0");
		vo.setVoucherID(-1);
		vo.setEntryID(-1); // 临时设置，后续过程将重新设置
		vo.setExplanation("抹零");
		vo.setAccountID(acctWZ);
		vo.setAccountID2(vo.getAccountID());
		
		vo.setDetailID(0); // 核算项目ID，临时设置，后面还有重新赋值
		vo.setCurrencyID(1);
		vo.setExchangeRate(1);
		vo.setDc(1); // 借方
		vo.setAmountFor(new BigDecimal(totalWipeZeroAmount));
		vo.setAmount(new BigDecimal(totalWipeZeroAmount));
		vo.setQuantity(BigDecimal.ZERO);
		vo.setMeasureUnitID(0);
		vo.setUnitPrice(BigDecimal.ZERO);
		vo.setSettleTypeID(0);
		vo.setCashFlowItem(0);
		vo.setTaskID(0);
		vo.setResourceID(0);

		// 设置核算项目ID
		if (DbSqlHelper.departmentMap.containsKey(departId)) {
			int dept = DbSqlHelper.departmentMap.get(departId).getId();

			if (DbSqlHelper.itemDetailMap.containsKey(dept)) {
				vo.setDetailID(DbSqlHelper.itemDetailMap.get(dept));
			}
		}
		return vo;
	}
	private VoucherEntry createQuotaEntry(String departId, double totalQuotaAmount) throws Exception {
		int acctQT = new KisAccountDao().get(SysConstant.QUOTA_ACCOUNT);
		VoucherEntry vo = new VoucherEntry();
        vo.setBrNo("0");
        vo.setVoucherID(-1);
        vo.setEntryID(-1);      // 临时设置，后续过程将重新设置
        vo.setExplanation("定额优惠");
        vo.setAccountID(acctQT);
        vo.setAccountID2(vo.getAccountID());
        vo.setDetailID(0);      // 核算项目ID，临时设置，后面还有重新赋值
        vo.setCurrencyID(1);
        vo.setExchangeRate(1);
        vo.setDc(1);            // 借方
        vo.setAmountFor(new BigDecimal(totalQuotaAmount));
        vo.setAmount(new BigDecimal(totalQuotaAmount));
        vo.setQuantity(BigDecimal.ZERO);
        vo.setMeasureUnitID(0);
        vo.setUnitPrice(BigDecimal.ZERO);
        vo.setSettleTypeID(0);
        vo.setCashFlowItem(0);
        vo.setTaskID(0);
        vo.setResourceID(0);

		// 设置核算项目ID
		if (DbSqlHelper.departmentMap.containsKey(departId)) {
			int dept = DbSqlHelper.departmentMap.get(departId).getId();

			if (DbSqlHelper.itemDetailMap.containsKey(dept)) {
				vo.setDetailID(DbSqlHelper.itemDetailMap.get(dept));
			}
		}
		return vo;
	}
    /***********************************************/
    // 同步凭证单据头
    /***********************************************/
    private Voucher syncVoucher(double totalAmount, String userID, int kisUserID) throws Exception{

        int currentYear = ConvertUtils.stringToInt(new SystemProfileDao().getProfileValue("GL", "CurrentYear"));
        int currentPeriod =  ConvertUtils.stringToInt(new SystemProfileDao().getProfileValue("GL", "CurrentPeriod"));

        // 合计所选的金额合计
//        double totalAmount = 0;
//        for (VoucherEntry dto : list){
//        	if (dto.getDc() == 0)
//            {
//        		totalAmount = totalAmount + dto.getAmount().doubleValue();
//            }
//        }
        VoucherDao voucherDao = new VoucherDao();
        // 创建凭证头
        Voucher vo = new Voucher();
        vo.setBrNo("0");
        vo.setVoucherID(new InnerDao().getNextInterID("t_Voucher"));
        vo.setDate(new Date());
        vo.setYear(currentYear);
        vo.setPeriod(currentPeriod);
        vo.setGroupID(1);   // 凭证字：记
        vo.setNumber(voucherDao.getNextNumber(1, currentYear, currentPeriod));
        vo.setExplanation("接口生成");
        vo.setAttachments(0);
        vo.setEntryCount(0); // 分录数，暂时设置为0，后续过程中再修改
        vo.setDebitTotal(totalAmount);   // 借贷先设置为相等，后面在判断是否有差额
        vo.setCreditTotal(totalAmount);
        vo.setChecked(0);   // 未审核
        vo.setPosted(0);    // 未过账
        vo.setPreparerID(kisUserID);   // 制单人
        vo.setCheckerID(-1);
        vo.setPosterID(-1);
        vo.setCashierID(-1);
        vo.setOwnerGroupID(0);
        vo.setSerialNum(voucherDao.getNextSerialNum(currentYear));
        vo.setTranType(0);
        vo.setTransDate(vo.getDate());
        vo.setFrameWorkID(-1);
        vo.setApproveID(-1);
        vo.setFootNote("");

        // 凭证头持久化
        voucherDao.create(vo);
        
        System.out.println("凭证ID：" + vo.getVoucherID());
        return vo;
    }
    /***********************************************/
    // 同步凭证分录体
    /***********************************************/
    private void syncVoucherEntry (List<VoucherEntry> entry, Voucher vo, BigDecimal dAmount, BigDecimal cAmount) throws Exception {



        // 判断借贷是否相等，不相等需要补差
        BigDecimal diffAmount = dAmount.subtract(cAmount);


        // 如果有差额
        if (diffAmount.compareTo(BigDecimal.ZERO) != 0){


            int acct = DbSqlHelper.accountsMap.get("5501.05.03");

            VoucherEntry ve = new VoucherEntry();
            ve.setBrNo("0");
            ve.setVoucherID(vo.getVoucherID());
            ve.setEntryID(-1);      // 临时设置，后续过程将重新设置
            ve.setExplanation("补差额");
            ve.setAccountID(acct);
            ve.setAccountID2(ve.getAccountID());
            ve.setDetailID(0);      // 核算项目ID，临时设置，后面还有重新赋值
            ve.setCurrencyID(1);
            ve.setExchangeRate(1);
            ve.setDc(0);            // 贷方
            ve.setAmountFor(diffAmount);
            ve.setAmount(diffAmount);
            ve.setQuantity(BigDecimal.ZERO);
            ve.setMeasureUnitID(0);
            ve.setUnitPrice(BigDecimal.ZERO);
            ve.setSettleTypeID(0);
            ve.setCashFlowItem(0);
            ve.setTaskID(0);
            ve.setResourceID(0);

            // 设置核算项目ID
            for (VoucherEntry vvo : entry){

                if (vvo.getAccountID() == acct){
                    ve.setDetailID(vvo.getDetailID());
                    break;
                }
            }

            entry.add(ve);
        }

        // 持久化分录体
        new VoucherEntryDao().create(entry);

        // 更新单据头的分录行数
       new VoucherDao().updateEntryCount();
    }  
}
