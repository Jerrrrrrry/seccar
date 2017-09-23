var SumSummaryInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = SumSummaryList.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);

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
                url: basePath + 'SumSummaryAction.do?m=list',
               // idField: 'vehicleid',
                //sortName: 'purchasedate',
                //sortOrder: 'desc',
                toolbar: '#bar_list',
                columns: [[
					{
					    field: 'category',
					    title: '合计类别',
					    width: 80,
					    align: 'left',
					    sortable: true
					},
					{
                        field: 'itemType',
                        title: '项目',
                        width: 80,
                        align: 'left',
                        sortable: true
                    },
                    {
                        field: 'details',
                        title: '明细',
                        width: 80,
                        align: 'right',
                        sortable: false
                    }
                    
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
                },
                onLoadError: function () {
                    xutil.ajaxLoadEnd();
                    xutil.exception();
                }
//                onLoadError: function () {
//                    top.location = basePath;
//                }
            });
            
            
            $('#dlg_filter').dialog({
                title: '二手车交易报表',
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