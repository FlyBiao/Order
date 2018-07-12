package com.cesaas.android.order.bean.order;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 20:15
 * Version 1.0
 */
public class SettleAccountsList implements Serializable {

    private int PayBizType;//6:正常 13：退款
    private double PayMent;//支付金额
    private int PayType;//支付类型
    private int OrderCount;//支付订单总数
    private int ShopId;

    public int getPayBizType() {
        return PayBizType;
    }

    public void setPayBizType(int payBizType) {
        PayBizType = payBizType;
    }

    public double getPayMent() {
        return PayMent;
    }

    public void setPayMent(double payMent) {
        PayMent = payMent;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int shopId) {
        ShopId = shopId;
    }

    public int getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(int orderCount) {
        OrderCount = orderCount;
    }
}
