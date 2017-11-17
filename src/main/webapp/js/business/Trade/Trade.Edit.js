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
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	var CurrentLoginUserAccesstype = $('#CurrentLoginUserAccesstype').val();
        	$('#licenseno').textbox({disabled:false});
            $('#vehicledesc').textbox({disabled:false});
            $('#tradername').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:false});
            $('#ownername').textbox({disabled:false});
            $('#ownermobile').textbox({disabled:false});
            $('#ownerid').textbox({disabled:false});
            $('#purchasedate').textbox({disabled:false});
            $('#interestrate').textbox({disabled:false});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#tradecost').textbox({disabled:false});
            $('#sellprice').textbox({disabled:true});
            $('#selldate').textbox({disabled:true});
            $('#buyerid').textbox({disabled:true});
            $('#buyername').textbox({disabled:true});
            $('#buyermobile').textbox({disabled:true});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditupload').linkbutton('enable');
            $('#btnEditSold').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            
        	$('#isdeleted').val('0');
        	$('#issold').val('0');
        	$('#settlement').val('0');
        	$('#traderid').val(userid);
        	$('#vehicleid').val('');
        	$('#licenseno').textbox('setValue', '');
            $('#vehicledesc').textbox('setValue', '');
            $('#tradername').textbox('setValue', username);
            $('#purchaseprice').textbox('setValue', '');
            $('#ownername').textbox('setValue', '');
            $('#ownermobile').textbox('setValue', '');
            $('#ownerid').textbox('setValue', '');
            $('#purchasedate').textbox('setValue', '');
            $('#interestrate').textbox('setValue', '1.5');
            $('#actualloan').textbox('setValue', '');
            $('#spareloan').textbox('setValue', '');
            if(CurrentLoginUserAccesstype == "三方业务员"){
            	$('#vehicletype').combobox('setValue', '第三方');
            }else{
            	$('#vehicletype').combobox('setValue', '自收车');
            }
            $('#comments').textbox('setValue', '');
            $('#earnest').textbox('setValue', '0');
            $('#tradecost').textbox('setValue', '0');
            $('#sellprice').textbox('setValue', '');
            $('#selldate').textbox('setValue', '');
            $('#buyerid').textbox('setValue', '');
            $('#buyername').textbox('setValue', '');
            $('#buyermobile').textbox('setValue', '');
            $("#imgdiv")[0].innerHTML="";
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
            $('#tradername').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownermobile').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#purchasedate').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:true});
            $('#earnest').textbox({disabled:true});
            $('#tradecost').textbox({disabled:true});
            $('#sellprice').textbox({disabled:true});
            $('#selldate').textbox({disabled:true});
            $('#buyerid').textbox({disabled:true});
            $('#buyername').textbox({disabled:true});
            $('#buyermobile').textbox({disabled:true});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditSold').linkbutton('disable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditupload').linkbutton('disable');
            
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
            $('#tradername').textbox({disabled:true});
            $('#purchaseprice').textbox({disabled:true});
            $('#ownername').textbox({disabled:true});
            $('#ownermobile').textbox({disabled:true});
            $('#ownerid').textbox({disabled:true});
            $('#purchasedate').textbox({disabled:true});
            $('#interestrate').textbox({disabled:true});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            $('#vehicletype').combobox({disabled:true});
            $('#comments').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#tradecost').textbox({disabled:true});
            $('#sellprice').textbox({disabled:true});
            $('#selldate').textbox({disabled:true});
            $('#buyerid').textbox({disabled:false});
            $('#buyername').textbox({disabled:false});
            $('#buyermobile').textbox({disabled:false});
            $('#btnEditSave').linkbutton('disable');
            $('#btnEditSaveadd').linkbutton('disable');
            $('#btnEditSold').linkbutton('enable');
            $('#btnEditSettle').linkbutton('enable');
            $('#btnEditDelete').linkbutton('enable');
            $('#btnEditupload').linkbutton('enable');
            
        };
        
        /***********************************************/
        // 修改视图
        /***********************************************/
        edit.editview = function () {
//        	alert("edit.clear");
        	var userid = $('#userid').val();
        	var username = $('#username').val();
        	$('#licenseno').textbox({disabled:false});
            $('#vehicledesc').textbox({disabled:false});
            $('#tradername').textbox({disabled:false});
            $('#purchaseprice').textbox({disabled:false});
            $('#ownername').textbox({disabled:false});
            $('#ownermobile').textbox({disabled:false});
            $('#ownerid').textbox({disabled:false});
            $('#purchasedate').textbox({disabled:false});
            $('#interestrate').textbox({disabled:false});
            $('#actualloan').textbox({disabled:true});
            $('#spareloan').textbox({disabled:true});
            //$('#vehicletype').combobox({disabled:false});
            $('#comments').textbox({disabled:false});
            $('#earnest').textbox({disabled:false});
            $('#tradecost').textbox({disabled:false});
            $('#sellprice').textbox({disabled:false});
            $('#selldate').textbox({disabled:false});
            $('#buyerid').textbox({disabled:false});
            $('#buyername').textbox({disabled:false});
            $('#buyermobile').textbox({disabled:false});
            $('#btnEditSave').linkbutton('enable');
            $('#btnEditSaveadd').linkbutton('enable');
            $('#btnEditSold').linkbutton('enable');
            $('#btnEditSettle').linkbutton('disable');
            $('#btnEditDelete').linkbutton('enable');
            $('#btnEditupload').linkbutton('enable');
            
        };
        
        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {
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
                    	 var CurrentLoginUserAccesstype = $('#CurrentLoginUserAccesstype').val();
                         if(CurrentLoginUserAccesstype == "管理员")
                         {
                        	 if(vo.dto.isdeleted=="1"){
                        		 edit.disabledview();
                        	 }else if(vo.dto.settlement=="1"){
                        		 edit.disabledview();
                        	 }else if(vo.dto.issold=="1"){
                        		 edit.soldview();
                        	 }else{
                        		 edit.editview();
                        	 }
                         } else
                        {
                        	 edit.disabledview();
                        }
//                    	$('#licenseno').textbox({disabled:true});
//                        $('#vehicledesc').textbox({disabled:false});
//                        $('#tradername').textbox({disabled:true});
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
//                        $('#tradecost').textbox({disabled:false});
//                        $('#sellprice').textbox({disabled:false});
//                        $('#selldate').textbox({disabled:false});
//                        $('#vehicleid').val('');
//                        $('#vehicleid').val(vo.dto.vehicleid);
//                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#isdeleted').val(vo.dto.isdeleted);
                    	$('#issold').val(vo.dto.issold);
                    	$('#settlement').val(vo.dto.settlement);
                    	$('#traderid').val(vo.dto.traderid);
                    	$('#vehicleid').val(vo.dto.vehicleid);
                    	$('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#vehicledesc').textbox('setValue', vo.dto.vehicledesc);
                        $('#tradername').textbox('setValue', vo.dto.tradername);
                        $('#purchaseprice').textbox('setValue', vo.dto.purchaseprice);
                        $('#ownername').textbox('setValue', vo.dto.ownername);
                        $('#ownermobile').textbox('setValue', vo.dto.ownermobile);
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
                        $('#tradecost').textbox('setValue', vo.dto.tradecost);
                        $('#sellprice').textbox('setValue', vo.dto.sellprice);
                        $('#selldate').textbox('setValue', vo.dto.selldate);
                        $('#buyerid').textbox('setValue', vo.dto.buyerid);
                        $('#buyername').textbox('setValue', vo.dto.buyername);
                        $('#buyermobile').textbox('setValue', vo.dto.buyermobile);
                        $("#imgdiv")[0].innerHTML="";
                        var picturepath = vo.dto.picturepath; 
                        var picturepaths = picturepath.split(",");
                        var str ="";
    					var imgdel = "images\\disable.png";
                		for(var i=0;i<picturepaths.length;i++){
                			var id = i+1;
                			var pic = "upload\\" + picturepaths[i];
//                			alert(pic);
                			str = str+"<img name='image' id='img" + id + "' src='"+ pic +"' height='100' width='145'/><img id='del" + id + "' src='"+imgdel+"' height='16' width='16' onclick='delFile(" + id + ")'  />";	
                		}
//                		alert(str);
                		var s_HTML=$("#imgdiv")[0].innerHTML;
    					$("#imgdiv")[0].innerHTML = s_HTML+str;
//                        alert(picturepath);
//                        alert(picturepaths.length);
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
        edit.save = function (save) {
//        	alert("edit.save");
  
            var isdeleted = $('#isdeleted').val();
            var issold = $('#issold').val();
            var traderid = $('#traderid').val();
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
            var tradername = $('#tradername').textbox('getValue');
            var purchaseprice = $('#purchaseprice').textbox('getValue');
            var vehicletype = $('#vehicletype').combobox('getValue');
            if(vehicletype=='第三方'){
	            if (purchaseprice.replace(/(^\s*)|(\s*$)/g, "")=="" ||purchaseprice.replace(/(^\s*)|(\s*$)/g, "")=="0")
	            {
	            	alert("请输入有效的收车价(大于0的值)");
	            	return;
	            }
            }
            var ownername = $('#ownername').textbox('getValue');
            if (ownername.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入卖车人姓名");
            	return;
            }
            var ownermobile = $('#ownermobile').textbox('getValue');
            var ownerid = $('#ownerid').textbox('getValue');
            var purchasedate = $('#purchasedate').textbox('getValue');
            if ((save == 'save'||save == 'savenew') && purchasedate.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入收车日期");
            	return;
            }
            var interestrate = $('#interestrate').textbox('getValue');
            var actualloan = $('#actualloan').textbox('getValue');
            var spareloan = $('#spareloan').textbox('getValue');
            var comments = $('#comments').textbox('getValue');
            var earnest = $('#earnest').textbox('getValue');
            var tradecost = $('#tradecost').textbox('getValue');
            var sellprice = $('#sellprice').textbox('getValue');
            var selldate = $('#selldate').textbox('getValue');
            if (save == 'sold' && selldate.replace(/(^\s*)|(\s*$)/g, "")=="")
            {
            	alert("请输入销售日期");
            	return;
            }
            var buyerid = $('#buyerid').textbox('getValue');
            var buyername = $('#buyername').textbox('getValue');
            var buyermobile = $('#buyermobile').textbox('getValue');
            var picturepath = edit.getpicpath();
            var operation = save
//            alert(picturepath);
            if (save == 'settle') {
                var settlement = 1;
                $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_SETTLE, function (r) {
                    if (r) {
			            $.ajax({
			                type: 'post',
			                url: basePath + 'TradeAction.do?m=save',
			                data: {
			                	isdeleted: isdeleted,
			                    issold: issold,
			                	traderid: traderid,
			                	vehicleid: vehicleid,
			                	licenseno: licenseno,
			                	vehicledesc: vehicledesc,
			                	tradername: tradername,
			                	purchaseprice: purchaseprice,
			                	ownername: ownername,
			                	ownermobile: ownermobile,
			                	ownerid: ownerid,
			                	purchasedate: purchasedate,
			                	interestrate: interestrate,
			                	actualloan: actualloan,
			                	spareloan: spareloan,
			                	vehicletype: vehicletype,
			                	comments: comments,
			                	earnest: earnest,
			                	tradecost: tradecost,
			                	sellprice: sellprice,
			                	selldate: selldate,
			                	buyerid: buyerid,
			                	buyername: buyername,
			                	buyermobile: buyermobile,
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
		            url: basePath + 'TradeAction.do?m=save',
		            data: {
		            	isdeleted: isdeleted,
		                issold: issold,
		            	traderid: traderid,
		            	vehicleid: vehicleid,
		            	licenseno: licenseno,
		            	vehicledesc: vehicledesc,
		            	tradername: tradername,
		            	purchaseprice: purchaseprice,
		            	ownername: ownername,
		            	ownermobile: ownermobile,
		            	ownerid: ownerid,
		            	purchasedate: purchasedate,
		            	interestrate: interestrate,
		            	actualloan: actualloan,
		            	spareloan: spareloan,
		            	vehicletype: vehicletype,
		            	comments: comments,
		            	earnest: earnest,
		            	tradecost: tradecost,
		            	sellprice: sellprice,
		            	selldate: selldate,
		            	buyerid: buyerid,
		            	buyername: buyername,
		            	buyermobile: buyermobile,
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
	                }});
		    	}
		        };

		        /***********************************************/
		        // 获取图片路径
		        /***********************************************/
		        edit.getpicpath = function () {
//		        	alert("getpicpath");
		        	var picturepath="";
		        	var imgLength = $("img[name=image]").length;
		        	var imgs = $("img[name=image]");
//		        	alert(imgs.length);
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
		        
//		        edit.getpicpath = function () {
////		        	alert("getpicpath");
//		        	var picturepath="";
//		        	var imgLength = $("img[name=image]").length;
//		        	for(var i=1;i<imgLength+1;i++){
//		        		var imgid = "img"+i;
//		        		alert(imgid);
//		        		$("img[name=image]")
//		        		var src = document.getElementById(imgid).src;
//		        		var imgname = (src.substring(src.lastIndexOf("/")+1)); 
//		        		alert(imgname);
//		        		if(picturepath ==""){
//			        		picturepath = imgname;
//		        		}else{
//			        		picturepath = picturepath + "," + imgname;		        			
//		        		}
//
//		        		alert(picturepath);
//		    		}
//		        	return picturepath;
//		        };
//        
//        /***********************************************/
//        // 结算
//        /***********************************************/
//        edit.settle = function (addnew) {        
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
//            var settlement = 1;
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
            var issold = $('#issold').val();
            var settlement = $('#settlement').val();
            var vehicletype = $('#vehicletype').combobox('getValue');
//            var vehicletype = $('#vehicletype').val();
            var purchaseprice = $('#purchaseprice').textbox('getValue');
            var tradecost = $('#tradecost').textbox('getValue');
            
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
                        url: basePath + 'TradeAction.do?m=deletesingle',
                        data: {
                        	vehicleid: vehicleid,
                        	issold: issold,
                        	vehicletype: vehicletype,
                        	purchaseprice: purchaseprice,
                        	tradecost: tradecost,
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
//                        url: basePath + 'TradeAction.do?m=sold',
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