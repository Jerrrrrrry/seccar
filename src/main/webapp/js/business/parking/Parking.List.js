var VoucherList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 过滤
        /***********************************************/
        list.filter = function () {
            $('#dlg_upload').dialog('open');
        };

        /***********************************************/
        // 刷新
        /***********************************************/
        list.refresh = function () {
            $('#list').datagrid('reload');
        };

        /***********************************************/
        // 全选
        /***********************************************/
        list.selectAll = function () {
            $('#list').datagrid('selectAll');
        };

        /***********************************************/
        // 全清
        /***********************************************/
        list.unselectAll = function () {
            $('#list').datagrid('unselectAll');
        };

        /***********************************************/
        // 清除过滤条件
        /***********************************************/
        list.clearFilter = function () {
            $('#bizDateFrom').datebox('clear');
            $('#bizDateTo').datebox('clear');
            $('#departmentNumber').textbox('clear');
            $('#departmentName').textbox('clear');
            xutil.focus('#bizDateFrom');
        };

        /***********************************************/
        // 借贷差额颜色
        /***********************************************/
        list.diffFormat = function(val, row){
            var result = '';
            if (val != '¥0.0000') {
                result = "<span style='color: red; font-weight: bold'>" + val + "</span>";
            }else{
                result = val;
            }

            return result;
        };

        /***********************************************/
        // 同步状态颜色
        /***********************************************/
        list.stateFormat = function(val, row){
            var result = '';
            if (val == '已同步') {
                result = "<span style='color: green; font-weight: bold'>" + val + "</span>";

            }else if (val == '已删除'){
                result = "<span style='color: red; font-weight: bold'>" + val + "</span>";

            }else{
                result = val;
            }

            return result;
        };

        /***********************************************/
        // 上传
        /***********************************************/
        list.doUpload = function(){

            /*if (!$('#fileUploadId').filebox('isValid')) {
                xutil.focus('#fileUploadId');
                return;
            }*/

           

            //$('#dlg_upload').dialog('close');
           
            /*$.AjaxFileUpload({
                url:basePath + 'CarLoanAction.do?m=uploadFile',
                type: 'post',
                secureuri:false,
                fileElementId:'file1',
                dataType:'json',
                elementIds: elementIds, //传递参数到服务器
                success:function(data,status){
                 if(typeof(data.error)!='undefined'){
                   if(data.error !=''){
                     alert(data.error);
                   }else{
                     alert(data.msg);
                   }
                 }
                },
                error:function(data,status,e){
                 alert(e);
                }
             });*/
            
           // var file = $('#fileUploadId').filebox('getValue');
            //alert(file);
            /*var formData = new FormData($("#fileuploadForm")[0]);
            $.ajax({
                type: 'post',
                url: basePath + 'CarLoanAction.do?m=uploadFile',
                data: formData,
                success: function (data) {

                    alert(data);
                },
                error: function () {
                    top.location = basePath;
                }
            });*/
            /*$('#list').datagrid('clearSelections');
            $('#list').datagrid({
                url: basePath + 'CarLoanAction.do?m=uploadFile',
                queryParams: prm});*.*/
        };
        
        list.doInit = function(){
        	var prm = {
                    bizDateFrom : "",
                    bizDateTo : "",
                    departmentNumber : "",
                    departmentName : ""
                };
	        $('#list').datagrid('clearSelections');
	        $('#list').datagrid({
	            url: basePath + 'ParkingAction.do?m=list',
	            queryParams: prm});
        }
        /***********************************************/
        // 同步
        /***********************************************/
        list.sync = function (status) {

            if (!xutil.isGridSelected('#list')) return;
            var id = xutil.getGridSelectedID('#list');

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_SYNC, function (r) {
                if (r) {
                    xutil.ajaxLoading();

                    $.ajax({
                        type: 'post',
                        url: basePath + 'ParkingAction.do?m=sync',
                        data: {id: id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];

                            if (vo.status == 'ok') {
                                xutil.ajaxLoadEnd();
                                $('#list').datagrid('reload');
                                $.messager.alert(AppConstant.M_INFO, '同步完成！', 'info');

                            } else if (vo.status == 'nologin') {
                                top.location = basePath;
                            } else {

                                xutil.ajaxLoadEnd();
                                if (vo.status == 'warning'){

                                    $("#msgContentt").html(vo.message);
                                    $("#msg").window('open');

                                }else{
                                    $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                                }
                            }
                        },
                        error: function () {
                            top.location = basePath;
                        }
                    });
                }
            });

        };

        return list;
    }
};