package com.cesaas.android.order.bean.pay;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created at 2018/2/8 17:32
 * Version 1.0
 */

public class UserBean implements Serializable {

    private String ShopName;
    private String Mobile;
    private String Pwd;

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }
}
