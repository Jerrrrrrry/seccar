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
  <script type="text/javascript" src="<%=basePath %>js/business/Trade/Trade.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/Trade/Trade.List.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/Trade/Trade.Edit.js"></script>
  <script type="text/javascript">

    $(function(){
    	TradeInit.getInstance('<%= basePath%>').init();
        
        var interval;

		function applyAjaxFileUpload(element) {
			$(element).AjaxFileUpload({
				action: '<%= basePath%>'+'TradeAction.do?m=uploadFile',
				onChange: function(filename) {
					// Create a span element to notify the user of an upload in progress
					var $span = $("<span />")
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
					}, 200);
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
					var $span = $("span." + $(this).attr("id")).text(filename + ", 文件大小 " + response.size + "  状态: "+ response.uploadMsg + ". "),
						$fileInput = $("<input />")
							.attr({
								type: "file",
								name: $(this).attr("name"),
								id: $(this).attr("id")
							});

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
        <td><input type="hidden" id="issold" />
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
        <th>经办人</th>
        <td><input id="tradername"></td>
        <th>收车价</th>
        <td><input id="purchaseprice"/></td>   
      </tr>
      <tr>
      	<th>卖车人姓名</th>
        <td><input id="ownername"/></td>
        <th>卖车人身份证</th>
        <td><input id="ownerid"/></td>
      </tr>
      <tr>
        <th>收车日期</th>
        <td><input id="purchasedate"/></td>
        <th>借款利率</th>
        <td ><input id="interestrate"/></td>
      </tr>
      <tr>
      	<th>实际借款金额</th>
        <td><input id="actualloan"/></td>
        <th>借款余量</th>
        <td><input id="spareloan"/></td>
      </tr>
      <tr>
        <th>车辆类型</th>
        <td><input id="vehicletype"/></td>
      	<th>备注</th>
        <td><input id="comments"/></td>
      </tr>
      <tr>
        <th>定金</th>
        <td><input id="earnest"/></td>
      	<th>交易费用</th>
        <td><input id="tradecost"/></td>
      </tr>
      <tr>
        <th>销售价格</th>
        <td><input id="sellprice"/></td>
      	<th>销售日期</th>
        <td><input id="selldate"/></td>
      </tr>
      <tr>
        <th>购车人姓名</th>
        <td><input id="buyername"/></td>
      	<th>购车人身份证</th>
        <td><input id="buyerid"/></td>
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
        <td></td>
        <td></td>
        <th>已删除</th>
        <td><input type="checkbox" id="filterisdeleted"/></td>
      </tr>
      <tr>
        <th>已出售</th>
        <td><input id="filterissold"/></td>
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
        <th>经办人</th>
        <td><input id="filtertradername"/></td>
        <th>客户名称</th>
        <td><input id="filtercustomer"/></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <th>车辆类型</th>
        <td><input id="filtervehicletype"/></td>
      </tr>
      <tr>
        <th>购车日期起</th>
        <td><input id="purchasestart"/></td>
        <th>购车日期止</th>
        <td><input id="purchaseend"/></td>
      </tr>
      <tr>
        <th>售车日期起</th>
        <td><input id="soldstart"/></td>
        <th>售车日期止</th>
        <td><input id="soldend"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

<div id="dlg_upload">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
   	<!-- <input class="easyui-filebox" name="fileUpload" id="fileUploadId" data-options="prompt:'浏览选择文件...', buttonText:'选择',required:true" style="width:80%">
   	 -->
 <input type="file" name="file1" id="file1" /> 
  </div>
</div>

<div id="bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><a id="btnAddnew"></a></td>
      <td><a id="btnFilter"></a></td>
      <td><a id="btnRefresh"></a></td>
      <!-- 
      <td><a id="btnView"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnSettle"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnSelectAll"></a></td>
      <td><a id="btnUnselectAll"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnDelete"></a></td>
      -->
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
