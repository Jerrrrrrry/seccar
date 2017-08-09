package com.hongchen.sync.model;


import java.util.*;
import java.util.Map.Entry;

import com.hongchen.kis.db.BillCodeRuleDao;
import com.hongchen.kis.db.DbSqlHelper;
import com.hongchen.kis.db.ICBillNoDao;
import com.hongchen.kis.db.ICStockBillDao;
import com.hongchen.kis.db.ICStockBillEntryDao;
import com.hongchen.kis.domain.pojo.ICStockBill;
import com.hongchen.kis.domain.pojo.ICStockBillEntry;
import com.hongchen.kis.domain.pojo.SettleDetailDto;
import com.hongchen.sync.util.ConvertUtils;

public class ICStockBizLogic {

//    private List<SettleDetailDto> srcEntries;
//    private HashMap<String, String> srcMaterialMap =  new HashMap<>();      // Key: 中天物料编码  Value: 中天物料名称
//    private HashMap<String, MaterialDto> materialMap = new HashMap<>();     // Key: 中天物料编码  Value: KIS物料DTO
//    private HashMap<String, StockDto> stockMap = new HashMap<>();           // Key: KIS仓库编码  Value: KIS仓库DTO
    private Map<String, Integer> pks = new HashMap<String, Integer>();                 // Key: 中天业务ID   Value: KIS销售出库单ID
//    private HashMap<String, String> deptMap = new HashMap<>();              // Key: 中天业务ID   Value: 部门编码

    /***********************************************/
    // 同步
    /***********************************************/
    public String sync(Map<String, List<SettleDetailDto>> map, int kisUserID) throws Exception {

        StringBuilder msg = new StringBuilder();
        if (map == null || map.size() == 0){
            return "";
        }


        syncICStock(map, kisUserID);
        syncICStockEntry(map);

        return msg.toString();
    }

    /***********************************************/
    // 同步销售出库单据头
    /***********************************************/
    private void syncICStock(Map<String, List<SettleDetailDto>> map, int kisUserID) throws Exception {

        for (String bizNumber : map.keySet()){
        	String date = bizNumber.split("\\+")[0];
        	String deptNum = bizNumber.split("\\+")[1];
            ICStockBill vo = new ICStockBill();
            vo.setBrNo("0");
//            vo.setInterID(new InnerDao().getNextInterID("ICStockBill"));
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

    }

    /***********************************************/
    // 同步销售出库单据体
    /***********************************************/
    private void syncICStockEntry(Map<String, List<SettleDetailDto>> map) throws Exception {

        if ( map.size() == 0){
            return;
        }
        List<ICStockBillEntry> dbList = new ArrayList<ICStockBillEntry>();
        for (Entry<String, List<SettleDetailDto>> entry: map.entrySet()){
        	List<SettleDetailDto> list = entry.getValue();
        	if (list != null)
        	{
        		for (SettleDetailDto dto : list){
        			ICStockBillEntry vo = new ICStockBillEntry();
        			vo.setBrNo("0");
        			vo.setInterID(pks.get(dto.getBizNumber()));
        			vo.setQty(dto.getQty());
        			vo.setAuxQty(dto.getQty());
        			vo.setConsignPrice(dto.getPrice());
        			vo.setConsignAmount(dto.getAmount());
        			vo.setItemID(dto.getMaterialId());         // 物料ID
        			vo.setUnitID(dto.getMaterialUnitID());     // 计量单位
        			vo.setDcStockID(dto.getStockId());                 // 发货仓库
        			vo.setEntryID(-1);                      // 暂时设置为-1，后续还会更改
        			vo.setDcSPID(0);
        			vo.setSnListID(0);
        			dbList.add(vo);
        		}
        	}
        }
        // 合并相同的数据
        for (int i = 0; i < dbList.size(); i ++){

            ICStockBillEntry ivo = dbList.get(i);
            if (ivo.isDel() || ivo.isSum()){
                continue;
            }

            int iParentID = ivo.getInterID();
            int iMaterialID = ivo.getItemID();
            int iUnitID = ivo.getUnitID();
            double iPrice = ivo.getConsignPrice();
            double iQty = ivo.getQty();

            ivo.setIsSum(true);

            for (int j = 0; j < dbList.size(); j ++){

                ICStockBillEntry jvo = dbList.get(j);
                if (jvo.isDel() || jvo.isSum()){
                    continue;
                }

                int jParentID = jvo.getInterID();
                int jMaterialID = jvo.getItemID();
                int jUnitID = jvo.getUnitID();
                double jPrice = jvo.getConsignPrice();

                if (iParentID != jParentID || iMaterialID != jMaterialID || iUnitID != jUnitID || iPrice != jPrice){
                    continue;
                }

                iQty = iQty + jvo.getQty();
                jvo.setIsDel(true);
            }

            ivo.setQty(iQty);
            ivo.setAuxQty(ivo.getQty());
            ivo.setConsignAmount(ivo.getQty() * ivo.getConsignPrice());
        }

        // 删除掉已经合并的分录
        for (int i = dbList.size() -1; i >= 0 ; i --){
            ICStockBillEntry ivo = dbList.get(i);
            if (ivo.isDel()){
            	dbList.remove(i);
            }
        }

        // 重新设置分录序号
        for (ICStockBillEntry vo : dbList){

            if (vo.getEntryID() != -1){
                continue;
            }

            int max = 0;
            for (ICStockBillEntry svo : dbList){

                if (vo.getInterID() == svo.getInterID()){
                    if (max <= svo.getEntryID()){
                        max = svo.getEntryID() + 1;
                    }
                }
            }

            vo.setEntryID(max);
        }

        // 持久化分录
        new ICStockBillEntryDao().create(dbList);
    }
    
}
