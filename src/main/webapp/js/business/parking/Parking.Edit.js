var ParkingEdit = {

    getInstance: function (basePath) {
//    	alert("edit");
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {

        	$('#vehicleid').val('');
        	$('#customer').textbox('setValue', '');
            $('#period').textbox('setValue', '');
            $('#customermobile').textbox('setValue', '');
            $('#licenseno').textbox('setValue', '');
            $('#cardescription').textbox('setValue', '');
            $('#inventoryints').textbox('setValue', '');
            $('#inventoryoutts').textbox('setValue', '');
            $('#parkingfee').textbox('setValue', '');
            $('#comments').textbox('setValue', '');
            $('#customer').textbox({disabled:false});
            $('#period').textbox({disabled:false});
            $('#customermobile').textbox({disabled:false});
            $('#licenseno').textbox({disabled:false});
            $('#cardescription').textbox({disabled:false});
            $('#inventoryints').textbox({disabled:false});
            $('#inventoryoutts').textbox({disabled:false});
            $('#parkingfee').textbox({disabled:false});
            $('#comments').textbox({disabled:false});
            
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveAndNew').linkbutton('enable');
            $('#btnSoldOut').linkbutton('disable');
        };
           
        
        edit.soldview = function () {
        	$('#customer').textbox({disabled:true});
            $('#period').textbox({disabled:true});
            $('#customermobile').textbox({disabled:true});
            $('#licenseno').textbox({disabled:true});
            $('#cardescription').textbox({disabled:true});
            $('#inventoryints').textbox({disabled:true});
            $('#inventoryoutts').textbox({disabled:true});
            $('#parkingfee').textbox({disabled:true});
            $('#comments').textbox({disabled:true});
            
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveAndNew').linkbutton('disable');
            $('#btnSoldOut').linkbutton('disable');
        };

        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {
//        	alert("edit.addNew");
            this.clear();
//            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#customer');
        };

        /***********************************************/
        // 复制
        /***********************************************/
//        edit.copy = function () {
//        	$('#id').val('');
//        	$('#userid').textbox('setValue', '');
//            $('#username').textbox('setValue', '');
//            $('#password1').textbox('setValue', '');
//            $('#password2').textbox('setValue', '');
//            $('#accesstype').textbox('setValue', '');
//            $('#comments').textbox('setValue', '');
//            $('#userdesc').textbox('setValue', '');
//            $('#createdts').textbox('setValue', '');
//            $('#lastupdatedts').textbox('setValue', '');
//            
//            $('#btnEditDelete').linkbutton('disable');
//            xutil.focus('#username');
//        };

        /***********************************************/
        // 显示
        /***********************************************/
        edit.view = function (index) {

            $('#list').datagrid('clearSelections');
            $('#list').datagrid('selectRow', index);
            var row = xutil.getGridRowByIndex('#list', index);
            currentIndex = index;
            this.clear();

            $.ajax({
                type: 'post',
                url: basePath + 'ParkingAction.do?m=view',
                data: {vehicleid: row.vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                        $('#vehicleid').val(vo.dto.vehicleid);
                        $('#customer').textbox('setValue', vo.dto.customer);
                        $('#period').textbox('setValue', vo.dto.period);
                        $('#customermobile').textbox('setValue', vo.dto.customermobile);
                        $('#cardescription').textbox('setValue', vo.dto.cardescription);
                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#inventoryints').textbox('setValue', vo.dto.inventoryints);
                        $('#inventoryoutts').textbox('setValue', vo.dto.inventoryoutts);
                        $('#parkingfee').textbox('setValue', vo.dto.parkingfee);
                        $('#comments').textbox('setValue', vo.dto.comments);
//                        $('#btnEditDelete').linkbutton('enable');
                        if (vo.dto.issold == '1')
                        {
                        	edit.soldview(); 
                        } else {
                        	 $('#btnSoldOut').linkbutton('enable');
                        }
                        $('#dlg_edit').dialog('open');
                        xutil.focus('#customer');
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
        // 显示下一个
        /***********************************************/
        edit.showNext = function(type){

            var index = xutil.moveGridNextByIndex('#list', type, currentIndex);
            edit.view(index);
        };

        /***********************************************/
        // 保存
        /***********************************************/
        edit.save = function (addnew) {
//        	alert("edit.save");
//            if (!$('#userid').textbox('isValid')) {
//                xutil.focus('#userid');
//                return;
//            }
//
//            if (!$('#username').textbox('isValid')) {
//                xutil.focus('#username');
//                return;
//            }

            var vehicleid = $('#vehicleid').val();
//            alert(vehicleid);
            var customer = $('#customer').textbox('getValue');
            var period = $('#period').textbox('getValue');
            var customermobile = $('#customermobile').textbox('getValue');
            var licenseno = $('#licenseno').textbox('getValue');
            if (licenseno.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入车牌号");
            	return;
            }
            var cardescription = $('#cardescription').textbox('getValue');
            var inventoryints = $('#inventoryints').textbox('getValue');
            if (inventoryints.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入入库时间");
            	return;
            }
            var inventoryoutts = $('#inventoryoutts').textbox('getValue');
            var parkingfee = $('#parkingfee').textbox('getValue');
            if (addnew == 'sold')
            {
	            if (parkingfee.replace(/(^\s*)|(\s*$)/g, "")=="" ||parkingfee.replace(/(^\s*)|(\s*$)/g, "")=="0")
	            {
	            	alert("请输入有效的停车费用(大于0的值)");
	            	return;
	            }
            }
            var comments = $('#comments').textbox('getValue');
            
            if (addnew == 'sold' && inventoryoutts.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入出库时间");
            	return;
            }
            var issold = '0';
            if (addnew == 'sold')
            {
            	issold = '1';
            }
            $.ajax({
                type: 'post',
                url: basePath + 'ParkingAction.do?m=save',
                data: {
                	vehicleid: vehicleid,
                    customer: customer,
                    period: period,
                    customermobile: customermobile,
                    licenseno: licenseno,
                    cardescription: cardescription,
                    inventoryints: inventoryints,
                    inventoryoutts: inventoryoutts,
                    parkingfee: parkingfee,
                    comments: comments,
                    issold:issold
                },
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {

                        $('#list').datagrid('reload');
                        if (addnew=='saveandnew') {
                            edit.clear();
                            xutil.focus('#customer');
                        } else {
                            $('#dlg_edit').dialog('close');
                        }

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
        // 删除
        /***********************************************/
        edit.del = function () {

            var vehicleid = $('#vehicleid').val();
            if (vehicleid == null || vehicleid.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    $('#btnDelete').linkbutton('disable');
                    $.ajax({
                        type: 'post',
                        url: basePath + 'ParkingAction.do?m=delete',
                        data: {vehicleid: vehicleid},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];
                            $('#btnDelete').linkbutton('enable');

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                edit.clear();
                                xutil.focus('#account');
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
                } else {
                    xutil.focus('#account');
                }
            });
        };

        /***********************************************/
        // 关闭
        /***********************************************/
        edit.close = function () {
            $('#dlg_edit').dialog('close');
        };

        return edit;
    }

};