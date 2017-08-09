$(function () {

    var xutil = XUtil.getInstance();
    $.extend($.fn.validatebox.defaults.rules, {
        zero: {
            validator: function (value) {

                var temp = value.replace(AppConstant.RMB, '');
                return (temp != 0);
            },
            message: '不能为0'
        },
        date: {
            validator: function (value) {

                return xutil.isDate(value);
            },
            message: '日期格式错误'
        }
    });

    //$.extend($.fn.datagrid.methods, {
    //    editCell: function(jq,param){
    //        return jq.each(function(){
    //            var opts = $(this).datagrid('options');
    //            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
    //            for(var i=0; i<fields.length; i++){
    //                var col = $(this).datagrid('getColumnOption', fields[i]);
    //                col.editor1 = col.editor;
    //                if (fields[i] != param.field){
    //                    col.editor = null;
    //                }
    //            }
    //            $(this).datagrid('beginEdit', param.index);
    //            for(var i=0; i<fields.length; i++){
    //                var col = $(this).datagrid('getColumnOption', fields[i]);
    //                col.editor = col.editor1;
    //            }
    //        });
    //    }
    //});
});
