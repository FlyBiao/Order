package com.cesaas.android.order.net.pay;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.pay.PayCallbackBean;
import com.cesaas.android.order.bean.pay.ResultPayListBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Author FGB
 * Description 创建订单支付
 * Created at 2018/1/26 17:46
 * Version 1.0
 */

public class PayListNet extends BaseNet {

    public PayListNet(Context context) {
        super(context, true);
        this.uri = "Pos/Sw/Retail/PayListLog";
    }

    public void setData(String start_date,String end_date,int page){
        try {
            data.put("PageIndex", page);
            data.put("PageSize", 50);
            data.put("StartDate", start_date);//开始时间
            data.put("EndDate", end_date);//结束时间
//            data.put("Sort",dateArray);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }


    public void setData(String start_date,String end_date,int page,JSONArray dateArray){
        try {
            data.put("PageIndex", page);
            data.put("PageSize", 50);
            data.put("StartDate", start_date);//开始时间
            data.put("EndDate", end_date);//结束时间
            data.put("Sort",dateArray);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }

    public void setData(int page,int refundStatus){
        try {
            data.put("PageIndex", page);
            data.put("PageSize", 50);
            data.put("refundStatus", refundStatus);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPostNet(); // 开始请求网络
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        Gson gson=new Gson();
        ResultPayListBean lbean=gson.fromJson(rJson,ResultPayListBean.class);
        EventBus.getDefault().post(lbean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
        Log.i(Constant.TAG, "CheckAccountsNet===" + e + "********=err==" + err);
    }

}
