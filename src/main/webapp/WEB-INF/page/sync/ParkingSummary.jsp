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
  <script type="text/javascript" src="<%=basePath %>js/jquery.ajaxfileupload.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/xutil.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/xcore.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/SumSummary/ParkingSummary.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/SumSummary/ParkingSummary.List.js"></script>
  <script type="text/javascript">

    $(function(){
    	ParkingSummaryInit.getInstance('<%= basePath%>').init();
    }

       );
    
	

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
      <!-- <td><a id="btnFilter"></a></td> -->
      <td><a id="btnRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnClose"></a></td>
      <td><input type="hidden" id="CurrentLoginUserAccesstype" value="<%= session.getAttribute("CurrentLoginUserAccesstype")%>" /></td>
    </tr>
  </table>
</div>
<div id="dlg_filter">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
    <table class="tb_filter" cellspacing="0">
      <tbody>
      <tr>
        <th>开始日期</th>
        <td><input id="purchasestart"/></td>
        <th>结束日期</th>
        <td><input id="purchaseend"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</body>