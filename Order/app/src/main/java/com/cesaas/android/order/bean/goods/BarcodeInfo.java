package com.cesaas.android.order.bean.goods;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created 2017/4/15 15:42
 * Version 1.0
 */
public class BarcodeInfo implements Serializable {
    private String Name1;//颜色

    private String Value1;//颜色Value

    private String Name2;//尺码

    private String Value2;// 尺码值

    private String Name3;

    private String Value3;


    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
    }

    public String getName3() {
        return Name3;
    }

    public void setName3(String name3) {
        Name3 = name3;
    }

    public String getValue3() {
        return Value3;
    }

    public void setValue3(String value3) {
        Value3 = value3;
    }
}
