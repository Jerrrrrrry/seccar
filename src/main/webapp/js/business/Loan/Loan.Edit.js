var LoanEdit = {

    getInstance: function (basePath) {
//    	alert("LoanEdit getInstance");
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {
//        	alert("edit.clear");
//        	var userid = $('#userid').val();
//        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:false});
            $('#vehicledesc').textbox({disabled:false});
            $('#ownername').textbox({disabled:false});
            $('#ownerid').textbox({disabled:false});
            $('#borrowdate').textbox({disabled:false});
            $('#returndate').textbox({disabled:false});
            $('#periodmonths').textbox({disabled:false});
            $('#borrowamount').textbox({disabled:false});
            $('#interestrate').textbox({disabled:false});
            $('#interestpaid').textbox({disabled:false});
            $('#interestpaidto').textbox({disabled:false});
            $('#nextpaymentdate').textbox({disabled:false});
            $('#midinterestrate').textbox({disabled:false});
            $('#midinterest').textbox({disabled:false});
            $('#parkingfee').textbox({disabled:false});
            $('#otherfee').textbox({disabled:false});
            $('#actualloan').textbox({disabled:false});
            $('#comments').textbox({disabled:false});
            $('#actualreturn').textbox({disabled:true});
            $('#actualreturndate').textbox({disabled:true});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditReturned').linkbutton('disable');
            $('#btnEditAbandon').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            
        	$('#isdeleted').val('0');
        	$('#isreturned').val('0');
        	$('#isabandon').val('0');
        	$('#settlement').val('0');
        	$('#vehicleid').val('');
        	$('#licenseno').textbox('setValue', '');
            $('#vehicledesc').textbox('setValue', '');
            $('#ownername').textbox('setValue', '');
            $('#ownerid').textbox('setValue', '');
            $('#borrowdate').textbox('setValue', '');
            $('#returndate').textbox('setValue', '');
            $('#periodmonths').textbox('setValue', '');
            $('#borrowamount').textbox('setValue', '');
            $('#interestrate').textbox('setValue', '1.5');
            $('#interestpaid').textbox('setValue', '');
            $('#interestpaidto').textbox('setValue', '');
            $('#nextpaymentdate').textbox('setValue', '');
            $('#midinterestrate').textbox('setValue', '');
            $('#midinterest').textbox('setValue', '');
            $('#parkingfee').textbox('setValue', '');
            $('#otherfee').textbox('setValue', '');
            $('#actualloan').textbox('setValue', '');
            $('#comments').textbox('setValue', '');
            $('#actualreturn').textbox('setValue', '');
            $('#actualreturndate').textbox('setValue', '');
        };
        
        /***********************************************/
        // 无法修改
        /***********************************************/
        edit.disabledview = function () {
//        	alert("edit.clear");
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#Loanrname').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#purchasedate').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:true});
            $('#earnest').textbox({disabled:true});
            $('#Loancost').textbox({disabled:true});
            $('#sellprice').textbox({disabled:true});
            $('#selldate').textbox({disabled:true});
            $('#buyerid').textbox({disabled:true});
            $('#buyername').textbox({disabled:true});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditReturned').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            
        };
        
        /***********************************************/
        // 已售视图
        /***********************************************/
        edit.soldview = function () {
//        	alert("edit.clear");
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#Loanrname').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#purchasedate').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#Loancost').textbox({disabled:false});
            $('#sellprice').textbox({disabled:false});
            $('#selldate').textbox({disabled:false});
            $('#buyerid').textbox({disabled:false});
            $('#buyername').textbox({disabled:false});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditReturned').linkbutton('enable');
            $('#btnEditSettle').linkbutton('enable');
            $('#btnEditDelete').linkbutton('enable');
            
        };
        
        /***********************************************/
        // 修改视图
        /***********************************************/
        edit.editview = function () {
//        	alert("edit.clear");
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#Loanrname').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#purchasedate').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#Loancost').textbox({disabled:false});
            $('#sellprice').textbox({disabled:false});
            $('#selldate').textbox({disabled:false});
            $('#buyerid').textbox({disabled:false});
            $('#buyername').textbox({disabled:false});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditReturned').linkbutton('enable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('enable');
            
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
                url: basePath + 'LoanAction.do?m=view',
                data: {vehicleid: row.vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                    	if(vo.dto.isdeleted=="1"){
                    		edit.disabledview();
                    	}else if(vo.dto.settlement=="1"){
                    		edit.disabledview();
                    	}else if(vo.dto.issold=="1"){
                    		edit.soldview();
                    	}else{
                    		edit.editview();
                    	}
//                    	$('#licenseno').textbox({disabled:true});
//                        $('#vehicledesc').textbox({disabled:false});
//                        $('#Loanrname').textbox({disabled:true});
//                        $('#purchaseprice').textbox({disabled:true});
//                        $('#ownername').textbox({disabled:false});
//                        $('#ownerid').textbox({disabled:false});
//                        $('#purchasedate').textbox({disabled:true});
//                        $('#interestrate').textbox({disabled:false});
//                        $('#actualloan').textbox({disabled:true});
//                        $('#spareloan').textbox({disabled:true});
//                        $('#vehicletype').combobox({disabled:true});
//                        $('#comments').textbox({disabled:false});
//                        $('#earnest').textbox({disabled:false});
//                        $('#Loancost').textbox({disabled:false});
//                        $('#sellprice').textbox({disabled:false});
//                        $('#selldate').textbox({disabled:false});
//                        $('#vehicleid').val('');
//                        $('#vehicleid').val(vo.dto.vehicleid);
//                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#isdeleted').val(vo.dto.isdeleted);
                    	$('#issold').val(vo.dto.issold);
                    	$('#settlement').val(vo.dto.settlement);
                    	$('#Loanrid').val(vo.dto.Loanrid);
                    	$('#vehicleid').val(vo.dto.vehicleid);
                    	$('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#vehicledesc').textbox('setValue', vo.dto.vehicledesc);
                        $('#Loanrname').textbox('setValue', vo.dto.Loanrname);
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
                        $('#vehicletype').combobox('setValue', vo.dto.vehicletype);
                        $('#comments').textbox('setValue', vo.dto.comments);
                        $('#earnest').textbox('setValue', vo.dto.earnest);
                        $('#Loancost').textbox('setValue', vo.dto.Loancost);
                        $('#sellprice').textbox('setValue', vo.dto.sellprice);
                        $('#selldate').textbox('setValue', vo.dto.selldate);
                        $('#buyerid').textbox('setValue', vo.dto.buyerid);
                        $('#buyername').textbox('setValue', vo.dto.buyername);
//                        $('#vehicleid').val('');
//                    	$('#licenseno').textbox('setValue', '');
//                        $('#vehicledesc').textbox('setValue', '');
//                        $('#Loanrname').textbox('setValue', '');
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
//                        $('#Loanfee').textbox('setValue', vo.dto.Loanfee);
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
        edit.save = function (save) {
//        	alert("edit.save");
  
            var isdeleted = $('#isdeleted').val();
            var isreturned = $('#isreturned').val();
            var isabandon = $('#isabandon').val();
            var settlement = $('#settlement').val();
            var traderid = $('#userid').val();
            var tradername = $('#username').val();
            var vehicleid = $('#vehicleid').val();
            var licenseno = $('#licenseno').textbox('getValue');
            var vehicledesc = $('#vehicledesc').textbox('getValue');
            var ownername = $('#ownername').textbox('getValue');
            var ownerid = $('#ownerid').textbox('getValue');
            var borrowdate = $('#borrowdate').textbox('getValue');
            var returndate = $('#returndate').textbox('getValue');
            var periodmonths = $('#periodmonths').textbox('getValue');
            var borrowamount = $('#borrowamount').textbox('getValue');
            var interestrate = $('#interestrate').textbox('getValue');
            var interestpaid = $('#interestpaid').textbox('getValue');
            var interestpaidto = $('#interestpaidto').textbox('getValue');
            var nextpaymentdate = $('#nextpaymentdate').textbox('getValue');
            var midinterestrate = $('#midinterestrate').textbox('getValue');
            var midinterest = $('#midinterest').textbox('getValue');
            var parkingfee = $('#parkingfee').textbox('getValue');
            var otherfee = $('#otherfee').textbox('getValue');
            var actualloan = $('#actualloan').textbox('getValue');
            var comments = $('#comments').textbox('getValue');
            var actualreturn = $('#actualreturn').textbox('getValue');
            var actualreturndate = $('#actualreturndate').textbox('getValue');
            var operation = save

            $.ajax({
                type: 'post',
                url: basePath + 'LoanAction.do?m=save',
                data: {
                	isdeleted: isdeleted,
                	isreturned: isreturned,
                	isabandon: isabandon,
                	settlement: settlement,
                	traderid: traderid,
                	tradername: tradername,
                	vehicleid: vehicleid,
                	licenseno: licenseno,
                	vehicledesc: vehicledesc,
                	ownername: ownername,
                	ownerid: ownerid,
                	borrowdate: borrowdate,
                	returndate: returndate,
                	periodmonths: periodmonths,
                	borrowamount: borrowamount,
                	interestrate: interestrate,
                	interestpaid: interestpaid,
                	interestpaidto: interestpaidto,
                	nextpaymentdate: nextpaymentdate,
                	midinterestrate: midinterestrate,
                	midinterest: midinterest,
                	parkingfee: parkingfee,
                	otherfee: otherfee,
                	actualloan: actualloan,
                	comments: comments,
                	actualreturn : actualreturn,
                	actualreturndate : actualreturndate,
                	operation: operation
                },
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {

                        $('#list').datagrid('reload');
                        if (save == 'savenew') {
                            edit.clear();
                            xutil.focus('#licenseno');
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
//        
//        /***********************************************/
//        // 结算
//        /***********************************************/
//        edit.settle = function (addnew) {        
//            var isdeleted = $('#isdeleted').val();
//            var issold = $('#issold').val();
//            var Loanrid = $('#Loanrid').val();
//            var vehicleid = $('#vehicleid').val();
//            var licenseno = $('#licenseno').textbox('getValue');
//            var vehicledesc = $('#vehicledesc').textbox('getValue');
//            var Loanrname = $('#Loanrname').textbox('getValue');
//            var purchaseprice = $('#purchaseprice').textbox('getValue');
//            var ownername = $('#ownername').textbox('getValue');
//            var ownerid = $('#ownerid').textbox('getValue');
//            var purchasedate = $('#purchasedate').textbox('getValue');
//            var interestrate = $('#interestrate').textbox('getValue');
//            var actualloan = $('#actualloan').textbox('getValue');
//            var spareloan = $('#spareloan').textbox('getValue');
//            var vehicletype = $('#vehicletype').combobox('getValue');
//            var comments = $('#comments').textbox('getValue');
//            var earnest = $('#earnest').textbox('getValue');
//            var Loancost = $('#Loancost').textbox('getValue');
//            var sellprice = $('#sellprice').textbox('getValue');
//            var selldate = $('#selldate').textbox('getValue');
//            var settlement = 1;
//            $.ajax({
//                type: 'post',
//                url: basePath + 'LoanAction.do?m=save',
//                data: {
//                	isdeleted: isdeleted,
//                    issold: issold,
//                	Loanrid: Loanrid,
//                	vehicleid: vehicleid,
//                	licenseno: licenseno,
//                	vehicledesc: vehicledesc,
//                	Loanrname: Loanrname,
//                	purchaseprice: purchaseprice,
//                	ownername: ownername,
//                	ownerid: ownerid,
//                	purchasedate: purchasedate,
//                	interestrate: interestrate,
//                	actualloan: actualloan,
//                	spareloan: spareloan,
//                	vehicletype: vehicletype,
//                	comments: comments,
//                	earnest: earnest,
//                	Loancost: Loancost,
//                	sellprice: sellprice,
//                	selldate: selldate,
//                	settlement: settlement
//                },
//                success: function (data) {
//                    if (data == null || data.length == 0) return;
//                    var vo = data[0];
//
//                    if (vo.status == 'ok') {
//
//                        $('#list').datagrid('reload');
////                        if (addnew) {
////                            edit.clear();
////                            xutil.focus('#customer');
////                        } else {
//                            $('#dlg_add').dialog('close');
////                        }
//
//                    } else if (vo.status == 'nologin') {
//                        top.location = basePath;
//                    } else {
//                        $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
//                    }
//                },
//
//                error: function () {
//                    top.location = basePath;
//                }
//            });
//        };
        /***********************************************/
        // 删除
        /***********************************************/
        edit.del = function () {

            var vehicleid = $('#vehicleid').val();
            var isdeleted = $('#isdeleted').val();
            var issold = $('#issold').val();
            var settlement = $('#settlement').val();
            var vehicletype = $('#vehicletype').combobox('getValue');
//            var vehicletype = $('#vehicletype').val();
            var purchaseprice = $('#purchaseprice').textbox('getValue');
            
            if (vehicleid == null || vehicleid.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }
            if (isdeleted == "1" || settlement == "1") {
                $.messager.alert(AppConstant.M_NODELETE_ERROR, 'warning');
                return;
            }

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    $('#btnEditDelete').linkbutton('disable');
                    $.ajax({
                        type: 'post',
                        url: basePath + 'LoanAction.do?m=deletesingle',
                        data: {
                        	vehicleid: vehicleid,
                        	issold: issold,
                        	vehicletype: vehicletype,
                        	purchaseprice: purchaseprice
                        },
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];
                            $('#btnEditDelete').linkbutton('disable');

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                edit.clear();
                                xutil.focus('#licenseno');
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
                    xutil.focus('#licenseno');
                }
            });
        };
//        /***********************************************/
//        // 销售出库
//        /***********************************************/
//        edit.del = function () {
//
//            var vehicleid = $('#vehicleid').val();
//            if (vehicleid == null || vehicleid.length == 0) {
//                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
//                return;
//            }
//
//            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
//                if (r) {
//                    $('#btnEditDelete').linkbutton('disable');
//                    $.ajax({
//                        type: 'post',
//                        url: basePath + 'LoanAction.do?m=sold',
//                        data: {vehicleid: vehicleid},
//                        success: function (data) {
//
//                            if (data == null || data.length == 0) return;
//                            var vo = data[0];
//                            $('#btnEditDelete').linkbutton('enable');
//
//                            if (vo.status == 'ok') {
//                                $('#list').datagrid('reload');
//                                edit.clear();
//                                xutil.focus('#account');
//                            } else if (vo.status == 'nologin') {
//                                top.location = basePath;
//                            } else {
//                                $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
//                            }
//                        },
//                        error: function () {
//                            top.location = basePath;
//                        }
//                    });
//                } else {
//                    xutil.focus('#account');
//                }
//            });
//        };

        /***********************************************/
        // 关闭
        /***********************************************/
        edit.close = function () {
            $('#dlg_add').dialog('close');
        };

        return edit;
    }

};