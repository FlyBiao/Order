package com.cesaas.android.order.net.pay;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.pay.PayCallbackBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description 创建订单支付
 * Created at 2018/1/26 17:46
 * Version 1.0
 */

public class PayFormStoreNet extends BaseNet {

    public PayFormStoreNet(Context context) {
        super(context, true);
        this.uri="Pos/Sw/Retail/PayFromStore";
    }

    public void setData(String RetrievalReferenceNumber,String TraceAuditNumber,double ConsumeAmount,String OrderId,int PayType,int IsPractical,String enCode){
        try {
            data.put("RetrievalReferenceNumber",RetrievalReferenceNumber);//参考号
            data.put("TraceAuditNumber",TraceAuditNumber);//凭证号
            data.put("ConsumeAmount",ConsumeAmount);//消费金额
            data.put("RetailId",OrderId);//支付订单号
            data.put("PayType",PayType);//支付类型
            data.put("IsPractical",IsPractical);//是否实销
            data.put("EnCode",enCode);//设备EN号
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPostNet(); // 开始请求网络
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        Log.i("test", "支付回调:"+rJson);
        PayCallbackBean bean= JsonUtils.fromJson(rJson,PayCallbackBean.class);
        EventBus.getDefault().post(bean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
        Log.i(Constant.TAG,"**=HttpException="+e+"..=err="+err);
    }

}
