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
  <script type="text/javascript" src="<%=basePath %>js/business/Loan/Loan.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/Loan/Loan.List.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/Loan/Loan.Edit.js"></script>
  <script type="text/javascript">

    $(function(){
    	LoanInit.getInstance('<%=basePath%>').init();
    	   // $("input",$("#comments").next("td")).click(function(){
    	   //     alert("ok");
    	   // });

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

<div id="dlg_add">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
    <table class="tb_add" cellspacing="0">
      <tbody>
      <tr>
        <td><input type="hidden" id="isdeleted" /></td>
        <td><input type="hidden" id="isreturned" />
        <input type="hidden" id="isabandon" />
        <input type="hidden" id="settlement" />
        </td>
        <td>
          <input type="hidden" id="traderid"/>
          <input type="hidden" id="userid" value="<%= session.getAttribute("CurrentLoginUserAccount")%>" />
          <input type="hidden" id="username" value="<%= session.getAttribute("CurrentLoginUserName")%>" />
        </td>
        <td>
          <input type="hidden" id="vehicleid" />
        </td>
      </tr>
      <tr>
        <th>车牌号</th>
        <td><input id="licenseno"/></td>
        <th>车辆描述</th>
        <td><input id="vehicledesc"/></td>
      </tr>
      <tr>
      	<th>抵押人姓名</th>
        <td><input id="ownername"/></td>
        <th>抵押人身份证</th>
        <td><input id="ownerid"/></td>
      </tr>
      <tr>
        <th>借款日期</th>
        <td><input id="borrowdate"></td>
        <th>约定还款日期</th>
        <td><input id="returndate"/></td>   
      </tr>
      <tr>
        <th>约定还款周期（月数）</th>
        <td><input id="periodmonths"/></td>
        <th>借款金额</th>
        <td ><input id="borrowamount"/></td>
      </tr>
      <tr>
        <th>利率</th>
        <td><input id="interestrate"/></td>
      	<th>已付利息</th>
        <td><input id="interestpaid"/></td>
      </tr>
      <tr>
        <th>利息付至日期</th>
        <td><input id="interestpaidto"/></td>
      	<th>下次付利日期</th>
        <td><input id="nextpaymentdate"/></td>
      </tr>
      <tr>
        <th>中介利率</th>
        <td><input id="midinterestrate"/></td>
      	<th>中介利息</th>
        <td><input id="midinterest"/></td>
      </tr>
      <tr>
        <th>停车费</th>
        <td><input id="parkingfee"/></td>
      	<th>其他费用</th>
        <td><input id="otherfee"/></td>
      </tr>
      <tr>
        <th>实付金额</th>
        <td><input id="actualloan"/></td>
      	<th>备注</th>
        <td><input id="comments"/></td>
      </tr>
      <tr>
        <th>已还本金额</th>
        <td><input id="actualreturn"/></td>
      	<th>上次归还本金时间</th>
        <td><input id="actualreturndate"/></td>
      </tr>
      </tbody>
    </table> 
  </div>
</div>

<div id="dlg_filter">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
    <table class="tb_filter" cellspacing="0">
      <tbody>
      <tr>
        <th>已删除</th>
        <td><input type="checkbox" id="filterisdeleted"/></td>
        <th>已弃车</th>
        <td><input type="checkbox" id="filterisabandon"/></td>
      </tr>
      <tr>
        <th>已归还</th>
        <td><input id="filterisreturned"/></td>
        <th>已结算</th>
        <td><input id="filtersettlement"/></td>
      </tr>
      <tr>
        <th>车牌号</th>
        <td><input id="filterlicenseno"/></td>
        <th>车辆描述</th>
        <td><input id="filtercardescription"/></td>
      </tr>
      <tr>
        <th>客户名称</th>
        <td><input id="filtercustomer"/></td>
        <th></th>
        <td></td>
      </tr>
      <tr>
        <th>借款日期起</th>
        <td><input id="loanstart"/></td>
        <th>借款日期止</th>
        <td><input id="loanend"/></td>
      </tr>
      <tr>
        <th>约定还款日期起</th>
        <td><input id="returnstart"/></td>
        <th>约定还款日期止</th>
        <td><input id="returnend"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<!-- 
<div id="dlg_upload">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
     -->
   	<!-- <input class="easyui-filebox" name="fileUpload" id="fileUploadId" data-options="prompt:'浏览选择文件...', buttonText:'选择',required:true" style="width:80%">
   	 	<!--
 <input type="file" name="file1" id="file1" /> 
  </div>
</div>
--> 

<div id="bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><a id="btnAddnew"></a></td>
      <td><a id="btnFilter"></a></td>
      <td><a id="btnRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnClose"></a></td>
    </tr>
  </table>
</div>

<div id="msg" class="easyui-window" title="验证错误" data-options="modal:true,closed:true" style="width:700px;height:400px;padding:10px;">
    <span id="msgContentt"></span>
</div>
</body>
</html>
