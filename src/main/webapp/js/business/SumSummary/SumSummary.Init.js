var SumSummaryInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = SumSummaryList.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);
            var lstSaveTS = 0;
            $("#stocklist").datagrid({
                striped: true,
                rownumbers: true,
                fit: true,
                border: false,
                method: 'post',
                url: basePath + 'SumSummaryAction.do?m=listStock',
               // idField: 'vehicleid',
                //sortName: 'purchasedate',
                //sortOrder: 'desc',
                toolbar: '#stock_bar_list',
                columns: [[
                           {     field: 'carType',
   					    title: '车辆类别',
   					    width: 80,
   					    align: 'left',
   					    sortable: true
   					},
                       {
                           field: 'inStockCarsAmount',
                           title: '当前在库数量(辆)',
                           width: 100,
                           align: 'right',
                           sortable: false
                       }
                       ,
                       {
                           field: 'inStockCarMoney',
                           title: '总计购车金额(元)',
                           width: 100,
                           align: 'right',
                           sortable: false
                       }
                ]],
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
            $('#exportStockbtn').linkbutton({
                text : '导出明细',
                plain : true,
                iconCls : 'tbtn_importexcel',
                onClick: function(){
                    var url = basePath + 'SumSummaryAction.do?m=downloadStock';
                    var fileName = "testAjaxDownload.txt";
                    var form = $("<form></form>").attr("action", url).attr("method", "post");
                    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
                    form.appendTo('body').submit().remove();
                    //list.openfilter();
                }
            });
            /*$('#purchasestart').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });
            $('#purchaseend').datebox({
                width : 200,
                validType : ['length[1,100]'],
                editable:false
            });*/
            /*$('#btnFilter').linkbutton({
                text : '过滤',
                plain : true,
                iconCls : 'tbtn_filter',
                onClick: function(){
                    list.openfilter();
                }
            });*/
            $('#btnRefresh').linkbutton({
                text : '刷新',
                plain : true,
                iconCls : 'tbtn_refresh',
                onClick: function(){
                    list.refreshStock();
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
            $("#soldlist").datagrid({
                striped: true,
                rownumbers: true,
                fit: true,
                border: false,
                method: 'post',
                url: basePath + 'SumSummaryAction.do?m=listSold',
               // idField: 'vehicleid',
                //sortName: 'purchasedate',
                //sortOrder: 'desc',
                toolbar: '#sold_bar_list',
                columns: [[
                           {     field: 'carType',
   					    title: '车辆类别',
   					    width: 80,
   					    align: 'left',
   					    sortable: true
   					},
                       {
                           field: 'outStockCarsAmount',
                           title: '本月出库数量(辆)',
                           width: 100,
                           align: 'right',
                           sortable: false
                       }
                       ,
                       {
                           field: 'outStockCarsMoney',
                           title: '打款合计金额(元)',
                           width: 100,
                           align: 'right',
                           sortable: false
                       }
                ]],
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
            $('#exportSoldbtn').linkbutton({
                text : '导出明细',
                plain : true,
                iconCls : 'tbtn_importexcel',
                onClick: function(){
                    var url = basePath + 'SumSummaryAction.do?m=downloadSold';
                    var fileName = "testAjaxDownload.txt";
                    var form = $("<form></form>").attr("action", url).attr("method", "post");
                    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
                    form.appendTo('body').submit().remove();
                    //list.openfilter();
                }
            });
            $('#btnSoldRefresh').linkbutton({
                text : '刷新',
                plain : true,
                iconCls : 'tbtn_refresh',
                onClick: function(){
                    list.refreshSold();
                }
            });
        };

        return init;
    }

};