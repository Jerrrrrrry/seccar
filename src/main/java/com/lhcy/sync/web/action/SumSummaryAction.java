package com.lhcy.sync.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.CarSummaryDto;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.dto.SummaryLoanDto;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.service.SumSummaryService;
import com.lhcy.sync.web.form.SumSummaryForm;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class SumSummaryAction extends DispatchAction {

    private Logger logger = Logger.getLogger(TradeReportAction.class);
    
    public ActionForward listStock(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

//          	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//          Date now = new Date();
//          sdf.format(date);
        	
        	SumSummaryForm form = (SumSummaryForm)m;
//        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
        	SumSummaryService ts = new SumSummaryService();
//            int pageSize = form.getRows();
//            int pageNow = form.getPage();
//            int count = 50;
//            int count = ts.count(form);
//            int rowBegin = (pageNow - 1) * pageSize;
//            int rowEnd = rowBegin + pageSize;
//            if(rowBegin > 0) rowBegin++;
        	 List<CarSummaryDto> list =  new ArrayList<CarSummaryDto>();
             list = ts.listStock(form);
            JsonUtils.printFromList(response, list, list.size());
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
   

 

    	private HSSFWorkbook generateStockXls() throws Exception
    	{
    		
    		SumSummaryService ts = new SumSummaryService();
    		List<TradeDto> tradeList  = ts.listTradeStock(null);
    		List<LoanDto> loanList  = ts.listLoanStock(null);
    		HSSFWorkbook wb = new HSSFWorkbook();
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String dateStr = format.format(new Date());
    		dateStr = dateStr + " 汇总";
    		HSSFSheet huizongSheet = wb.createSheet(dateStr);
    		HSSFRow huizongrow = huizongSheet.createRow((int) 0);
    		huizongrow.createCell((short) 0).setCellValue("车辆类别");
    		huizongrow.createCell((short) 1).setCellValue("当前在库数量(辆)");
    		huizongrow.createCell((short) 2).setCellValue("总计金额(元)");

    		 List<CarSummaryDto> list = ts.listStock(null);
    		 int q = 1;
     		for (CarSummaryDto dto : list)
     		{
     			HSSFRow row = huizongSheet.createRow(q);q++;
     			row.createCell((short) 0).setCellValue(dto.getCarType());
     			row.createCell((short) 1).setCellValue(dto.getInStockCarsAmount());
     			row.createCell((short) 2).setCellValue(dto.getInStockCarMoney());
     		}
    		
    		HSSFSheet sanfangSheet = wb.createSheet("三方车");
    		HSSFRow sanfangrow = sanfangSheet.createRow((int) 0);
    		createTradeHeaders(sanfangrow);
    		
    		HSSFSheet zishouSheet = wb.createSheet("自收车");
    		HSSFRow zishourow = zishouSheet.createRow((int) 0);
    		createTradeHeaders(zishourow);
    		int i = 1;
    		int k = 1;
//    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    		for (TradeDto dto:tradeList)
        	{
    			HSSFRow row  = null;
    			if ("第三方".equalsIgnoreCase(dto.getVehicletype()))
    			{
    				row = sanfangSheet.createRow(i);
    				i++;
    			} else {
    				row = zishouSheet.createRow(k);
    				k++;
    			}
    			row.createCell((short) 0).setCellValue(dto.getTradername());
    			row.createCell((short) 1).setCellValue(dto.getPurchasedate());
    			row.createCell((short) 2).setCellValue(dto.getLicenseno());
    			row.createCell((short) 3).setCellValue(dto.getVehicledesc());
    			row.createCell((short) 4).setCellValue(dto.getVehicletype());
    			row.createCell((short) 5).setCellValue(dto.getPurchaseprice());
    			row.createCell((short) 6).setCellValue(dto.getOwnerid());
    			row.createCell((short) 7).setCellValue(dto.getOwnername());
    			row.createCell((short) 8).setCellValue(dto.getOwnermobile());
    			row.createCell((short) 9).setCellValue(dto.getInterestrate());
    			row.createCell((short) 10).setCellValue(dto.getActualloan());
    			row.createCell((short) 11).setCellValue(dto.getLixichengben());
    			row.createCell((short) 12).setCellValue(dto.getEarnest());
    			row.createCell((short) 13).setCellValue(dto.getSellprice());
    			row.createCell((short) 14).setCellValue(dto.getSelldate());
    			row.createCell((short) 15).setCellValue(dto.getBuyername());
    			row.createCell((short) 16).setCellValue(dto.getBuyerid());
    			row.createCell((short) 17).setCellValue(dto.getBuyermobile());
    			row.createCell((short) 18).setCellValue(dto.getTradecost());
        	}
    		HSSFSheet chedaiSheet = wb.createSheet("车贷");
    		HSSFRow loanrow = chedaiSheet.createRow((int) 0);
    		createLoanHeaders(loanrow);
    		int j = 1;
    		for (LoanDto dto : loanList)
    		{
    			HSSFRow row = chedaiSheet.createRow(j);j++;
    			row.createCell((short) 0).setCellValue(dto.getTradername());
    			row.createCell((short) 1).setCellValue(dto.getBorrowdate());
    			row.createCell((short) 2).setCellValue(dto.getLicenseno());
    			row.createCell((short) 3).setCellValue(dto.getVehicledesc());
    			row.createCell((short) 4).setCellValue("押车");
    			row.createCell((short) 5).setCellValue(dto.getOwnername());
    			row.createCell((short) 6).setCellValue(dto.getOwnerid());
    			row.createCell((short) 7).setCellValue(dto.getMobileno());
    			row.createCell((short) 8).setCellValue(dto.getReturndate());
    			row.createCell((short) 9).setCellValue(dto.getPeriodmonths());
//    			row.createCell((short) 10).setCellValue(dto.getTotalinterest());
    			row.createCell((short) 10).setCellValue(dto.getBorrowamount());
    			row.createCell((short) 11).setCellValue(dto.getInterestrate());
    			row.createCell((short) 12).setCellValue(dto.getEarnest());
    			row.createCell((short) 13).setCellValue(dto.getActualloan());
    			row.createCell((short) 14).setCellValue(dto.getLixichengben());
    			row.createCell((short) 15).setCellValue(dto.getParkingfee());
    			row.createCell((short) 16).setCellValue(dto.getOtherfee());
    			row.createCell((short) 17).setCellValue(dto.getActualmonths());
    			row.createCell((short) 18).setCellValue(dto.getInterestpaid());
    			row.createCell((short) 19).setCellValue(dto.getMidinterestrate());
    			row.createCell((short) 20).setCellValue(dto.getMidinterest());
    			row.createCell((short) 21).setCellValue(dto.getComments());
    			row.createCell((short) 22).setCellValue(dto.getActualreturn());
    			row.createCell((short) 23).setCellValue(dto.getActualreturndate());
    		}
    		
    		return wb;
    }
    	private void createTradeHeaders(HSSFRow row) {
    		String[] tradeHeaders = new String[]{"经办人","收车日期","车牌号码","车辆描述","车辆类型","收车价","卖车人身份证","卖车人姓名","卖车人电话",
    				"利率","实际借款金额","利息成本","定金","销售价格","销售日期","购车人姓名","购车人身份证","购车人电话","交易费用"};
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = row.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    	}
    	private void createLoanHeaders(HSSFRow row) {
    		String[] tradeHeaders = new String[]{"经办人","借款日期","车牌号码","车辆描述","车辆类型","抵押人姓名","抵押人身份证","抵押人手机号","约定还款日期"
    				,"还款周期(月)","借款金额","利率","定金","实际打款金额","利息成本","停车费","其他费用","实际还款周期","已付利息"
    				,"已还本金差","中介返点","备注","已还本金额","归还本金日期"};
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = row.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    	}
    	/********************sold start***************************/
    	public ActionForward listSold(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {

            try {
            	if (ContextUtils.getCurrentUserID(request) == null) {
                    throw new Exception(SysConstant.M_NO_LOGIN);
                }

//              	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//              Date now = new Date();
//              sdf.format(date);
            	
            	SumSummaryForm form = (SumSummaryForm)m;
//            	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//            	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
            	SumSummaryService ts = new SumSummaryService();
//                int pageSize = form.getRows();
//                int pageNow = form.getPage();
//                int count = 50;
//                int count = ts.count(form);
//                int rowBegin = (pageNow - 1) * pageSize;
//                int rowEnd = rowBegin + pageSize;
//                if(rowBegin > 0) rowBegin++;
            	 List<CarSummaryDto> list =  new ArrayList<CarSummaryDto>();
                 list = ts.listSold(form);
                JsonUtils.printFromList(response, list, list.size());
            }catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                request.getSession().setAttribute("ex", e);
            }

            return null;
        }
        public ActionForward downloadStock(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {        

            //获取用户的名称
        	try {
            	if (ContextUtils.getCurrentUserID(request) == null) {
                    throw new Exception(SysConstant.M_NO_LOGIN);
                }
            	Calendar calendar = Calendar.getInstance();
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
            	String filename = "库存合计"+formatter.format(calendar.getTime())+".xls";
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(filename,"utf-8"));
            
            //下载文件
            //1.先获取到要下载文件的绝对路径
           
//            ByteArrayOutputStream fis=new ByteArrayOutputStream();
            OutputStream os=null;
            try {
                
                os=response.getOutputStream();
                this.generateStockXls().write(os);                
            } catch (Exception e) {
                
                e.printStackTrace();
            } finally{
                try {
                    os.close();
//                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
                
            }catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                request.getSession().setAttribute("ex", e);
            }
            
            return null;
    }
        public ActionForward downloadSold(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {        

            //获取用户的名称
        	try {
            	if (ContextUtils.getCurrentUserID(request) == null) {
                    throw new Exception(SysConstant.M_NO_LOGIN);
                }
            	Calendar calendar = Calendar.getInstance();
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
            	String filename = "卖车合计"+formatter.format(calendar.getTime())+".xls";
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(filename,"utf-8"));
            
            //下载文件
            //1.先获取到要下载文件的绝对路径
           
//            ByteArrayOutputStream fis=new ByteArrayOutputStream();
            OutputStream os=null;
            try {
                
                os=response.getOutputStream();
                this.generateSoldXls().write(os);                
            } catch (Exception e) {
                
                e.printStackTrace();
            } finally{
                try {
                    os.close();
//                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
                
            }catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                request.getSession().setAttribute("ex", e);
            }
            
            return null;
    }
        private HSSFWorkbook generateSoldXls() throws Exception
    	{
    		SumSummaryService ts = new SumSummaryService();
    		List<TradeDto> tradeList  = ts.getTradeCarsSellInPeriod();
    		List<LoanDto> loanList  = ts.getLoanCarsSellInPeriod();
    		HSSFWorkbook wb = new HSSFWorkbook();
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String dateStr = format.format(new Date());
    		dateStr = dateStr + " 汇总";
    		HSSFSheet huizongSheet = wb.createSheet(dateStr);
    		HSSFRow huizongrow = huizongSheet.createRow((int) 0);
    		huizongrow.createCell((short) 0).setCellValue("车辆类别");
    		huizongrow.createCell((short) 1).setCellValue("本月出库数量(辆)");
    		huizongrow.createCell((short) 2).setCellValue("收车价格合计(元)");
    		huizongrow.createCell((short) 3).setCellValue("卖车价格合计(元)");
    		huizongrow.createCell((short) 4).setCellValue("当月利润(元)");
    		huizongrow.createCell((short) 5).setCellValue("累计利润(元)");

 
    		List<CarSummaryDto> list =  ts.listSold(null);
   		 	int q = 1;
   		 	double accruedtotalProfit = 0;
    		for (CarSummaryDto dto : list)
    		{
    			HSSFRow row = huizongSheet.createRow(q);q++;
    			row.createCell((short) 0).setCellValue(dto.getCarType());
    			row.createCell((short) 1).setCellValue(dto.getOutStockCarsAmount());
    			row.createCell((short) 2).setCellValue(dto.getTotalPuchasePrice());
    			row.createCell((short) 3).setCellValue(dto.getTotalSellPrice());
    			row.createCell((short) 4).setCellValue(dto.getTotalProfit());
    			row.createCell((short) 5).setCellValue(dto.getAccruedTotalProfit());
    			accruedtotalProfit = accruedtotalProfit + dto.getAccruedTotalProfit();
    		}
    		HSSFRow row22 = huizongSheet.createRow(q);
    		row22.createCell((short) 0).setCellValue("合计");
    		row22.createCell((short) 5).setCellValue(accruedtotalProfit);
			
    		HSSFSheet sanfangSheet = wb.createSheet("三方车");
    		HSSFRow sanfangrow = sanfangSheet.createRow((int) 0);
    		String[] tradeHeaders = new String[]{"收车日期","卖车人姓名","车辆描述","收车价","是否出库","销售价格","销售日期","利润"};
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = sanfangrow.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    		
    		HSSFSheet zishouSheet = wb.createSheet("自收车");
    		HSSFRow zishourow = zishouSheet.createRow((int) 0);
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = zishourow.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    		int i = 1;
    		int k = 1;
//    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    		for (TradeDto dto:tradeList)
        	{
    			HSSFRow row  = null;
    			if ("第三方".equalsIgnoreCase(dto.getVehicletype()))
    			{
    				row = sanfangSheet.createRow(i);
    				i++;
    			} else {
    				row = zishouSheet.createRow(k);
    				k++;
    			}
    			int w = 0;
//    			row.createCell((short) w++).setCellValue(dto.getTradername());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getPurchasedate()));
    			row.createCell((short) w++).setCellValue(dto.getOwnername());
//    			row.createCell((short) w++).setCellValue(dto.getLicenseno());
    			row.createCell((short) w++).setCellValue(dto.getVehicledesc());
//    			row.createCell((short) w++).setCellValue(dto.getVehicletype());
    			row.createCell((short) w++).setCellValue(dto.getPurchaseprice());
    			row.createCell((short) w++).setCellValue(dto.getIssold());
//    			row.createCell((short) w++).setCellValue(dto.getOwnerid());
//    			row.createCell((short) w++).setCellValue(dto.getOwnermobile());
//    			row.createCell((short) w++).setCellValue(dto.getInterestrate());
//    			row.createCell((short) w++).setCellValue(dto.getActualloan());
//    			row.createCell((short) w++).setCellValue(dto.getLixichengben());
//    			row.createCell((short) w++).setCellValue(dto.getEarnest());
    			row.createCell((short) w++).setCellValue(dto.getSellprice());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getSelldate()));
//    			row.createCell((short) w++).setCellValue(dto.getBuyername());
//    			row.createCell((short) w++).setCellValue(dto.getBuyerid());
//    			row.createCell((short) w++).setCellValue(dto.getBuyermobile());
//    			row.createCell((short) w++).setCellValue(dto.getTradecost());
    			row.createCell((short) w++).setCellValue(dto.getSellprice() - dto.getPurchaseprice());
        	}
    		HSSFSheet chedaiSheet = wb.createSheet("车贷");
    		HSSFRow loanrow = chedaiSheet.createRow((int) 0);
    		String[] loanHeaders = new String[]{"借款日期","抵押人姓名","车辆描述","实际打款金额","已付利息","中介返点","是否归还","归还本金日期"};
    		for (int i1 = 0; i1 < loanHeaders.length; i1++)
    		{
    			HSSFCell cell = loanrow.createCell((short) i1);
    			cell.setCellValue(loanHeaders[i1]);
    		}
    		int j = 1;
    		for (LoanDto dto : loanList)
    		{
    			int w =0;
    			HSSFRow row = chedaiSheet.createRow(j);j++;
//    			row.createCell((short) w++).setCellValue(dto.getTradername());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getBorrowdate()));
    			row.createCell((short) w++).setCellValue(dto.getOwnername());
//    			row.createCell((short) w++).setCellValue(dto.getLicenseno());
    			row.createCell((short) w++).setCellValue(dto.getVehicledesc());
//    			row.createCell((short) w++).setCellValue("押车");
//    			row.createCell((short) w++).setCellValue(dto.getOwnerid());
//    			row.createCell((short) w++).setCellValue(dto.getMobileno());
//    			row.createCell((short) w++).setCellValue(dto.getReturndate());
//    			row.createCell((short) w++).setCellValue(dto.getPeriodmonths());
//    			row.createCell((short) 10).setCellValue(dto.getTotalinterest());
//    			row.createCell((short) w++).setCellValue(dto.getBorrowamount());
//    			row.createCell((short) w++).setCellValue(dto.getInterestrate());
//    			row.createCell((short) w++).setCellValue(dto.getEarnest());
    			row.createCell((short) w++).setCellValue(dto.getActualloan());
//    			row.createCell((short) w++).setCellValue(dto.getLixichengben());
//    			row.createCell((short) w++).setCellValue(dto.getParkingfee());
//    			row.createCell((short) w++).setCellValue(dto.getOtherfee());
//    			row.createCell((short) w++).setCellValue(dto.getActualmonths());
    			row.createCell((short) w++).setCellValue(dto.getInterestpaid());
//    			row.createCell((short) w++).setCellValue(dto.getMidinterestrate());
    			row.createCell((short) w++).setCellValue(dto.getMidinterest());
//    			row.createCell((short) w++).setCellValue(dto.getComments());
//    			row.createCell((short) w++).setCellValue(dto.getActualreturn());
    			row.createCell((short) w++).setCellValue(dto.getIsreturned());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getActualreturndate()));
    		}
    		return wb;
    }
    	private void createSoldTradeHeaders(HSSFRow row) {
    		String[] tradeHeaders = new String[]{"经办人","收车日期","车牌号码","车辆描述","车辆类型","收车价","卖车人身份证","卖车人姓名","卖车人电话",
    				"利率","实际借款金额","利息成本","定金","销售价格","销售日期","购车人姓名","购车人身份证","购车人电话","交易费用"};
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = row.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    	}
    	private void createSoldLoanHeaders(HSSFRow row) {
    		String[] tradeHeaders = new String[]{"经办人","借款日期","车牌号码","车辆描述","车辆类型","抵押人姓名","抵押人身份证","抵押人手机号","约定还款日期"
    				,"还款周期(月)","借款金额","利率","定金","实际打款金额","利息成本","停车费","其他费用","实际还款周期","已付利息"
    				,"已还本金差","中介返点","备注","已还本金额","归还本金日期"};
    		for (int i = 0; i < tradeHeaders.length; i++)
    		{
    			HSSFCell cell = row.createCell((short) i);
    			cell.setCellValue(tradeHeaders[i]);
    		}
    	}
    	public ActionForward downloadLoan(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {        

            //获取用户的名称
        	try {
            	if (ContextUtils.getCurrentUserID(request) == null) {
                    throw new Exception(SysConstant.M_NO_LOGIN);
                }
            	Calendar calendar = Calendar.getInstance();
            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
            	String filename = "车贷合计"+formatter.format(calendar.getTime())+".xls";
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(filename,"utf-8"));
            
            //下载文件
            //1.先获取到要下载文件的绝对路径
           
//            ByteArrayOutputStream fis=new ByteArrayOutputStream();
            OutputStream os=null;
            try {
                
                os=response.getOutputStream();
                this.generateLoanXls().write(os);                
            } catch (Exception e) {
                
                e.printStackTrace();
            } finally{
                try {
                    os.close();
//                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
                
            }catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                request.getSession().setAttribute("ex", e);
            }
            
            return null;
    }
    	private HSSFWorkbook generateLoanXls() throws Exception
    	{
    		SumSummaryService ts = new SumSummaryService();
    		List<LoanDto> loanList  = ts.listInterestCostOnly(null);
    		HSSFWorkbook wb = new HSSFWorkbook();
    
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String dateStr = format.format(new Date());
    		dateStr = dateStr + " 汇总";
    		HSSFSheet huizongSheet = wb.createSheet(dateStr);
    		HSSFRow huizongrow = huizongSheet.createRow((int) 0);
    		huizongrow.createCell((short) 0).setCellValue("车辆类别");
    		huizongrow.createCell((short) 1).setCellValue("本月出库数量(辆)");
    		huizongrow.createCell((short) 2).setCellValue("中介返点合计(元)");
    		huizongrow.createCell((short) 3).setCellValue("实际打款金额合计(元)");
    		huizongrow.createCell((short) 4).setCellValue("已付利息合计(元)");
    		huizongrow.createCell((short) 5).setCellValue("回款总额合计(元) ");

 
    		List<SummaryLoanDto> list = ts.listLoans(null);
   		 	int q = 1;
    		for (SummaryLoanDto dto : list)
    		{
    			HSSFRow row = huizongSheet.createRow(q);q++;
    			row.createCell((short) 0).setCellValue(dto.getCategory());
    			row.createCell((short) 1).setCellValue(dto.getTotalOutStock());
    			row.createCell((short) 2).setCellValue(dto.getSumTotalMidinterest());
    			row.createCell((short) 3).setCellValue(dto.getSumTotalActualLoan());
    			row.createCell((short) 4).setCellValue(dto.getSumTotalPaidInterest());
    			row.createCell((short) 5).setCellValue(dto.getSumTotalReturn());
    		}

    		
    		HSSFSheet chedaiSheet = wb.createSheet("车贷");
    		HSSFRow loanrow = chedaiSheet.createRow((int) 0);
    		String[] loanHeaders = new String[]{"借款日期","抵押人姓名","车辆描述","实际打款金额","已付利息","中介返点","已还本金差","是否归还","归还本金日期"};
    		for (int i1 = 0; i1 <loanHeaders.length; i1++)
    		{
    			HSSFCell cell = loanrow.createCell((short) i1);
    			cell.setCellValue(loanHeaders[i1]);
    		}
    		int j = 1;
    		for (LoanDto dto : loanList)
    		{
    			HSSFRow row = chedaiSheet.createRow(j);j++;
    			int w=0;
//    			row.createCell((short) w++).setCellValue(dto.getTradername());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getBorrowdate()));
    			row.createCell((short) w++).setCellValue(dto.getOwnername());
//    			row.createCell((short) w++).setCellValue(dto.getLicenseno());
    			row.createCell((short) w++).setCellValue(dto.getVehicledesc());
//    			row.createCell((short) w++).setCellValue("押车");
//    			row.createCell((short) w++).setCellValue(dto.getOwnerid());
////    			row.createCell((short) w++).setCellValue(dto.getMobileno());
//    			row.createCell((short) w++).setCellValue(dto.getReturndate());
//    			row.createCell((short) w++).setCellValue(dto.getPeriodmonths());
//    			row.createCell((short) 10).setCellValue(dto.getTotalinterest());
//    			row.createCell((short) w++).setCellValue(dto.getBorrowamount());
//    			row.createCell((short) w++).setCellValue(dto.getInterestrate());
//    			row.createCell((short) w++).setCellValue(dto.getEarnest());
    			row.createCell((short) w++).setCellValue(dto.getActualloan());
//    			row.createCell((short) w++).setCellValue(dto.getLixichengben());
//    			row.createCell((short) w++).setCellValue(dto.getParkingfee());
//    			row.createCell((short) w++).setCellValue(dto.getOtherfee());
//    			row.createCell((short) w++).setCellValue(dto.getActualmonths());
    			row.createCell((short) w++).setCellValue(dto.getInterestpaid());
    			row.createCell((short) w++).setCellValue(dto.getMidinterest());
    			row.createCell((short) w++).setCellValue(dto.getMidinterestrate());
    			row.createCell((short) w++).setCellValue(dto.getIsreturned());
//    			row.createCell((short) w++).setCellValue(dto.getComments());
//    			row.createCell((short) w++).setCellValue(dto.getActualreturn());
    			row.createCell((short) w++).setCellValue(removeTime(dto.getActualreturndate()));
    		}
    		return wb;
    }
    public ActionForward listLoans(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

//          	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//          Date now = new Date();
//          sdf.format(date);
        	
        	SumSummaryForm form = (SumSummaryForm)m;
//        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
        	SumSummaryService ts = new SumSummaryService();
//            int pageSize = form.getRows();
//            int pageNow = form.getPage();
//            int count = 50;
//            int count = ts.count(form);
//            int rowBegin = (pageNow - 1) * pageSize;
//            int rowEnd = rowBegin + pageSize;
//            if(rowBegin > 0) rowBegin++;
            List<SummaryLoanDto> list =  new ArrayList<SummaryLoanDto>();
            list = ts.listLoans(form);
            JsonUtils.printFromList(response, list, list.size());
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
    
    public ActionForward downloadInterestCost(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {        

        //获取用户的名称
    	try {
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }
        	Calendar calendar = Calendar.getInstance();
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
        	String filename = "利息成本合计"+formatter.format(calendar.getTime())+".xls";
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(filename,"utf-8"));
        
        //下载文件
        //1.先获取到要下载文件的绝对路径
       
//        ByteArrayOutputStream fis=new ByteArrayOutputStream();
        OutputStream os=null;
        try {
            
            os=response.getOutputStream();
            this.generateInterestXls().write(os);                
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally{
            try {
                os.close();
//                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }
        
        return null;
}private String removeTime(String date){
	if (date != null){
		date = date.replace("00:00:00.0", "");
	}
	return date;
}


    private HSSFWorkbook generateInterestXls() throws Exception{
    	SumSummaryService ts = new SumSummaryService();
		List<LoanDto> loanList  = ts.listInterestCostOnly(null);
//		
		Calendar currentMonth = Calendar.getInstance();
		String month = String.valueOf(currentMonth.get(Calendar.MONTH));
		int actualCurrentMonth = currentMonth.get(Calendar.MONTH) + 1;
		currentMonth.add(Calendar.MONTH, -1);
		int actualPreviousMonth = currentMonth.get(Calendar.MONTH) + 1;
		String previousMonth = String.valueOf(currentMonth.get(Calendar.MONTH));
		System.out.print(previousMonth + " " + month);
//		double accruedTotalCost = 0;
//		double previousMonthCost = 0;
//		double currentMonthCost = 0;
//		for (LoanDto dto : loanList) {
//			for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
//			{
//				accruedTotalCost = accruedTotalCost + a.getValue().doubleValue();
//				if (month.equals(a.getKey()))
//				{
//					currentMonthCost = currentMonthCost + a.getValue().doubleValue();
//				}
//				if (previousMonth.equals(a.getKey()))
//				{
//					previousMonthCost = previousMonthCost + a.getValue().doubleValue();
//				}
//			}
//		}
    	List<CarSummaryDto> listInterestCost = ts.listInterestCost(null);
		HSSFWorkbook wb = new HSSFWorkbook();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(new Date());
		dateStr = dateStr + " 汇总";
		HSSFSheet huizongSheet = wb.createSheet(dateStr);
		HSSFRow huizongrow = huizongSheet.createRow((int) 0);
		huizongrow.createCell((short) 0).setCellValue("车辆类别");
		huizongrow.createCell((short) 1).setCellValue("上月金额合计(元)");
		huizongrow.createCell((short) 2).setCellValue("当月金额合计(元) ");
		huizongrow.createCell((short) 3).setCellValue("累计(元) ");

		int i = 1;
		double heji_PreviousMonthCost=0.0;
		double heji_CurrentMonthCost=0.0;
		double heji_AccruedTotalCost=0.0;
		for (CarSummaryDto dto : listInterestCost)
		{
			HSSFRow huizongrow2 = huizongSheet.createRow(i);i++;
			huizongrow2.createCell((short) 0).setCellValue(dto.getCarType());
			huizongrow2.createCell((short) 1).setCellValue(dto.getPreviousMonthCost());
			huizongrow2.createCell((short) 2).setCellValue(dto.getCurrentMonthCost());
			huizongrow2.createCell((short) 3).setCellValue(dto.getAccruedTotalCost());
			heji_PreviousMonthCost = heji_PreviousMonthCost +dto.getPreviousMonthCost();
			heji_CurrentMonthCost = heji_CurrentMonthCost + dto.getCurrentMonthCost();
			heji_AccruedTotalCost = heji_AccruedTotalCost + dto.getAccruedTotalCost();
		}
		HSSFRow huizongrow2 = huizongSheet.createRow(i);
		huizongrow2.createCell((short) 0).setCellValue("合计");
		huizongrow2.createCell((short) 1).setCellValue(heji_PreviousMonthCost);
		huizongrow2.createCell((short) 2).setCellValue(heji_CurrentMonthCost);
		huizongrow2.createCell((short) 3).setCellValue(heji_AccruedTotalCost);
		huizongSheet.autoSizeColumn(1); 
		
		List<TradeDto> tradeList  = ts.listInterestCostOnlyForTrade(null);
		
		HSSFSheet sanfangcheSheet = wb.createSheet("三方车");
		HSSFRow sanfangrow = sanfangcheSheet.createRow((int) 0);
		String[] tradeHeaders = new String[]{"收车日期","车辆描述","收车价","实际借款金额","是否出库","出库日期"};
		for (int i1 = 0; i1 < tradeHeaders.length; i1++)
		{
			HSSFCell cell = sanfangrow.createCell((short) i1);
			cell.setCellValue(tradeHeaders[i1]);
		}
		sanfangrow.createCell(sanfangrow.getLastCellNum()).setCellValue(actualPreviousMonth+"月利息成本");
		sanfangrow.createCell(sanfangrow.getLastCellNum()).setCellValue(actualCurrentMonth+"月利息成本");
		sanfangrow.createCell(sanfangrow.getLastCellNum()).setCellValue("累计利息成本");;
		
		HSSFSheet zishouchecheSheet = wb.createSheet("自收车");
		HSSFRow zishourow = zishouchecheSheet.createRow((int) 0);
		for (int i1 = 0; i1 < tradeHeaders.length; i1++)
		{
			HSSFCell cell = zishourow.createCell((short) i1);
			cell.setCellValue(tradeHeaders[i1]);
		}
		zishourow.createCell(zishourow.getLastCellNum()).setCellValue(actualPreviousMonth+"月利息成本");
		zishourow.createCell(zishourow.getLastCellNum()).setCellValue(actualCurrentMonth+"月利息成本");;
		zishourow.createCell(zishourow.getLastCellNum()).setCellValue("累计利息成本");;
		
		int p = 1;
		int k = 1;
		for (TradeDto dto:tradeList)
    	{
			HSSFRow row  = null;
			if ("第三方".equalsIgnoreCase(dto.getVehicletype()))
			{
				row = sanfangcheSheet.createRow(p);
				p++;
			} else {
				row = zishouchecheSheet.createRow(k);
				k++;
			}
			int q = 0;
			//row.createCell((short) q++).setCellValue(dto.getTradername());
			row.createCell((short) q++).setCellValue(removeTime(dto.getPurchasedate()));
//			row.createCell((short) q++).setCellValue(dto.getLicenseno());
			row.createCell((short) q++).setCellValue(dto.getVehicledesc());
//			row.createCell((short) q++).setCellValue(dto.getVehicletype());
			row.createCell((short) q++).setCellValue(dto.getPurchaseprice());
//			row.createCell((short) q++).setCellValue(dto.getOwnerid());
//			row.createCell((short) q++).setCellValue(dto.getOwnername());
//			row.createCell((short) q++).setCellValue(dto.getOwnermobile());
//			row.createCell((short) q++).setCellValue(dto.getInterestrate());
			row.createCell((short) q++).setCellValue(dto.getActualloan());
			row.createCell((short) q++).setCellValue(dto.getIssold());
			row.createCell((short) q++).setCellValue(removeTime(dto.getSelldate()));
//			row.createCell((short) q++).setCellValue(removeBRWithBreak(dto.getLixichengben()));
//			row.createCell((short) q++).setCellValue(dto.getEarnest());
//			row.createCell((short) q++).setCellValue(dto.getSellprice());
//			row.createCell((short) q++).setCellValue(dto.getBuyername());
//			row.createCell((short) q++).setCellValue(dto.getBuyerid());
//			row.createCell((short) q++).setCellValue(dto.getBuyermobile());
//			row.createCell((short) q++).setCellValue(dto.getTradecost());
			if (dto.getMonthAndCost().get(previousMonth) == null)
			{
				row.createCell((short) q++).setCellValue(0);
			} else {
				row.createCell((short) q++).setCellValue(dto.getMonthAndCost().get(previousMonth).doubleValue());
			}
			if (dto.getMonthAndCost().get(month) == null)
			{
				row.createCell((short) q++).setCellValue(0);
			} else {
				row.createCell((short) q++).setCellValue(dto.getMonthAndCost().get(month).doubleValue());
			}
			double accruedTotalCost =0;
			for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
			{
				accruedTotalCost = accruedTotalCost + a.getValue().doubleValue();
			}
			row.createCell((short) q++).setCellValue(accruedTotalCost);
    	}
		for (int i1 = 0; i1 < (tradeHeaders.length+3); i1++)
		{
			sanfangcheSheet.setColumnWidth(i1, 3000);
			zishouchecheSheet.setColumnWidth(i1, 3000);
		}
		
		HSSFSheet chedaiSheet = wb.createSheet("车贷");
		HSSFRow loanrow = chedaiSheet.createRow((int) 0);
		String[] loanHeaders = new String[]{"借款日期","抵押人姓名","车辆描述","实际打款金额","是否归还","归还本金日期"};
		for (int i1 = 0; i1 <loanHeaders.length; i1++)
		{
			HSSFCell cell = loanrow.createCell((short) i1);
			cell.setCellValue(loanHeaders[i1]);
		}
		loanrow.createCell(loanrow.getLastCellNum()).setCellValue(actualPreviousMonth+"月利息成本");
		loanrow.createCell(loanrow.getLastCellNum()).setCellValue(actualCurrentMonth+"月利息成本");;
		loanrow.createCell(loanrow.getLastCellNum()).setCellValue("累计利息成本");;
		int j = 1;
		for (LoanDto dto : loanList)
		{
			int q=0;
			HSSFRow row = chedaiSheet.createRow(j);j++;
//			row.createCell((short) q++).setCellValue(dto.getTradername());
			row.createCell((short) q++).setCellValue(removeTime(dto.getBorrowdate()));
//			row.createCell((short) q++).setCellValue(dto.getLicenseno());
			row.createCell((short) q++).setCellValue(dto.getOwnername());
			row.createCell((short) q++).setCellValue(dto.getVehicledesc());
//			row.createCell((short) q++).setCellValue("押车");
//			row.createCell((short) q++).setCellValue(dto.getOwnerid());
//			row.createCell((short) q++).setCellValue(dto.getMobileno());
//			row.createCell((short) q++).setCellValue(dto.getReturndate());
//			row.createCell((short) q++).setCellValue(dto.getPeriodmonths());
//			row.createCell((short) q++).setCellValue(dto.getTotalinterest());
//			row.createCell((short) q++).setCellValue(dto.getBorrowamount());
//			row.createCell((short) q++).setCellValue(dto.getInterestrate());
//			row.createCell((short) q++).setCellValue(dto.getEarnest());
			row.createCell((short) q++).setCellValue(dto.getActualloan());
//			row.createCell((short) q++).setCellValue(removeBRWithBreak(dto.getLixichengben()));
			row.createCell((short) q++).setCellValue(dto.getIsreturned());
//			row.createCell((short) q++).setCellValue(dto.getParkingfee());
//			row.createCell((short) q++).setCellValue(dto.getOtherfee());
//			row.createCell((short) q++).setCellValue(dto.getActualmonths());
//			row.createCell((short) q++).setCellValue(dto.getInterestpaid());
//			row.createCell((short) q++).setCellValue(dto.getMidinterestrate());
//			row.createCell((short) q++).setCellValue(dto.getMidinterest());
//			row.createCell((short) q++).setCellValue(dto.getComments());
//			row.createCell((short) q++).setCellValue(dto.getActualreturn());
			row.createCell((short) q++).setCellValue(removeTime(dto.getActualreturndate()));
			if (dto.getMonthAndCost().get(previousMonth) == null)
			{
				row.createCell((short) q++).setCellValue(0);
			} else {
				row.createCell((short) q++).setCellValue(dto.getMonthAndCost().get(previousMonth).doubleValue());
			}
			if (dto.getMonthAndCost().get(month) == null)
			{
				row.createCell((short) q++).setCellValue(0);
			} else {
				row.createCell((short) q++).setCellValue(dto.getMonthAndCost().get(month).doubleValue());
			}
			double accruedTotalCost =0;
			for (Entry<String, Double> a : dto.getMonthAndCost().entrySet())
			{
				accruedTotalCost = accruedTotalCost + a.getValue().doubleValue();
			}
			row.createCell((short) q++).setCellValue(accruedTotalCost);
		}
		for (int i1 = 0; i1 < (loanHeaders.length+3); i1++)
		{
			chedaiSheet.setColumnWidth(i1, 3000);
		}
		
		
		return wb;
	}




	public ActionForward listInterestCost(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

//          	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//          Date now = new Date();
//          sdf.format(date);
        	
        	SumSummaryForm form = (SumSummaryForm)m;
//        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
        	SumSummaryService ts = new SumSummaryService();
//            int pageSize = form.getRows();
//            int pageNow = form.getPage();
//            int count = 50;
//            int count = ts.count(form);
//            int rowBegin = (pageNow - 1) * pageSize;
//            int rowEnd = rowBegin + pageSize;
//            if(rowBegin > 0) rowBegin++;
        	 List<CarSummaryDto> list =  new ArrayList<CarSummaryDto>();
             list = ts.listInterestCost(form);
            JsonUtils.printFromList(response, list, list.size());
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
//    
//    public ActionForward listTrade(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        try {
//        	if (ContextUtils.getCurrentUserID(request) == null) {
//                throw new Exception(SysConstant.M_NO_LOGIN);
//            }
//
////          	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
////          Date now = new Date();
////          sdf.format(date);
//        	
//        	SumSummaryForm form = (SumSummaryForm)m;
////        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
////        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
//        	SumSummaryService ts = new SumSummaryService();
////            int pageSize = form.getRows();
////            int pageNow = form.getPage();
////            int count = 50;
////            int count = ts.count(form);
////            int rowBegin = (pageNow - 1) * pageSize;
////            int rowEnd = rowBegin + pageSize;
////            if(rowBegin > 0) rowBegin++;
//            List<SummaryDto> list =  new ArrayList<SummaryDto>();
//            list = ts.listTrade(form);
//            JsonUtils.printFromList(response, list, list.size());
//        }catch(Exception e){
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            request.getSession().setAttribute("ex", e);
//        }
//
//        return null;
//    }
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (ContextUtils.getCurrentUserID(request) == null) {
            ActionForward forward = new ActionForward("/NoLoginAction.do");
            forward.setRedirect(true);
            return forward;
        }

        return mapping.findForward("show");
    }

}
