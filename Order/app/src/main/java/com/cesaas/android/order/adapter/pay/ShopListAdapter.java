package com.cesaas.android.order.adapter.pay;

import android.content.Context;
import android.widget.ImageView;

import com.cesaas.android.order.R;
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

public class ShopListAdapter  extends BaseQuickAdapter<ShopBean, BaseViewHolder> {

    private List<ShopBean> mData;
    private Context mContext;

    public ShopListAdapter(List<ShopBean> mData, Context mContext) {
        super( R.layout.item_shop,mData);
        this.mData=mData;
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean item) {
        helper.setText(R.id.tv_shop_name,item.getName());
        helper.setText(R.id.tv_create_time,item.getCreateTime());
        helper.setText(R.id.tv_shop_encode,item.getEnCode());
        if(item.getModel()==1){
            helper.setText(R.id.tv_shop_model,"WPOS-3");
        }else{
            helper.setText(R.id.tv_shop_model,"WPOS");
        }
    }
}