var AccountInit = {

    getInstance: function (basePath) {
        var init = {};
        init.init = function () {

            var tree = AccountTree.getInstance(basePath);
            var list = AccountList.getInstance(basePath);
            var edit = AccountEdit.getInstance(basePath);
            var xutil = XUtil.getInstance(basePath);

            /***********************************************/
            // 表格初始化
            /***********************************************/
            // 右表
            $("#list").datagrid({
                striped: true,
                rownumbers: true,
                pageSize: 20,
                pageList: [20, 50, 100],
                fit: true,
                singleSelect: false,
                selectOnCheck: true,
                pagination: true,
                border: false,
                method: 'post',
                url: basePath + 'AccountAction.do?m=list',
                idField: 'id',
                sortName: 'number',
                sortOrder: 'asc',
                toolbar: '#bar_list',
                loadMsg: '',
                columns: [[
                    {
                        field: 'id',
                        checkbox: true
                    },
                    {
                        field: 'number',
                        title: '编号',
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
                        field: 'shortNumber',
                        title: '中天编号',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'shortName',
                        title: '简称',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'treeName',
                        title: '分类',
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
                        field: 'createUserName',
                        title: '创建人',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        width: 150,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'lastUpdateUserName',
                        title: '最后修改人',
                        width: 120,
                        halign: 'center',
                        sortable: true
                    },
                    {
                        field: 'lastUpdateTime',
                        title: '最后修改时间',
                        width: 150,
                        halign: 'center',
                        sortable: true
                    }
                ]],
                onDblClickRow: function (index, data) {
                    $('#btnFirst').linkbutton('enable');
                    $('#btnPrevious').linkbutton('enable');
                    $('#btnNext').linkbutton('enable');
                    $('#btnLast').linkbutton('enable');
                    edit.view(index);
                },
                onBeforeLoad: function (param) {
                    xutil.ajaxLoading('#center,#pnl_tree');
                },
                onLoadSuccess: function (data) {
                    xutil.ajaxLoadEnd();
                },
                onLoadError: function () {
                    xutil.ajaxLoadEnd();
                    xutil.exception();
                }
            });

            /***********************************************/
            // 树初始化
            /***********************************************/
            $('#pnl_tree').panel({
                tools: [
                    {
                        iconCls: 'tbtn_tree_new',
                        handler: function () {
                            tree.addNew();
                        }
                    },
                    {
                        iconCls: 'tbtn_refresh',
                        handler: function () {
                            tree.refresh();
                        }
                    },
                    {
                        iconCls: 'tbtn_edit',
                        handler: function () {
                            tree.view();
                        }
                    },
                    {
                        iconCls: 'tbtn_remove',
                        handler: function () {
                            tree.delete();
                        }
                    }
                ]
            });

            $('#showChildren').bind('click', function () {
                tree.showChildren();
            });

            $("#l_tree").tree({
                lines: true,
                url: basePath + 'AccountTreeAction.do?m=tree',

                onSelect: function (node) {
                    tree.select(node);
                },
                onLoadSuccess: function (node, data) {
                    tree.load();
                },
                onLoadError: function () {
                    xutil.exception();
                }
            });

            $("#move_tree").tree({
                lines: true,
                url: '',
                onDblClick: function (node) {
                    tree.move(node);
                },
                onLoadError: function () {
                    xutil.exception();
                }
            });

            $("#change_tree").tree({
                lines: true,
                url: '',
                onDblClick: function (node) {
                    edit.changeTree(node);
                },
                onLoadError: function () {
                    xutil.exception();
                }
            });

            /***********************************************/
            // 字段初始化
            /***********************************************/
            $('#parentTreeName').textbox({width: 200, disabled: true});

            $('#treeNumber').textbox({
                width: 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType: ['length[1,100]']
            });

            $('#treeName').textbox({
                width: 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType: ['length[1,100]']
            });

            $('#editTreeName').textbox({
                width: 200,
                disabled: true
            });

            $('#number').textbox({
                width: 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType: ['length[1,100]']
            });

            $('#name').textbox({
                width: 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType: ['length[1,100]']
            });

            $('#shortNumber').textbox({
                width: 200,
                required: true,
                missingMessage: AppConstant.M_REQUIRED,
                validType: ['length[0,200]']
            });

            $('#shortName').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#comment').textbox({
                width: 200,
                validType: ['length[0,100]']
            });

            $('#createUserName').textbox({
                width: 200,
                disabled: true
            });

            $('#createTime').textbox({
                width: 200,
                disabled: true
            });

            $('#lastUpdateUserName').textbox({
                width: 200,
                disabled: true
            });

            $('#lastUpdateTime').textbox({
                width: 200,
                disabled: true
            });

            /***********************************************/
            // 列表工具栏初始化
            /***********************************************/
            $('#btnAddnew').linkbutton({
                text: '新增',
                plain: true,
                iconCls: 'tbtn_addnew',
                onClick: function(){
                    list.addNew();
                }
            });

            $('#btnRefresh').linkbutton({
                text: '刷新',
                plain: true,
                iconCls: 'tbtn_refresh',
                onClick: function(){
                    list.refresh();
                }
            });

            $('#btnDelete').linkbutton({
                text: '删除',
                plain: true,
                iconCls: 'tbtn_remove',
                onClick: function(){
                    list.delete();
                }
            });

            $('#btnMove').linkbutton({
                text: '移动至分类',
                plain: true,
                iconCls: 'tbtn_move',
                onClick: function(){
                    list.move();
                }
            });

            $('#btnEnable').linkbutton({
                text: '启用',
                plain: true,
                iconCls: 'tbtn_ok',
                onClick: function(){
                    list.enable(1);
                }
            });

            $('#btnDisable').linkbutton({
                text: '禁用',
                plain: true,
                iconCls: 'tbtn_forbid',
                onClick: function(){
                    list.enable(0);
                }
            });

            $('#btnSelectAll').linkbutton({
                text: '全选',
                plain: true,
                iconCls: 'tbtn_selectall',
                onClick: function(){
                    list.selectAll();
                }
            });

            $('#btnUnselectAll').linkbutton({
                text: '全清',
                plain: true,
                iconCls: 'tbtn_deleteall',
                onClick: function(){
                    list.unselectAll();
                }
            });

            $('#btnClose').linkbutton({
                text: '关闭',
                plain: true,
                iconCls: 'tbtn_quit',
                onClick: function(){
                    window.parent.closeCurrentTab();
                }
            });

            $('#btnFilter').linkbutton({
                text: '快速过滤',
                plain: true,
                iconCls: 'tbtn_filter',
                onClick: function(){
                    list.filter();
                }
            });

            $('#filterValue').textbox({
                width: 150
            });

            $('#filterValue').textbox('textbox').bind('keydown', function (event) {
                if (event.keyCode == 13) {
                    list.filter();
                }
            });

            $('#filterField').combobox({
                width: 120,
                editable: false,
                valueField: 'value',
                textField: 'label',
                data: [
                    {
                        label: '名称+编号',
                        value: 'nm+cd',
                        selected: true
                    },
                    {
                        label: '编号',
                        value: 'number'
                    },
                    {
                        label: '名称',
                        value: 'name'
                    },
                    {
                        label: '中天编号',
                        value: 'shortNumber'
                    },
                    {
                        label: '简称',
                        value: 'shortName'
                    }
                ]
            });

            /***********************************************/
            // 窗口初始化
            /***********************************************/

            // 树-编辑窗口
            $('#dlg_tree').dialog({
                title: '分类',
                width: 400,
                height: 200,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false,
                toolbar: [
                    {
                        text: '保存',
                        iconCls: 'tbtn_save',
                        handler: function () {
                            tree.save(false);
                        }
                    },
                    {
                        text: '保存并新增',
                        iconCls: 'tbtn_savenew',
                        handler: function () {
                            tree.save(true);
                        }
                    },
                    '-',
                    {
                        text: '关闭',
                        iconCls: 'tbtn_quit',
                        handler: function () {
                            tree.close();
                        }
                    }
                ]
            });

            // 编辑窗口
            $('#dlg_edit').dialog({
                title: '科目关系维护',
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
                        handler: function () {
                            edit.addNew();
                        }
                    },
                    {
                        text: '复制',
                        iconCls: 'tbtn_copy',
                        handler: function () {
                            edit.copy();
                        }
                    },
                    {
                        text: '保存',
                        iconCls: 'tbtn_save',
                        handler: function () {
                            edit.save(false);
                        }
                    },
                    {
                        text: '保存并新增',
                        iconCls: 'tbtn_savenew',
                        handler: function () {
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
                        handler: function () {
                            edit.delete();
                        }
                    },
                    {
                        text: '移动至分类',
                        id: 'btnEditMove',
                        iconCls: 'tbtn_move',
                        handler: function () {
                            edit.move();
                        }
                    },
                    '-',
                    {
                        text: '关闭',
                        iconCls: 'tbtn_quit',
                        handler: function () {
                            edit.close();
                        }
                    }
                ],
                onClose: function () {
                    xutil.focus('#filterValue');
                }
            });

            // 移动分类窗口
            $('#dlg_moveTree').dialog({
                title: '分类',
                width: 350,
                height: 450,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false
            });

            // 修改分类窗口
            $('#dlg_changeTree').dialog({
                title: '分类',
                width: 350,
                height: 450,
                modal: true,
                closed: true,
                minimizable: false,
                maximizable: false,
                resizable: false,
                collapsible: false
            });

            xutil.focus('#filterValue');
        };

        return init;
    }

};
