<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>蓝海餐饮KIS接口</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/default.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        function addTab(title, id, url) {

            if ($('#mainTab').tabs('exists', title)) {
                $('#mainTab').tabs('select', title);
            } else {

                var frmId = '';
                if(id != null && id.length > 0){
                    frmId = 'id="' + id + '"';
                }

                var content = '<iframe ' + frmId + ' scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:98%;"></iframe>';
                $('#mainTab').tabs('add', {
                    title: title,
                    content: content,
                    closable: true,
                    fit: true
                });
            }
        }

        function addNewTab(title, url) {

            var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:98%;"></iframe>';
            $('#mainTab').tabs('add', {
                title: title,
                content: content,
                closable: true,
                fit: true
            });
        }

        // 调用子窗体刷新
        function callTabPageRefresh(id){

            var page = $('#' + id);
            if (page == null || page.length == 0){
                return;
            }

            $("#" + id)[0].contentWindow.refresh();
        }

        function callTabPageMoveNext(id, type, pk){

            var page = $('#' + id);
            if (page == null || page.length == 0){
                return;
            }

            var result = $("#" + id)[0].contentWindow.moveNext(type, pk);
            return result;
        }

        function closeCurrentTab(){
            var tab = $('#mainTab').tabs('getSelected');
            var index = $('#mainTab').tabs('getTabIndex',tab);
            $('#mainTab').tabs('close', index);
        }

        function openSetPwd(){
            $("#password1").textbox("setText","");
            $("#password2").textbox("setText","");
            $("#password3").textbox("setText","");
            $("#dlg-pwd").dialog("open");
            $("#password1").next("span").find("input")[0].focus();
        }

        function doSetPwd(){

            var password1 = $("#password1").textbox("getText");
            var password2 = $("#password2").textbox("getText");
            var password3 = $("#password3").textbox("getText");

            if (password2 != password3){
                $.messager.alert("系统提示","新密码和确认密码不一致！","warning");
                return;
            }

            $.ajax({
                type: "post",
                url: "<%=basePath%>MainAction.do?m=chgPwd",
                data: {password1:password1,password2:password2},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    console.log(vo);

                    if (vo.status == 'ok') {
                        $.messager.alert('系统提示', '修改密码成功！','info');

                    }else if (vo.status == 'ng'){
                        $.messager.alert('系统提示', '旧密码错误！', 'warning');

                    } else if (vo.status == 'nologin') {
                        top.location = '<%=basePath %>';
                    } else {
                        $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                    }

                    $("#btnSavePwd").linkbutton('enable');
                    $("#password1").textbox("setText","");
                    $("#password2").textbox("setText","");
                    $("#password3").textbox("setText","");
                },
                error: function (err) {
                    top.location = '<%=basePath %>';
                }
            });
        }

        $(function () {

            $('#tr_ic').tree({
                checkbox: false,
                animate: true,
                lines: true,
                onClick: function (node) {

                    if (node.id == '凭证同步') {
                        addTab('数据上传同步', null, '<%=basePath %>VoucherAction.do');

                    }else if (node.id == '销售出库单同步'){
                        addTab('销售出库单同步', null, '<%=basePath %>ICStockAction.do');

                    }else if (node.id == '科目关系维护'){
                        addTab('科目关系维护', null, '<%=basePath %>AccountAction.do');
                    }
                }
            });

            $('#tr_st').tree({
                checkbox: false,
                animate: true,
                lines: true,
                onClick: function (node) {

                    if (node.id == 'pwd') {
                        openSetPwd();

                    }else if (node.id == '用户'){
                        addTab('用户', null, '<%=basePath %>UserAction.do');
                    }
                }
            });
        });

    </script>
</head>
<body class="easyui-layout">
<form id="form1">
    <div data-options="region:'north',split:false" title="" style="height:30px;padding:0px;overflow-x : hidden;overflow-y : hidden">
        <table border="0" cellpadding="0" width="100%">
            <tr>
                <td><span style="font-size: 20px;">蓝海餐饮KIS接口</span></td>
                <td style="text-align: right">登录者：<%= session.getAttribute("CurrentLoginUserName") %>&nbsp;&nbsp;&nbsp;<a href="<%=basePath %>">登出</a>&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </table>
    </div>

    <div data-options="region:'west',split:true,border:true" title="功能菜单" style="width:222px;padding1:1px;overflow:hidden;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="单据同步"style="padding:10px;">
                <ul id="tr_ic" class="easyui-tree" data-options="lines:true">
                    <li data-options="id:'凭证同步', 'iconCls':'icon-tree-app'">数据上传同步</li>
                    <!-- <li data-options="id:'销售出库单同步', 'iconCls':'icon-tree-app'">销售出库单同步</li> -->
                    <li data-options="id:'科目关系维护', 'iconCls':'icon-tree-app'">科目关系维护</li>
                </ul>
            </div>
            <div title="系统设置" style="padding:10px;">
                <ul id="tr_st" class="easyui-tree" data-options="lines:true">

                    <% String acount = (String)session.getAttribute("CurrentLoginUserAccount");
                        if (acount != null && "superman".equals(acount)){
                    %>
                    <li data-options="id:'用户', 'iconCls':'icon-tree-app'">用户</li>
                    <% } %>

                    <li data-options="id:'pwd', 'iconCls':'icon-tree-app'">修改密码</li>
                </ul>
            </div>
        </div>
    </div>
    <div region="center" border="true" title="" data-options="border:false" style="overflow:hidden;">
        <div class="easyui-layout" id="subWrap" fit="true" style="overflow:hidden;width:100%;height:100%">
            <div region="center" border="true" title="">
                <div id="mainTab" class="easyui-tabs" data-options="fit:true,border:false">
                </div>
            </div>
        </div>
    </div>

    <div id="dlg-pwd" class="easyui-dialog" title="设置密码" style="width:400px;height:200px;padding:0px"
         data-options="modal:true,closed:true,minimizable:false,maximizable:false,resizable:false,collapsible:false">

        <div region="north" border="false">
            <div style="background:#F4F4F4;">
                <a class="easyui-linkbutton" iconCls="tbtn_save" plain="true" id="btnSavePwd" onclick="doSetPwd();">保存</a>
                <a class="easyui-linkbutton" iconCls="tbtn_quit" plain="true" onclick="$('#dlg-pwd').dialog('close');">关闭</a>
            </div>
        </div>
        <div region="center" border="false" title="" style="padding-left: 20px">
            <br/>
            <table class="tb_edit" style="overflow:hidden" cellspacing="0">
                <tbody>
                <tr>
                    <th>旧密码</th>
                    <td><input class="easyui-textbox" style="width:150px" type="password" name="password1" maxlength="100" id="password1"/></td>
                </tr>
                <tr>
                    <th>新密码</th>
                    <td><input class="easyui-textbox" style="width:150px" type="password" name="password2" maxlength="100" id="password2"/></td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td><input class="easyui-textbox" style="width:150px" type="password" name="password3" maxlength="100" id="password3"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>
</body>
</html>
