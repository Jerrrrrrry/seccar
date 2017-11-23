package com.lhcy.sync.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;

import com.lhcy.sync.dao.LoanDao;
import com.lhcy.sync.dao.ParkingDao;
import com.lhcy.sync.dao.TradeDao;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.dto.ParkingSummaryDto;
import com.lhcy.sync.domain.dto.SummaryDto;
import com.lhcy.sync.domain.dto.SummaryLoanDto;
import com.lhcy.sync.domain.dto.SummaryTradeDto;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.web.form.LoanForm;
import com.lhcy.sync.web.form.SumSummaryForm;
import com.lhcy.sync.web.form.TradeForm;

public class SumSummaryService {
	private TradeDao tradeDao = new TradeDao();
	private LoanDao loanDao = new LoanDao();
	private ParkingDao parkingDao = new ParkingDao();
	
	public List<CarSummaryDto> listStock(SumSummaryForm form) throws Exception {
		List<CarSummaryDto> newlist = new ArrayList<CarSummaryDto>();
//		TradeForm tradeForm = new TradeForm();
//		tradeForm.setFilterissold("0");
//		tradeForm.setFilterisdeleted("0");
//		tradeForm.setSort("createdts");
//		tradeForm.setOrder("desc");
//		List<TradeDto> list  = tradeDao.list(0, Integer.MAX_VALUE, tradeForm, "管理员");
//		for (TradeDto dto : list)
//		{
//			SummaryDto newdto = new SummaryDto();
//			newdto.setCarDesc(dto.getVehicledesc());
//			newdto.setCategory("押车");
//			newdto.setInstockDate(removeTime(dto.getPurchasedate()));
//			newdto.setActualPaiedToLoaner(String.valueOf(dto.getActualloan()));
//			newlist.add(newdto);
//		}
		List<CarSummaryDto> list  = tradeDao.getSummaryForCarTrade();
		for (CarSummaryDto dto : list)
		{
			if (!dto.isSold()){
				newlist.add(dto);
			}
		}
		CarSummaryDto dto = loanDao.getSummaryForCarLoan();
		newlist.add(dto);
		return newlist;
	}
	public List<CarSummaryDto> listSold(SumSummaryForm form) throws Exception {
		List<CarSummaryDto> newlist = new ArrayList<CarSummaryDto>();
		
		List<TradeDto> list  = tradeDao.getTradeCarsSellInPeriod(getFirstDayOfCurrentMonth(0), getLastDayOfCurrentMonth(0));
		 List<CarSummaryDto> accruedSummary = tradeDao.getSummaryForCarTrade();
		CarSummaryDto sanfang = new CarSummaryDto();sanfang.setCarType("三方车");newlist.add(sanfang);
		CarSummaryDto zishouche = new CarSummaryDto();zishouche.setCarType("自收车");newlist.add(zishouche);
		for (TradeDto dto : list) {
			if ("第三方".equalsIgnoreCase(dto.getVehicletype()))
			{
				sanfang.setOutStockCarsAmount(sanfang.getOutStockCarsAmount()+1);
				sanfang.setTotalPuchasePrice(sanfang.getTotalPuchasePrice() + dto.getPurchaseprice());
				sanfang.setTotalSellPrice(sanfang.getTotalSellPrice() + dto.getSellprice());
			} else {
				zishouche.setOutStockCarsAmount(zishouche.getOutStockCarsAmount()+1);
				zishouche.setTotalPuchasePrice(zishouche.getTotalPuchasePrice() + dto.getPurchaseprice());
				zishouche.setTotalSellPrice(zishouche.getTotalSellPrice() + dto.getSellprice());
			}
		}
		sanfang.setTotalProfit(sanfang.getTotalSellPrice() - sanfang.getTotalPuchasePrice());
		zishouche.setTotalProfit(zishouche.getTotalSellPrice() - zishouche.getTotalPuchasePrice());
		
		for (CarSummaryDto dto : accruedSummary)
		{
			if (dto.isSold()){
				if ("第三方".equalsIgnoreCase(dto.getCarType()))
				{
					sanfang.setAccruedTotalProfit(dto.getTotalSellPrice()-dto.getOutStockCarsMoney());
					
				} else if ("自收车".equalsIgnoreCase(dto.getCarType())){
					zishouche.setAccruedTotalProfit(dto.getTotalSellPrice()-dto.getOutStockCarsMoney());
				}
			}
		}
		
		
		List<LoanDto> list2 = loanDao.getLoanCarsSellInPeriod(getFirstDayOfCurrentMonth(0), getLastDayOfCurrentMonth(0));
		CarSummaryDto chedai = new CarSummaryDto();newlist.add(chedai);
		chedai.setCarType("抵押车");
		chedai.setOutStockCarsAmount(list2.size());
		for (LoanDto dto : list2) {
			chedai.setTotalPuchasePrice(chedai.getTotalPuchasePrice() + dto.getActualloan());
			chedai.setTotalSellPrice(chedai.getTotalSellPrice() + dto.getActualloan() + dto.getInterestpaid());
		}
		chedai.setTotalProfit(chedai.getTotalSellPrice() - chedai.getTotalPuchasePrice());
		CarSummaryDto dd = loanDao.getSummaryForCarLoan();
		chedai.setAccruedTotalProfit(dd.getTotalProfit());
		return newlist;
	}
	public List<SummaryLoanDto> listLoans(SumSummaryForm form) throws Exception {
		List<SummaryLoanDto> newlist = new ArrayList<SummaryLoanDto>();
		List<LoanDto> list2 = loanDao.getLoanCarsSellInPeriod(getFirstDayOfCurrentMonth(0), getLastDayOfCurrentMonth(0));
		SummaryLoanDto chedai = new SummaryLoanDto();newlist.add(chedai);
		chedai.setCategory("抵押车当月");
		chedai.setTotalOutStock(list2.size());
		for (LoanDto dto : list2) {
			chedai.setSumTotalMidinterest(chedai.getSumTotalMidinterest() + dto.getMidinterest());//中介返点合计
			chedai.setSumTotalActualLoan(chedai.getSumTotalActualLoan() + dto.getActualloan());//实际打款金额合计
			chedai.setSumTotalPaidInterest(chedai.getSumTotalPaidInterest() + dto.getInterestpaid());//已付利息合计
		}
		chedai.setSumTotalReturn(chedai.getSumTotalActualLoan()+chedai.getSumTotalPaidInterest());
		
		SummaryLoanDto chedaileiji = new SummaryLoanDto();newlist.add(chedaileiji);
		SummaryLoanDto carloanSummary = loanDao.listLoanReport();
		SummaryLoanDto carloanSummary2 = loanDao.listLoanReportReturned();
		chedaileiji.setCategory("抵押车累计");
		chedaileiji.setSumTotalMidinterest(Double.valueOf(carloanSummary.getMidinterest()));
		chedaileiji.setSumTotalActualLoan(Double.valueOf(carloanSummary.getActualloan()));
		chedaileiji.setSumTotalPaidInterest(Double.valueOf(carloanSummary.getInterestpaid()));
		chedaileiji.setSumTotalReturn(Double.valueOf(carloanSummary2.getActualloan())+chedaileiji.getSumTotalPaidInterest()-Double.valueOf(carloanSummary2.getMidinterestrate()));
		return newlist;
	}
	public List<TradeDto> getTradeCarsSellInPeriod() throws Exception {
		return tradeDao.getTradeCarsSellInPeriod(getFirstDayOfCurrentMonth(0), getLastDayOfCurrentMonth(0));
	}
	public List<LoanDto> getLoanCarsSellInPeriod() throws Exception {
		return loanDao.getLoanCarsSellInPeriod(getFirstDayOfCurrentMonth(0), getLastDayOfCurrentMonth(0));
		
	}
	
	private String getFirstDayOfCurrentMonth(int offset){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 Calendar   cal_1=Calendar.getInstance();
		 cal_1.add(Calendar.MONTH, offset);
	   cal_1.set(Calendar.DAY_OF_MONTH,1);
	   String firstDay = format.format(cal_1.getTime());
	   return firstDay;
	}
	private String getLastDayOfCurrentMonth(int offset){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 Calendar ca = Calendar.getInstance(); 
		 ca.add(Calendar.MONTH, offset);
		   ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		   String last = format.format(ca.getTime());
		   return last;
	}
  
	public List<TradeDto> listTradeStock(SumSummaryForm form) throws Exception {
		TradeForm tradeForm = new TradeForm();
		tradeForm.setFilterissold("0");
		tradeForm.setFilterisdeleted("0");
		tradeForm.setSort("createdts");
		tradeForm.setOrder("desc");
		tradeForm.setFilterField("isdeteled");
		List<TradeDto> list  = tradeDao.list(0, Integer.MAX_VALUE, tradeForm, "管理员");
		return list;
	}
	public List<LoanDto> listLoanStock(SumSummaryForm form) throws Exception {
		LoanForm loanForm = new LoanForm();
		loanForm.setFilterisreturned("0");
		loanForm.setFilterisdeleted("0");
		loanForm.setFilterisabandon("0");
		loanForm.setFilterField("isdeteled");
		loanForm.setSort("createdts");
		loanForm.setOrder("desc");
		List<LoanDto> list  = loanDao.list(0, Integer.MAX_VALUE, loanForm, "管理员");
		return list;
	}
	private String removeTime(String date){
		if (date != null){
			date = date.replace("00:00:00.0", "");
		}
		return date;
	}
	public List<LoanDto> listInterestCostOnly(SumSummaryForm form) throws Exception {
		return loanDao.list(0, Integer.MAX_VALUE, new LoanForm(), "管理员");
	}
	public List<TradeDto> listInterestCostOnlyForTrade(SumSummaryForm form) throws Exception {
		return tradeDao.list(0, Integer.MAX_VALUE, new TradeForm(), "管理员");
	}
	public List<CarSummaryDto> listInterestCost(SumSummaryForm form) throws Exception {
		List<CarSummaryDto> newlist = new ArrayList<CarSummaryDto>();
		
		Calendar currentMonth = Calendar.getInstance();
		String month = String.valueOf(currentMonth.get(Calendar.MONTH));
		currentMonth.add(Calendar.MONTH, -1);
		String previousMonth = String.valueOf(currentMonth.get(Calendar.MONTH));
		System.out.print(previousMonth + " " + month);
		
		
		List<TradeDto> list3 = tradeDao.list(0, Integer.MAX_VALUE, new TradeForm(), "管理员");
		CarSummaryDto sanfangche = new CarSummaryDto();newlist.add(sanfangche);
		sanfangche.setCarType("三方车");
		double accruedTotalCost1 = 0;
		double previousMonthCost1 = 0;
		double currentMonthCost1 = 0;
		
		CarSummaryDto zishouche = new CarSummaryDto();newlist.add(zishouche);
		zishouche.setCarType("自收车");
		double accruedTotalCost2 = 0;
		double previousMonthCost2 = 0;
		double currentMonthCost2 = 0;
		for (TradeDto dto : list3) {
			if("第三方".equalsIgnoreCase(dto.getVehicletype()))
			{
				for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
				{
					accruedTotalCost1 = accruedTotalCost1 + a.getValue().doubleValue();
					if (month.equals(a.getKey()))
					{
						currentMonthCost1 = currentMonthCost1 + a.getValue().doubleValue();
					}
					if (previousMonth.equals(a.getKey()))
					{
						previousMonthCost1 = previousMonthCost1 + a.getValue().doubleValue();
					}
				}
			} else if ("自收车".equalsIgnoreCase(dto.getVehicletype()))
			{
				for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
				{
					accruedTotalCost2 = accruedTotalCost2 + a.getValue().doubleValue();
					if (month.equals(a.getKey()))
					{
						currentMonthCost2 = currentMonthCost2 + a.getValue().doubleValue();
					}
					if (previousMonth.equals(a.getKey()))
					{
						previousMonthCost2 = previousMonthCost2 + a.getValue().doubleValue();
					}
				}
			}
		}
		sanfangche.setPreviousMonthCost(previousMonthCost1);
		sanfangche.setCurrentMonthCost(currentMonthCost1);
		sanfangche.setAccruedTotalCost(accruedTotalCost1);
		
		zishouche.setPreviousMonthCost(previousMonthCost2);
		zishouche.setCurrentMonthCost(currentMonthCost2);
		zishouche.setAccruedTotalCost(accruedTotalCost2);
		
		
		List<LoanDto> list2 = loanDao.list(0, Integer.MAX_VALUE, new LoanForm(), "管理员");
		CarSummaryDto chedai = new CarSummaryDto();newlist.add(chedai);
		chedai.setCarType("抵押车");
		double accruedTotalCost = 0;
		double previousMonthCost = 0;
		double currentMonthCost = 0;
		for (LoanDto dto : list2) {
			for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
			{
				accruedTotalCost = accruedTotalCost + a.getValue().doubleValue();
				if (month.equals(a.getKey()))
				{
					currentMonthCost = currentMonthCost + a.getValue().doubleValue();
				}
				if (previousMonth.equals(a.getKey()))
				{
					previousMonthCost = previousMonthCost + a.getValue().doubleValue();
				}
			}
		}
		chedai.setPreviousMonthCost(previousMonthCost);
		chedai.setCurrentMonthCost(currentMonthCost);
		chedai.setAccruedTotalCost(accruedTotalCost);
		return newlist;
	}
	
	
	
	public List<SummaryLoanDto> list2(SumSummaryForm form) throws Exception {
		List<SummaryLoanDto> carloanSummary = loanDao.listLoanReportEachMonth();
		return carloanSummary;
	}
	public List<SummaryDto> list(SumSummaryForm form) throws Exception {
		CarSummaryDto carTradeSummaryDto = new  CarSummaryDto();//tradeDao.getSummaryForCarTrade();
		CarSummaryDto carLoanSummaryDto = loanDao.getSummaryForCarLoan();
		CarSummaryDto tradeProfitDto = tradeDao.getSummaryTradeProfit();
		CarSummaryDto loaninterestoutDto = loanDao.getSummaryLoanOutInterest();
		CarSummaryDto loaninterestinDto = loanDao.getSummaryLoanInInterest();
		CarSummaryDto tradeInterestInThirdDto = tradeDao.getSumTradeInterestInThird();
		CarSummaryDto tradeInterestOutThirdDto = tradeDao.getSumTradeInterestOutThird();
		CarSummaryDto tradeInterestInSelfDto = tradeDao.getSumTradeInterestInSelf();
		CarSummaryDto tradeInterestOutSelfDto = tradeDao.getSumTradeInterestOutSelf();
		ParkingSummaryDto parkingSummaryDto= parkingDao.getSummaryForCarParking();
		SummaryLoanDto carloanSummary = loanDao.listLoanReport();
		
		List<SummaryDto> args = new ArrayList<SummaryDto>();
		SummaryDto dto = new SummaryDto();
		dto.setCategory("库存合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车");
		dto.setDetails(carLoanSummaryDto.getInStockCarsAmount()+"辆");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("收车");
		dto.setDetails(carTradeSummaryDto.getInStockCarsAmount()+"辆");
		args.add(dto);
		
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("合计");
		dto.setDetails((carLoanSummaryDto.getInStockCarsAmount()+carTradeSummaryDto.getInStockCarsAmount())+"辆");
		args.add(dto);
		
		/****************************/
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("卖车合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车");
		dto.setDetails(carLoanSummaryDto.getOutStockCarsAmount()+"辆");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("收车");
		dto.setDetails(carTradeSummaryDto.getOutStockCarsAmount()+"辆");
		args.add(dto);
		
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("合计");
		dto.setDetails((carLoanSummaryDto.getOutStockCarsAmount()+carTradeSummaryDto.getOutStockCarsAmount())+"辆");
		args.add(dto);
		
		/****************************/
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("停车合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("正在停车车辆");
		dto.setDetails(parkingSummaryDto.getInStockCarsAmount()+"辆");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("已出库车辆");
		dto.setDetails(parkingSummaryDto.getOutStockCarsAmount()+"辆");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("已出库停车总费用");
		dto.setDetails(toTwoDigits(parkingSummaryDto.getOutStockTotalFee())+"元");
		args.add(dto);
		
		
		/****************************/
		/****************************/
		/*dto = new SummaryDto();
		dto.setCategory("");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("押车汇总");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总借款金额合计");
		dto.setDetails((carloanSummary.getBorrowamount()==null?"0.00":carloanSummary.getBorrowamount())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("实际打款金额合计");
		dto.setDetails((carloanSummary.getActualloan()==null?"0.00":carloanSummary.getActualloan())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("已付利息合计");
		dto.setDetails((carloanSummary.getInterestpaid()==null?"0.00":carloanSummary.getInterestpaid())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("中介返点合计");
		dto.setDetails((carloanSummary.getMidinterest()==null?"0.00":carloanSummary.getMidinterest())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("已还本金合计(减去本金差)");
		dto.setDetails(carloanSummary.getActualreturn()+"元");
		args.add(dto);
		*/
		
		/****************************/
		/****************************/
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("卖车利润合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方总利润");
		dto.setDetails(toTwoDigits(tradeProfitDto.getTotalprofitthird())+"元");
		args.add(dto);
		

		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收总利润");
		dto.setDetails(toTwoDigits(tradeProfitDto.getTotalprofitself())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总利润");
		dto.setDetails(toTwoDigits(tradeProfitDto.getTotalprofitself()+tradeProfitDto.getTotalprofitthird())+"元");
		args.add(dto);
		
		/**************利息成本合计**************/
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("利息成本合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		/*dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车已出库利息成本");
		dto.setDetails(toTwoDigits(loaninterestoutDto.getLoaninterestout())+"元");
		args.add(dto);
		

		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车在库利息成本");
		dto.setDetails(toTwoDigits(loaninterestinDto.getLoaninterestin())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车总利息成本");
		dto.setDetails(toTwoDigits(loaninterestoutDto.getLoaninterestout()+loaninterestinDto.getLoaninterestin())+"元");
		args.add(dto);*/
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方未售车利息成本");
		dto.setDetails(toTwoDigits(tradeInterestInThirdDto.getTradeinterestinthird())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方已售车利息成本");
		dto.setDetails(toTwoDigits(tradeInterestOutThirdDto.getTradeinterestoutthird())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方车总利息成本");
		dto.setDetails(toTwoDigits(tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird())+"元");
		args.add(dto);

		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收未售车利息成本");
		dto.setDetails(toTwoDigits(tradeInterestInSelfDto.getTradeinterestinself())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收已售车利息成本");
		dto.setDetails(toTwoDigits(tradeInterestOutSelfDto.getTradeinterestoutself())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收车总利息成本");
		dto.setDetails(toTwoDigits(tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("收卖车总利息成本");
		dto.setDetails(toTwoDigits(tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird()+tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself())+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总利息成本");
		dto.setDetails(toTwoDigits(loaninterestoutDto.getLoaninterestout()+loaninterestinDto.getLoaninterestin()+tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird()+tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself())+"元");
		args.add(dto);
		
		return args;
	}

	public List<SummaryTradeDto> listTrade(SumSummaryForm form) throws Exception {
		return tradeDao.listTrade();
	}
	public List<SummaryLoanDto> listLoan(SumSummaryForm form) throws Exception {
		return loanDao.listLoan();
	}
	private String toTwoDigits(double d){
		DecimalFormat    df   = new DecimalFormat("#################0.00");   
		return df.format(d);
	}

}
