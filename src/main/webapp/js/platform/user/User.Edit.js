var UserEdit = {

    getInstance: function (basePath) {
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {

            $('#id').val('');
            $('#account').textbox('setValue', '');
            $('#name').textbox('setValue', '');
            $('#password1').textbox('setValue', '');
            $('#password2').textbox('setValue', '');
            $('#comment').textbox('setValue', '');
            $('#createTime').textbox('setValue', '');
            $('#updateTime').textbox('setValue', '');
            $('#enable').prop('checked', true);
        };

        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {

            this.clear();
            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#account');
        };

        /***********************************************/
        // 复制
        /***********************************************/
        edit.copy = function () {

            $('#id').val('');
            $('#account').textbox('setValue', '');
            $('#name').textbox('setValue', '');
            $('#password1').textbox('setValue', '');
            $('#password2').textbox('setValue', '');
            $('#createTime').textbox('setValue', '');
            $('#updateTime').textbox('setValue', '');
            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#account');
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
                url: basePath + 'UserAction.do?m=view',
                data: {id: row.id},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                        $('#id').val(vo.dto.id);
                        $('#account').textbox('setValue', vo.dto.account);
                        $('#name').textbox('setValue', vo.dto.name);
                        $('#password1').textbox('setValue', '');
                        $('#password2').textbox('setValue', '');
                        $('#comment').textbox('setValue', vo.dto.comment);
                        $('#createTime').textbox('setValue', vo.dto.createTime);
                        $('#updateTime').textbox('setValue', vo.dto.updateTime);
                        $('#enable').prop('checked', vo.dto.enable == 1);
                        $('#btnEditDelete').linkbutton('enable');

                        $('#dlg_edit').dialog('open');
                        xutil.focus('#account');
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

            if (!$('#account').textbox('isValid')) {
                xutil.focus('#account');
                return;
            }

            if (!$('#name').textbox('isValid')) {
                xutil.focus('#name');
                return;
            }

            var id = $('#id').val();
            var account = $('#account').textbox('getValue');
            var name = $('#name').textbox('getValue');
            var password1 = $('#password1').textbox('getValue');
            var password2 = $('#password2').textbox('getValue');
            var comment = $('#comment').textbox('getValue');
            var enable = $('#enable').prop('checked') ? 1 : 0;

            if (password1 != password2){
                $.messager.alert(AppConstant.M_INFO, '密码不一致！', 'warning');
                return;
            }

            $.ajax({
                type: 'post',
                url: basePath + 'UserAction.do?m=save',
                data: {
                    id: id,
                    account: account,
                    name: name,
                    password: password1,
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
                            xutil.focus('#account');
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
                        url: basePath + 'UserAction.do?m=delete',
                        data: {id: id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];
                            $('#btnDelete').linkbutton('enable');

                            if (vo.status == 'ok') {
                                $('#list').datagrid('reload');
                                edit.clear();
                                xutil.focus('#account');
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
                    xutil.focus('#account');
                }
            });
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