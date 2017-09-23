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
  <script type="text/javascript" src="<%=basePath %>js/business/parking/Parking.Init.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/parking/Parking.List.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/business/parking/Parking.Edit.js"></script>
  <script type="text/javascript">

    $(function(){
    	ParkingInit.getInstance('<%= basePath%>').init();
        
        var interval;

		function applyAjaxFileUpload(element) {
			$(element).AjaxFileUpload({
				action: '<%= basePath%>'+'ParkingAction.do?m=uploadFile',
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
          <input type="hidden" id="vehicleid" />
        </td>
      </tr>
      <tr>
        <th>客户名称</th>
        <td><input id="customer"/></td>
        <th>停车时间</th>
        <td><input id="period"/></td>
      </tr>
      <tr>
        <th>客户电话</th>
        <td><input id="customermobile"/></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>车牌号</th>
        <td><input id="licenseno"/></td>
        <th>车辆描述</th>
        <td><input id="cardescription"/></td>
      </tr>
      <tr>
        <th>入库时间</th>
        <td><input id="inventoryints"/></td>
        <th>出库时间</th>
        <td><input id="inventoryoutts"/></td>
      </tr>
      <tr>
      	<th>停车费</th>
        <td><input id="parkingfee"/></td>
        <th>备注</th>
        <td ><input id="comments"/></td>
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
        <th>客户名称</th>
        <td><input id="filtercustomer"/></td>
        <th>车牌号</th>
        <td><input id="filterlicenseno"/></td>
      </tr>
      <tr>
        <th>车辆描述</th>
        <td><input id="filtercardescription"/></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <th>入库时间</th>
        <td><input id="filterinventoryints"/></td>
        <th>出库时间</th>
        <td><input id="filterinventoryoutts"/></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<div id="bar_list">
  <table cellspacing="0" cellpadding="0">
    <tr>
      <td><a id="btnAddnew"></a></td>
      <td><a id="btnFilter"></a></td>
      <td><a id="btnRefresh"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnSelectAll"></a></td>
      <td><a id="btnUnselectAll"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnDelete"></a></td>
      <td><div class="datagrid-btn-separator"></div></td>
      <td><a id="btnClose"></a></td>
    </tr>
  </table>
</div>
<div id="dlg_upload">
  <div region="center" border="false" title="" style="padding-left: 20px">
    <br/>
   	<!-- <input class="easyui-filebox" name="fileUpload" id="fileUploadId" data-options="prompt:'浏览选择文件...', buttonText:'选择',required:true" style="width:80%">
   	 -->
   	 
        
 <input type="file" name="file1" id="file1" /> 
  </div>
</div>
<div id="msg" class="easyui-window" title="验证错误" data-options="modal:true,closed:true" style="width:700px;height:400px;padding:10px;">
    <span id="msgContentt"></span>
</div>
</body>
</html>
