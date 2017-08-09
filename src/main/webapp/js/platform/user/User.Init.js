var UserInit = {

    getInstance: function (basePath){
        var init = {};
        init.init = function (){

            var list = UserList.getInstance(basePath);
            var edit = UserEdit.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);

            /***********************************************/
            // 表格初始化
            /***********************************************/
            // 右表
            $("#list").datagrid({
                striped: true,
                rownumbers: true,
                pageSize: 20,
                pageList: [20,50,100],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: basePath + 'UserAction.do?m=list',
                idField: 'id',
                sortName: 'account',
                sortOrder: 'asc',
                toolbar: '#bar_list',
                loadMsg : '',
                columns: [[
                    {
                        field: 'id',
                        checkbox: true
                    },
                    {
                        field: 'account',
                        title: '账号',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'name',
                        title: '名称',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'enable',
                        title: '启用',
                        width: 50,
                        halign: 'center',
                        align: 'center',
                        formatter: xutil.formatEnable,
                        sortable: true
                    },
                    {
                        field: 'comment',
                        title: '备注',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        width: 160,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'updateTime',
                        title: '最后修改时间',
                        width: 160,
                        halign: 'center',
                        sortable: true
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
                },
                onLoadError: function () {
                    xutil.ajaxLoadEnd();
                    xutil.exception();
                }
            });

            /***********************************************/
            // 字段初始化
            /***********************************************/
            $('#account').textbox({
                width : 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType : ['length[1,100]']
            });

            $('#name').textbox({
                width : 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType : ['length[1,100]']
            });

            $('#password1').textbox({
                type : 'password',
                width : 200,
                validType : ['length[0,100]']
            });

            $('#password2').textbox({
                type : 'password',
                width : 200,
                validType : ['length[0,100]']
            });

            $('#comment').textbox({
                width: 200,
                validType: ['length[0,100]'],
                multiline: true
            });

            $('#createTime').textbox({
                width: 200,
                disabled: true
            });

            $('#updateTime').textbox({
                width: 200,
                disabled: true
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

            $('#btnRefresh').linkbutton({
                text : '刷新',
                plain : true,
                iconCls : 'tbtn_refresh',
                onClick: function(){
                    list.refresh();
                }
            });

            $('#btnDelete').linkbutton({
                text : '删除',
                plain : true,
                iconCls : 'tbtn_remove',
                onClick: function(){
                    list.delete();
                }
            });

            $('#btnEnable').linkbutton({
                text : '启用',
                plain : true,
                iconCls : 'tbtn_ok',
                onClick: function(){
                    list.enable(1);
                }
            });

            $('#btnDisable').linkbutton({
                text : '禁用',
                plain : true,
                iconCls : 'tbtn_forbid',
                onClick: function(){
                    list.enable(0);
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

            $('#btnFilter').linkbutton({
                text : '快速过滤',
                plain : true,
                iconCls : 'tbtn_filter',
                onClick: function(){
                    list.filter();
                }
            });

            $('#filterValue').textbox({
                width : 150
            });

            $('#filterValue').textbox('textbox').bind('keydown', function(event) {
                if(event.keyCode == 13){
                    list.filter();
                }
            });

            $('#filterField').combobox({
                width : 120,
                editable : false,
                valueField: 'value',
                textField: 'label',
                data : [
                    {
                        label: '账号',
                        value: 'account',
                        selected : true
                    },
                    {
                        label: '名称',
                        value: 'name'
                    }
                ]
            });

            /***********************************************/
            // 窗口初始化
            /***********************************************/
            // 编辑窗口
            $('#dlg_edit').dialog({
                title: '用户',
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
                    {
                        text: '复制',
                        iconCls: 'tbtn_copy',
                        handler: function(){
                            edit.copy();
                        }
                    },
                    {
                        text: '保存',
                        id:'btnEditSave',
                        iconCls: 'tbtn_save',
                        handler: function(){
                            edit.save(false);
                        }
                    },
                    {
                        text: '保存并新增',
                        iconCls: 'tbtn_savenew',
                        handler: function(){
                            edit.save(true);
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
                            edit.delete();
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
                    xutil.focus('#filterValue');
                }
            });

            xutil.focus('#filterValue');
        };

        return init;
    }

};