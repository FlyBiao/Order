package com.cesaas.android.order.net.user;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.user.ResultUserBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description
 * Created at 2018/2/8 16:22
 * Version 1.0
 */

public class UserInfoNet extends BaseNet {

    public UserInfoNet(Context context) {
        super(context,true);
        this.uri="User/Sw/User/Detail";
    }

    public void setData() {
        try {
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPostNet(); // 开始请求网络
    }

    public void setData(String UserTicket) {
        try {
            data.put("UserTicket", UserTicket);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPostNet(); // 开始请求网络
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        Log.i("test",rJson);
        Gson gson = new Gson();
        ResultUserBean lbean = gson.fromJson(rJson, ResultUserBean.class);
        EventBus.getDefault().post(lbean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
    }

}
