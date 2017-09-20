var TradeInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = TradeList.getInstance(basePath);
            var edit = TradeEdit.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);

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
                url: basePath + 'TradeAction.do?m=list',
                idField: 'vehicleid',
                sortName: 'purchasedate',
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
                        field: 'tradername',
                        title: '经办人',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'vehicletype',
                        title: '车辆类型',
                        width: 60,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'purchaseprice',
                        title: '收车价',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'purchasedate',
                        title: '收车日期',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'ownerid',
                        title: '卖车人身份证',
                        width: 160,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'ownername',
                        title: '卖车人姓名',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'interestrate',
                        title: '利率',
                        width: 35,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'actualloan',
                        title: '实际借款金额',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'earnest',
                        title: '定金',
                        width: 40,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'sellprice',
                        title: '销售价格',
                        width: 60,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'selldate',
                        title: '销售日期',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'buyerid',
                        title: '购车人身份证',
                        width: 160,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'buyername',
                        title: '购车人姓名',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'tradecost',
                        title: '交易费用',
                        width: 60,
                        align: 'left',
                        sortable: false
                    },
//                    {
//                        field: 'pricediff',
//                        title: '价差',
//                        width: 60,
//                        align: 'left',
//                        sortable: false
//                    },
//                    {
//                        field: 'totalprofit',
//                        title: '总利润',
//                        width: 60,
//                        align: 'left',
//                        sortable: false
//                    },
//                    {
//                        field: 'profit',
//                        title: '利润',
//                        width: 60,
//                        align: 'left',
//                        sortable: false
//                    },
//                    {
//                        field: 'traderprofit',
//                        title: '第三方利润',
//                        width: 60,
//                        align: 'left',
//                        sortable: false
//                    },
//                    {
//                        field: 'comments',
//                        title: '备注',
//                        width: 60,
//                        align: 'left',
//                        sortable: false
//                    },
                    {
                        field: 'picturepath',
                        title: '图片路径',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'isdeleted',
                        title: '已删除',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'issold',
                        title: '已售',
                        width: 50,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'settlement',
                        title: '已结算',
                        width: 80,
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
                	  purchaseprice: '<span class="subtotal">' + list.compute("purchaseprice") + '</span>',
                	  actualloan: '<span class="subtotal">' + list.compute("actualloan") + '</span>',
                	  earnest: '<span class="subtotal">' + list.compute("earnest") + '</span>',
                	  sellprice: '<span class="subtotal">' + list.compute("sellprice") + '</span>',
                	  tradecost: '<span class="subtotal">' + list.compute("tradecost") + '</span>'
                  });
                },
                onLoadError: function () {
                    xutil.ajaxLoadEnd();
                    xutil.exception();
                }
//                onLoadError: function () {
//                    top.location = basePath;
//                }
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

            $('#tradername').textbox({
                width : 200,
                validType : ['length[0,100]']
            });

            $('#purchaseprice').textbox({
                width : 200,
                validType : ['length[0,100]']
            });
            
            $('#ownername').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#ownerid').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#purchasedate').datebox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#interestrate').textbox({
                width: 200,
                validType: ['length[0,100]']
//                multiline: true
            });
            $('#actualloan').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#spareloan').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#vehicletype').combobox({
                width : 120,
                editable : false,
                valueField: 'value',
                textField: 'label',
                data : [
                    {
                        label: '自收车',
                        value: '自收车',
                        selected : true
                    },
                    {
                        label: '第三方',
                        value: '第三方'
                    }
                ]
            });
            $('#comments').textbox({
                width: 200,
                validType: ['length[0,100]'],
                multiline: true
            });

            $('#earnest').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#tradecost').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            $('#sellprice').textbox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            $('#selldate').datebox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            $('#buyername').textbox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            $('#buyerid').textbox({
                width: 200,
                validType: ['length[0,100]'],
                disabled: true
            });
            

            $('#filterissold').combobox({
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
                        label: '已售',
                        value: '1'
                    },
                    {
                        label: '未售',
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
            $('#filtertradername').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filtercustomer').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filtervehicletype').combobox({
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
                        label: '第三方',
                        value: '第三方'
                    },
                    {
                        label: '自收车',
                        value: '自收车'
                    }
                ]
            });
            $('#purchasestart').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#purchaseend').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#soldstart').datebox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#soldend').datebox({
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
            


//            {
//            	id: 'btnEditupload',
//                text: '上传图片',
//                iconCls: 'tbtn_enable',
//                handler: function(){
//                	//$('#dlg_upload').dialog('open');
//                	$('#file1').click();
//                }
//            },
            /***********************************************/
            // 窗口初始化
            /***********************************************/
            $('#dlg_add').dialog({
                title: '出入库管理',
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
//
//                    {
//                    	id: 'btnEditupload',
//                        text: '上传图片',
//                        iconCls: 'tbtn_enable',
//                        handler: function(){
//                        	//$('#dlg_upload').dialog('open');
//                        	$('#file1').click();
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
                    	id: 'btnEditSold',
                        text: '出库',
                        iconCls: 'tbtn_save',
                        handler: function(){
                        	edit.save('sold');
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
                title: '二手车交易',
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
            /***********************************************/
            // 窗口初始化
            /***********************************************/
            $('#dlg_upload').dialog({
                title: '上传图片',
                width: 700,
                height: 300,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false,
               
                onClose : function(){
//                xutil.focus('#filterValue');
                }
            }); 
//            list.doInit();
        };

        return init;
    }

};