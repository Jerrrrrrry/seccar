package com.lhcy.sync.service;

import java.util.ArrayList;
import java.util.List;

import com.lhcy.sync.dao.LoanDao;
import com.lhcy.sync.dao.TradeDao;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.dto.SummaryDto;
import com.lhcy.sync.domain.dto.SummaryLoanDto;
import com.lhcy.sync.domain.dto.SummaryTradeDto;
import com.lhcy.sync.web.form.SumSummaryForm;

public class SumSummaryService {
	private TradeDao tradeDao = new TradeDao();
	private LoanDao loanDao = new LoanDao();
	
	public List<SummaryDto> list(SumSummaryForm form) throws Exception {
		CarSummaryDto carTradeSummaryDto = tradeDao.getSummaryForCarTrade();
		CarSummaryDto carLoanSummaryDto = loanDao.getSummaryForCarLoan();
		CarSummaryDto tradeProfitDto = tradeDao.getSummaryTradeProfit();
		CarSummaryDto loaninterestoutDto = loanDao.getSummaryLoanOutInterest();
		CarSummaryDto loaninterestinDto = loanDao.getSummaryLoanInInterest();
		CarSummaryDto tradeInterestInThirdDto = tradeDao.getSumTradeInterestInThird();
		CarSummaryDto tradeInterestOutThirdDto = tradeDao.getSumTradeInterestOutThird();
		CarSummaryDto tradeInterestInSelfDto = tradeDao.getSumTradeInterestInSelf();
		CarSummaryDto tradeInterestOutSelfDto = tradeDao.getSumTradeInterestOutSelf();
		
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
		dto.setCategory("卖车利润合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方总利润");
		dto.setDetails(tradeProfitDto.getTotalprofitthird()+"元");
		args.add(dto);
		

		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收总利润");
		dto.setDetails(tradeProfitDto.getTotalprofitself()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总利润");
		dto.setDetails(tradeProfitDto.getTotalprofitself()+tradeProfitDto.getTotalprofitthird()+"元");
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
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车已出库利息成本");
		dto.setDetails(loaninterestoutDto.getLoaninterestout()+"元");
		args.add(dto);
		

		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车在库利息成本");
		dto.setDetails(loaninterestinDto.getLoaninterestin()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("押车总利息成本");
		dto.setDetails(loaninterestoutDto.getLoaninterestout()+loaninterestinDto.getLoaninterestin()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方未售车利息成本");
		dto.setDetails(tradeInterestInThirdDto.getTradeinterestinthird()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方已售车利息成本");
		dto.setDetails(tradeInterestOutThirdDto.getTradeinterestoutthird()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("三方车总利息成本");
		dto.setDetails(tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird()+"元");
		args.add(dto);

		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收未售车利息成本");
		dto.setDetails(tradeInterestInSelfDto.getTradeinterestinself()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收已售车利息成本");
		dto.setDetails(tradeInterestOutSelfDto.getTradeinterestoutself()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("自收车总利息成本");
		dto.setDetails(tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("收卖车总利息成本");
		dto.setDetails(tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird()+tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself()+"元");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总利息成本");
		dto.setDetails(loaninterestoutDto.getLoaninterestout()+loaninterestinDto.getLoaninterestin()+tradeInterestInThirdDto.getTradeinterestinthird() + tradeInterestOutThirdDto.getTradeinterestoutthird()+tradeInterestInSelfDto.getTradeinterestinself() + tradeInterestOutSelfDto.getTradeinterestoutself()+"元");
		args.add(dto);
		
		return args;
	}

	public List<SummaryTradeDto> listTrade(SumSummaryForm form) throws Exception {
		return tradeDao.listTrade();
	}
	public List<SummaryLoanDto> listLoan(SumSummaryForm form) throws Exception {
		return loanDao.listLoan();
	}

}
