package com.cesaas.android.order.net.order;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.ResultOrderCenterBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 23:05
 * Version 1.0
 */
public class CompleteOrderNet extends BaseNet{
    public CompleteOrderNet(Context context) {
        super(context, true);
        this.uri= Urls.ORDER_CENTER;
    }

    public void setData(String start_date,String end_date,int page){
        try {
            data.put("PageIndex", page);
            data.put("PageSize", 30);
            data.put("start_date", start_date);//开始时间
            data.put("end_date", end_date);//结束时间
//            data.put("Sort",dateArray);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }


    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        ResultOrderCenterBean lbean= JsonUtils.fromJson(rJson,ResultOrderCenterBean.class);
        EventBus.getDefault().post(lbean);

    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
        Log.i(Constant.TAG, "CheckAccountsNet===" + e + "********=err==" + err);
    }


}
