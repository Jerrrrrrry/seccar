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
  <script type="text/javascript" src="<%=basePath %>js/easyui-ex.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/xutil.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/xcore.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/secondcar/SecondCar.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/secondcar/SecondCar.List.js"></script>
  <script type="text/javascript">

    $(function(){
        ICStockInit.getInstance('<%= basePath%>').init();

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

<div id="bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><a id="btnFilter"></a></td>
      <td><a id="btnRefresh"></a></td>
      <td><a id="btnSync"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnSelectAll"></a></td>
      <td><a id="btnUnselectAll"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnClose"></a></td>
    </tr>
  </table>
</div>
<div id="dlg_filter">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
    <table class="tb_edit" cellspacing="0">
      <tbody>
      <tr>
        <th>结算日期</th>
        <td><input id="bizDateFrom"/>
        </td>
        <th>至</th>
        <td><input id="bizDateTo"/>
        </td>
      </tr>
      <tr>
        <th>门店编号</th>
        <td><input id="departmentNumber"/></td>
        <th>门店名称</th>
        <td><input id="departmentName"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<div id="msg" class="easyui-window" title="验证错误" data-options="modal:true,closed:true" style="width:700px;height:400px;padding:10px;">
    <span id="msgContentt"></span>
</div>
</body>
</html>
