package com.lhcy.sync.web.action;


import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.hongchen.sync.reader.FileProcessor;
import com.hongchen.sync.util.Constants;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.bo.UuidCreator;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.pojo.Loan;
import com.lhcy.sync.service.LoanService;
import com.lhcy.sync.web.form.LoanForm;

public class LoanAction extends DispatchAction {

    private Logger logger = Logger.getLogger(LoanAction.class);

	
    /***********************************************/
    // 取得列表
    /***********************************************/
    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println("listlistlistlistlistlist"); 
        try {
            // 检查登录
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

//          	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//          Date now = new Date();
//          sdf.format(date);
        	
        	LoanForm form = (LoanForm)frm;
//        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
        	LoanService ts = new LoanService();
            int pageSize = form.getRows();
            int pageNow = form.getPage();
//            int count = 50;
            int count = ts.count(form);
            int rowBegin = (pageNow - 1) * pageSize;
            int rowEnd = rowBegin + pageSize;
            if(rowBegin > 0) rowBegin++;
            List<LoanDto> list =  new ArrayList<LoanDto>();
            list = ts.list(rowBegin, pageSize, form);
            JsonUtils.printFromList(response, list, count);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
    
    /***********************************************/
    // 保存一个
    /***********************************************/
    public ActionForward save(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null){
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            LoanForm form = (LoanForm)frm;
            Loan vo = new Loan();
            LoanService ts = new LoanService();
            BeanUtils.copyProperties(vo, form);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            //根据不同操作类型给vo赋值
    		double borrowamount = vo.getBorrowamount();
    		double interestrate = vo.getInterestrate();
    		double interestpaid = vo.getInterestpaid();
    		double actualreturn = vo.getActualreturn();
    		double periodmonths = vo.getPeriodmonths();
    		double actualloan = vo.getActualloan();
    		double midinterest =  vo.getMidinterest();
    		double otherfee = vo.getOtherfee();
        	if(form.getOperation().equals("returned")){
        		double remaininterest = (interestrate/100*borrowamount*periodmonths)-interestpaid;

        		if(remaininterest >= 0 || borrowamount > actualreturn){
        			throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_NOTALLPAID_ERROR);
        		}else{
        			vo.setIsreturned("1");
        		}
        		
        	}else if(form.getOperation().equals("settle"))
        	{
        		if(vo.getIsdeleted() != "1"){
	        		if(vo.getIsabandon().equals("1") || vo.getIsreturned().equals("1")){
	        			double totalprofit = actualreturn + interestpaid - actualloan - midinterest - otherfee;
	        			vo.setTotalprofit(totalprofit);
		        		vo.setSettlement("1");
		        		String settlementdate =sdf.format(new Date());
		        		vo.setSettlementdate(settlementdate);
	        		}else{
	        			throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_NOTRETURNABANDON_ERROR);
	        		}
        		}else
        		{
        			throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_DELETED_ERROR);
        		}
        	}else if(form.getOperation().equals("abandon"))
        	{
        		if(vo.getIsdeleted().equals("1") || vo.getSettlement().equals("1")){
        			throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_SETTLEDELETED_ERROR);
        		}else
        		{
        			vo.setIsabandon("1");
        		}
        	}else{
        		vo.setIsdeleted("0");
        		vo.setIsreturned("0");
        		vo.setIsabandon("0");
        		vo.setSettlement("0");
        		vo.setSettlementdate(null);
        	}
            // 新增
            if (vo.getVehicleid() == null || vo.getVehicleid().length() == 0){

                LoanDto dto = ts.getEquals(vo.getLicenseno());
                // 已存在
                if (dto != null && dto.getLicenseno() != null && dto.getLicenseno().length() > 0){
                    throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_EXIST_ERROR);
                }else{
                    vo.setVehicleid(UuidCreator.getNewId());
                    ts.create(vo);
                }

                // 修改
            }else{
                LoanDto dto = ts.getEquals(vo.getLicenseno());
                System.out.println(dto.getVehicleid()+" ------------- "+dto.getLicenseno());
                // 已存在
                if (dto != null && dto.getLicenseno() != null && dto.getLicenseno().length() > 0 && !dto.getVehicleid().equals(vo.getVehicleid())){
                    throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_EXIST_ERROR);
                }else{
//                	if(form.getOperation().equals("settle")){
//                		ts.settle(vo);
//                	}else if(form.getOperation().equals("return")){
//                		ts.settle(vo);
//                	}else{
                		ts.update(vo);
//                	}
                }
            }

            JsonUtils.printActionResultOK(response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;

    }

    /***********************************************/
    // 显示一个
    /***********************************************/
    public ActionForward view(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null){
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            LoanService sv = new LoanService();
            LoanForm form = (LoanForm)frm;
            LoanDto vo = sv.query(form.getVehicleid());

            if (vo == null || vo.getVehicleid() == null || vo.getVehicleid().length() == 0){
                throw new Exception(SysConstant.M_NO_DATA_FIND);
            }

            JsonUtils.printActionResultFromObject(response, vo);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }
    /***********************************************/
    // 删除一个
    /***********************************************/
    public ActionForward deletesingle(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null){
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            LoanForm form = (LoanForm)frm;
            if (form.getVehicleid().length() == 0){
                JsonUtils.printActionResultOK(response);
                return null;
            }

            LoanService sv = new LoanService();
            String vehichleid = form.getVehicleid();

            sv.deletesingle(vehichleid);
//            if(vehicletype.equals("自收车") || issold.equals("1")){
//            	System.out.println("ID: "+vehichleid+"车辆类型： "+vehicletype+"已售："+issold);
//    		}else{
//    			double spareloantmp = 0.00;//vo.getspareloan()
//    			spareloantmp = sv.getspare();
//    			double	sloan = spareloantmp + purchaseprice;
//                sv.updateSpare(sloan);
//    		}
            JsonUtils.printActionResultOK(response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }
//    
//    /***********************************************/
//    // 删除多个
//    /***********************************************/
//    public ActionForward delete(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        try {
//            // 检查登录
//            if (ContextUtils.getCurrentUserID(request) == null){
//                throw new Exception(SysConstant.M_NO_LOGIN);
//            }
//
//            LoanForm form = (LoanForm)frm;
//            if (form.getVehicleid().length() == 0){
//                JsonUtils.printActionResultOK(response);
//                return null;
//            }
//
//            LoanService sv = new LoanService();
//            String[] list = form.getVehicleid().split(",");
//            sv.delete(list);
//            JsonUtils.printActionResultOK(response);
//        }catch(Exception e){
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            JsonUtils.printActionResultFromException(response, e);
//        }
//
//        return null;
//    }
//    
//    /***********************************************/
//    // 间隔天数
//    /***********************************************/
//    private static int getIntervalDays(Date sDate, Date eDate) {
//
//        if (null == sDate || null == eDate) {
//            return -1;
//        }
//        long intervalMilli = eDate.getTime() - sDate.getTime();
//        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
//     }
//    
//    /***********************************************/
//    // 间隔月数
//    /***********************************************/
//    private static int getIntervalMonths(Date sDate, Date eDate) {
//
//        if (null == sDate || null == eDate) {
//            return -1;
//        }
//        @SuppressWarnings("deprecation")
//		int intervalYear = eDate.getYear() - sDate.getYear();
//        @SuppressWarnings("deprecation")
//		int intervalMonth = eDate.getMonth()- sDate.getMonth();
//        intervalMonth = intervalMonth + intervalYear*12;
//        int days = getIntervalDays(sDate, eDate);	
//		int monthdiff = 0;
//		try{
//				if(intervalMonth==0){
//					monthdiff = intervalMonth+1;
//				}else if(days > ((intervalMonth)*31)){
//					monthdiff = intervalMonth+1;
//				}else{
//					monthdiff = intervalMonth;
//				}
////				System.out.println("days: " + days + "intervalMonth: " + intervalMonth +"monthdiff" + monthdiff);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//        return monthdiff;
//     }
//
//

    /***********************************************/
    // 跳转
    /***********************************************/
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 检查登录
        if (ContextUtils.getCurrentUserID(request) == null) {
            ActionForward forward = new ActionForward("/NoLoginAction.do");
            forward.setRedirect(true);
            return forward;
        }

        return mapping.findForward("show");
    }
    
    public ActionForward uploadFile(ActionMapping mapping, ActionForm frm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FileOutputStream outer = null;
		FormFile uploadFile = null;
		try {
			// 检查登录
			if (ContextUtils.getCurrentUserID(request) == null) {
				throw new Exception( SysConstant.M_NO_LOGIN);
			}

			uploadFile = (FormFile) frm.getMultipartRequestHandler().getFileElements().get("file"); 
			int fileSize = uploadFile.getFileSize();
			FileProcessor processor = new FileProcessor();
			File newFile = new File(Constants.PROCESSING_FOLDER_PATH + processor.toProcessingFileName(uploadFile.getFileName()));
			outer = new FileOutputStream(newFile);
			byte[] buffer = uploadFile.getFileData();
			outer.write(buffer);
			outer.flush();
			uploadFile.destroy();
			String uploadMsg = processor.process(newFile, ContextUtils.getCurrentUserID(request), ContextUtils.getCurrentKisUserID(request));
			outer.close();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{\"size\":\""+GetFileSize(fileSize)+ "\", \"uploadMsg\":\"" +uploadMsg+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			request.getSession().setAttribute("ex", e);
		} finally {
			if (outer != null)
			{
				try{outer.close();}catch(Exception e){}
			}
			if (uploadFile != null)
			{
				try{uploadFile.destroy();}catch(Exception e){}
			}
		}

		return null;
	}

	public String GetFileSize(int fileSize) {
		String size = "";
		long fileS = fileSize + 0L;
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileS < 1024) {
			size = df.format((double) fileS) + "BT";
		} else if (fileS < 1048576) {
			size = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			size = df.format((double) fileS / 1048576) + "MB";
		} else {
			size = df.format((double) fileS / 1073741824) + "GB";
		}
		return size;
	}
    
//    public static void main(String args[])
//    {
//    	
//    	double d =2000001;
//    	System.out.println(Math.ceil(d/100000)+"   "+(Math.ceil(d/100000))*100000);
//    	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
//		try{
//				Date sDate =sdf.parse("2017-08-10");
//				Date eDate =sdf.parse("2017-09-11");
//				//new Date();
//				//int days = getIntervalDays(sDate, eDate);
//				int months = getIntervalMonths(sDate, eDate);
//				//int monthdiff = 0;
//				//				if(months==0){
//				//					monthdiff = months+1;
//				//				}else if(days > ((months)*31)){
//				//					monthdiff = months+1;
//				//				}else{
//				//					monthdiff = months;
//				//				}
//				System.out.println("monthdiff" + months);
//		}catch(Exception e){
//			e.printStackTrace();
//		}

//    }
    
    
}

