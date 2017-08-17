package com.lhcy.sync.web.action;

import com.hongchen.sync.reader.FileProcessor;
import com.hongchen.sync.util.Constants;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.web.form.UploadFileForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarLoanAction extends DispatchAction {

    private Logger logger = Logger.getLogger(CarLoanAction.class);

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

			uploadFile = (FormFile) frm.getMultipartRequestHandler().getFileElements().get("file1"); 
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
    /***********************************************/
    // 取得列表
    /***********************************************/
    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            UploadFileForm form = (UploadFileForm)frm;

            int pageSize = form.getRows();
            int pageNow = form.getPage();
            int count = 10;//tsv.count(form);
            int rowBegin = (pageNow - 1) * pageSize;
            int rowEnd = rowBegin + pageSize;
            if(rowBegin > 0) rowBegin++;
            LoanDto dto = new LoanDto();
            dto.setId("1");
            dto.setBizDate("2017.5.12");
            dto.setFullName("张庆全");
            dto.setCarName("哈弗");
            dto.setLoanPeriod("2017.5.12-8.11");
            dto.setNetLoanAmount("33000");
            dto.setPaymentPerMonth("3个月");
            dto.setInterestRate("825");
            dto.setPaybackRate("1822");
            dto.setParkingFee("600");
            dto.setOthers("test");
            dto.setArrcuredPayAmount("29925");
            dto.setAllInterestAmount("2222");
            dto.setComments("2.5-1.98");

            LoanDto dto1 = new LoanDto();
            dto1.setId("1");
            dto1.setBizDate("2017.5.12");
            dto1.setFullName("李亚楠");
            dto1.setCarName("肖克");
            dto1.setLoanPeriod("2017.5.12-2019.5.11");
            dto1.setNetLoanAmount("70000");
            dto1.setPaymentPerMonth("2917");
            dto1.setInterestRate("1400");
            dto1.setPaybackRate("130");
            dto1.setParkingFee("600");
            dto1.setOthers("test");
            dto1.setArrcuredPayAmount("3390");
            dto1.setAllInterestAmount("4500");
            dto1.setComments("贷24个月");
            dto1.setTemperaryMaterils("备用车钥匙");
            List<LoanDto> list =  new ArrayList<LoanDto>();
            list.add(dto);
            list.add(dto1);
            //list = tsv.list(rowBegin, rowEnd, form);

            JsonUtils.printFromList(response, list, count);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
//
//    /***********************************************/
//    // 同步
//    /***********************************************/
//    public ActionForward sync(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        try {
//            // 检查登录
//            if (ContextUtils.getCurrentUserID(request) == null) {
//                throw new BizException(BizStatus.nologin, SysConstant.M_NO_LOGIN);
//            }
//
//            VoucherForm form = (VoucherForm)frm;
//
//            if (form.getId().length() == 0){
//                JsonUtils.printActionResultOK(response);
//                return null;
//            }
//
//            String[] pk = form.getId().split(",");
//            SettltSumService tsv = new SettltSumService();
//            List<VoucherTempDto> list = tsv.voucherList(pk);
//
//            String msg = "";
//            VoucherSyncService sv = new VoucherSyncService();
//            msg = sv.sync(list, ContextUtils.getCurrentUserID(request), ContextUtils.getCurrentKisUserID(request));
//
//            if (msg.length() > 0){
//                throw new BizException(BizStatus.warning ,msg);
//            }else{
//                JsonUtils.printActionResultOK(response);
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            JsonUtils.printActionResultFromException(response, e);
//        }
//
//        return null;
//    }

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
}
