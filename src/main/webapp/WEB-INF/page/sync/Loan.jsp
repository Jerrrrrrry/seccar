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

        var interval;

		function applyAjaxFileUpload(element) {
			$(element).AjaxFileUpload({
				action: '<%= basePath%>'+'TradeAction.do?m=uploadFile',
				onChange: function(filename) {
					// Create a span element to notify the user of an upload in progress
					/*var $span = $("<span />")
						.attr("class", $(this).attr("id"))
						.text("上传中")
						.insertAfter($(this));

					$(this).remove();

					interval = window.setInterval(function() {
						var text = $span.text();
						if (text.length < 13) {
							$span.text(text + ".");
						} else {
							$span.text("上传中");
						}
					}, 200);*/
				},
				onSubmit: function(filename) {
					// Return false here to cancel the upload
					/*var $fileInput = $("<input />")
						.attr({
							type: "file",
							name: $(this).attr("name"),
							id: $(this).attr("id")
						});

					$("span." + $(this).attr("id")).replaceWith($fileInput);

					applyAjaxFileUpload($fileInput);

					return false;*/

					// Return key-value pair to be sent along with the file
					return true;
				},
				onComplete: function(filename, response) {
					window.clearInterval(interval);
					try{if (response != null && 'DO_NOT_LOGIN'==response.size)
						{
						top.location = '<%= basePath%>';
						return;
						}
					}catch(e){}
				
					/*var $span = $("span." + $(this).attr("id")).text(filename + ", 文件大小 " + response.size + "  状态: "+ response.uploadMsg + ". "),
						$fileInput = $("<input />")
							.attr({
								type: "file",
								name: $(this).attr("name"),
								id: $(this).attr("id")
							});*/

					var imgLength = $("img[name=image]").length+1;
					var imgsrc =response.uploadMsg;
					var imgdel = "images\\disable.png";
					//alert(imgsrc);
					//alert(imgLength);
					var str="<img name='image' id='img" + imgLength + "' src='"+response.uploadMsg+"' height='100' width='145'/><img id='del" + imgLength + "' src='"+imgdel+"' height='16' width='16' onclick='delFile(" + imgLength + ")'  />";	
					var s_HTML=$("#imgdiv")[0].innerHTML;
					$("#imgdiv")[0].innerHTML = s_HTML+str;
					
					var s_picturepath = $("#picturepath")[0].value;
					if(s_picturepath == ""){
						$("#picturepath")[0].value = response.uploadMsg;						
					}
					else{
						$("#picturepath")[0].value = s_picturepath+","+response.uploadMsg;
					}
					
					
					if (typeof(response.error) === "string") {
						$span.replaceWith($fileInput);

						applyAjaxFileUpload($fileInput);

						alert(response.error);

						return;
					}

					$("<a />")
						.attr("href", "#")
						.text("上传新数据")
						.bind("click", function(e) {
							$span.replaceWith($fileInput);

							applyAjaxFileUpload($fileInput);
						})
						.appendTo($span);
				}
			});
		}

		applyAjaxFileUpload("#file1");
    });

	//删除附件
	function delFile(id){
		//alert("#img"+id);
		$("#img"+id).remove();
		$("#del"+id).remove();
	}

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
          <input type="hidden" id="traderid"/><input type="hidden" id="tradername"/>
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
      	<th>抵押人手机号</th>
        <td><input id="mobileno"/></td>
        <th>借款金额</th>
        <td ><input id="borrowamount"/></td>
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
        <th>利息总额</th>
        <td><input id="totalinterest"/></td>
      </tr>
      <tr>
        <th>利率</th>
        <td><input id="interestrate"/></td>
      	<th>定金</th>
        <td><input id="earnest"/></td>
      </tr>
      <tr>
        <th>实际还款周期</th>
        <td><input id="actualmonths"/></td>
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
        <th>已还本金差</th>
        <td><input id="midinterestrate"/></td>
      	<th>中介返点</th>
        <td><input id="midinterest"/></td>
      </tr>
      <tr>
        <th>停车费</th>
        <td><input id="parkingfee"/></td>
      	<th>其他费用</th>
        <td><input id="otherfee"/></td>
      </tr>
      <tr>
        <th>实际打款金额</th>
        <td><input id="actualloan"/></td>
      	<th>备注</th>
        <td><input id="comments"/></td>
      </tr>
      <tr>
        <th>已还本金额</th>
        <td><input id="actualreturn"/></td>
      	<th>归还本金时间</th>
        <td><input id="actualreturndate"/></td>
      </tr>
      <tr height="20">
      </tr>
      <tr>
      	<td><a id="btnEditupload"></a></td>
        <td><input type = "hidden" id="picturepath"/></td>
        <td></td>
        <td></td>
      </tr>
      </tbody>
    </table> 
	<input type="file" name="file1" id="file1" style="display:none" /> 
    <div id="imgdiv"></div>
    <input type="hidden" id="CurrentLoginUserAccesstype" value="<%= session.getAttribute("CurrentLoginUserAccesstype")%>" />
    
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
