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
                idField: 'vehicleid',
                sortName: 'inventoryints',
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
                        field: 'comments',
                        title: '备注',
                        width: 280,
                        align: 'left',
                        sortable: false
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