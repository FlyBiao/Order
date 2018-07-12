package com.cesaas.android.order.net.order;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.ResultOrderCenterBean;
import com.cesaas.android.order.bean.order.ResultWaitPayOrderDetailBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description 待支付订单
 * Created 2017/4/9 23:05
 * Version 1.0
 */
public class WitPayOrderNet extends BaseNet{
    public WitPayOrderNet(Context context) {
        super(context, true);
        this.uri= Urls.WAIT_PAY_ORDER;
    }

    public void setData(String orderId){
        try {
            data.put("RetailId",orderId);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }


    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        ResultWaitPayOrderDetailBean bean=JsonUtils.fromJson(rJson,ResultWaitPayOrderDetailBean.class);
        EventBus.getDefault().post(bean);

    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
        Log.i(Constant.TAG, "CheckAccountsNet===" + e + "********=err==" + err);
    }


}
