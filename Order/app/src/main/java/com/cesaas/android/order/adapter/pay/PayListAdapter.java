package com.cesaas.android.order.adapter.pay;

import android.content.Context;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.pay.PayListBean;
import com.cesaas.android.order.bean.pay.ShopBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created at 2018/2/2 16:38
 * Version 1.0
 */

public class PayListAdapter extends BaseQuickAdapter<PayListBean, BaseViewHolder> {

    private List<PayListBean> mData;
    private Context mContext;

    public PayListAdapter(List<PayListBean> mData, Context mContext) {
        super( R.layout.item_pay,mData);
        this.mData=mData;
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayListBean item) {
        helper.setText(R.id.tv_order_id,"流水号("+item.getPayId()+")");
        helper.setText(R.id.tv_pay_amount,item.getPayment()+"");
        helper.setText(R.id.tv_pay_date,item.getPayDate());
        helper.setText(R.id.tv_pay_no,item.getPayNo());
        switch (item.getPayType()){
            case 2:
                helper.setText(R.id.tv_pay_type,"微信支付");
                break;
            case 3:
                helper.setText(R.id.tv_pay_type,"支付宝支付");
                break;
            case 4:
                helper.setText(R.id.tv_pay_type,"银联支付");
                break;
            case 5:
                helper.setText(R.id.tv_pay_type,"现金支付");
                break;
        }
    }
}