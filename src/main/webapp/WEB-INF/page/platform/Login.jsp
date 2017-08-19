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
    <meta charset="UTF-8">
    <title>东方汽车金融管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/default.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        function load() {
            $('#txtAccount').next("span").find('input')[0].focus();
        }

        function submitForm() {
            var account = $('#txtAccount').val();
            var password = $('#txtPassword').val();

            if (account.length == 0) {
                $('#txtAccount').next("span").find('input')[0].focus() ;
                $.messager.alert('系统提示','请输入帐号!','warning');
                return false;
            }

            $('#ff').submit();
        }

        $(function () {

            var explorer = window.navigator.userAgent ;
            if (explorer.indexOf("MSIE") >= 0){
                var href = 'http://www.haosou.com/s?q=chrome&src=srp&fr=home_haoso.com';
                var html = '<div class="sys_info"><div class="sys_tip icon-tip"></div>';
                html += '<div>您正在使用IE浏览器，使用速度会较慢，建议使用谷歌浏览器，或360浏览器、QQ浏览器等国产浏览器的急速模式。</div><br/><div style="color: red">本系统不支持IE6、IE7、IE8，如使用IE浏览器，请升级至IE9以上。<div></div>';

                $('#info').html(html);
            }
        });

    </script>
</head>
<body onload="load()">
<form id="ff" action="<%=basePath %>LoginAction.do?m=login" method="post">
    <div id="info"></div>
    <div style="margin:10px 0;"></div>
    <div style="position:absolute; top:50%; left:50%; margin:-150px 0 0 -200px;">
        <div class="easyui-panel" title="系统登录" style="width:400px;padding:5px">
            <div style="padding:40px 0px 20px 70px">
                <table class="tb_edit2">
                    <tr>
                        <th>账号</th>
                        <td><input id="txtAccount" name="account" type="text" class="easyui-textbox" style="width:150px;" value=""/></td>
                    </tr>
                    <tr>
                        <th>密码</th>
                        <td><input id="txtPassword" name="password" type="password" class="easyui-textbox" value="" style="width:150px;" /></td>
                    </tr>
                </table>
            </div>
            <div style="text-align:center;padding:5px">
                <a onclick="return submitForm();" class="easyui-linkbutton" data-options="iconCls:'tbtn_ok'">登录</a>
            </div>
        </div>
    </div>
    <div id="footer">&nbsp;&nbsp;蓝海餐饮KIS接口</div>
</form>
</body>
</html>

<script>
    var err = "<html:errors/>";
    if (err != ""){
        $.messager.alert('系统提示',err,'warning');
    }
</script>