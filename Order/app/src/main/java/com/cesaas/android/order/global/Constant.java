package com.cesaas.android.order.global;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 21:09
 * Version 1.0
 */
public class Constant {

    public static final String TAG = "BugInfos";
    /** 日志开关 */
    public static boolean DEBUG = false;

    /** UI设计的基准宽度 */
    public static int UI_WIDTH = 720;

    /** UI设计的基准高度 */
    public static int UI_HEIGHT = 1280;

    /** UI设计的密度 */
    public static int UI_DENSITY = 2;
    //
    public static int REQUEST_CONTACT=20;

    //扫描订单Code
    public static String SCAN_ORDER_CODE="100";
    public static final int RESULT_CODE = 101;


//	public static String IP="http://120.76.40.118/EpApi/";//
//	public static String Express_IP="http://120.76.40.118/OpenApi/";//快递物流

    /**
     * 测试环境
     */
    public static String MENU_POWER_IP="http://112.74.135.25/Dowlond/";//菜单权限
    public static String Express_IP="http://112.74.135.25/OpenApi/";//快递物流ip
    public static String IP = "http://m.cesaas.com/EpApi/";//

    public static String Test_IP = "http://192.168.1.194/EpApi/";//本地测试
//	public static String Express_IP="http://192.168.1.163/OpenApi/";//快递物流ip


    /** 列表一次请求的数量 */
    public static String MAXROW = "30";

    /** 图片处理：裁剪 */
    public static final int CUTIMG = 0;
    /** 图片处理：缩放 */
    public static final int SCALEIMG = 1;


    /** 标示是否登录 */
    public static String SPF_ISLOGIN = "isLogin";
    /** 帐号（手机号） */
    public static String SPF_ACCOUNT = "account";
    // 昵称
    public static String SPF_NICK = "nick";
    // 用户头像
    public static String SPF_UIMG = "img";
    // 积分
    public static String SPF_POINT = "point";
    public static String VIP_ID = "vipId";
    // 支付密码
    public static String SPF_ISPAYPWD = "ispay_pwd";
    /** 时间参数 */
    public static String SPF_TIME = "t_time";
    /** 请求的TOKEN */
    public static String SPF_TOKEN= "token";
    /** 区域编码 */
    public static String SPF_ACODE = "area_code";
    /** 是否推送 */
    public static String SPF_MSG_PUSH = "msg_push";


    // 商家版API
    public static class API {

        public static final String BASE_URL = "http://112.74.135.25/EpApi/User/";
        // public static final String
        // BASE_URL="http://192.168.1.113/EpApi/User/";
        // 用户登录
        public static final String USER_LOGIN = BASE_URL + "Sw/Account/Login";
        // 个人信息
        public static final String USER_DETAIL = BASE_URL + "Sw/User/Detail";
        // 上传用户头像
        public static final String USER_UPLOAD_HEAD_ICON = BASE_URL
                + "Sw/User/UploadHeadIcon";
        // 用户修改昵称
        public static final String USER_SET_NICK = BASE_URL
                + "Sw/User/ModifyName";
        // 修改用户密码
        public static final String USER_SET_PWD = BASE_URL
                + "Sw/Password/Reset";

        // 粉丝List
        public static final String USER_FANS = BASE_URL + "Sw/Fans/GetList";

    }


}
