var ICStockInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = ICStockList.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);

            /***********************************************/
            // 表格初始化
            /***********************************************/
            // 右表
            $("#list").datagrid({
                striped: true,
                rownumbers: true,
                pageSize: 50,
                pageList: [50,100],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: '',
                idField: 'id',
                sortName: 'bizDate',
                sortOrder: 'asc',
                toolbar: '#bar_list',
                columns: [[
                    {
                        field: 'id',
                        checkbox: true
                    },
                    {
                        field: 'bizDate',
                        title: '销售日期',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'departmentNumber',
                        title: '门店编码',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'departmentName',
                        title: '门店名称',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'beerAmount',
                        title: '酒水',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: true
                    },
                    {
                        field: 'totalAmount',
                        title: '总金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: true
                    },
                    {
                        field: 'status',
                        title: '同步状态',
                        width: 120,
                        halign: 'center',
                        align: 'center',
                        sortable: false,
                        formatter: list.stateFormat
                    },
                    {
                        field: 'billNumber',
                        title: '销售出库单号',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'syncUser',
                        title: '同步人',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'syncTime',
                        title: '同步时间',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    }
                ]],
                onLoadError: function () {
                    top.location = basePath;
                }
            });

            /***********************************************/
            // 列表工具栏初始化
            /***********************************************/
            $('#btnFilter').linkbutton({
                text : '过滤',
                plain : true,
                iconCls : 'tbtn_filter',
                onClick: function(){
                    list.filter();
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
            $('#dlg_filter').dialog({
                title: '过滤',
                width: 590,
                height: 200,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false,
                toolbar: [
                    {
                        text: '过滤',
                        iconCls: 'tbtn_filter',
                        handler: function () {
                            list.doFilter();
                        }
                    },
                    {
                        text: '清除',
                        iconCls: 'tbtn_clear',
                        handler: function () {
                            list.clearFilter();
                        }
                    },
                    '-',
                    {
                        text: '关闭',
                        iconCls: 'tbtn_quit',
                        handler: function () {
                            $('#dlg_filter').dialog('close');
                        }
                    }
                ]
            });

            /***********************************************/
            // 过滤界面字段初始化
            /***********************************************/
            $('#bizDateFrom').datebox({
                width: 160,
                validType: ['length[0,10]', 'date'],
                required: true,
                missingMessage: AppConstant.M_REQUIRED
            });

            $('#bizDateTo').datebox({
                width: 160,
                validType: ['length[0,10]', 'date'],
                required: true,
                missingMessage: AppConstant.M_REQUIRED
            });

            $('#departmentNumber').textbox({
                width: 160,
                validType: ['length[0,100]']
            });

            $('#departmentName').textbox({
                width: 160,
                validType: ['length[0,100]']
            });

            $('#bizDateFrom').textbox('setValue', xutil.getFirstDayOfMonth());
            $('#bizDateTo').textbox('setValue', new Date().format("yyyy-MM-dd"));
            $('#dlg_filter').window('open');
        };

        return init;
    }

};