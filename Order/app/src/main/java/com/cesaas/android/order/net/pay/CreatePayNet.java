package com.cesaas.android.order.net.pay;

import android.content.Context;
import android.util.Log;

import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.pay.ResultCreatePayBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/**
 * Author FGB
 * Description 创建支付流水
 * Created at 2018/1/26 17:42
 * Version 1.0
 */

public class CreatePayNet extends BaseNet {
    public CreatePayNet(Context context) {
        super(context, true);
        this.uri="Pos/Sw/Retail/CreatePay";
    }

    public void setData(String SheetId,double Payment,int PayType,String PayNo,String TraceAudit,String BankNo,String Remark,String VoucherRecord,String TId,String BankName,int SheetCategory,int CardCategory,String enCode,String CreateTime){
        try {
            data.put("SheetId",SheetId);//业务ID
            data.put("Payment",Payment);//支付金额
            data.put("CreateTime",CreateTime);//支付金额
            data.put("PayType",PayType);//支付类型
            data.put("CurrencyType",0);//币种【默认0】
            data.put("PayNo",PayNo);//支付流水号
            data.put("TraceAudit",TraceAudit);//支付参考号
            data.put("BankNo",BankNo);//银行卡号
            data.put("Remark",Remark);//备注
            data.put("EnCode", enCode);//设备EN号AbPrefsUtil.getInstance().getString("enCode")
            data.put("VoucherRecord",VoucherRecord);//凭证记录
            data.put("TId",TId);//店铺ID
            data.put("SheetCategory",SheetCategory);//0, 零售单 1, 储值 2,独立收银
            data.put("BankName",BankName);//交易银行名称
            data.put("CardCategory",CardCategory);//卡类型：1借记卡 2信用卡 3 贷记卡【微信，支付宝，现金100】
            data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPostNet(); // 开始请求网络
    }

    @Override
    protected void mSuccess(String rJson) {
        super.mSuccess(rJson);
        Log.i("test","支付流水:"+rJson);
        Gson gson=new Gson();
        ResultCreatePayBean lbean = gson.fromJson(rJson, ResultCreatePayBean.class);
        EventBus.getDefault().post(lbean);
    }

    @Override
    protected void mFail(HttpException e, String err) {
        super.mFail(e, err);
        Log.i(Constant.TAG,"**=HttpException="+e+"..=err="+err);
    }

}
