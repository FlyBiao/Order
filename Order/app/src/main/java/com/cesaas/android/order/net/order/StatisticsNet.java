package com.cesaas.android.order.net.order;

import android.content.Context;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.SettleAccountsBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;

/**
 * Author FGB
 * Description 结算对账Net
 * Created 2017/4/10 20:11
 * Version 1.0
 */
public class StatisticsNet extends BaseNet{
    public StatisticsNet(Context context) {
        super(context, true);
        this.uri= Urls.POS_STATISTICS;
    }

    public void setData(String start_date ,String end_date ){
        try{

            data.put("start_date", start_date);
            data.put("end_date",end_date);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

        }catch (Exception e){
            e.printStackTrace();
        }
        mPostNet();
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        SettleAccountsBean bean= JsonUtils.fromJson(rJson,SettleAccountsBean.class);
        EventBus.getDefault().post(bean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
    }
}
