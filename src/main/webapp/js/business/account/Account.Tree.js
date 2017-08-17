var AccountTree = {

    getInstance: function (basePath) {
        var tree = {};
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 包含下级节点
        /***********************************************/
        tree.showChildren = function () {

            var node = $('#l_tree').tree('getSelected');
            if (node == null) {
                return;
            }

            // 如果已经是叶子节点，不需要重新加载
            if ($('#l_tree').tree('isLeaf', node.target)) {
                return;
            }

            if ($('#showChildren').prop('checked')) {
                this.filterByTree(node.id, true);
            } else {
                this.filterByTree(node.id, false);
            }
        };

        /***********************************************/
        // 根据选中的树过滤
        /***********************************************/
        tree.filterByTree = function (treeid, isShowChildren) {

            if (isShowChildren) {
                var prm = {treeID: treeid, showChildren: true};
                $('#list').datagrid('clearSelections');
                $('#list').datagrid({queryParams: prm});
            } else {
                var prm = {treeID: treeid};
                $('#list').datagrid('clearSelections');
                $('#list').datagrid({queryParams: prm});
            }
        };

        /***********************************************/
        // 新增分类
        /***********************************************/
        tree.addNew = function () {

            if (!xutil.isNodeSelected('#l_tree')) return;
            var node = $('#l_tree').tree('getSelected');

            $('#parentTreeID').val(node.id);
            $('#treeID').val('');
            $('#parentTreeName').textbox('setValue', node.text);
            $('#treeNumber').textbox('clear');
            $('#treeName').textbox('clear');
            $('#dlg_tree').dialog('open');
            xutil.focus('#treeNumber');
        };

        /***********************************************/
        // 刷新分类
        /***********************************************/
        tree.refresh = function () {
            $('#l_tree').tree('reload');
        };

        /***********************************************/
        // 查看分类
        /***********************************************/
        tree.view = function () {

            if (xutil.isRootNodeSelected('#l_tree')) return;
            if (!xutil.isNodeSelected('#l_tree')) return;

            var node = $('#l_tree').tree('getSelected');
            $('#treeID').val(node.id);
            $('#parentTreeName').textbox('clear');
            $('#treeNumber').textbox('clear');
            $('#treeName').textbox('clear');
            $('#dlg_tree').dialog('open');

            $.ajax({
                type: 'post',
                url: basePath + 'AccountTreeAction.do?m=view',
                data: {treeID: node.id},
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#parentTreeName').textbox('setValue', vo.dto.parentName);
                        $('#treeID').val(vo.dto.id);
                        $('#parentTreeID').val(vo.dto.parentID);
                        $('#treeNumber').textbox('setValue', vo.dto.number);
                        $('#treeName').textbox('setValue', vo.dto.name);
                        xutil.focus('#treeNumber');

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
        // 保存分类
        /***********************************************/
        tree.save = function (addnew) {

            if (!$('#treeNumber').textbox('isValid')) {
                xutil.focus('#treeNumber');
                return;
            }

            if (!$('#treeName').textbox('isValid')) {
                xutil.focus('#treeName');
                return;
            }

            var treeID = $('#treeID').val();
            var parentTreeID = $('#parentTreeID').val();
            var treeNumber = $('#treeNumber').textbox('getValue');
            var treeName = $('#treeName').textbox('getValue');

            $.ajax({
                type: 'post',
                url: basePath + 'AccountTreeAction.do?m=save',
                data: {treeID: treeID, parentTreeID: parentTreeID, treeNumber: treeNumber, treeName: treeName},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#lastSelectedTreeID').val(vo.dto);
                        $('#l_tree').tree('reload');

                        if (addnew) {
                            $('#treeID').val('');
                            $('#treeNumber').textbox('clear');
                            $('#treeName').textbox('clear');
                            xutil.focus('#treeNumber');
                        } else {
                            $('#dlg_tree').dialog('close');
                        }

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
        // 删除分类
        /***********************************************/
        tree.delete = function () {

            if (xutil.isRootNodeSelected('#l_tree')) return;
            if (!xutil.isNodeSelected('#l_tree')) return;
            var node = $('#l_tree').tree('getSelected');

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    $.ajax({
                        type: 'post',
                        url: basePath + 'AccountTreeAction.do?m=delete',
                        data: {treeID: node.id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];

                            if (vo.status == 'ok') {
                                $('#l_tree').tree('reload');
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
        // 关闭分类
        /***********************************************/
        tree.close = function () {
            $('#dlg_tree').dialog('close');
        };

        /***********************************************/
        // 选择树节点后，过滤后边的表格
        /***********************************************/
        tree.select = function (node) {

            $("#showChildren").prop("checked", false);
            var prm = {treeID: node.id};
            $('#lastSelectedTreeID').val(node.id);
            $('#list').datagrid('clearSelections');
            $('#list').datagrid({queryParams: prm});
        };

        /***********************************************/
        // 加载左树
        /***********************************************/
        tree.load = function () {

            var id = $('#lastSelectedTreeID').val();
            if (id == null || id.length == 0) {
                return;
            }

            var node = $('#l_tree').tree('find', id);
            if (node == null || node.target == null) {
                return;
            }
            $('#l_tree').tree('select', node.target);
        };

        /***********************************************/
        // 移动至分类
        /***********************************************/
        tree.move = function (node) {

            var id = xutil.getGridSelectedID('#list');
            var treeID = node.id;
            $.ajax({
                type: 'post',
                url: basePath + 'AccountAction.do?m=move',
                data: {id: id, treeID: treeID},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#list').datagrid('reload');
                        $('#dlg_moveTree').dialog('close');
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

        return tree;
    }
};