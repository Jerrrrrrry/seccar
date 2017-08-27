var TradeEdit = {

    getInstance: function (basePath) {
//    	alert("edit");
        var edit = {};
        var currentIndex = 0;
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 清空
        /***********************************************/
        edit.clear = function () {

        	$('#vehicleid').val('');
        	$('#customer').textbox('setValue', '');
            $('#period').textbox('setValue', '');
            $('#licenseno').textbox('setValue', '');
            $('#cardescription').textbox('setValue', '');
            $('#inventoryints').textbox('setValue', '');
            $('#inventoryoutts').textbox('setValue', '');
            $('#Tradefee').textbox('setValue', '');
            $('#comments').textbox('setValue', '');
        };

        /***********************************************/
        // 新增
        /***********************************************/
        edit.addNew = function () {
//        	alert("edit.addNew");
            this.clear();
//            $('#btnEditDelete').linkbutton('disable');
            xutil.focus('#customer');
        };

        /***********************************************/
        // 复制
        /***********************************************/
//        edit.copy = function () {
//        	$('#id').val('');
//        	$('#userid').textbox('setValue', '');
//            $('#username').textbox('setValue', '');
//            $('#password1').textbox('setValue', '');
//            $('#password2').textbox('setValue', '');
//            $('#accesstype').textbox('setValue', '');
//            $('#comments').textbox('setValue', '');
//            $('#userdesc').textbox('setValue', '');
//            $('#createdts').textbox('setValue', '');
//            $('#lastupdatedts').textbox('setValue', '');
//            
//            $('#btnEditDelete').linkbutton('disable');
//            xutil.focus('#username');
//        };

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
                url: basePath + 'TradeAction.do?m=view',
                data: {vehicleid: row.vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == "ok") {
                        $('#vehicleid').val(vo.dto.vehicleid);
                        $('#customer').textbox('setValue', vo.dto.customer);
                        $('#period').textbox('setValue', vo.dto.period);
                        $('#cardescription').textbox('setValue', vo.dto.cardescription);
                        $('#licenseno').textbox('setValue', vo.dto.licenseno);
                        $('#inventoryints').textbox('setValue', vo.dto.inventoryints);
                        $('#inventoryoutts').textbox('setValue', vo.dto.inventoryoutts);
                        $('#Tradefee').textbox('setValue', vo.dto.Tradefee);
                        $('#comments').textbox('setValue', vo.dto.comments);
//                        $('#btnEditDelete').linkbutton('enable');

                        $('#dlg_edit').dialog('open');
                        xutil.focus('#customer');
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
//        	alert("edit.save");
//            if (!$('#userid').textbox('isValid')) {
//                xutil.focus('#userid');
//                return;
//            }
//
//            if (!$('#username').textbox('isValid')) {
//                xutil.focus('#username');
//                return;
//            }

            var vehicleid = $('#vehicleid').val();
//            alert(vehicleid);
            var customer = $('#customer').textbox('getValue');
            var period = $('#period').textbox('getValue');
            var licenseno = $('#licenseno').textbox('getValue');
            var cardescription = $('#cardescription').textbox('getValue');
            var inventoryints = $('#inventoryints').textbox('getValue');
            var inventoryoutts = $('#inventoryoutts').textbox('getValue');
            var Tradefee = $('#Tradefee').textbox('getValue');
            var comments = $('#comments').prop('checked') ? 0 : 1;


            $.ajax({
                type: 'post',
                url: basePath + 'TradeAction.do?m=save',
                data: {
                	vehicleid: vehicleid,
                    customer: customer,
                    period: period,
                    licenseno: licenseno,
                    cardescription: cardescription,
                    inventoryints: inventoryints,
                    inventoryoutts: inventoryoutts,
                    Tradefee: Tradefee,
                    comments: comments
                },
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {

                        $('#list').datagrid('reload');
                        if (addnew) {
                            edit.clear();
                            xutil.focus('#customer');
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
        edit.del = function () {

            var vehicleid = $('#vehicleid').val();
            if (vehicleid == null || vehicleid.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SAVED, 'warning');
                return;
            }

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_DELETE, function (r) {
                if (r) {
                    $('#btnDelete').linkbutton('disable');
                    $.ajax({
                        type: 'post',
                        url: basePath + 'TradeAction.do?m=delete',
                        data: {vehicleid: vehicleid},
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