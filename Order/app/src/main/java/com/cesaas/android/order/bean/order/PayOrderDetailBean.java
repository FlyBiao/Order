package com.cesaas.android.order.bean.order;

import java.io.Serializable;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 18:15
 * Version 1.0
 */
public class PayOrderDetailBean implements Serializable {
    private double ConsumeAmount;
    private String CreateTime;
    private String RetailId;
    private int PayType;
    private String TraceAuditNumber;

    public double getConsumeAmount() {
        return ConsumeAmount;
    }

    public void setConsumeAmount(double consumeAmount) {
        ConsumeAmount = consumeAmount;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getRetailId() {
        return RetailId;
    }

    public void setRetailId(String retailId) {
        RetailId = retailId;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public String getTraceAuditNumber() {
        return TraceAuditNumber;
    }

    public void setTraceAuditNumber(String traceAuditNumber) {
        TraceAuditNumber = traceAuditNumber;
    }
}
