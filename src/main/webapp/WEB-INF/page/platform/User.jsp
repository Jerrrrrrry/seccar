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
  <script type="text/javascript" src="<%=basePath %>js/platform/user/User.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/platform/user/User.List.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/platform/user/User.Edit.js"></script>
  <script type="text/javascript">

    $(function(){
      UserInit.getInstance('<%= basePath%>').init();

    });

  </script>
</head>
<body class="easyui-layout">
<div region="center" border="false" title="" class="grid_line">
  <div class="easyui-layout" fit="true" style="overflow:hidden;width:100%;height:100%">
    <table id="list">
    </table>
  </div>
</div>

<div id="dlg_edit">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
    <table class="tb_edit" cellspacing="0">
      <tbody>
      <tr>
        <td></td>
        <td></td>
        <td></td>
        <td>
          启用<input type="checkbox" id="islocked"/>
          <input type="hidden" id="id" />
        </td>
      </tr>
      <tr>
        <th>账号<span style="color:red">*</span></th>
        <td><input id="userid"/></td>
        <th>名称<span style="color:red">*</span></th>
        <td><input id="username"/></td>
      </tr>
      <tr>
        <th>密码</th>
        <td><input id="password1"/></td>
        <th>重复密码</th>
        <td><input id="password2"/></td>
      </tr>
      <tr>
      	<th>权限类型</th>
        <td><input id="accesstype"/></td>
        <th>用户描述</th>
        <td ><input id="userdesc"/></td>
      </tr>
      <tr>
        <th>备注</th>
        <td ><input id="comments"/></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>创建时间</th>
        <td><input id="createdts"/></td>
        <th>最后修改时间</th>
        <td><input id="lastupdatedts"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

<div id="bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><a id="btnAddnew"></a></td>
      <td><a id="btnRefresh"></a></td>
      <td><a id="btnDelete"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnEnable"></a></td>
      <td><a id="btnDisable"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnSelectAll"></a></td>
      <td><a id="btnUnselectAll"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnClose"></a></td>
      <!-- 
      <td><a id="btnAddnewtest"></a></td>  
       -->
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
