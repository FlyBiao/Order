package com.cesaas.android.order.bean.user;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created at 2018/2/8 16:23
 * Version 1.0
 */

public class UserBean implements Serializable {
    public String Mobile;//手机号
    public String Icon;//用户头像
    public String Name;//用户名称
    public String NickName;//用户昵称
    public String Sex;//性别
    public String ShopId;//店铺ID
    public String ShopName;//店铺名称
    public String Siganature;//签名
    public String ShopMobile;//店铺电话
    public String TypeName;//用户身份：店员，店长
    public String TypeId;//1：店长，2：店员
    public int VipId;
    public String Ticket;//生成拉粉二维码用票
    public String ImToken;
    public String CounselorId;//顾问ID
    public String GzNo;//公众号GzNo
    public int tId;

    public String ShopPostCode;//商品提交码
    public String ShopProvince;//商品所在省
    public String ShopAddress;//商品所在地址
    public String ShopArea;//商品所在区域
    public String ShopCity;//商品所在城市


    public String BrandName;
    public int BrandId;
}
