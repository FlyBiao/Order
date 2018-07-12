package com.cesaas.android.order.adapter.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.order.ResultWaitPayOrderDetailBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 17:02
 * Version 1.0
 */
public class WaitPayOrderAdapter extends SwipeMenuAdapter<WaitPayOrderAdapter.DefaultViewHolder> {

    private List<ResultWaitPayOrderDetailBean.TModelEntity.ItemEntity> mItemEntityList;

    public WaitPayOrderAdapter(List<ResultWaitPayOrderDetailBean.TModelEntity.ItemEntity> mItemEntityList){
        this.mItemEntityList=mItemEntityList;
    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wait_order_detail, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mItemEntityList.get(position).getStyleName(),
                mItemEntityList.get(position).getQuantity(),
                mItemEntityList.get(position).getSellPrice(),
                mItemEntityList.get(position).getPayMent());

    }

    @Override
    public int getItemCount() {
        return mItemEntityList == null ? 0 : mItemEntityList.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder{
        TextView tv_order_name,tv_order_count,tv_order_sale_price,tv_order_pay_price;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            this.tv_order_name= (TextView) itemView.findViewById(R.id.tv_order_name);
            this.tv_order_count= (TextView) itemView.findViewById(R.id.tv_order_count);
            this.tv_order_sale_price= (TextView) itemView.findViewById(R.id.tv_order_sale_price);
            this.tv_order_pay_price= (TextView) itemView.findViewById(R.id.tv_order_pay_price);
        }

        public void setData(String styleName,int quantity,double sellPrice,double payMent) {
            this.tv_order_name.setText(styleName);
            this.tv_order_count.setText(quantity+"");
            this.tv_order_sale_price.setText(sellPrice+"");
            this.tv_order_pay_price.setText(payMent+"");
        }
    }

}
