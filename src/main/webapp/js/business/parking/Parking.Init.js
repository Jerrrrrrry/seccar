var ParkingInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = ParkingList.getInstance(basePath);
            var edit = ParkingEdit.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);
            var lstSaveTS = 0;
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
            	   	},
                pageSize: 20,
                pageList: [20,100,10000],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: basePath + 'ParkingAction.do?m=list',
                idField: 'vehicleid',
                sortName: 'createdts',
                sortOrder: 'desc',
                toolbar: '#bar_list',
                columns: [[
					{
					    field: 'vehicleid',
					    checkbox: true
					},
                    {
                        field: 'customer',
                        title: '客户名称',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'period',
                        title: '停车时间',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'customermobile',
                        title: '客户电话',
                        width: 120,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'cardescription',
                        title: '车辆描述',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'licenseno',
                        title: '车牌号',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'inventoryints',
                        title: '入库时间',
                        width: 180,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'inventoryoutts',
                        title: '出库时间',
                        width: 180,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'parkingfee',
                        title: '停车费',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                    	field: 'issold',
                    	title: '出库',
                    	width: 80,
                    	align: 'left',
                    	sortable: false
                    },
                    {
                        field: 'comments',
                        title: '备注',
                        width: 280,
                        align: 'left',
                        sortable: false
                    }
                ]],
                onDblClickRow: function(index, data){
                    $('#btnFirst').linkbutton('enable');
                    $('#btnPrevious').linkbutton('enable');
                    $('#btnNext').linkbutton('enable');
                    $('#btnLast').linkbutton('enable');
                    var now = Date.now();
                	if((now - lstSaveTS)<1000){
                		return;
                	}
                	lstSaveTS = now;
                    edit.view(index);
                },
                onBeforeLoad : function(param){
                    xutil.ajaxLoading('body');
                },
                onLoadSuccess : function (data) {
                  xutil.ajaxLoadEnd();
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
            $('#customer').textbox({
                width : 200,
                validType : ['length[1,100]']
            });

            $('#period').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            
            $('#customermobile').textbox({
                width : 200,
                validType : ['length[1,100]']
            });

            $('#licenseno').textbox({
                width : 200,
                validType : ['length[0,100]']
            });

            $('#cardescription').textbox({
                width : 200,
                validType : ['length[0,100]']
            });
            
            $('#inventoryints').datebox({
                width: 200,
                validType: ['length[0,100]'],
                editable:false
            });

            $('#inventoryoutts').datebox({
                width: 200,
                validType: ['length[0,100]'],
                editable:false
            });
            $('#parkingfee').textbox({
                width: 200,
                validType: ['length[0,100]']
            });
            
            $('#comments').textbox({
                width: 200,
                validType: ['length[0,100]'],
                multiline: true
            });

            $('#filtercustomer').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filterlicenseno').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filtercardescription').textbox({
                width : 200,
                validType : ['length[1,100]']
            });
            $('#filterinventoryints').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });
            $('#filterinventoryintsend').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });
            $('#filterinventoryoutts').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });
            $('#filterinventoryouttsend').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
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

            $('#btnSync').linkbutton({
                text : '同步',
                plain : true,
                iconCls : 'tbtn_attemper',
                onClick: function(){
                    list.sync();
                }
            });

            $('#btnSelectAll').linkbutton({
                text : '全选',
                plain : true,
                iconCls : 'tbtn_selectall',
                onClick: function(){
                    list.selectAll();
                }
            });

            $('#btnUnselectAll').linkbutton({
                text : '全清',
                plain : true,
                iconCls : 'tbtn_deleteall',
                onClick: function(){
                    list.unselectAll();
                }
            });

            $('#btnDelete').linkbutton({
                text : '删除',
                plain : true,
                iconCls : 'tbtn_remove',
                onClick: function(){
                    list.del();
                }
            });
            $('#btnClose').linkbutton({
                text : '关闭',
                plain : true,
                iconCls : 'tbtn_quit',
                onClick: function(){
                    window.parent.closeCurrentTab();
                }
            });

            /***********************************************/
            // 窗口初始化
            /***********************************************/
            $('#dlg_edit').dialog({
                title: '停车管理',
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
                        text: '新增',
                        iconCls: 'tbtn_addnew',
                        handler: function(){
                            edit.addNew();
                        }
                    },
//                    {
//                        text: '复制',
//                        iconCls: 'tbtn_copy',
//                        handler: function(){
//                            edit.copy();
//                        }
//                    },
                    {
                        text: '保存',
                        id:'btnEditSave',
                        iconCls: 'tbtn_save',
                        handler: function(){
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.save('save');
                        }
                    },
                    {
                        text: '保存并新增',
                        iconCls: 'tbtn_savenew',
                        id:'btnEditSaveAndNew',
                        handler: function(){
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.save('saveandnew');
                        }
                    },
                    {
                      text: '出库',
                      iconCls: 'tbtn_tempsave',
                      id:'btnSoldOut',
                      handler: function(){
                    	  var now = Date.now();
                      	if((now - lstSaveTS)<1000){
                      		return;
                      	}
                      	lstSaveTS = now;
                    	  edit.save('sold');
                      }
                    },
                    '-',
                    {
                        id: 'btnFirst',
                        iconCls: 'tbtn_first',
                        handler: function () {
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.showNext('first');
                        }
                    },
                    {
                        id: 'btnPrevious',
                        iconCls: 'tbtn_previous',
                        handler: function () {
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.showNext('previous');
                        }
                    },
                    {
                        id: 'btnNext',
                        iconCls: 'tbtn_next',
                        handler: function () {
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.showNext('next');
                        }
                    },
                    {
                        id: 'btnLast',
                        iconCls: 'tbtn_last',
                        handler: function () {
                        	var now = Date.now();
                        	if((now - lstSaveTS)<1000){
                        		return;
                        	}
                        	lstSaveTS = now;
                            edit.showNext('last');
                        }
                    },
                    '-',
//                    {
//                        text: '删除',
//                        id: 'btnEditDelete',
//                        iconCls: 'tbtn_remove',
//                        handler: function(){
//                            edit.delete();
//                        }
//                    },
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
            $('#dlg_filter').dialog({
                title: '停车管理',
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
//            list.doInit();
        };

        return init;
    }

};