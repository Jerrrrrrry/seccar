
$(function () {

    //禁止后退键 作用于Firefox、Opera
    document.onkeypress=banBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown=banBackSpace;
});

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function banBackSpace(e){
    var ev = e || window.event;//获取event对象
    var obj = ev.target || ev.srcElement;//获取事件源

    var t = obj.type || obj.getAttribute('type');//获取事件源类型

    //获取作为判断条件的事件类型
    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');
    //处理null值情况
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;
    vEnabled = (vEnabled == null) ? true : vEnabled;

    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readonly属性为true或enabled属性为false的，则退格键失效
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")
    && (vReadOnly == true || vReadOnly == 'readonly' || vEnabled != true)) ? true : false;

    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true : false;

    //判断
    if(flag2){
        return false;
    }
    if(flag1){
        return false;
    }
}

// 日期格式化
Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    };

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
};
