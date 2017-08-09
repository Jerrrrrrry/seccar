<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/default.css"/>
    <script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/appConstant.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/xutil.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/xcore.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/sync/account/Account.Init.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/sync/account/Account.Tree.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/sync/account/Account.List.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/sync/account/Account.Edit.js"></script>
    <script type="text/javascript">

        $(function(){
            AccountInit.getInstance('<%= basePath%>').init();
        });

    </script>
</head>
<body class="easyui-layout">
    <div id="pnl_tree" region="west" border="false" data-options="split:true" title=" " class="tree_line" style="width:200px;">
        <div class="easyui-layout" fit="true" style="overflow:hidden;width:100%;height:100%">
            <div region="center" fit="true" style="width:100%;height:100%"  border="false" title="">
                <input id="showChildren" type="checkbox">包含下级节点<br/><br/>
                <ul id="l_tree">
                </ul>
            </div>
        </div>
    </div>
    <div id="center" region="center" border="false" title="" class="tree_grid_line">
        <div class="easyui-layout" fit="true" style="overflow:hidden;width:100%;height:100%">
            <table id="list">
            </table>
        </div>
    </div>

    <div id="dlg_tree" style="width:400px;height:200px;padding:0px">
        <div region="center" border="false" title="" style="padding-left: 20px">
            <br/>
            <table class="tb_edit" cellspacing="0">
                <tbody>
                <tr>
                    <th>上级分类</th>
                    <td>
                        <input id="parentTreeName"/>
                        <input type="hidden" id="parentTreeID" />
                        <input type="hidden" id="treeID" />
                        <input type="hidden" id="lastSelectedTreeID" />
                    </td>
                </tr>
                <tr>
                    <th>编号<span style="color:red">*</span></th>
                    <td><input id="treeNumber"/></td>
                </tr>
                <tr>
                    <th>名称<span style="color:red">*</span></th>
                    <td><input id="treeName"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="dlg_edit">
        <div region="center" border="false" title="" style="padding-left: 20px">
            <br/>
            <table class="tb_edit" cellspacing="0">
                <tbody>
                <tr>
                    <th>分类</th>
                    <td>
                        <input id="editTreeName"/>
                        <input type="hidden" id="id" />
                        <input type="hidden" id="editTreeID" />
                    </td>
                    <td></td>
                    <td>
                        启用<input type="checkbox" id="enable"/>
                    </td>
                </tr>
                <tr>
                    <th>编号<span style="color:red">*</span></th>
                    <td><input id="number"/></td>
                    <th>名称<span style="color:red">*</span></th>
                    <td><input id="name"/></td>
                </tr>
                <tr>
                    <th>中天编号</th>
                    <td><input id="shortNumber"/></td>
                    <th>简称</th>
                    <td><input id="shortName"/></td>
                </tr>
                <tr>
                    <th>备注</th>
                    <td colspan="3"><input id="comment"/></td>
                </tr>
                <tr>
                    <th>创建人</th>
                    <td><input id="createUserName"/></td>
                    <th>创建时间</th>
                    <td><input id="createTime"/></td>
                </tr>
                <tr>
                    <th>最后修改人</th>
                    <td><input id="lastUpdateUserName"/></td>
                    <th>最后修改人</th>
                    <td><input id="lastUpdateTime"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="dlg_moveTree">
        <div region="center" border="false" title="" style="padding-left: 20px">
            <br/>
            <ul id="move_tree">
            </ul>
        </div>
    </div>
    <div id="dlg_changeTree">
        <div region="center" border="false" title="" style="padding-left: 20px">
            <br/>
            <ul id="change_tree">
            </ul>
        </div>
    </div>
    <div id="bar_list">
        <table cellspacing="0" cellpadding="0">
            <tr>
                <td><a id="btnAddnew"></a></td>
                <td><a id="btnRefresh"></a></td>
                <td><a id="btnDelete"></a></td>
                <td><a id="btnMove"></a></td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td><a id="btnEnable"></a></td>
                <td><a id="btnDisable"></a></td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td><a id="btnSelectAll"></a></td>
                <td><a id="btnUnselectAll"></a></td>
                <td><div class="datagrid-btn-separator"></div></td>
                <td><a id="btnClose"></a></td>
            </tr>
        </table>
        <div>
            <input id="filterField" />
            <input id="filterValue"/>
            <a id="btnFilter"></a>
        </div>
    </div>
</body>
</html>
