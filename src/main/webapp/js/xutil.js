var XUtil = {

    getInstance: function (basePath) {

        var xutil = {};

        /***********************************************/
        // 显示错误，从session中取得错误信息
        /***********************************************/
        xutil.exception = function () {

            $.ajax({
                type: 'post',
                url: basePath + 'MainAction.do?m=exception',
                success: function (data) {
                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'nologin') {
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
        // 设置焦点
        /***********************************************/
        xutil.focus = function (input) {
            $(input).next('span').find('input')[0].focus();
            $(input).next('span').find('input')[0].select();
        };

        /***********************************************/
        // 失去焦点
        /***********************************************/
        xutil.blur = function (input) {
            $(input).next('span').find('input')[0].blur();
        };


        /***********************************************/
        // 取得值
        /***********************************************/
        xutil.val = function (input) {
            return $($(input).next('span').find('input')[0]).val();
        };

        /***********************************************/
        // 设置属性
        /***********************************************/
        xutil.attr = function (input, attrName, attrValue) {
            $($(input).next('span').find('input')[0]).attr(attrName, attrValue);
        };

        /***********************************************/
        // 设置红色
        /***********************************************/
        xutil.cssRed = function (input) {
            $($(input).next('span').find('input')[0]).css('color', 'red');
        };

        /***********************************************/
        // 设置默认色
        /***********************************************/
        xutil.cssDefault = function (input) {
            $($(input).next('span').find('input')[0]).css('color', '#000000');
        };

        /***********************************************/
        // 设置灰色
        /***********************************************/
        xutil.cssGray = function (input) {
            $($(input).next('span').find('input')[0]).css('color', '#7B7B7B');
        };

        /***********************************************/
        // 删除属性
        /***********************************************/
        xutil.removeAttr = function (input, attrName) {
            $($(input).next('span').find('input')[0]).removeAttr(attrName);
        };

        /***********************************************/
        // 检查选择行
        /***********************************************/
        xutil.isGridSelected = function (grid) {

            var rows = $(grid).datagrid('getSelections');
            if (rows.length == 0) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SELECT_ROW, 'warning');
                return false;
            }

            return true;
        };

        /***********************************************/
        // 检查是否选中
        /***********************************************/
        xutil.isNodeSelected = function (tree) {

            var node = $(tree).tree('getSelected');
            if (node == null) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_NO_SELECT_TREE, 'warning');
                return false;
            }

            return true;
        };

        /***********************************************/
        // 检查是否选中根节点
        /***********************************************/
        xutil.isRootNodeSelected = function (tree) {

            var node = $('#l_tree').tree('getSelected');
            if (node == null) {
                return false;
            }

            var root = $(tree).tree('getRoot');
            if (root != null && root.id == node.id) {
                $.messager.alert(AppConstant.M_INFO, AppConstant.M_CAN_NOT_SELECT_ROOT_TREE, 'warning');
                return true;
            }

            return false;
        };

        /***********************************************/
        // 获得所有行ID
        /***********************************************/
        xutil.getGridSelectedID = function (grid) {

            var ids = [];
            var rows = $(grid).datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }

            if (ids.length == 0) {
                return '';
            }

            var result = ids.join(',');
            return result;
        };

        /***********************************************/
        // 获得Grid选中第一个索引
        /***********************************************/
        xutil.getGridFirstSelectedIndex = function (grid) {

            var index = [];
            var rows = $(grid).datagrid('getSelections');

            for (var i = 0; i < rows.length; i++) {
                index.push($(grid).datagrid('getRowIndex', rows[i]));
            }

            index.sort();
            return index[0];
        };

        /***********************************************/
        // 根据一个Index，获得一个Row
        /***********************************************/
        xutil.getGridRowByIndex = function (grid, index) {

            var rows = $(grid).datagrid('getRows');
            if (rows.length > index){
                return rows[index];
            }

            return null;

        };

        /***********************************************/
        // 根据一个ID，获得一个Index
        /***********************************************/
        xutil.getGridIndexByID = function(grid, id){

            var rows = $(grid).datagrid('getRows');
            if (rows.length == 0){
                return 0;
            }

            for(var i = 0; i < rows.length; i ++){
                var row = rows[i];
                if (row.id == id){
                    return i;
                }
            }

            return 0;
        };

        /***********************************************/
        // 根据一个ID，获得下一个ID
        /***********************************************/
        xutil.moveGridNextByID = function(grid, type, currentRowID){

            var rows = $(grid).datagrid('getRows');

            // 列表没有数据
            if (rows.length == 0){
                return null;
            }

            // 当前行的索引
            var index = xutil.getGridIndexByID('#list', currentRowID);

            // 下一个
            if (type == 'next'){
                index ++;
            }

            // 最后一个
            else if (type == 'last'){
                index = rows.length -1;
            }

            // 上一个
            else if (type == 'previous'){
                index --;
            }

            // 第一个
            else if (type == 'first'){
                index = 0;
            }

            var row = xutil.getGridRowByIndex(grid, index);
            if (row == null) return null;
            return row.id;
        };

        /***********************************************/
        // 根据一个ID，获得下一个ID和TreeID
        /***********************************************/
        xutil.moveTreeGridNextByID = function(grid, type, currentRowID){

            var rows = $(grid).datagrid('getRows');

            // 列表没有数据
            if (rows.length == 0){
                return null;
            }

            // 当前行的索引
            var index = xutil.getGridIndexByID('#list', currentRowID);

            // 下一个
            if (type == 'next'){
                index ++;
            }

            // 最后一个
            else if (type == 'last'){
                index = rows.length -1;
            }

            // 上一个
            else if (type == 'previous'){
                index --;
            }

            // 第一个
            else if (type == 'first'){
                index = 0;
            }

            var row = xutil.getGridRowByIndex(grid, index);
            if (row == null) return null;
            var result = {
                id : row.id,
                treeID: row.treeID
            };
            return result;
        };

        /***********************************************/
        // 移动到下一行，根据Index
        /***********************************************/
        xutil.moveGridNextByIndex = function(grid, type, currentIndex){

            var index = 0;
            var rows = $(grid).datagrid('getRows');
            $('#btnFirst').linkbutton('enable');
            $('#btnPrevious').linkbutton('enable');
            $('#btnNext').linkbutton('enable');
            $('#btnLast').linkbutton('enable');

            // 下一个
            if (type == 'next'){
                index = ++ currentIndex;
                if (index >= rows.length){
                    index = rows.length -1;
                    $('#btnNext').linkbutton('disable');
                    $('#btnLast').linkbutton('disable');
                }
            }

            // 最后一个
            else if (type == 'last'){
                index = rows.length -1;
                $('#btnNext').linkbutton('disable');
                $('#btnLast').linkbutton('disable');
            }

            // 上一个
            else if (type == 'previous'){
                index = -- currentIndex;
                if(index < 0){
                    index = 0;
                    $('#btnFirst').linkbutton('disable');
                    $('#btnPrevious').linkbutton('disable');
                }
            }

            // 第一个
            else if (type == 'first'){
                index = 0;
                $('#btnFirst').linkbutton('disable');
                $('#btnPrevious').linkbutton('disable');
            }

            return index;
        };

        /***********************************************/
        // 删除Grid中所选行
        /***********************************************/
        xutil.deleteGridSelectedRow = function (grid) {

            var ids = [];
            var rows = $(grid).datagrid('getSelections');
            if (rows == null || rows.length == 0){
                return;
            }

            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }

            for (var i = 0; i < ids.length; i++) {

                rows = $(grid).datagrid('getRows');
                for (var j = 0; j < rows.length; j++) {

                    var id = rows[j].id;
                    if (id == ids[i]) {
                        var index = $(grid).datagrid('getRowIndex', rows[j]);
                        $(grid).datagrid('deleteRow', index);
                    }
                }
            }
        };

        /***********************************************/
        // 清除Grid中所有行
        /***********************************************/
        xutil.clearGridRow = function (grid) {

            var rows = $(grid).datagrid('getRows');

            for (var i = rows.length - 1; i >= 0; i--) {

                $(grid).datagrid('deleteRow', i);
            }
        };

        /***********************************************/
        // 开始编辑Grid所有行
        /***********************************************/
        xutil.beginEditGridRow = function (grid) {

            $(grid).datagrid('acceptChanges');
            var rows = $(grid).datagrid('getRows');

            for (var i = 0; i < rows.length; i++) {

                $(grid).datagrid('beginEdit', i);
            }
        };

        /***********************************************/
        // 获得Grid删除行ID
        /***********************************************/
        xutil.getGridDeletedRowID = function (grid) {

            var ids = [];
            var rows = $(grid).datagrid('getChanges', 'deleted');

            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }

            var result = ids.join(',');
            return result;

        };

        /***********************************************/
        // 获得Grid的Editor
        /***********************************************/
        xutil.getGridEditor = function(grid, index, field){
            var fieldVal = field.replace('#', '');
            var editor = $(grid).datagrid('getEditor', {index: index, field: fieldVal}).target;
            return editor;
        };

        /***********************************************/
        // 列表界面启用背景图
        /***********************************************/
        xutil.formatEnable = function (val, row) {

            var result = '';
            if (val == '1') {
                result = "<img src='" + basePath + "images/enable.png' style='height:15px;width:15px;padding:0px;' />";
            } else {
                result = "<img src='" + basePath + "images/disable.png' style='height:15px;width:15px;padding:0px;' />";
            }
            return result;
        };

        /***********************************************/
        // Ajax遮罩
        /***********************************************/
        xutil.ajaxLoading = function (prm) {

            if (prm == null || prm.length == 0) {
                $("<div class=\"datagrid-mask\"></div>").css({
                    display: "block",
                    width: "100%",
                    height: $(window).height()
                }).appendTo('body');

            } else {
                var div = prm.split(',');
                for (var i = 0; i < div.length; i++) {
                    $("<div class=\"datagrid-mask\"></div>").css({
                        display: "block",
                        width: "100%",
                        height: $(window).height()
                    }).appendTo(div[i]);
                }
            }

            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({
                display: "block",
                left: ($(document.body).outerWidth(true) - 190) / 2,
                top: ($(window).height() - 45) / 2
            });
        };

        /***********************************************/
        // Ajax取消遮罩
        /***********************************************/
        xutil.ajaxLoadEnd = function () {
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        };

        /***********************************************/
        // 格式化数量
        /***********************************************/
        xutil.fdouble = function(s, n) {
            n = n > 0 && n <= 20 ? n : 2;
            s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
            var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
            t = "";
            for (i = 0; i < l.length; i++) {
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
            }
            return t.split("").reverse().join("") + "." + r;
        };

        /***********************************************/
        // 取消格式化数量
        /***********************************************/
        xutil.rdouble = function (s) {

            if (s == undefined) return 0;
            var val = s.replace(/[^\d\.-]/g, "").replace(',','');
            if (val == '') return 0;
            return parseFloat(val);
        };

        /***********************************************/
        // 格式化金额
        /***********************************************/
        xutil.fdecimal = function(s, n) {
            n = n > 0 && n <= 20 ? n : 2;
            s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
            var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
            t = "";
            for (i = 0; i < l.length; i++) {
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
            }
            return AppConstant.RMB + t.split("").reverse().join("") + "." + r;
        };

        /***********************************************/
        // 取消格式化金额
        /***********************************************/
        xutil.rdecimal = function (s) {

            if (s == undefined) return 0;
            var val = s.replace(/[^\d\.-]/g, '').replace(AppConstant.RMB, '').replace(',','');
            if (val == '') return 0;
            return parseFloat(val);
        };

        /***********************************************/
        // 格式化Grid中的金额
        /***********************************************/
        xutil.formatDecimal = function(val, row){

            if (val == undefined || val == ''){
                return '';
            }

            return xutil.fdecimal(val, 2);
        };

        /***********************************************/
        // 格式化Grid中的数量
        /***********************************************/
        xutil.formatQty = function(val, row){

            if (val == undefined || val == ''){
                return '';
            }

            return xutil.fdouble(val, 4);
        };

        /***********************************************/
        // 是否日期  形如 (2008-07-22)
        /***********************************************/
        xutil.isDate = function (str) {
            var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if (r == null)return false;
            var d = new Date(r[1], r[3] - 1, r[4]);
            return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
        };

        /***********************************************/
        // 是否日期时间  形如 (2008-07-22 13:04:06)
        /***********************************************/
        xutil.isDateTime = function (str) {
            var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
            var r = str.match(reg);
            if (r == null)return false;
            var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
            return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
        };

        /***********************************************/
        // 是否时间 形如 (13:04:06)
        /***********************************************/
        xutil.isTime = function (str) {
            var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
            if (a == null) return false;
            if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
                return false
            }
            return true;
        };

        /***********************************************/
        // 取得当月第一天
        /***********************************************/
        xutil.getFirstDayOfMonth = function(){
            var val = new Date().format("yyyy-MM");
            var result = val + '-01';
            return result;
        };

        return xutil;
    }
};