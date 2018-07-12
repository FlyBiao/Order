package com.cesaas.android.order.net.user;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.MD5;
import com.cesaas.android.order.utils.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description 用户登录Net
 * Created 2017/4/9 21:29
 * Version 1.0
 */
public class LoginNet extends BaseNet {

    public LoginNet(Context context, String user, String pwd) {
        super(context, true);
        this.uri = Urls.LOGIN;
        try {
            data.put("account", user.replace(" ", ""));
            data.put("password", new MD5().toMD5(pwd));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        Gson gson = new Gson();
        LoginBean lbean = gson.fromJson(rJson, LoginBean.class);
        if (lbean.isSuccess())
            //通过EventBus发布一个事件
            EventBus.getDefault().post(lbean);
        else
            ToastUtils.show(mContext, lbean.getMessage(), ToastUtils.CENTER);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);

        Log.i(Constant.TAG, "HttpException==="+e+"---ERROR:="+err);
    }
}
