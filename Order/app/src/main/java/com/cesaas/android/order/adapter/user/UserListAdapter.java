package com.cesaas.android.order.adapter.user;

import android.content.Context;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.pay.ShopBean;
import com.cesaas.android.order.bean.pay.UserBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created at 2018/2/2 16:38
 * Version 1.0
 */

public class UserListAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

    private List<UserBean> mData;
    private Context mContext;

    public UserListAdapter(List<UserBean> mData, Context mContext) {
        super( R.layout.item_user,mData);
        this.mData=mData;
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.tv_shop_name,item.getShopName());
        helper.setText(R.id.tv_mobile,item.getMobile());

    }
}