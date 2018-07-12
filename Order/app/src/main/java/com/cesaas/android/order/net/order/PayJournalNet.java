package com.cesaas.android.order.net.order;

import android.content.Context;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.PayOrderDetailBeanListBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.event.EventBase;

import org.greenrobot.eventbus.EventBus;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 18:11
 * Version 1.0
 */
public class PayJournalNet extends BaseNet {
    public PayJournalNet(Context context) {
        super(context, true);
        this.uri= Urls.PAY_JOURNAL;
    }

    public void setData(String orderId){
        try{
            data.put("RetailId",orderId);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

        }catch (Exception e){
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        PayOrderDetailBeanListBean bean= JsonUtils.fromJson(rJson,PayOrderDetailBeanListBean.class);
        EventBus.getDefault().post(bean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
    }
}
