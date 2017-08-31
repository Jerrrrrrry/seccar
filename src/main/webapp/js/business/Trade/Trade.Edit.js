var TradeEdit = {

    getInstance: function (basePath) {
//    	alert("edit");
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {
//        	alert("edit.clear");
        	$('#isdeleted').val('');
        	$('#issold').val('');
        	$('#traderid').val('');
        	$('#vehicleid').val('');
        	$('#licenseno').textbox('setValue', '');
            $('#vehicledesc').textbox('setValue', '');
            $('#tradername').textbox('setValue', '');
            $('#purchaseprice').textbox('setValue', '');
            $('#ownername').textbox('setValue', '');
            $('#ownerid').textbox('setValue', '');
            $('#purchasedate').textbox('setValue', '');
            $('#interestrate').textbox('setValue', '');
            $('#actualloan').textbox('setValue', '');
            $('#spareloan').textbox('setValue', '');
            $('#vehicletype').textbox('setValue', '自收车');
            $('#comments').textbox('setValue', '');
            $('#earnest').textbox('setValue', '');
            $('#tradecost').textbox('setValue', '');
            $('#sellprice').textbox('setValue', '');
            $('#selldate').textbox('setValue', '');
        };

        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {
//        	alert("edit.addNew");
            this.clear();
//            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#licenseno');
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
                url: basePath + 'TradeAction.do?m=view',
                data: {vehicleid: row.vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                        $('#licenseno').textbox({disabled:true});
                        $('#vehicledesc').textbox({disabled:true});
                        $('#tradername').textbox({disabled:true});
                        $('#purchaseprice').textbox({disabled:true});
                        $('#ownername').textbox({disabled:true});
                        $('#ownerid').textbox({disabled:true});
                        $('#purchasedate').textbox({disabled:true});
                        $('#interestrate').textbox({disabled:true});
                        $('#actualloan').textbox({disabled:true});
                        $('#spareloan').textbox({disabled:true});
                        $('#vehicletype').textbox({disabled:true});
                        $('#comments').textbox({disabled:true});
                        $('#earnest').textbox({disabled:true});
                        $('#tradecost').textbox({disabled:true});
                        $('#sellprice').textbox({disabled:false});
                        $('#selldate').textbox({disabled:false});
//                        $('#vehicleid').val('');
//                        $('#vehicleid').val(vo.dto.vehicleid);
//                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
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
                        $('#spareloan').textbox('setValue', vo.dto.spareloan);
                        $('#vehicletype').textbox('setValue', vo.dto.vehicletype);
                        $('#comments').textbox('setValue', vo.dto.comments);
                        $('#earnest').textbox('setValue', vo.dto.earnest);
                        $('#tradecost').textbox('setValue', vo.dto.tradecost);
                        $('#sellprice').textbox('setValue', vo.dto.sellprice);
                        $('#selldate').textbox('setValue', vo.dto.selldate);
//                        $('#vehicleid').val('');
//                    	$('#licenseno').textbox('setValue', '');
//                        $('#vehicledesc').textbox('setValue', '');
//                        $('#tradername').textbox('setValue', '');
//                        $('#purchaseprice').textbox('setValue', '');
//                        $('#ownername').textbox('setValue', '');
//                        $('#ownerid').textbox('setValue', '');
//                        $('#purchasedate').textbox('setValue', '');
//                        $('#interestrate').textbox('setValue', '');
//                        $('#actualloan').textbox('setValue', '');
//                        $('#spareloan').textbox('setValue', '');
//                        $('#earnest').textbox('setValue', '');
//                        $('#comments').textbox('setValue', '');
//                        $('#vehicledesc').textbox('setValue', vo.dto.period);
//                        $('#cardescription').textbox('setValue', vo.dto.cardescription);
//                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
//                        $('#inventoryints').textbox('setValue', vo.dto.inventoryints);
//                        $('#inventoryoutts').textbox('setValue', vo.dto.inventoryoutts);
//                        $('#Tradefee').textbox('setValue', vo.dto.Tradefee);
//                        $('#comments').textbox('setValue', vo.dto.comments);
//                        $('#btnEditDelete').linkbutton('enable');

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
            var vehicleid = $('#vehicleid').val();
//            alert(vehicleid);
            var licenseno = $('#licenseno').textbox('getValue');
            var vehicledesc = $('#vehicledesc').textbox('getValue');
            var tradername = $('#tradername').textbox('getValue');
            var purchaseprice = $('#purchaseprice').textbox('getValue');
            var ownername = $('#ownername').textbox('getValue');
            var ownerid = $('#ownerid').textbox('getValue');
            var purchasedate = $('#purchasedate').textbox('getValue');
            var interestrate = $('#interestrate').textbox('getValue');
            var actualloan = $('#actualloan').textbox('getValue');
            var spareloan = $('#spareloan').textbox('getValue');
            var earnest = $('#earnest').textbox('getValue');
            var vehicletype = $('#vehicletype').combobox('getValue');
            var comments = $('#comments').textbox('getValue');
            

            $.ajax({
                type: 'post',
                url: basePath + 'TradeAction.do?m=save',
                data: {
                	vehicleid: vehicleid,
                	licenseno: licenseno,
                	vehicledesc: vehicledesc,
                	tradername: tradername,
                	purchaseprice: purchaseprice,
                	ownername: ownername,
                	ownerid: ownerid,
                	purchasedate: purchasedate,
                	interestrate: interestrate,
                	earnest: earnest,
                	actualloan: actualloan,
                	spareloan: spareloan
                },
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {

                        $('#list').datagrid('reload');
                        if (addnew) {
                            edit.clear();
                            xutil.focus('#customer');
                        } else {
                            $('#dlg_add').dialog('close');
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
                        url: basePath + 'TradeAction.do?m=delete',
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
        // 销售出库
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
                        url: basePath + 'TradeAction.do?m=sold',
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