package com.lhcy.sync.web.action;

import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.bo.UuidCreator;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.AccountDto;
import com.lhcy.sync.domain.pojo.Account;
import com.lhcy.sync.service.AccountService;
import com.lhcy.sync.web.form.AccountForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class AccountAction extends DispatchAction {

    private Logger logger = Logger.getLogger(AccountAction.class);

    /***********************************************/
    // 取得列表
    /***********************************************/
    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");
            int pageSize = form.getRows();
            int pageNow = form.getPage();
            int count = sv.count(form);
            int rowBegin = (pageNow - 1) * pageSize;
            int rowEnd = rowBegin + pageSize;
            if(rowBegin > 0) rowBegin++;

            List<AccountDto> list = sv.list(rowBegin, rowEnd, form);
            JsonUtils.printFromList(response, list, count);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;

    }

    /***********************************************/
    // 显示一个
    /***********************************************/
    public ActionForward view(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");
            AccountDto vo = sv.query(form.getId());

            if (vo == null || vo.getId() == null || vo.getId().length() == 0){
                throw new Exception( SysConstant.M_NO_DATA_FIND);
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
    // 保存一个
    /***********************************************/
    public ActionForward save(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");
            Account vo = new Account();
            BeanUtils.copyProperties(vo, form);

            // 新增
            if (vo.getId() == null || vo.getId().length() == 0){

                AccountDto dto = sv.getEquals(vo.getNumber());
                // 已存在
                if (dto != null && dto.getNumber() != null && dto.getNumber().length() > 0){
                    throw new Exception(  "编号:" + vo.getNumber() + SysConstant.M_EXIST_ERROR);
                }else{
                    vo.setId(UuidCreator.getNewId());
                    vo.setCreateUserID(ContextUtils.getCurrentUserID(request));
                    vo.setCreateTime(new Date());
                    vo.setLastUpdateUserID(vo.getCreateUserID());
                    vo.setLastUpdateTime(vo.getCreateTime());
                    sv.create(vo);
                }

                // 修改
            }else{
                AccountDto dto = sv.getEquals(vo.getNumber());
                // 已存在
                if (dto != null && dto.getNumber() != null && dto.getNumber().length() > 0 && !dto.getId().equals(vo.getId())){
                    throw new Exception( "编号:" + vo.getNumber() + SysConstant.M_EXIST_ERROR);
                }else{
                    vo.setLastUpdateUserID(ContextUtils.getCurrentUserID(request));
                    vo.setLastUpdateTime(new Date());
                    sv.update(vo);
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
    // 删除多个
    /***********************************************/
    public ActionForward delete(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");

            if (form.getId().length() == 0){
                JsonUtils.printActionResultOK(response);
                return null;
            }

            String[] list = form.getId().split(",");
            sv.delete(list);
            JsonUtils.printActionResultOK(response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;

    }

    /***********************************************/
    // 启用或禁用多个
    /***********************************************/
    public ActionForward enable(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");

            if (form.getId().length() == 0){
                JsonUtils.printActionResultOK(response);
                return null;
            }

            String[] list = form.getId().split(",");
            sv.enable(list, form.getEnable(), ContextUtils.getCurrentUserID(request));
            JsonUtils.printActionResultOK(response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;

    }

    /***********************************************/
    // 移动至分类多个
    /***********************************************/
    public ActionForward move(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception( SysConstant.M_NO_LOGIN);
            }

            AccountForm form = (AccountForm)frm;
            AccountService sv = new AccountService("lhcy");

            if (form.getId().length() == 0){
                JsonUtils.printActionResultOK(response);
                return null;
            }

            String[] list = form.getId().split(",");
            sv.move(list, form.getTreeID(), ContextUtils.getCurrentUserID(request));
            JsonUtils.printActionResultOK(response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;

    }

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
