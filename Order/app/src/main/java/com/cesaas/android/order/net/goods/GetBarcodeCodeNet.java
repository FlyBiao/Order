package com.cesaas.android.order.net.goods;

import android.content.Context;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.goods.ResultCodeGoodsBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;

/**
 * Author FGB
 * Description
 * Created 2017/4/15 15:29
 * Version 1.0
 */
public class GetBarcodeCodeNet extends BaseNet {
    public GetBarcodeCodeNet(Context context) {
        super(context, true);
        this.uri= Urls.GET_BARCODE_CODE;
    }

    public void setData(String BarcodeCode){
        try{
            data.put("BarcodeCode",BarcodeCode);
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

        }catch (Exception e){
            e.printStackTrace();
        }
        mPostNet();
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        ResultCodeGoodsBean bean= JsonUtils.fromJson(rJson,ResultCodeGoodsBean.class);
        EventBus.getDefault().post(bean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
    }
}
