package com.lhcy.core.bo;

public class BillStatusConvert {

    public static String getName(int status) {

        String result = "";

        switch (status) {

            case BillStatus.addnew:
                result = "新增";
                break;

            case BillStatus.tempsave:
                result = "暂存";
                break;

            case BillStatus.save :
                result = "保存";
                break;

            case BillStatus.submit :
                result = "提交";
                break;

            case BillStatus.audit :
                result = "审核";
                break;

            case BillStatus.redclash :
                result = "红冲";
                break;

            default:
                result = "未指定";
        }

        return result;
    }

}
