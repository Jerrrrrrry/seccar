var LoanInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){
            var xutil = XUtil.getInstance(basePath);
            var list = LoanList.getInstance(basePath);
            var edit = LoanEdit.getInstance(basePath);

            /***********************************************/
            // 表格初始化
            /***********************************************/
            // 右表
            $("#list").datagrid({
                striped: true,
                rownumbers: true,
                rowStyler:function(index,row){
               	 	var color = list.rowcolor(index,row);
               	 	return color;
//               	 	'background-color:pink;color:blue;font-weight:bold;';
            	   	},
                pageSize: 20,
                pageList: [20,100,10000],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: basePath + 'LoanAction.do?m=list',
                idField: 'vehicleid',
                sortName: 'borrowdate',
                sortOrder: 'desc',
                toolbar: '#bar_list',
                columns: [[
					{
					    field: 'vehicleid',
					    checkbox: true
					},
                    {
                        field: 'licenseno',
                        title: '车牌号码',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'vehicledesc',
                        title: '车辆描述',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'ownername',
                        title: '抵押人姓名',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'ownerid',
                        title: '抵押人身份证',
                        width: 120,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'mobileno',
                        title: '抵押人手机号',
                        width: 100,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'borrowdate',
                        title: '借款日期',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'returndate',
                        title: '约定还款日期',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'periodmonths',
                        title: '还款周期（月）',
                        width: 60,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'totalinterest',
                        title: '利息总额',
                        width: 60,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'tradername',
                        title: '经办人',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'borrowamount',
                        title: '借款金额',
                        width: 100,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'interestrate',
                        title: '利率',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'earnest',
                        title: '定金',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                    	field: 'actualloan',
                    	title: '实付金额',
                    	width: 100,
                    	align: 'left',
                    	sortable: true
                    },
                    {
                        field: 'parkingfee',
                        title: '停车费',
                        width: 60,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'otherfee',
                        title: '其他费用',
                        width: 60,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'actualmonths',
                        title: '实际还款周期',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
//                    {
//                        field: 'interest',
//                        title: '利息(每月)',
//                        width: 40,
//                        align: 'left',
//                        sortable: false
//                    },
                    {
                        field: 'interestpaid',
                        title: '已付利息',
                        width: 60,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'interestpaidto',
                        title: '利息付至',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'nextpaymentdate',
                        title: '下次付息',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'totalinterest',
                        title: '利息总额',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'midinterestrate',
                        title: '中介利率',
                        width: 160,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'midinterest',
                        title: '中介利息',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'comments',
//                        data-options=''events:{blur:onSelectBtypeComboBox},width:'300px',required:true,validType:['length[1,64]']',
                        title: '备注',
                        width: 60,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'actualreturn',
                        title: '已还本金额',
                        width: 100,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'actualreturndate',
                        title: '上次归还本金日期',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'interestcost',
                        title: '利息成本',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'totalprofit',
                        title: '总利润',
                        width: 100,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'picturepath',
                        title: '图片路径',
                        width: 150,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'isdeleted',
                        title: '已删除',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'isreturned',
                        title: '已归还',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'settlement',
                        title: '已结算',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'isabandon',
                        title: '已弃车',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    
                ]],
                onDblClickRow: function(index, data){
                    $('#btnFirst').linkbutton('enable');
                    $('#btnPrevious').linkbutton('enable');
                    $('#btnNext').linkbutton('enable');
                    $('#btnLast').linkbutton('enable');
                    edit.view(index);
                },
                onBeforeLoad : function(param){
                    xutil.ajaxLoading('body');
                },
                onLoadSuccess : function (data) {
                  xutil.ajaxLoadEnd();
                  //添加“合计”列
                  $('#list').datagrid('appendRow', {
                	  licenseno: '<span class="subtotal">合计</span>',
                      borrowamount: '<span class="subtotal">' + list.compute("borrowamount") + '</span>',
                      interestpaid: '<span class="subtotal">' + list.compute("interestpaid") + '</span>',
                      parkingfee: '<span class="subtotal">' + list.compute("parkingfee") + '</span>',
                      otherfee: '<span class="subtotal">' + list.compute("otherfee") + '</span>',
                      actualloan: '<span class="subtotal">' + list.compute("actualloan") + '</span>',
                      actualreturn: '<span class="subtotal">' + list.compute("actualreturn") + '</span>',
                      totalprofit: '<span class="subtotal">' + list.compute("totalprofit") + '</span>'
                  });
                },
                onLoadError: function () {
                    xutil.ajaxLoadEnd();
                    xutil.exception();
                },
                onLoadError: function () {
                    top.location = basePath;
                }
            });
            
            /***********************************************/
            // 字段初始化
            /***********************************************/
            $('#licenseno').textbox({
                width : 200,
                validType : ['length[1,100]']
            });

            $('#vehicledesc').textbox({
                width : 200,
                validType : ['length[1,100]']
            });

            $('#ownername').textbox({
                width : 200,
                validType : ['length[0,100]']
            });

            $('#ownerid').textbox({
                width : 200,
                validType : ['length[0,100]']
            });

            $('#mobileno').textbox({
                width : 200,
                validType : ['length[0,100]']
            });
            
            $('#borrowdate').datebox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#returndate').datebox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#periodmonths').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#totalinterest').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#borrowamount').textbox({
                width: 200,
                validType: ['length[0,100]']
//                multiline: true
            });

            $('#interestrate').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#earnest').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#actualmonths').textbox({
                width: 200,
                validType: ['length[0,100]'],
                onChange: function(){  

                	var borrowamount = $('#borrowamount').textbox('getValue');
                	var actualmonths = $('#actualmonths').textbox('getValue');
                	var interestrate = $('#interestrate').textbox('getValue');
                	var interestpaid = $('#interestpaid').textbox('getValue');
                	interestpaid = borrowamount*(interestrate/100)*actualmonths;
                    $('#interestpaid').textbox('setValue', interestpaid);
                	}  

            });
            
            $('#interestpaid').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#interestpaidto').datebox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#nextpaymentdate').datebox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#midinterestrate').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#midinterest').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#parkingfee').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#otherfee').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#actualloan').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#comments').textbox({
                width: 200,
                validType: ['length[0,100]'],
                multiline: true
            });
            $('#actualreturn').textbox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            $('#actualreturndate').datebox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            

            $('#filterisreturned').combobox({
                width : 120,
                editable : false,
                valueField: 'value',
                textField: 'label',
                data : [
                    {
                    label: '未选择',
                    value: '',
                    selected : true
                    },
                    {
                        label: '已归还',
                        value: '1'
                    },
                    {
                        label: '未归还',
                        value: '0'
                    }
                ]
            });
            $('#filtersettlement').combobox({
                width : 120,
                editable : false,
                valueField: 'value',
                textField: 'label',
                data : [
                    {
                    label: '未选择',
                    value: '',
                    selected : true
                    },
                    {
                        label: '已结算',
                        value: '1'
                    },
                    {
                        label: '未结算',
                        value: '0'
                    }
                ]
            });
            $('#filterlicenseno').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filtercardescription').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filtercustomer').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#loanstart').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#loanend').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#returnstart').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#returnend').datebox({
                width : 200,
                validType : ['length[1,100]']
            });

            /***********************************************/
            // 列表工具栏初始化
            /***********************************************/
            $('#btnAddnew').linkbutton({
                text : '新增',
                plain : true,
                iconCls : 'tbtn_addnew',
                onClick: function(){
                    list.addNew();
                }
            });
//            $('#btnView').linkbutton({
//                text : '查看',
//                plain : true,
//                iconCls : 'tbtn_auditing',
//                onClick: function(){
//                    list.view();
//                }
//            });

            $('#btnFilter').linkbutton({
                text : '过滤',
                plain : true,
                iconCls : 'tbtn_filter',
                onClick: function(){
                    list.openfilter();
                }
            });
            $('#btnRefresh').linkbutton({
                text : '刷新',
                plain : true,
                iconCls : 'tbtn_refresh',
                onClick: function(){
                    list.refresh();
                }
            });

//            $('#btnSync').linkbutton({
//                text : '同步',
//                plain : true,
//                iconCls : 'tbtn_attemper',
//                onClick: function(){
//                    list.sync();
//                }
//            });

//            $('#btnSelectAll').linkbutton({
//                text : '全选',
//                plain : true,
//                iconCls : 'tbtn_selectall',
//                onClick: function(){
//                    list.selectAll();
//                }
//            });
//
//            $('#btnUnselectAll').linkbutton({
//                text : '全清',
//                plain : true,
//                iconCls : 'tbtn_deleteall',
//                onClick: function(){
//                    list.unselectAll();
//                }
//            });
            
//            $('#btnSettle').linkbutton({
//                text : '结算',
//                plain : true,
//                iconCls : 'tbtn_submit',
//                onClick: function(){
//                    edit.save("settle");
//                }
//            });
//            $('#btnDelete').linkbutton({
//                text : '删除',
//                plain : true,
//                iconCls : 'tbtn_remove',
//                onClick: function(){
//                    list.del();
//                }
//            });
            $('#btnClose').linkbutton({
                text : '关闭',
                plain : true,
                iconCls : 'tbtn_quit',
                onClick: function(){
                    window.parent.closeCurrentTab();
                }
            });
            
            $('#btnEditupload').linkbutton({
                text : '上传图片',
                plain : true,
                iconCls : 'tbtn_enable',
                onClick: function(){
                	$('#file1').click();
                }
            });
            
            /***********************************************/
            // 窗口初始化
            /***********************************************/
            $('#dlg_add').dialog({
                title: '车贷管理',
                width: 700,
                height: 470,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false,
                toolbar: [
                    {
                    	id: 'btnEditadd',
                        text: '新增',
                        iconCls: 'tbtn_addnew',
                        handler: function(){
                            edit.addNew();
                        }
                    },
//                    {
//                    	id: 'btnEditupload',
//                        text: '上传图片',
//                        iconCls: 'tbtn_addnew',
//                        handler: function(){
//                        	$('#dlg_upload').dialog('open');
//                        }
//                    },
                    {
                        text: '保存',
                        id:'btnEditSave',
                        iconCls: 'tbtn_tempsave',
                        handler: function(){
                        	//alert('btnEditSavefalse');
                            edit.save('save');
                        }
                    },
                    {
                    	id: 'btnEditSaveadd',
                        text: '保存并新增',
                        iconCls: 'tbtn_savenew',
                        handler: function(){
                            edit.save('savenew');
                        }
                    },
                    '-',
                    {
                    	id: 'btnEditReturned',
                        text: '已归还',
                        iconCls: 'tbtn_ok',
                        handler: function(){
                        	edit.save('returned');
                        }
                    },
                    '-',
                    {
                    	id: 'btnEditAbandon',
                        text: '已弃车',
                        iconCls: 'tbtn_undo',
                        handler: function(){
                        	edit.save('abandon');
                        }
                    },
                    '-',
                    {
                    	id: 'btnEditSettle',
                        text : '结算',
                        iconCls : 'tbtn_submit',
                        onClick: function(){
                            edit.save('settle');
                        }
                    },
                    '-',
                    {
                        id: 'btnFirst',
                        iconCls: 'tbtn_first',
                        handler: function () {
                            edit.showNext('first');
                        }
                    },
                    {
                        id: 'btnPrevious',
                        iconCls: 'tbtn_previous',
                        handler: function () {
                            edit.showNext('previous');
                        }
                    },
                    {
                        id: 'btnNext',
                        iconCls: 'tbtn_next',
                        handler: function () {
                            edit.showNext('next');
                        }
                    },
                    {
                        id: 'btnLast',
                        iconCls: 'tbtn_last',
                        handler: function () {
                            edit.showNext('last');
                        }
                    },
                    '-',
                    {
                        text: '删除',
                        id: 'btnEditDelete',
                        iconCls: 'tbtn_remove',
                        handler: function(){
                            edit.del();
                        }
                    },
                    '-',
                    {
                        text: '关闭',
                        iconCls: 'tbtn_quit',
                        handler: function(){
                            edit.close();
                        }
                    }
                ],
                onClose : function(){
//                    xutil.focus('#filterValue');
                }
            }); 
            /***********************************************/
            // 窗口初始化
            /***********************************************/
//            $('#dlg_edit').dialog({
//                title: '停车管理',
//                width: 700,
//                height: 300,
//                modal: true,
//                closed: true,
//                minimizable: false,
//                maximizable: false,
//                resizable: false,
//                collapsible: false,
//                toolbar: [
//                    {
//                        text: '新增',
//                        iconCls: 'tbtn_addnew',
//                        handler: function(){
//                            edit.addNew();
//                        }
//                    },
////                    {
////                        text: '复制',
////                        iconCls: 'tbtn_copy',
////                        handler: function(){
////                            edit.copy();
////                        }
////                    },
//                    {
//                        text: '保存',
//                        id:'btnEditSave',
//                        iconCls: 'tbtn_tempsave',
//                        handler: function(){
//                        	//alert('btnEditSavefalse');
//                            edit.save(false);
//                        }
//                    },
//                    {
//                        text: '保存并新增',
//                        iconCls: 'tbtn_savenew',
//                        handler: function(){
//                            edit.save(true);
//                        }
//                    },
//                    '-',
//                    {
//                        id: 'btnFirst',
//                        iconCls: 'tbtn_first',
//                        handler: function () {
//                            edit.showNext('first');
//                        }
//                    },
//                    {
//                        id: 'btnPrevious',
//                        iconCls: 'tbtn_previous',
//                        handler: function () {
//                            edit.showNext('previous');
//                        }
//                    },
//                    {
//                        id: 'btnNext',
//                        iconCls: 'tbtn_next',
//                        handler: function () {
//                            edit.showNext('next');
//                        }
//                    },
//                    {
//                        id: 'btnLast',
//                        iconCls: 'tbtn_last',
//                        handler: function () {
//                            edit.showNext('last');
//                        }
//                    },
//                    '-',
////                    {
////                        text: '删除',
////                        id: 'btnEditDelete',
////                        iconCls: 'tbtn_remove',
////                        handler: function(){
////                            edit.delete();
////                        }
////                    },
//                    '-',
//                    {
//                        text: '关闭',
//                        iconCls: 'tbtn_quit',
//                        handler: function(){
//                            edit.close();
//                        }
//                    }
//                ],
//                onClose : function(){
////                    xutil.focus('#filterValue');
//                }
//            }); 
            
            /***********************************************/
            // 窗口初始化
            /***********************************************/
            $('#dlg_filter').dialog({
                title: '车贷业务',
                width: 700,
                height: 300,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false,
                toolbar: [                
                    {
                        text: '过滤',
                        id:'btnFilterDlg',
                        iconCls: 'tbtn_filter',
                        handler: function(){
                        	//alert('btnEditSavefalse');
                            list.filter();
                        }
                    },
                    {
                        text: '重置',
                        id:'btnClear',
                        iconCls: 'tbtn_deleteall',
                        handler: function(){
                        	//alert('btnEditSavefalse');
                            list.clearFilter();
                        }
                    },
                    '-',
                    {
                        text: '关闭',
                        iconCls: 'tbtn_quit',
                        handler: function(){
                        	$('#dlg_filter').dialog('close');
                        }
                    }
                ],
                onClose : function(){
//                xutil.focus('#filterValue');
                }
            }); 
//            /***********************************************/
//            // 窗口初始化
//            /***********************************************/
//            $('#dlg_upload').dialog({
//                title: '上传图片',
//                width: 700,
//                height: 300,
//                modal: true,
//                closed: true,
//                minimizable: false,
//                maximizable: false,
//                resizable: false,
//                collapsible: false,
//               
//                onClose : function(){
////                xutil.focus('#filterValue');
//                }
//            }); 
//            list.doInit();
        };

        return init;
    }

};