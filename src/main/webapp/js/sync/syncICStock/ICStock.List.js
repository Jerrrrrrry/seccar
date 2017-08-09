var ICStockList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath);

        /***********************************************/
        // 过滤
        /***********************************************/
        list.filter = function () {
            $('#dlg_filter').dialog('open');
        };

        /***********************************************/
        // 刷新
        /***********************************************/
        list.refresh = function () {
            $('#list').datagrid('reload');
        };

        /***********************************************/
        // 全选
        /***********************************************/
        list.selectAll = function () {
            $('#list').datagrid('selectAll');
        };

        /***********************************************/
        // 全清
        /***********************************************/
        list.unselectAll = function () {
            $('#list').datagrid('unselectAll');
        };

        /***********************************************/
        // 清除过滤条件
        /***********************************************/
        list.clearFilter = function () {
            $('#bizDateFrom').datebox('clear');
            $('#bizDateTo').datebox('clear');
            $('#departmentNumber').textbox('clear');
            $('#departmentName').textbox('clear');
            xutil.focus('#bizDateFrom');
        };

        /***********************************************/
        // 同步状态颜色
        /***********************************************/
        list.stateFormat = function(val, row){
            var result = '';
            if (val == '已同步') {
                result = "<span style='color: green; font-weight: bold'>" + val + "</span>";

            }else if (val == '已删除'){
                result = "<span style='color: red; font-weight: bold'>" + val + "</span>";

            }else{
                result = val;
            }

            return result;
        };

        /***********************************************/
        // 过滤
        /***********************************************/
        list.doFilter = function(){

            if (!$('#bizDateFrom').datebox('isValid')) {
                xutil.focus('#bizDateFrom');
                return;
            }

            if (!$('#bizDateTo').datebox('isValid')) {
                xutil.focus('#bizDateTo');
                return;
            }

            $('#dlg_filter').dialog('close');
            var bizDateFrom = $('#bizDateFrom').datebox('getValue');
            var bizDateTo = $('#bizDateTo').datebox('getValue');
            var departmentNumber = $('#departmentNumber').textbox('getValue');
            var departmentName = $('#departmentName').textbox('getValue');

            var prm = {
                bizDateFrom : bizDateFrom,
                bizDateTo : bizDateTo,
                departmentNumber : departmentNumber,
                departmentName : departmentName
            };

            $('#list').datagrid('clearSelections');
            $('#list').datagrid({
                url: basePath + 'ICStockAction.do?m=list',
                queryParams: prm});

        };

        /***********************************************/
        // 同步
        /***********************************************/
        list.sync = function (status) {

            if (!xutil.isGridSelected('#list')) return;
            var id = xutil.getGridSelectedID('#list');

            $.messager.confirm(AppConstant.M_INFO, AppConstant.M_CONFIRM_SYNC2, function (r) {
                if (r) {
                    xutil.ajaxLoading();

                    $.ajax({
                        type: 'post',
                        url: basePath + 'ICStockAction.do?m=sync',
                        data: {id: id},
                        success: function (data) {

                            if (data == null || data.length == 0) return;
                            var vo = data[0];

                            if (vo.status == 'ok') {
                                xutil.ajaxLoadEnd();
                                $('#list').datagrid('reload');
                                $.messager.alert(AppConstant.M_INFO, '同步完成！', 'info');

                            } else if (vo.status == 'nologin') {
                                top.location = basePath;
                            } else {

                                xutil.ajaxLoadEnd();
                                if (vo.status == 'warning'){

                                    $("#msgContentt").html(vo.message);
                                    $("#msg").window('open');

                                }else{
                                    $.messager.alert(AppConstant.M_INFO, vo.message, vo.status);
                                }
                            }
                        },
                        error: function () {
                            top.location = basePath;
                        }
                    });
                }
            });

        };

        return list;
    }
};