package com.lhcy.sync.web.action;


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


import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.bo.UuidCreator;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.domain.pojo.Trade;
import com.lhcy.sync.service.TradeService;
import com.lhcy.sync.web.form.TradeForm;

public class TradeAction extends DispatchAction {

    private Logger logger = Logger.getLogger(TradeAction.class);

	
    /***********************************************/
    // 取得列表
    /***********************************************/
    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
        	if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	
//          Date now = new Date();
//          sdf.format(date);
        	
        	TradeForm form = (TradeForm)frm;
//        	System.out.println(form.getFilterField()+" " +form.getFilterValue() +" " + form.getFilterlicenseno() 
//        	+ " " + form.getFiltercustomer()+ " " + form.getFiltercardescription()+ " " + form.getFilterinventoryints()+ " " + form.getFilterinventoryoutts());
        	TradeService ts = new TradeService();
            int pageSize = form.getRows();
            int pageNow = form.getPage();
            int count = 50;//tsv.count(form);
            int rowBegin = (pageNow - 1) * pageSize;
            int rowEnd = rowBegin + pageSize;
            if(rowBegin > 0) rowBegin++;
            List<TradeDto> list =  new ArrayList<TradeDto>();
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

            TradeForm form = (TradeForm)frm;
            Trade vo = new Trade();
            TradeService ts = new TradeService();
            BeanUtils.copyProperties(vo, form);

            // 新增
            if (vo.getVehicleid() == null || vo.getVehicleid().length() == 0){

                TradeDto dto = ts.getEquals(vo.getLicenseno());
                // 已存在
                if (dto != null && dto.getLicenseno() != null && dto.getLicenseno().length() > 0){
                    throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_EXIST_ERROR);
                }else{
                    vo.setVehicleid(UuidCreator.getNewId());
                    ts.create(vo);
                }

                // 修改
            }else{
                TradeDto dto = ts.getEquals(vo.getLicenseno());
                System.out.println(dto.getVehicleid()+" ------------- "+dto.getLicenseno());
                // 已存在
                if (dto != null && dto.getLicenseno() != null && dto.getLicenseno().length() > 0 && !dto.getVehicleid().equals(vo.getVehicleid())){
                    throw new Exception( "车辆:" + vo.getLicenseno() + SysConstant.M_EXIST_ERROR);
                }else{
                    ts.update(vo);
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

            TradeService sv = new TradeService();
            TradeForm form = (TradeForm)frm;
            TradeDto vo = sv.query(form.getVehicleid());

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
//            TradeForm form = (TradeForm)frm;
//            if (form.getVehicleid().length() == 0){
//                JsonUtils.printActionResultOK(response);
//                return null;
//            }
//
//            TradeService sv = new TradeService();
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
