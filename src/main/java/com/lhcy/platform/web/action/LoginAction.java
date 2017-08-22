package com.lhcy.platform.web.action;

import com.lhcy.platform.domain.pojo.User;
import com.lhcy.platform.service.UserService;
import com.lhcy.platform.web.form.LoginForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends DispatchAction {

    private Logger logger = Logger.getLogger(LoginAction.class);

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try{

            LoginForm frm = (LoginForm)form;
            if (frm == null || frm.getAccount() == null || frm.getAccount().length() == 0) {
                ActionMessages error = new ActionMessages();
                error.add("error", new ActionMessage("请输入帐号!",false));
                saveErrors(request, error);
                return mapping.findForward("failed");
            }

            UserService sv = new UserService();
            User vo = sv.loginCheck(frm.getAccount(), frm.getPassword());
            //User vo = new User();
            //vo.setId("123");vo.setAccount("asd");vo.setName("haha");
            if (vo == null || vo.getUserid() == null || vo.getUserid().length() == 0) {
                ActionMessages error = new ActionMessages();
                error.add("error", new ActionMessage("用户名/密码错误,或帐号未启用！",false));
                saveErrors(request, error);
                return mapping.findForward("failed");
            }

//            int kisUserID = new KisUserDao().get(vo.getName());
            System.out.println("CurrentLoginUserID: " + vo.getId());
            System.out.println("CurrentLoginUserAccount: " + vo.getUserid());
            System.out.println("CurrentLoginUserName: " + vo.getUsername());
//            System.out.println("CurrentLoginKisUserID: " + kisUserID);
            request.getSession().setAttribute("CurrentLoginUserID", vo.getId());
            request.getSession().setAttribute("CurrentLoginUserAccount", vo.getUserid());
            request.getSession().setAttribute("CurrentLoginUserName", vo.getUsername());
//            request.getSession().setAttribute("CurrentLoginKisUserID", kisUserID);

            ActionForward forward = new ActionForward("MainAction.do");
            forward.setRedirect(true);
            return forward;

        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }

        return null;
    }

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
