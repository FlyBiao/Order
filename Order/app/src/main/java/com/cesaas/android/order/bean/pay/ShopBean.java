package com.cesaas.android.order.bean.pay;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created at 2018/2/2 16:38
 * Version 1.0
 */

public class ShopBean implements Serializable {

    private String Name;
    private String EnCode;
    private String CreateTime;
    private int Model;//机型
    private String PN ;//设备编号
    private String Mcode ;//店铺Mcode

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEnCode() {
        return EnCode;
    }

    public void setEnCode(String enCode) {
        EnCode = enCode;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getModel() {
        return Model;
    }

    public void setModel(int model) {
        Model = model;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getMcode() {
        return Mcode;
    }

    public void setMcode(String mcode) {
        Mcode = mcode;
    }
}
