var VoucherInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = VoucherList.getInstance(basePath);
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
                sortName: 'fileName',
                sortOrder: 'asc',
                toolbar: '#bar_list',
                columns: [[
                    {
                        field: 'id',
                        checkbox: true
                    },
                    {
                        field: 'fileName',
                        title: '文件名',
                        width: 200,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'size',
                        title: '大小',
                        width: 70,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'uploadDate',
                        title: '上传日期',
                        width: 150,
                        halign: 'center',
                        sortable: true
                    }/*,
                    {
                        field: 'fileId',
                        title: '操作',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'settleAmount',
                        title: '结算金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'discountAmount',
                        title: '优惠金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'wipeTheZeroAmount',
                        title: '抹零金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'quotaAmount',
                        title: '定额优惠',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'giftAmount',
                        title: '增单金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'serviceAmount',
                        title: '服务费',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'roomsAmount',
                        title: '包房费',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'debitAmount',
                        title: '借方总金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'creditAmount',
                        title: '贷方总金额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false
                    },
                    {
                        field: 'diffAmount',
                        title: '借贷差额',
                        width: 120,
                        halign: 'center',
                        align: 'right',
                        sortable: false,
                        formatter: list.diffFormat
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
                        field: 'voucherNumber',
                        title: '凭证号',
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
                    }*/
                ]],
                onLoadError: function () {
                    top.location = basePath;
                }
            });

            /***********************************************/
            // 列表工具栏初始化
            /***********************************************/
            $('#btnUpload').linkbutton({
                text : '上传数据',
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

            /*$('#btnSync').linkbutton({
                text : '同步',
                plain : true,
                iconCls : 'tbtn_attemper',
                onClick: function(){
                    list.sync();
                }
            });*/

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
                title: '上传数据',
                width: 590,
                height: 200,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false/*,
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
                ]*/
            });
            list.doInit();
            $('#dlg_upload').window('open');
        };

        return init;
    }

};