var TradeList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath); 
        /***********************************************/
        // 新增
        /***********************************************/
        list.addNew = function () {
//        	alert("addnew");
            var edit = TradeEdit.getInstance(basePath);
            edit.clear();
            $('#dlg_add').dialog('open');
            xutil.focus('#licenseno');
        };
        
        /***********************************************/
        // 打开过滤界面
        /***********************************************/
        list.openfilter = function () {
        	list.clearFilter();
            $('#dlg_filter').dialog('open');
        };
        /***********************************************/
        // 过滤
        /***********************************************/
        list.filter = function () {
        	 $('#dlg_filter').dialog('close');
            var filterField = "filter";
            var filtercustomer = $('#filtercustomer').textbox('getValue');
            var filterlicenseno = $('#filterlicenseno').textbox('getValue');
            var filtercardescription = $('#filtercardescription').textbox('getValue');
            var filterinventoryints = $('#filterinventoryints').datebox('getValue');
            var filterinventoryoutts = $('#filterinventoryoutts').datebox('getValue');

            var prm = {
            		filterField: filterField,
            		filtercustomer: filtercustomer, 
            		filterlicenseno: filterlicenseno, 
            		filtercardescription: filtercardescription, 
            		filterinventoryints: filterinventoryints,
            		filterinventoryoutts: filterinventoryoutts};
            $('#list').datagrid('clearSelections');
            $('#list').datagrid({queryParams: prm});
//            xutil.focus('#filterValue');
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
            $('#filterinventoryints').datebox('clear');
            $('#filterinventoryoutts').datebox('clear');
            $('#filtercustomer').textbox('clear');
            $('#filterlicenseno').textbox('clear');
            $('#filtercardescription').textbox('clear');
            xutil.focus('#filtercustomer');
        };
        /***********************************************/
        // 删除
        /***********************************************/
        list.del = function () {

            if (!xutil.isGridSelected('#list')) return;
            var vehicleid = xutil.getGridSelectedVehicleID('#list');
//            alert(vehicleid);
            xutil.ajaxLoading('body');
            $.ajax({
                type: 'post',
                url: basePath + 'TradeAction.do?m=delete',
                data: {vehicleid: vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#list').datagrid('reload');
                    } else if (vo.status == 'nologin') {
                        top.location = basePath;
                    } else {
                        $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                    }

                },
                error: function () {
                    top.location = basePath;
                }
            });
        };
        /***********************************************/
        // 销售出库
        /***********************************************/
        list.view = function (index) {
        
            if (!xutil.isGridSelected('#list')) return;
            var vehicleid = xutil.getGridSelectedVehicleID('#list');
//            alert(vehicleid);
                $.ajax({
                    type: 'post',
                    url: basePath + 'TradeAction.do?m=view',
                    data: {vehicleid: vehicleid},
                    success: function (data) {

                        if (data == null || data.length == 0) return;
                        var vo = data[0];

                        if (vo.status == "ok") {
                        	$('#licenseno').textbox({disabled:true});
                            $('#vehicledesc').textbox({disabled:false});
                            $('#tradername').textbox({disabled:true});
                            $('#purchaseprice').textbox({disabled:true});
                            $('#ownername').textbox({disabled:false});
                            $('#ownerid').textbox({disabled:false});
                            $('#purchasedate').textbox({disabled:true});
                            $('#interestrate').textbox({disabled:false});
                            $('#actualloan').textbox({disabled:true});
                            $('#spareloan').textbox({disabled:true});
                            $('#vehicletype').combobox({disabled:true});
                            $('#comments').textbox({disabled:false});
                            $('#earnest').textbox({disabled:false});
                            $('#tradecost').textbox({disabled:false});
                            $('#sellprice').textbox({disabled:false});
                            $('#selldate').textbox({disabled:false});
//                            $('#vehicleid').val('');
//                            $('#vehicleid').val(vo.dto.vehicleid);
//                            $('#licenseno').textbox('setValue', vo.dto.licenseno);
                            $('#isdeleted').val(vo.dto.isdeleted);
                        	$('#issold').val(vo.dto.issold);
                        	$('#traderid').val(vo.dto.traderid);
                        	$('#vehicleid').val(vo.dto.vehicleid);
                        	$('#licenseno').textbox('setValue', vo.dto.licenseno);
                            $('#vehicledesc').textbox('setValue', vo.dto.vehicledesc);
                            $('#tradername').textbox('setValue', vo.dto.tradername);
                            $('#purchaseprice').textbox('setValue', vo.dto.purchaseprice);
                            $('#ownername').textbox('setValue', vo.dto.ownername);
                            $('#ownerid').textbox('setValue', vo.dto.ownerid);
                            $('#purchasedate').textbox('setValue', vo.dto.purchasedate);
                            $('#interestrate').textbox('setValue', vo.dto.interestrate);
                            $('#actualloan').textbox('setValue', vo.dto.actualloan);
                            if(vo.dto.vehicletype=="第三方"){
                            	$('#spareloan').textbox('setValue', vo.dto.spareloan);
                            }else{
                            	$('#spareloan').textbox('setValue', '0');	
                            }
//                            $('#spareloan').textbox('setValue', vo.dto.spareloan);
                            $('#vehicletype').combobox('setValue', vo.dto.vehicletype);
                            $('#comments').textbox('setValue', vo.dto.comments);
                            $('#earnest').textbox('setValue', vo.dto.earnest);
                            $('#tradecost').textbox('setValue', vo.dto.tradecost);
                            $('#sellprice').textbox('setValue', vo.dto.sellprice);
                            $('#selldate').textbox('setValue', vo.dto.selldate);
//                            $('#vehicleid').val('');
//                        	$('#licenseno').textbox('setValue', '');
//                            $('#vehicledesc').textbox('setValue', '');
//                            $('#tradername').textbox('setValue', '');
//                            $('#purchaseprice').textbox('setValue', '');
//                            $('#ownername').textbox('setValue', '');
//                            $('#ownerid').textbox('setValue', '');
//                            $('#purchasedate').textbox('setValue', '');
//                            $('#interestrate').textbox('setValue', '');
//                            $('#actualloan').textbox('setValue', '');
//                            $('#spareloan').textbox('setValue', '');
//                            $('#earnest').textbox('setValue', '');
//                            $('#comments').textbox('setValue', '');
//                            $('#vehicledesc').textbox('setValue', vo.dto.period);
//                            $('#cardescription').textbox('setValue', vo.dto.cardescription);
//                            $('#licenseno').textbox('setValue', vo.dto.licenseno);
//                            $('#inventoryints').textbox('setValue', vo.dto.inventoryints);
//                            $('#inventoryoutts').textbox('setValue', vo.dto.inventoryoutts);
//                            $('#Tradefee').textbox('setValue', vo.dto.Tradefee);
//                            $('#comments').textbox('setValue', vo.dto.comments);
//                            $('#btnEditDelete').linkbutton('enable');

                            $('#dlg_add').dialog('open');
                            xutil.focus('#sellprice');
                        } else if (vo.status == 'nologin') {
                            top.location = basePath;
                        } else {
                            $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                        }
                    },

                    error: function () {
                        top.location = basePath;
                    }
                });
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
        
//        list.doInit = function(){
//        	var prm = {
//                    bizDateFrom : "",
//                    bizDateTo : "",
//                    departmentNumber : "",
//                    departmentName : ""
//                };
//	        $('#list').datagrid('clearSelections');
//	        $('#list').datagrid({
//	            url: basePath + 'TradeAction.do?m=list',
//	            queryParams: prm});
//        }
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
                        url: basePath + 'TradeAction.do?m=sync',
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