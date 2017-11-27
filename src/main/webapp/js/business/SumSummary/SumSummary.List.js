var SumSummaryList = {

    getInstance: function (basePath) {
        var list = {};
        var xutil = XUtil.getInstance(basePath); 
        
        //指定列求和
         list.compute = function (colName) {
             var rows = $('#list').datagrid('getRows');
//         	alert(rows);
             var total = 0;
             for (var i = 0; i < rows.length; i++) {
                 total += parseFloat(rows[i][colName]);
             }

//         	alert(total);
             return total;
         }
         list.computeSold = function (colName) {
             var rows = $('#soldlist').datagrid('getRows');
//         	alert(rows);
             var total = 0;
             for (var i = 0; i < rows.length; i++) {
                 total += parseFloat(rows[i][colName]);
             }

//         	alert(total);
             return total;
         }
         list.computeCost = function (colName) {
             var rows = $('#interestCostlist').datagrid('getRows');
//         	alert(rows);
             var total = 0;
             for (var i = 0; i < rows.length; i++) {
                 total += parseFloat(rows[i][colName]);
             }

//         	alert(total);
             return total;
         }
               
        /***********************************************/
        // 打开过滤界面
        /***********************************************/
        list.openfilter = function () {
        	//list.clearFilter();
            $('#dlg_filter').dialog('open');
        };
        /***********************************************/
        // 过滤
        /***********************************************/
        list.filter = function () {
        	$('#dlg_filter').dialog('close');
            /*var filterField = "filter";
            var filterisdeleted = $('#filterisdeleted').prop('checked') ? 1 : 0;
            var filterissold = $('#filterissold').combobox('getValue');
            var filtersettlement = $('#filtersettlement').combobox('getValue');
            var filterlicenseno = $('#filterlicenseno').textbox('getValue');
            var filtercardescription = $('#filtercardescription').textbox('getValue');
            var filtertradername = $('#filtertradername').textbox('getValue');
            var filtercustomer = $('#filtercustomer').textbox('getValue');
            var filtervehicletype = $('#filtervehicletype').combobox('getValue');*/
            var purchasestart = $('#purchasestart').datebox('getValue');
            var purchaseend = $('#purchaseend').datebox('getValue');
           /* var soldstart = $('#soldstart').datebox('getValue');
            var soldend = $('#soldend').datebox('getValue');*/

            var prm = {
            		/*filterField: filterField,
            		filterlicenseno: filterlicenseno, 
            		filtercardescription: filtercardescription, 
            		filtertradername: filtertradername, 
            		filtercustomer: filtercustomer, 
            		filtervehicletype: filtervehicletype, */
            		purchasestart: purchasestart,
            		purchaseend: purchaseend/*,
            		soldstart: soldstart,
            		soldend: soldend,
            		filterisdeleted: filterisdeleted,
            		filterissold: filterissold,
            		filtersettlement: filtersettlement*/
            		};
            $('#list').datagrid('clearSelections');
            $('#list').datagrid({queryParams: prm});
//            xutil.focus('#filterValue');
        };

        /***********************************************/
        // 刷新
        /***********************************************/
        list.refreshStock = function () {
            $('#stocklist').datagrid('reload');
        };
        list.refreshSold = function () {
            $('#soldlist').datagrid('reload');
        };
        list.refreshLoan = function () {
            $('#loanlist').datagrid('reload');
        };
        list.refreshInterestCost = function () {
            $('#interestCostlist').datagrid('reload');
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
        	$('#filterisdeleted').prop('checked', false);
        	$('#filterissold').combobox('setValue','');
        	$('#filtersettlement').combobox('setValue','');
            $('#filterlicenseno').textbox('clear');
            $('#filtercardescription').textbox('clear');
            $('#filtertradername').textbox('clear');
            $('#filtercustomer').textbox('clear');
        	$('#filtervehicletype').combobox('setValue','');
            $('#purchasestart').datebox('clear');
            $('#purchaseend').datebox('clear');
            $('#soldstart').datebox('clear');
            $('#soldend').datebox('clear');
            xutil.focus('#filterlicenseno');
        };
        /***********************************************/
        // 删除
        /***********************************************/
        list.del = function () {

            if (!xutil.isGridSelected('#list')) return;
            var vehicleid = xutil.getGridSelectedVehicleID('#list');
//            alert(vehicleid);
            xutil.ajaxLoading('body');
            $.ajax({
                type: 'post',
                url: basePath + 'SumSummaryAction.do?m=delete',
                data: {vehicleid: vehicleid},
                success: function (data) {

                    if (data == null || data.length == 0) return;
                    var vo = data[0];

                    if (vo.status == 'ok') {
                        $('#list').datagrid('reload');
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
        // 借贷差额颜色
        /***********************************************/
        list.diffFormat = function(val, row){
            var result = '';
            if (val != '¥0.0000') {
                result = "<span style='color: red; font-weight: bold'>" + val + "</span>";
            }else{
                result = val;
            }

            return result;
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

       

        return list;
    }
};