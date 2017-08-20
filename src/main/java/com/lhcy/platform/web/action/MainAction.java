package com.lhcy.platform.web.action;

import com.hongchen.kis.db.ConfigDataInitializer;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.EncryptUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.platform.domain.pojo.User;
import com.lhcy.platform.service.UserService;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends DispatchAction {

    private Logger logger = Logger.getLogger(MainAction.class);

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 检查登录
        if (request.getSession().getAttribute("CurrentLoginUserID") == null) {
            ActionForward forward = new ActionForward("/");
            forward.setRedirect(true);
            return forward;
        }
        return mapping.findForward("show");
    }

    public ActionForward chgPwd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 检查登录
        if (ContextUtils.getCurrentUserID(request) == null) {
            throw new Exception(SysConstant.M_NO_LOGIN);
        }

        try {
            if (form == null){
                return null;
            }

            try {
                String pwd1 = (String)request.getParameter("password1");
                String pwd2 = (String)request.getParameter("password2");
                pwd1 = pwd1 == null ? "" : pwd1;
                pwd2 = pwd2 == null ? "" : pwd2;
                String account = (String)request.getSession().getAttribute("CurrentLoginUserAccount");

                UserService sv = new UserService();
                User vo = sv.loginCheck(account, pwd1);
                if (vo == null || vo.getUserid() == null || vo.getUserid().length() == 0) {

                    HttpActionResult ar = new HttpActionResult("ng", null, null);
                    JsonUtils.printFromObject(response, ar);

                }else{
                    vo.setPassword(EncryptUtils.getMd5String(pwd2));
                    sv.chgPwd(vo);
                    HttpActionResult ar = new HttpActionResult("ok", null, null);
                    JsonUtils.printFromObject(response, ar);
                }

            }catch (Exception ex){
                ex.printStackTrace();
                JsonUtils.printActionResultFromException(response, ex);
            }

        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }
}
