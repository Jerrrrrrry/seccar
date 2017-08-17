var AccountEdit = {

    getInstance: function (basePath) {
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {

            $('#id').val('');
            $('#number').textbox('clear');
            $('#name').textbox('clear');
            $('#shortNumber').textbox('clear');
            $('#shortName').textbox('clear');
            $('#comment').textbox('clear');
            $('#enable').prop('checked', true);
            $('#createUserName').textbox('clear');
            $('#createTime').textbox('clear');
            $('#lastUpdateUserName').textbox('clear');
            $('#lastUpdateTime').textbox('clear');
        };

        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {

            this.clear();
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditMove').linkbutton('disable');
            xutil.focus('#number');
        };

        /***********************************************/
        // 复制
        /***********************************************/
        edit.copy = function () {

            $('#id').val('');
            $('#number').textbox('clear');
            $('#name').textbox('clear');
            $('#createUserName').textbox('clear');
            $('#createTime').textbox('clear');
            $('#lastUpdateUserName').textbox('clear');
            $('#lastUpdateTime').textbox('clear');
            $('#btnEditDelete').linkbutton('disable');
            $('#btnEditMove').linkbutton('disable');
            xutil.focus('#number');
        };

        /***********************************************/
        // 显示
        /***********************************************/
        edit.view = function (index) {

            $('#list').datagrid('clearSelections');
            $('#list').datagrid('selectRow', index);
            var row = xutil.getGridRowByIndex('#list', index);
            currentIndex = index;
            this.clear();

            $.ajax({
                type: 'post',
                url: basePath + 'AccountAction.do?m=view',
                data: {id: row.id},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                        $('#id').val(vo.dto.id);
                        $('#editTreeName').textbox('setValue', vo.dto.treeName);
                        $('#editTreeID').val(vo.dto.treeID);
                        $('#number').textbox('setValue', vo.dto.number);
                        $('#name').textbox('setValue', vo.dto.name);
                        $('#shortNumber').textbox('setValue', vo.dto.shortNumber);
                        $('#shortName').textbox('setValue', vo.dto.shortName);
                        $('#comment').textbox('setValue', vo.dto.comment);
                        $('#enable').prop('checked', vo.dto.enable == 1);
                        $('#createUserName').textbox('setValue', vo.dto.createUserName);
                        $('#createTime').textbox('setValue', vo.dto.createTime);
                        $('#lastUpdateUserName').textbox('setValue', vo.dto.lastUpdateUserName);
                        $('#lastUpdateTime').textbox('setValue', vo.dto.lastUpdateTime);
                        $('#btnEditDelete').linkbutton('enable');
                        $('#btnEditMove').linkbutton('enable');

                        $('#dlg_edit').dialog('open');
                        xutil.focus('#number');
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
        // 显示下一个
        /***********************************************/
        edit.showNext = function(type){

            var index = xutil.moveGridNextByIndex('#list', type, currentIndex);
            edit.view(index);
        };

        /***********************************************/
        // 保存
        /***********************************************/
        edit.save = function (addnew) {

            if (!$('#number').textbox('isValid')) {
                xutil.focus('#number');
                return;
            }

            if (!$('#name').textbox('isValid')) {
                xutil.focus('#name');
                return;
            }

            var id = $('#id').val();
            var treeID = $('#editTreeID').val();
            var number = $('#number').textbox('getValue');
            var name = $('#name').textbox('getValue');
            var shortNumber = $('#shortNumber').textbox('getValue');
            var shortName = $('#shortName').textbox('getValue');
            var comment = $('#comment').textbox('getValue');
            var enable = $('#enable').prop('checked') ? 1 : 0;

            $.ajax({
                type: 'post',
                url: basePath + 'AccountAction.do?m=save',
                data: {
                    id: id,
                    treeID: treeID,
                    number: number,
                    name: name,
                    shortNumber: shortNumber,
                    shortName: shortName,
                    comment: comment,
                    enable: enable
                },
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {

                        $('#list').datagrid('reload');
                        if (addnew) {
                            edit.clear();
                            $('#btnEditDelete').linkbutton('disable');
                            $('#btnEditMove').linkbutton('disable');
                            xutil.focus('#number');
                        } else {
                            $('#dlg_edit').dialog('close');
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
        // 删除
        /***********************************************/
        edit.delete = function () {
            var id = $('#id').val();
            if (id == null || id.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    $('#btnDelete').linkbutton('disable');
                    $.ajax({
                        type: 'post',
                        url: basePath + 'AccountAction.do?m=delete',
                        data: {id: id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];
                            $('#btnDelete').linkbutton('enable');

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                edit.clear();
                                xutil.focus('#number');
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
                    xutil.focus('#number');
                }
            });
        };

        /************************************************/
        // 移动至分类
        /***********************************************/
        edit.move = function () {

            var id = $('#id').val();
            if (id == null || id.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }

            $('#change_tree').tree({url: basePath + 'AccountTreeAction.do?m=tree'});
            $('#dlg_changeTree').dialog('open');

        };

        /***********************************************/
        // 修改树ID和树名称
        /***********************************************/
        edit.changeTree = function (node) {

            $('#editTreeID').val(node.id);
            $('#editTreeName').textbox('setValue', node.text);
            $('#dlg_changeTree').dialog('close');
        };

        /***********************************************/
        // 关闭
        /***********************************************/
        edit.close = function () {
            $('#dlg_edit').dialog('close');
        };

        return edit;
    }

};