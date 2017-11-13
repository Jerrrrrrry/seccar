var SumTradeInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = SumTradeList.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);
            var lstSaveTS = 0;
            /***********************************************/
            // 表格初始化
            /***********************************************/
            // 右表

            $("#list").datagrid({
                striped: true,
                rownumbers: true,
                pageSize: 20,
                pageList: [20,100,10000],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: basePath + 'SumTradeAction.do?m=list',
               // idField: 'vehicleid',
                //sortName: 'purchasedate',
                //sortOrder: 'desc',
                toolbar: '#bar_list',
                columns: [[
					{
					    field: 'vehicletype',
					    title: '车辆类型',
					    width: 80,
					    align: 'left',
					    sortable: true
					},
					{
                        field: 'issold',
                        title: '是否已售',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'settlement',
                        title: '是否结算',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'purchaseprice',
                        title: '收车价',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'actualloan',
                        title: '实际借款金额',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'earnest',
                        title: '定金',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'sellprice',
                        title: '卖车价',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'tradecost',
                        title: '交易费用',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'interestcost',
                        title: '利息费用',
                        width: 80,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'pricediff',
                        title: '价差',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'totalprofit',
                        title: '总利润',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'profit',
                        title: '利润',
                        width: 100,
                        align: 'left',
                        sortable: false
                    },
                    {
                        field: 'traderprofit',
                        title: '三方利润',
                        width: 100,
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
                  //添加“合计”列
                  $('#list').datagrid('appendRow', {
                	  vehicletype: '<span class="subtotal">合计</span>',
                	  purchaseprice: '<span class="subtotal">' + list.compute("purchaseprice") + '</span>',
                	  actualloan: '<span class="subtotal">' + list.compute("actualloan") + '</span>',
                	  earnest: '<span class="subtotal">' + list.compute("earnest") + '</span>',
                	  sellprice: '<span class="subtotal">' + list.compute("sellprice") + '</span>',
                	  tradecost: '<span class="subtotal">' + list.compute("tradecost") + '</span>',
                	  interestcost: '<span class="subtotal">' + list.compute("interestcost") + '</span>',
                	  pricediff: '<span class="subtotal">' + list.compute("pricediff") + '</span>',
                	  totalprofit: '<span class="subtotal">' + list.compute("totalprofit") + '</span>',
                	  profit: '<span class="subtotal">' + list.compute("profit") + '</span>',
                	  traderprofit: '<span class="subtotal">' + list.compute("traderprofit") + '</span>'
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
            $('#purchasestart').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });
            $('#purchaseend').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
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
            $('#btnClose').linkbutton({
                text : '关闭',
                plain : true,
                iconCls : 'tbtn_quit',
                onClick: function(){
                    window.parent.closeCurrentTab();
                }
            });
            $('#dlg_filter').dialog({
                title: '过滤条件',
                width: 580,
                height: 140,
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