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
  <script type="text/javascript" src="<%=basePath %>js/business/SumSummary/SumSummary.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/SumSummary/SumSummary.List.js"></script>
  <script type="text/javascript">

    $(function(){
    	SumSummaryInit.getInstance('<%= basePath%>').init();
    	var myDate = new Date();
    	var currentMonth= myDate.getMonth() + 1;  
    	var currentYearMonth =myDate.getFullYear()+'年'+currentMonth+'月';
    	document.getElementById('stockLabelId').innerText = currentYearMonth+'库存合计：';
    	document.getElementById('loanLabelId').innerText = currentYearMonth+'车贷合计';
    	document.getElementById('soldLabelId').innerText = currentYearMonth+'利润合计：';
    	document.getElementById('interestLabelId').innerText = '利息成本合计：';
    }

       );
    
	

  </script>
</head>
<body class="easyui-layout">
<div region="center" border="false" title="" class="grid_line">
  <div   style="overflow:hidden;width:100%;height:22%">
    <table id="stocklist">
    </table>
  </div>
  <hr />
   <div   style="overflow:hidden;width:100%;height:18%">
    <table id="loanlist">
    </table>
  </div>
  <hr />
  <div   style="overflow:hidden;width:100%;height:26%">
    <table id="soldlist">
    </table>
  </div>
  <hr />
  <div   style="overflow:hidden;width:100%;height:26%">
    <table id="interestCostlist">
    </table>
  </div>
  <hr />
</div>
<div id="stock_bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><font color="red" id="stockLabelId"></font></td>
      <!-- <td><a id="btnFilter"></a></td> -->
      <td><a id="btnRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <!-- <td><a id="btnClose"></a></td> -->
      <td><a id="exportStockbtn"></a></td>
      <td><input type="hidden" id="CurrentLoginUserAccesstype" value="<%= session.getAttribute("CurrentLoginUserAccesstype")%>" /></td>
    </tr>
  </table>
</div>
<div id="loan_bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><font color="red" id="loanLabelId"></font></td>
      <!-- <td><a id="btnFilter"></a></td> -->
      <td><a id="loanBtnRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <!-- <td><a id="btnClose"></a></td> -->
      <td><a id="exportLoanbtn"></a></td>
      <td><input type="hidden" id="CurrentLoginUserAccesstype" value="<%= session.getAttribute("CurrentLoginUserAccesstype")%>" /></td>
    </tr>
  </table>
</div>
<div id="sold_bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><font color="red"  id="soldLabelId"></font></td>
      <!-- <td><a id="btnFilter"></a></td> -->
      <td><a id="btnSoldRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <!-- <td><a id="btnClose"></a></td> -->
      <td><a id="exportSoldbtn"></a></td>
      <!-- <td><div class="datagrid-btn-separator"></div></td>
      <td><font color="red">注:对于抵押车，收车价格合计＝打款金额合计;　卖车价格合计＝已付利息合计＋已还本金合计</font></td> -->
      <td><input type="hidden" id="CurrentLoginUserAccesstype" value="<%= session.getAttribute("CurrentLoginUserAccesstype")%>" /></td>
    </tr>
  </table>
</div>
<div id="interest_bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><font color="red"  id="interestLabelId"></font></td>
      <!-- <td><a id="btnFilter"></a></td> -->
      <td><a id="btnInterestCostRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <!-- <td><a id="btnClose"></a></td> -->
      <td><a id="exportInterestCostbtn"></a></td>
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