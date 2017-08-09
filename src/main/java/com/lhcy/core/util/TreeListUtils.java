package com.lhcy.core.util;

import com.lhcy.core.bo.TreeNode;
import com.lhcy.core.vo.TreeNodeVo;

import java.util.ArrayList;
import java.util.List;

public class TreeListUtils {

    public static List<TreeNodeVo> getTreeNodeList(List<TreeNode> list){

        List<TreeNodeVo> result = new ArrayList<TreeNodeVo>();
        for (TreeNode node : list){

            // 根节点
            if (node.getParentID() == null || node.getParentID().length() == 0){
                String name = (node.getLongName() == null || node.getLongName().length() == 0) ? node.getName() : node.getLongName();
                TreeNodeVo vo = new TreeNodeVo();
                vo.setText(name);
                vo.setId(node.getId());
                vo.setChildren(new ArrayList<TreeNodeVo>());

                createChildrenTreeNode(vo, list, vo.getId());
                setChildrenTreeNodeIcon(vo);
                result.add(vo);
            }
        }

        return result;
    }

    private static void createChildrenTreeNode(TreeNodeVo pvo, List<TreeNode> list, String parent){

        for (TreeNode node : list){

            if (parent.equals(node.getParentID())){
                String name = (node.getLongName() == null || node.getLongName().length() == 0) ? node.getName() : node.getLongName();
                TreeNodeVo vo = new TreeNodeVo();
                vo.setText(name);
                vo.setId(node.getId());
                vo.setChildren(new ArrayList<TreeNodeVo>());
                pvo.getChildren().add(vo);

                createChildrenTreeNode(vo, list, vo.getId());
            }
        }
    }

    private static void setChildrenTreeNodeIcon(TreeNodeVo vo){

        if (vo.getChildren().size() == 0){
            vo.setIconCls("icon-tree-app");
            return;
        }

        for (int i = 0; i < vo.getChildren().size(); i ++){
            TreeNodeVo children = vo.getChildren().get(i);
            if (children.getChildren().size() == 0){
                children.setIconCls("icon-tree-app");
                children.setChildren(null);
            }else{
                setChildrenTreeNodeIcon(children);
            }
        }
    }

    public static List<String> getChildrenID(List<TreeNode> list, String currentID){
        List<String> result = new ArrayList<String>();
        result.add(currentID);

        for (TreeNode node : list){
            if (currentID.equals(node.getParentID())){
               result.addAll(getChildrenID(list, node.getId()));
            }
        }

        return result;
    }
}
