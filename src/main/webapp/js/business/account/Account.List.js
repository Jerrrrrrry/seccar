var AccountList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 快速过滤
        /***********************************************/
        list.filter = function () {

            $('#showChildren').prop('checked', false);
            var filterField = $('#filterField').combobox('getValue');
            var filterValue = $('#filterValue').textbox('getValue');

            var prm = {filterField: filterField, filterValue: filterValue};
            $('#list').datagrid('clearSelections');
            $('#list').datagrid({queryParams: prm});
            xutil.focus('#filterValue');
        };

        /***********************************************/
        // 新增
        /***********************************************/
        list.addNew = function () {

            if (!xutil.isNodeSelected('#l_tree')) return;
            var edit = AccountEdit.getInstance(basePath);
            var node = $('#l_tree').tree('getSelected');

            edit.clear();
            $('#dlg_edit').dialog('open');
            $('#editTreeID').val(node.id);
            $('#editTreeName').textbox('setValue', node.text);
            $('#enable').prop('checked', true);
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditMove').linkbutton('disable');
            xutil.focus('#number');
        };

        /***********************************************/
        // 刷新
        /***********************************************/
        list.refresh = function () {
            $('#list').datagrid('reload');
            xutil.focus('#filterValue');
        };

        /***********************************************/
        // 全选
        /***********************************************/
        list.selectAll = function () {
            $('#list').datagrid('selectAll');
            xutil.focus('#filterValue');
        };

        /***********************************************/
        // 全清
        /***********************************************/
        list.unselectAll = function () {
            $('#list').datagrid('unselectAll');
            xutil.focus('#filterValue');
        };

        /***********************************************/
        // 删除
        /***********************************************/
        list.delete = function () {
            if (!xutil.isGridSelected('#list')) return;
            var id = xutil.getGridSelectedID('#list');

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    xutil.ajaxLoading('#pnl_tree,#center');

                    $.ajax({
                        type: 'post',
                        url: basePath + 'AccountAction.do?m=delete',
                        data: {id: id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                xutil.focus('#filterValue');

                            } else if (vo.status == 'nologin') {
                                top.location = basePath;
                            } else {
                                $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                            }
                        },
                        error: function () {
                            top.location = basePath;
                        }
                    });
                } else {
                    xutil.focus('#filterValue');
                }
            });
        };

        /***********************************************/
        // 启用
        /***********************************************/
        list.enable = function (status) {

            if (!xutil.isGridSelected('#list')) return;
            var id = xutil.getGridSelectedID('#list');
            xutil.ajaxLoading('#center,#pnl_tree');

            $.ajax({
                type: 'post',
                url: basePath + 'AccountAction.do?m=enable',
                data: {id: id, enable: status},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#list').datagrid('reload');
                        xutil.focus('#filterValue');

                    } else if (vo.status == 'nologin') {
                        top.location = basePath;
                    } else {
                        $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                    }
                },
                error: function () {
                    top.location = basePath;
                }
            });
        };

        /***********************************************/
        // 移动至分类
        /***********************************************/
        list.move = function () {

            if (!xutil.isGridSelected('#list')) return;
            $('#move_tree').tree({url: basePath + 'AccountTreeAction.do?m=tree'});
            $('#dlg_moveTree').dialog('open');
        };

        return list;
    }
};