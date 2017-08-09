var UserList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 快速过滤
        /***********************************************/
        list.filter = function () {

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

            var edit = UserEdit.getInstance(basePath);
            edit.clear();
            $('#dlg_edit').dialog('open');
            $('#enable').prop('checked', true);
            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#account');
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
                    xutil.ajaxLoading('body');

                    $.ajax({
                        type: 'post',
                        url: basePath + 'UserAction.do?m=delete',
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
            xutil.ajaxLoading('body');

            $.ajax({
                type: 'post',
                url: basePath + 'UserAction.do?m=enable',
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

        return list;
    }
};