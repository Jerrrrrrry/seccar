package com.lhcy.core.util;

import com.lhcy.core.bo.SysConstant;

import javax.servlet.http.HttpServletRequest;

public class ContextUtils {

    public static String getCurrentUserID(HttpServletRequest request){
        String result = null;
        if(request.getSession().getAttribute("CurrentLoginUserID") != null) {
            result = (String) request.getSession().getAttribute("CurrentLoginUserID");
        }
        return result;
    }

    public static String getCurrentUserAccessType(HttpServletRequest request){
        String result = null;
        if(request.getSession().getAttribute("CurrentLoginUserAccesstype") != null) {
            result = (String) request.getSession().getAttribute("CurrentLoginUserAccesstype");
        }
        return result;
    }
    public static String getCurrentUserAccount(HttpServletRequest request){
        String result = null;
        if(request.getSession().getAttribute("CurrentLoginUserAccount") != null) {
            result = (String) request.getSession().getAttribute("CurrentLoginUserAccount");
        }
        return result;
    }

    public static int getCurrentKisUserID(HttpServletRequest request){
        int result = SysConstant.CREATE_USER_ID;
        if(request.getSession().getAttribute("CurrentLoginKisUserID") != null) {
            result = (Integer)request.getSession().getAttribute("CurrentLoginKisUserID");
        }
        return result;
    }
    
    public static String getProjectRealPath(HttpServletRequest request){
        String result = null;
        if(request.getSession().getServletContext().getRealPath("/") != null) {
            result = (String) request.getSession().getServletContext().getRealPath("/");
        }
        return result;
    }

}
