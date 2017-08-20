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
        	$('#userid').textbox('setValue', '');
            $('#username').textbox('setValue', '');
            $('#password1').textbox('setValue', '');
            $('#password2').textbox('setValue', '');
            $('#accesstype').textbox('setValue', '');
            $('#comments').textbox('setValue', '');
            $('#userdesc').textbox('setValue', '');
            $('#createdts').textbox('setValue', '');
            $('#lastupdatedts').textbox('setValue', '');
            $('#islocked').prop('checked', true);
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
        	$('#userid').textbox('setValue', '');
            $('#username').textbox('setValue', '');
            $('#password1').textbox('setValue', '');
            $('#password2').textbox('setValue', '');
            $('#accesstype').textbox('setValue', '');
            $('#comments').textbox('setValue', '');
            $('#userdesc').textbox('setValue', '');
            $('#createdts').textbox('setValue', '');
            $('#lastupdatedts').textbox('setValue', '');
            
            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#username');
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
                data: {userid: row.userid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                    	alert(vo.dto.userid);
                        $('#id').val(vo.dto.userid);
                        $('#userid').textbox('setValue', vo.dto.userid);
                        $('#username').textbox('setValue', vo.dto.username);
                        $('#password1').textbox('setValue', '');
                        $('#password2').textbox('setValue', '');
                        $('#accesstype').textbox('setValue', vo.dto.accesstype);
                        $('#comments').textbox('setValue', vo.dto.comments);
                        $('#userdesc').textbox('setValue', vo.dto.userdesc);
                        $('#createdts').textbox('setValue', vo.dto.createdts);
                        $('#lastupdatedts').textbox('setValue', vo.dto.lastupdatedts);
                        $('#islocked').prop('checked', vo.dto.enable == 1);
                        $('#btnEditDelete').linkbutton('enable');

                        $('#dlg_edit').dialog('open');
                        xutil.focus('#userid');
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
            var id = $('#').val();
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