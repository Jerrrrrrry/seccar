package com.lhcy.core.bo;

public class SysConstant {
    public static final int DB_FK_ERROR = 1451;
    public static final String M_PK_ERROR = "此数据关联其他内容，不能删除！";
    public static final String M_EXIST_ERROR = "已经存在，不能重复！";
    public static final String M_NOTSOLD_ERROR = "未销售，不能结算！";
    public static final String M_SETTLEMENT_ERROR = "已结算/已删除，不能删除！";
    public static final String M_NO_DATA_FIND = "此数据已经不存在！";
    public static final String M_NO_SELECT_TREE = "没有选择分类！";
    public static final String M_NO_USER_DEFINED = "不是用户自定义，不能删除！";
    public static final String M_NO_LOGIN = "没有登录";
    public static final String M_MULT_BANK_ACCOUNT = "现金银行多账户";
    public static final String M_UPDATE_COUNT_ERROR = "此数据已经被其他操作更新，请刷新后重试！";
    public static final String M_STATUS_CHECK_SUBMIT = "只有[保存]状态的数据，才能提交！";
    public static final String M_STATUS_CHECK_DELETE = "只有[保存]状态的数据，才能删除！";
    public static final String M_STATUS_CHECK_REDCLASH = "只有[审核]状态的数据，才能红冲！";
    public static final int ENTRY_DEFAULT_COUNT = 100;
    public static final int CREATE_USER_ID = 16394;   // 单据创建人
    public static final String WIPE_ZERO_ACCOUNT = "5501.05.03";   // 抹零科目 正式：5501.05.03      测试：5501.03.16.02.02
    public static final String QUOTA_ACCOUNT = "5501.05.02";  // 定额优惠 正式：5501.05.02       测试：5501.03.16.02.01

}