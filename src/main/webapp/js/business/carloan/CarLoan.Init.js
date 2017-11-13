var VoucherInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = VoucherList.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);
            var lstSaveTS = 0;
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
                        title: '日期',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'fullName',
                        title: '姓名',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'carName',
                        title: '车名',
                        width: 80,
                        align: 'center',
                        sortable: true
                    },
                    {
                        field: 'loanPeriod',
                        title: '借款期限',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'netLoanAmount',
                        title: '借款本金',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'paymentPerMonth',
                        title: '月还',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'interestRate',
                        title: '利息',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'paybackRate',
                        title: '返点',
                        width: 80,
                        align: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'parkingFee',
                        title: '停车费',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'others',
                        title: '其他',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'arrcuredPayAmount',
                        title: '打款金额',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'comments',
                        title: '备注',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'allInterestAmount',
                        title: '应还利息',
                        width: 80,
                        align: 'center',
                        sortable: false
                    },
                    {
                        field: 'temperaryMaterils',
                        title: '放公司的材料',
                        width: 80,
                        align: 'center',
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
            $('#btnUpload').linkbutton({
                text : '新增',
                plain : true,
                iconCls : 'tbtn_upload',
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
            $('#dlg_upload').dialog({
                title: '上传凭证',
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
                        text: '上传',
                        iconCls: 'tbtn_upload',
                        handler: function () {
                            list.doUpload();
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
            list.doInit();
        };

        return init;
    }

};