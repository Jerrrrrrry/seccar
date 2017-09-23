package com.lhcy.sync.service;

import java.util.ArrayList;
import java.util.List;

import com.lhcy.sync.dao.LoanDao;
import com.lhcy.sync.dao.TradeDao;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.SummaryDto;
import com.lhcy.sync.web.form.SumSummaryForm;

public class SumSummaryService {
	private TradeDao tradeDao = new TradeDao();
	private LoanDao loanDao = new LoanDao();
	
	public List<SummaryDto> list(SumSummaryForm form) throws Exception {
		CarSummaryDto carTradeSummaryDto = tradeDao.getSummaryForCarTrade();
		CarSummaryDto carLoanSummaryDto = loanDao.getSummaryForCarLoan();
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
		dto.setCategory("费用合计");
		dto.setDetails("");
		dto.setItemType("");
		args.add(dto);
		
		dto = new SummaryDto();
		dto.setCategory("");
		dto.setItemType("总利润");
		dto.setDetails("113,012.00元");
		args.add(dto);
		
		
		return args;
	}

}
