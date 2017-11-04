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

        //指定列求和
         list.compute = function (colName) {
             var rows = $('#list').datagrid('getRows');
//         	alert(rows);
             var total = 0;
             for (var i = 0; i < rows.length; i++) {
                 total += parseFloat(rows[i][colName]);
             }

//         	alert(total);
             return total;
         }
       //过期提醒
         list.rowcolor = function (index, row) {
//         	alert("rowcolor");
//         	alert("list.daydiff");
         	var issold = row.issold;
         	var isdeleted = row.isdeleted;
         	var color = '';
         	if(issold == '1' || isdeleted == '1'){
         		color = '';
         	}else{
 	        	var today = list.getNowFormatDate();
             	var purchasedate = row.purchasedate;
 	        	if(today != '' && today != null && purchasedate != '' && purchasedate != null){
 	        		var buyindays = list.getDays(today.substring(0,10), purchasedate.substring(0,10));
 	        		if(buyindays >=30){
 	        			color =  'background-color:rgb(255,0,0);color:black;font-weight:bold;';
 	        		} else if(buyindays >=15){
 	        			color =  'background-color:rgb(255,106,106);color:black;font-weight:bold;';
 	        		} else {
 	        			color =  'background-color:rgb(255,218,185);color:black;font-weight:bold;';
 	        		}
 	        	}
         	}
         	return color;
         };

         //指定列日期相差天数
           list.getDays = function (strDateStart,strDateEnd){
//           	alert("list.getDays");
           	   var strSeparator = "-"; //日期分隔符
           	   var oDate1;
           	   var oDate2;
           	   var iDays;
           	   oDate1= strDateStart.split(strSeparator);
           	   oDate2= strDateEnd.split(strSeparator);
           	   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
           	   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
           	   iDays = parseInt(Math.ceil(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
           	   return iDays ;
           	};
           //当前日期格式化
             list.getNowFormatDate = function () {

                 var date = new Date();

                 var seperator1 = "-";

                 var seperator2 = ":";

                 var month = date.getMonth() + 1;

                 var strDate = date.getDate();

                 if (month >= 1 && month <= 9) {

                     month = "0" + month;

                 }

                 if (strDate >= 0 && strDate <= 9) {

                     strDate = "0" + strDate;

                 }

                 var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate

                         + " " + date.getHours() + seperator2 + date.getMinutes()

                         + seperator2 + date.getSeconds();

                 return currentdate;

             } ;
             
        /***********************************************/
        // 打开过滤界面
        /***********************************************/
        list.openfilter = function () {
        	//list.clearFilter();
            $('#dlg_filter').dialog('open');
        };
        /***********************************************/
        // 过滤
        /***********************************************/
        list.filter = function () {
        	$('#dlg_filter').dialog('close');
            var filterField = "filter";
            var filterisdeleted = $('#filterisdeleted').prop('checked') ? 1 : 0;
            var filterissold = $('#filterissold').combobox('getValue');
            var filtersettlement = $('#filtersettlement').combobox('getValue');
            var filterlicenseno = $('#filterlicenseno').textbox('getValue');
            var filtercardescription = $('#filtercardescription').textbox('getValue');
            var filtertradername = $('#filtertradername').textbox('getValue');
            var filtercustomer = $('#filtercustomer').textbox('getValue');
            var filtervehicletype = $('#filtervehicletype').combobox('getValue');
            var purchasestart = $('#purchasestart').datebox('getValue');
            var purchaseend = $('#purchaseend').datebox('getValue');
            var soldstart = $('#soldstart').datebox('getValue');
            var soldend = $('#soldend').datebox('getValue');

            var prm = {
            		filterField: filterField,
            		filterlicenseno: filterlicenseno, 
            		filtercardescription: filtercardescription, 
            		filtertradername: filtertradername, 
            		filtercustomer: filtercustomer, 
            		filtervehicletype: filtervehicletype, 
            		purchasestart: purchasestart,
            		purchaseend: purchaseend,
            		soldstart: soldstart,
            		soldend: soldend,
            		filterisdeleted: filterisdeleted,
            		filterissold: filterissold,
            		filtersettlement: filtersettlement
            		};
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
        	$('#filterisdeleted').prop('checked', false);
        	$('#filterissold').combobox('setValue','');
        	$('#filtersettlement').combobox('setValue','');
            $('#filterlicenseno').textbox('clear');
            $('#filtercardescription').textbox('clear');
            $('#filtertradername').textbox('clear');
            $('#filtercustomer').textbox('clear');
        	$('#filtervehicletype').combobox('setValue','');
            $('#purchasestart').datebox('clear');
            $('#purchaseend').datebox('clear');
            $('#soldstart').datebox('clear');
            $('#soldend').datebox('clear');
            xutil.focus('#filterlicenseno');
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
        
//        /***********************************************/
//        // 查看
//        /***********************************************/
//        list.settle = function (settle) {
//        
//            if (!xutil.isGridSelected('#list')) return;
//            var vehicleid = xutil.getGridSelectedVehicleID('#list');
//            var isdeleted = $('#isdeleted').val();
//            var issold = $('#issold').val();
//            var traderid = $('#traderid').val();
//            var vehicleid = $('#vehicleid').val();
//            var licenseno = $('#licenseno').textbox('getValue');
//            var vehicledesc = $('#vehicledesc').textbox('getValue');
//            var tradername = $('#tradername').textbox('getValue');
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
//            var tradecost = $('#tradecost').textbox('getValue');
//            var sellprice = $('#sellprice').textbox('getValue');
//            var selldate = $('#selldate').textbox('getValue');
//            var operation = save
////            alert(sellprice);
////            if (save == 'settle') {
////                var settlement = 1;
////            } else {
////            	var settlement = 0;
////            }
//            $.ajax({
//                type: 'post',
//                url: basePath + 'TradeAction.do?m=save',
//                data: {
//                	isdeleted: isdeleted,
//                    issold: issold,
//                	traderid: traderid,
//                	vehicleid: vehicleid,
//                	licenseno: licenseno,
//                	vehicledesc: vehicledesc,
//                	tradername: tradername,
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
//                	tradecost: tradecost,
//                	sellprice: sellprice,
//                	selldate: selldate,
////                	settlement : settlement,
//                	operation: operation
//                },
//                success: function (data) {
//                    if (data == null || data.length == 0) return;
//                    var vo = data[0];
//
//                    if (vo.status == 'ok') {
//
//                        $('#list').datagrid('reload');
//                        if (save == 'savenew') {
//                            edit.clear();
//                            xutil.focus('#licenseno');
//                        } else {
//                            $('#dlg_add').dialog('close');
//                        }
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
        // 查看
        /***********************************************/
//        list.view = function (index) {
//        
//            if (!xutil.isGridSelected('#list')) return;
//            var vehicleid = xutil.getGridSelectedVehicleID('#list');
////            alert(vehicleid);
//                $.ajax({
//                    type: 'post',
//                    url: basePath + 'TradeAction.do?m=view',
//                    data: {vehicleid: vehicleid},
//                    success: function (data) {
//
//                        if (data == null || data.length == 0) return;
//                        var vo = data[0];
//
//                        if (vo.status == "ok") {
//                        	$('#licenseno').textbox({disabled:true});
//                            $('#vehicledesc').textbox({disabled:false});
//                            $('#tradername').textbox({disabled:true});
//                            $('#purchaseprice').textbox({disabled:true});
//                            $('#ownername').textbox({disabled:false});
//                            $('#ownerid').textbox({disabled:false});
//                            $('#purchasedate').textbox({disabled:true});
//                            $('#interestrate').textbox({disabled:false});
//                            $('#actualloan').textbox({disabled:true});
//                            $('#spareloan').textbox({disabled:true});
//                            $('#vehicletype').combobox({disabled:true});
//                            $('#comments').textbox({disabled:false});
//                            $('#earnest').textbox({disabled:false});
//                            $('#tradecost').textbox({disabled:false});
//                            $('#sellprice').textbox({disabled:false});
//                            $('#selldate').textbox({disabled:false});
////                            $('#vehicleid').val('');
////                            $('#vehicleid').val(vo.dto.vehicleid);
////                            $('#licenseno').textbox('setValue', vo.dto.licenseno);
//                            $('#isdeleted').val(vo.dto.isdeleted);
//                        	$('#issold').val(vo.dto.issold);
//                        	$('#traderid').val(vo.dto.traderid);
//                        	$('#vehicleid').val(vo.dto.vehicleid);
//                        	$('#licenseno').textbox('setValue', vo.dto.licenseno);
//                            $('#vehicledesc').textbox('setValue', vo.dto.vehicledesc);
//                            $('#tradername').textbox('setValue', vo.dto.tradername);
//                            $('#purchaseprice').textbox('setValue', vo.dto.purchaseprice);
//                            $('#ownername').textbox('setValue', vo.dto.ownername);
//                            $('#ownerid').textbox('setValue', vo.dto.ownerid);
//                            $('#purchasedate').textbox('setValue', vo.dto.purchasedate);
//                            $('#interestrate').textbox('setValue', vo.dto.interestrate);
//                            $('#actualloan').textbox('setValue', vo.dto.actualloan);
//                            if(vo.dto.vehicletype=="第三方"){
//                            	$('#spareloan').textbox('setValue', vo.dto.spareloan);
//                            }else{
//                            	$('#spareloan').textbox('setValue', '0');	
//                            }
////                            $('#spareloan').textbox('setValue', vo.dto.spareloan);
//                            $('#vehicletype').combobox('setValue', vo.dto.vehicletype);
//                            $('#comments').textbox('setValue', vo.dto.comments);
//                            $('#earnest').textbox('setValue', vo.dto.earnest);
//                            $('#tradecost').textbox('setValue', vo.dto.tradecost);
//                            $('#sellprice').textbox('setValue', vo.dto.sellprice);
//                            $('#selldate').textbox('setValue', vo.dto.selldate);
//
//                            $('#dlg_add').dialog('open');
//                            xutil.focus('#sellprice');
//                        } else if (vo.status == 'nologin') {
//                            top.location = basePath;
//                        } else {
//                            $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
//                        }
//                    },
//
//                    error: function () {
//                        top.location = basePath;
//                    }
//                });
//            };

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