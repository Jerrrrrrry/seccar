package com.lhcy.sync.web.action;

import com.lhcy.core.bo.*;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.core.util.TreeListUtils;
import com.lhcy.core.vo.TreeNodeVo;
import com.lhcy.sync.service.AccountService;
import com.lhcy.sync.web.form.AccountForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountTreeAction extends DispatchAction {

    private Logger logger = Logger.getLogger(AccountTreeAction.class);

    /***********************************************/
    // 加载树
    /***********************************************/
    public ActionForward tree(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            AccountService sv = new AccountService("lhcy");

            List<TreeNode> tree = sv.treeList();
            List<TreeNodeVo> treeVo = TreeListUtils.getTreeNodeList(tree);
            JsonUtils.printFromObject(response, treeVo);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            request.getSession().setAttribute("ex", e);
        }

        return null;
    }

    /***********************************************/
    // 显示树一个节点
    /***********************************************/
    public ActionForward view(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            AccountService sv = new AccountService("lhcy");
            AccountForm form = (AccountForm)frm;

            TreeNode node = sv.treeQuery(form.getTreeID());

            if (node == null || node.getId() == null || node.getId().length() == 0){
                throw new Exception(SysConstant.M_NO_DATA_FIND);
            }

            JsonUtils.printActionResultFromObject(response, node);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }

    /***********************************************/
    // 保存树节点
    /***********************************************/
    public ActionForward save(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            AccountService sv = new AccountService("lhcy");
            AccountForm form = (AccountForm)frm;

            String treeID = form.getTreeID();
            TreeNode vo = new TreeNode();
            // 新增
            if(treeID == null || treeID.length() == 0){

                TreeNode dto = sv.treeGetEquals(form.getTreeNumber());
                // 已存在
                if (dto != null && dto.getNumber() != null && dto.getNumber().length() > 0){
                    throw new Exception("编号:" + dto.getNumber() + SysConstant.M_EXIST_ERROR);
                }

                // 不存在
                else{
                    vo.setId(UuidCreator.getNewId());
                    vo.setParentID(form.getParentTreeID());
                    vo.setNumber(form.getTreeNumber());
                    vo.setName(form.getTreeName());
                    sv.treeCreate(vo);
                }
            }

            // 修改
            else{

                TreeNode dto = sv.treeGetEquals(form.getTreeNumber());
                // 已存在
                if (dto != null && dto.getNumber() != null && dto.getNumber().length() > 0 && !dto.getId().equals(treeID)){
                    throw new Exception("编号:" + dto.getNumber() + SysConstant.M_EXIST_ERROR);
                }

                // 不存在
                else{
                    vo.setId(treeID);
                    vo.setParentID(form.getParentTreeID());
                    vo.setNumber(form.getTreeNumber());
                    vo.setName(form.getTreeName());
                    sv.treeUpdate(vo);
                }
            }
            JsonUtils.printActionResultFromObject(response, vo.getId());

        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }

    /***********************************************/
    // 删除树节点
    /***********************************************/
    public ActionForward delete(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 检查登录
            if (ContextUtils.getCurrentUserID(request) == null) {
                throw new Exception(SysConstant.M_NO_LOGIN);
            }

            AccountService sv = new AccountService("lhcy");
            AccountForm form = (AccountForm) frm;

            sv.treeDelete(form.getTreeID());
            JsonUtils.printActionResultOK(response);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            JsonUtils.printActionResultFromException(response, e);
        }

        return null;
    }
}
