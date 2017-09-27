package com.lhcy.sync.web.action;

import java.util.ArrayList;
import java.util.List;

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
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.dto.SummaryDto;
import com.lhcy.sync.domain.dto.SummaryTradeDto;
import com.lhcy.sync.service.SumSummaryService;
import com.lhcy.sync.web.form.SumSummaryForm;

public class SumTradeAction extends DispatchAction {

    private Logger logger = Logger.getLogger(TradeReportAction.class);

    public ActionForward list(ActionMapping mapping, ActionForm m, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
            List<SummaryTradeDto> list =  new ArrayList<SummaryTradeDto>();
            list = ts.listTrade(form);
            JsonUtils.printFromList(response, list, list.size());
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (ContextUtils.getCurrentUserID(request) == null) {
            ActionForward forward = new ActionForward("/NoLoginAction.do");
            forward.setRedirect(true);
            return forward;
        }

        return mapping.findForward("show");
    }

}
