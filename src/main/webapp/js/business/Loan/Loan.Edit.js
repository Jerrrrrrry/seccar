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
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:false});
            $('#vehicledesc').textbox({disabled:false});
            $('#ownername').textbox({disabled:false});
            $('#ownerid').textbox({disabled:false});
            $('#tradername').textbox({disabled:true});
            $('#mobileno').textbox({disabled:false});
            $('#borrowdate').textbox({disabled:false});
            $('#returndate').textbox({disabled:false});
            $('#periodmonths').textbox({disabled:false});
            //$('#totalinterest').textbox({disabled:false});
            $('#borrowamount').textbox({disabled:false});
            $('#interestrate').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#actualmonths').textbox({disabled:false});
            $('#interestpaid').textbox({disabled:false});
            $('#interestpaidto').textbox({disabled:false});
            $('#nextpaymentdate').textbox({disabled:false});
            $('#midinterestrate').textbox({disabled:false});
            $('#midinterest').textbox({disabled:false});
            $('#parkingfee').textbox({disabled:false});
            $('#otherfee').textbox({disabled:false});
            $('#actualloan').textbox({disabled:false});
            $('#comments').textbox({disabled:false});
            $('#actualreturn').textbox({disabled:false});
            $('#actualreturndate').datebox({disabled:false});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditReturned').linkbutton('disable');
            $('#btnEditAbandon').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditupload').linkbutton('enable');
            
        	$('#isdeleted').val('0');
        	$('#isreturned').val('0');
        	$('#isabandon').val('0');
        	$('#traderid').val(userid);
        	$('#tradername').textbox('setValue','');
        	$('#settlement').val('0');
        	$('#vehicleid').val('');
        	$('#licenseno').textbox('setValue', '');
            $('#vehicledesc').textbox('setValue', '');
            $('#ownername').textbox('setValue', '');
            $('#ownerid').textbox('setValue', '');
            $('#mobileno').textbox('setValue', '');
            $('#borrowdate').textbox('setValue', '');
            $('#returndate').textbox('setValue', '');
            $('#periodmonths').textbox('setValue', '0');
//            $('#totalinterest').textbox('setValue', '0');
            $('#borrowamount').textbox('setValue', '0');
            $('#interestrate').textbox('setValue', '0.0');
            $('#earnest').textbox('setValue', '0');
            $('#actualmonths').textbox('setValue', '0');
            $('#interestpaid').textbox('setValue', '0');
            $('#interestpaidto').textbox('setValue', '');
            $('#nextpaymentdate').textbox('setValue', '');
            $('#midinterestrate').textbox('setValue', '0');
            $('#midinterest').textbox('setValue', '0');
            $('#parkingfee').textbox('setValue', '0');
            $('#otherfee').textbox('setValue', '0');
            $('#actualloan').textbox('setValue', '0');
            $('#comments').textbox('setValue', '');
            $('#actualreturn').textbox('setValue', '0');
            $('#actualreturndate').datebox('setValue', '');
            $("#imgdiv")[0].innerHTML="";
        };
        
        /***********************************************/
        // 无法修改
        /***********************************************/
        edit.disabledview = function () {
//        	alert("edit.clear");
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#tradername').textbox({disabled:true});
            $('#mobileno').textbox({disabled:true});
            $('#borrowdate').textbox({disabled:true});
            $('#returndate').textbox({disabled:true});
            $('#periodmonths').textbox({disabled:true});
//            $('#totalinterest').textbox({disabled:true});
            $('#borrowamount').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#earnest').textbox({disabled:true});
            $('#actualmonths').textbox({disabled:true});
            $('#interestpaid').textbox({disabled:true});
            $('#interestpaidto').textbox({disabled:true});
            $('#nextpaymentdate').textbox({disabled:true});
            $('#midinterestrate').textbox({disabled:true});
            $('#midinterest').textbox({disabled:true});
            $('#parkingfee').textbox({disabled:true});
            $('#otherfee').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#comments').textbox({disabled:true});
            $('#actualreturn').textbox({disabled:true});
            $('#actualreturndate').datebox({disabled:true});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditReturned').linkbutton('disable');
            $('#btnEditAbandon').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditupload').linkbutton('disable');
            
        };
        
        /***********************************************/
        // 已弃车视图
        /***********************************************/
        edit.abandonview = function () {
//        	alert("edit.clear");
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#mobileno').textbox({disabled:true});
            $('#borrowdate').textbox({disabled:true});
            $('#tradername').textbox({disabled:true});
            $('#returndate').textbox({disabled:true});
            $('#periodmonths').textbox({disabled:true});
//            $('#totalinterest').textbox({disabled:true});
            $('#borrowamount').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#earnest').textbox({disabled:true});
            $('#actualmonths').textbox({disabled:true});
            $('#interestpaid').textbox({disabled:true});
            $('#interestpaidto').textbox({disabled:true});
            $('#nextpaymentdate').textbox({disabled:true});
            $('#midinterestrate').textbox({disabled:true});
            $('#midinterest').textbox({disabled:true});
            $('#parkingfee').textbox({disabled:true});
            $('#otherfee').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#comments').textbox({disabled:true});
            $('#actualreturn').textbox({disabled:true});
            $('#actualreturndate').datebox({disabled:true});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditReturned').linkbutton('disable');
            $('#btnEditAbandon').linkbutton('disable');
            $('#btnEditSettle').linkbutton('enable');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditupload').linkbutton('disable');
            
        };
        
        /***********************************************/
        // 已还视图
        /***********************************************/
        edit.returnedview = function () {
        	$('#licenseno').textbox({disabled:true});
            $('#vehicledesc').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#mobileno').textbox({disabled:true});
            $('#tradername').textbox({disabled:true});
            $('#borrowdate').textbox({disabled:true});
            $('#returndate').textbox({disabled:true});
            $('#periodmonths').textbox({disabled:true});
//            $('#totalinterest').textbox({disabled:true});
            $('#borrowamount').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#earnest').textbox({disabled:true});
            $('#actualmonths').textbox({disabled:true});
            $('#interestpaid').textbox({disabled:true});
            $('#interestpaidto').textbox({disabled:true});
            $('#nextpaymentdate').textbox({disabled:true});
            $('#midinterestrate').textbox({disabled:true});
            $('#midinterest').textbox({disabled:true});
            $('#parkingfee').textbox({disabled:true});
            $('#otherfee').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#comments').textbox({disabled:true});
            $('#actualreturn').textbox({disabled:true});
            $('#actualreturndate').datebox({disabled:false});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditReturned').linkbutton('enable');
            $('#btnEditAbandon').linkbutton('disable');
            $('#btnEditSettle').linkbutton('enable');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditupload').linkbutton('enable');
            
        };
        
        /***********************************************/
        // 修改视图
        /***********************************************/
        edit.editview = function () {
//        	alert("edit.clear");
        	$('#licenseno').textbox({disabled:false});
            $('#vehicledesc').textbox({disabled:false});
            $('#ownername').textbox({disabled:false});
            $('#ownerid').textbox({disabled:false});
            $('#tradername').textbox({disabled:true});
            $('#mobileno').textbox({disabled:false});
            $('#borrowdate').textbox({disabled:false});
            $('#returndate').textbox({disabled:false});
            $('#periodmonths').textbox({disabled:false});
//            $('#totalinterest').textbox({disabled:false});
            $('#borrowamount').textbox({disabled:false});
            $('#interestrate').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#actualmonths').textbox({disabled:false});
            $('#interestpaid').textbox({disabled:false});
            $('#interestpaidto').textbox({disabled:false});
            $('#nextpaymentdate').textbox({disabled:false});
            $('#midinterestrate').textbox({disabled:false});
            $('#midinterest').textbox({disabled:false});
            $('#parkingfee').textbox({disabled:false});
            $('#otherfee').textbox({disabled:false});
            $('#actualloan').textbox({disabled:false});
            $('#comments').textbox({disabled:false});
            $('#actualreturn').textbox({disabled:false});
            $('#actualreturndate').datebox({disabled:false});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditReturned').linkbutton('enable');
            $('#btnEditAbandon').linkbutton('enable');
            $('#btnEditSettle').linkbutton('enable');
            $('#btnEditDelete').linkbutton('enable');
            $('#btnEditupload').linkbutton('enable');
            
            
        };
        
        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {
//        	alert("edit.addNew");
            this.clear();
//            $('#btnEditDelete').linkbutton('disable');
            $('#tradername').textbox('setValue',$('#username').val());
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
                    	 var CurrentLoginUserAccesstype = $('#CurrentLoginUserAccesstype').val();
                         if(CurrentLoginUserAccesstype == "管理员")
                         {
	                    	if(vo.dto.isdeleted=="1"){
	                    		edit.disabledview();
	                    	}else if(vo.dto.settlement=="1"){
	                    		edit.disabledview();
	                    	}else if(vo.dto.isabandon=="1"){
	                    		edit.abandonview();
	                    	}else if(vo.dto.isreturned=="1"){
	                    		edit.returnedview();
	                    	}else{
	                    		edit.editview();
	                    	}
                         } else{
                        	 edit.disabledview();
                         }
                    	
                    	$('#isdeleted').val(vo.dto.isdeleted);
                    	$('#isreturned').val(vo.dto.isreturned);
                    	$('#isabandon').val(vo.dto.isabandon);
                    	$('#settlement').val(vo.dto.settlement);
                    	$('#vehicleid').val(vo.dto.vehicleid);
                    	$('#tradername').textbox('setValue',vo.dto.tradername);
                    	$('#traderid').val(vo.dto.traderid);
                    	$('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#vehicledesc').textbox('setValue', vo.dto.vehicledesc);
                        $('#ownername').textbox('setValue', vo.dto.ownername);
                        $('#ownerid').textbox('setValue', vo.dto.ownerid);
                        $('#mobileno').textbox('setValue', vo.dto.mobileno);
                        $('#borrowdate').textbox('setValue', vo.dto.borrowdate);
                        $('#returndate').textbox('setValue', vo.dto.returndate);
                        $('#periodmonths').textbox('setValue', vo.dto.periodmonths);
//                        $('#totalinterest').textbox('setValue', vo.dto.totalinterest);
                        $('#borrowamount').textbox('setValue', vo.dto.borrowamount);
                        $('#interestrate').textbox('setValue', vo.dto.interestrate);
                        $('#earnest').textbox('setValue', vo.dto.earnest);
                        $('#actualmonths').textbox('setValue', vo.dto.actualmonths);
                        $('#interestpaid').textbox('setValue', vo.dto.interestpaid);
                        $('#interestpaidto').textbox('setValue', vo.dto.interestpaidto);
                        $('#nextpaymentdate').textbox('setValue', vo.dto.nextpaymentdate);
                        $('#midinterestrate').textbox('setValue', vo.dto.midinterestrate);
                        $('#midinterest').textbox('setValue', vo.dto.midinterest);
                        $('#parkingfee').textbox('setValue', vo.dto.parkingfee);
                        $('#otherfee').textbox('setValue', vo.dto.otherfee);
                        $('#actualloan').textbox('setValue', vo.dto.actualloan);
                        $('#comments').textbox('setValue', vo.dto.comments);
                        $('#actualreturn').textbox('setValue', vo.dto.actualreturn);
                        $('#actualreturndate').textbox('setValue', vo.dto.actualreturndate);

                        $("#imgdiv")[0].innerHTML="";
                        var picturepath = vo.dto.picturepath; 
                        var picturepaths = picturepath.split(",");
                        var str ="";
    					var imgdel = "images\\disable.png";
                		for(var i=0;i<picturepaths.length;i++){
                			var id = i+1;
                			var pic = "upload\\" + picturepaths[i];
//                			alert(pic);
                			str = str+"<img name='image' id='img" + id + "' src='"+ pic +"' height='100' width='145'/><img id='del" + id + "' src='"+imgdel+"' height='16' width='16' onclick='delFile(" + id + ")'  disabled/>";	
                		}
//                		alert(str);
                		var s_HTML=$("#imgdiv")[0].innerHTML;
    					$("#imgdiv")[0].innerHTML = s_HTML+str;

                        $('#dlg_add').dialog('open');
                        xutil.focus('#interestpaid');
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
            var traderid = $('#traderid').val();
            var tradername = $('#tradername').textbox('getValue');
            var vehicleid = $('#vehicleid').val();
            var licenseno = $('#licenseno').textbox('getValue');
            if (licenseno.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入车牌号");
            	return;
            }
            var vehicledesc = $('#vehicledesc').textbox('getValue');
            if (vehicledesc.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入车辆描述");
            	return;
            }
            var ownername = $('#ownername').textbox('getValue');
            if (ownername.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入抵押人姓名");
            	return;
            }
            var ownerid = $('#ownerid').textbox('getValue');
            var mobileno = $('#mobileno').textbox('getValue');
            var borrowamount = $('#borrowamount').textbox('getValue');
            if (borrowamount.replace(/(^\s*)|(\s*$)/g, "")=="" ||borrowamount.replace(/(^\s*)|(\s*$)/g, "")=="0")
            {
            	alert("请输入有效的借款金额(大于0的值)");
            	return;
            }
            var borrowdate = $('#borrowdate').textbox('getValue');
            if (borrowdate.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入借款日期");
            	return;
            }
            var returndate = $('#returndate').textbox('getValue');
            if (returndate.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入约定还款日期");
            	return;
            }
            var periodmonths = $('#periodmonths').textbox('getValue');
            if (periodmonths.replace(/(^\s*)|(\s*$)/g, "")==""||periodmonths.replace(/(^\s*)|(\s*$)/g, "")=="0")
            {
            	alert("请输入有效的约定还款周期(大于0的值)");
            	return;
            }
//            var totalinterest = $('#totalinterest').textbox('getValue');
            /*if (totalinterest.replace(/(^\s*)|(\s*$)/g, "")=="" ||totalinterest.replace(/(^\s*)|(\s*$)/g, "")=="0")
            {
            	alert("请输入有效的利息总额(大于0的值)");
            	return;
            }*/
            var interestrate = $('#interestrate').textbox('getValue');
            if (interestrate.replace(/(^\s*)|(\s*$)/g, "")=="" ||isNaN(interestrate))
            {
            	alert("请输入有效的利率(大于0的值)");
            	return;
            } else if (interestrate <= 0 )
            	{
            	alert("请输入有效的利率(大于0的值)");
            	return;
            	}
            var earnest = $('#earnest').textbox('getValue');
            var actualmonths = $('#actualmonths').textbox('getValue');
            var interestpaid = $('#interestpaid').textbox('getValue');
            var interestpaidto = $('#interestpaidto').textbox('getValue');
            var nextpaymentdate = $('#nextpaymentdate').textbox('getValue');
            var midinterestrate = $('#midinterestrate').textbox('getValue');
            var midinterest = $('#midinterest').textbox('getValue');
            var parkingfee = $('#parkingfee').textbox('getValue');
            var otherfee = $('#otherfee').textbox('getValue');
            var actualloan = $('#actualloan').textbox('getValue');
            if (actualloan.replace(/(^\s*)|(\s*$)/g, "")=="" ||actualloan.replace(/(^\s*)|(\s*$)/g, "")=="0")
            {
            	alert("请输入有效的实际打款金额(大于0的值)");
            	return;
            }
            var comments = $('#comments').textbox('getValue');
            var actualreturn = $('#actualreturn').textbox('getValue');
            var actualreturndate = $('#actualreturndate').datebox('getValue');
            /*if (actualreturndate.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入上次归还本金时间");
            	return;
            }*/
            var picturepath = edit.getpicpath();
            var operation = save
            
                if (save == 'settle') {
                    var settlement = 1;
                    $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_SETTLE, function (r) {
                        if (r) {
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
                                	mobileno: mobileno,
                                	borrowdate: borrowdate,
                                	returndate: returndate,
                                	periodmonths: periodmonths,
//                                	totalinterest: totalinterest,
                                	borrowamount: borrowamount,
                                	interestrate: interestrate,
                                	earnest: earnest,
                                	actualmonths: actualmonths,
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
    				            	picturepath : picturepath,
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
                                            $('#tradername').textbox('setValue',$('#username').val());
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
                        }
            
                        });
    		    }else{
    		    	var msg = '是否已经确认您输入的信息准确无误?';
    		    	if (save == 'savenew' || save=='save') {
    		    		msg = '请确认本次操作您输入的信息准确无误，您本次要保存的车辆是　\r\n车牌号：'+licenseno+ ' 车辆信息 :'+vehicledesc + '\r\n是否继续?';
    		    	} else if (save == 'sold'){
    		    		msg = '您本次将出库以下车辆，错误出库将会导致余量计算的不准确， 请确认是否继续?　\r\n车牌号：'+licenseno+ ' 车辆信息 :'+vehicledesc;
    		    	}
    		    	$.messager.confirm(AppConstant.M_INFO, msg, function (r) {
    	                if (r) {
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
    	                	mobileno: mobileno,
    	                	borrowdate: borrowdate,
    	                	returndate: returndate,
    	                	periodmonths: periodmonths,
//    	                	totalinterest: totalinterest,
    	                	borrowamount: borrowamount,
    	                	interestrate: interestrate,
    	                	earnest: earnest,
    	                	actualmonths: actualmonths,
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
			            	picturepath : picturepath,
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
    	                            $('#tradername').textbox('setValue',$('#username').val());
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
    		    	}
    		    	});}
            
        };
        
        /***********************************************/
        // 获取图片路径
        /***********************************************/
        edit.getpicpath = function () {
//        	alert("getpicpath");
        	var picturepath="";
        	var imgLength = $("img[name=image]").length;
        	var imgs = $("img[name=image]");
//        	alert(imgs.length);
        	for(var i=0;i<imgLength;i++){
        		var imgid = "img"+i;
        		var img = imgs[i];
        		var src = img.src;
        		var imgname = (src.substring(src.lastIndexOf("/")+1)); 
        		if(picturepath ==""){
	        		picturepath = imgname;
        		}else{
	        		picturepath = picturepath + "," + imgname;		        			
        		}
    		}
        	return picturepath;
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
        	var traderid = $('#traderid').val();
            var vehicleid = $('#vehicleid').val();
            var isdeleted = $('#isdeleted').val();
            var isabandon = $('#isabandon').val();
            var isreturned = $('#isreturned').val();
            var settlement = $('#settlement').val();
//            var vehicletype = $('#vehicletype').combobox('getValue');
//            var vehicletype = $('#vehicletype').val();
//            var purchaseprice = $('#purchaseprice').textbox('getValue');
            
            if (vehicleid == null || vehicleid.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }
            if (isdeleted == "1" || settlement == "1" ) {
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
                        	isabandon: isabandon,
                        	isreturned: isreturned,
                        	settlement: settlement,
                        	traderid:traderid
                        },
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];
                            $('#btnEditDelete').linkbutton('disable');

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                edit.clear();
                                xutil.focus('#licenseno');
                                $('#tradername').textbox('setValue',$('#username').val());
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