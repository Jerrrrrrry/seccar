package com.lhcy.sync.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.hongchen.sync.reader.FileProcessor;
import com.hongchen.sync.util.Constants;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.CarLoanDto;
import com.lhcy.sync.web.form.SecondCarForm;;

public class SecondCarAction extends DispatchAction {

    private Logger logger = Logger.getLogger(CarLoanAction.class);

	
    /***********************************************/
    // 取得列表
    /***********************************************/
    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

        	SecondCarForm form = (SecondCarForm)frm;

            int pageSize = form.getRows();
            int pageNow = form.getPage();
            int count = 10;//tsv.count(form);
            int rowBegin = (pageNow - 1) * pageSize;
            int rowEnd = rowBegin + pageSize;
            if(rowBegin > 0) rowBegin++;
            CarLoanDto dto = new CarLoanDto();
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

            CarLoanDto dto1 = new CarLoanDto();
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
            List<CarLoanDto> list =  new ArrayList<CarLoanDto>();
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
