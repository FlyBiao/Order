package com.cesaas.android.order.adapter.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.order.OrderCenterBean;
import com.cesaas.android.order.listener.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Author FGB
 * Description 订单中心Adapter
 * Created 2017/4/9 23:21
 * Version 1.0
 */
public class OrderCenterAdapter extends SwipeMenuAdapter<OrderCenterAdapter.DefaultViewHolder> {

    public static Context mContext;
    private OnItemClickListener mOnItemClickListener;

    private List<OrderCenterBean> mOrderCenterBeanList;

    public OrderCenterAdapter(Context mContext,List<OrderCenterBean> mOrderCenterBeanList){
        this.mContext=mContext;
        this.mOrderCenterBeanList=mOrderCenterBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_center, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mOrderCenterBeanList.get(position).getRetailId()
        ,mOrderCenterBeanList.get(position).getPayAmount()
        ,mOrderCenterBeanList.get(position).getCreateTime()
        ,mOrderCenterBeanList.get(position).getRetailFrom()
        ,mOrderCenterBeanList.get(position).getRetailCheck());

        holder.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mOrderCenterBeanList == null ? 0 : mOrderCenterBeanList.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_order_id,tv_accounts_pay_amount,tv_pay_date,tv_order_retailfrom,tv_pay_status;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.tv_order_id= (TextView) itemView.findViewById(R.id.tv_order_id);
            this.tv_accounts_pay_amount= (TextView) itemView.findViewById(R.id.tv_accounts_pay_amount);
            this.tv_pay_date= (TextView) itemView.findViewById(R.id.tv_pay_date);
            this.tv_order_retailfrom= (TextView) itemView.findViewById(R.id.tv_order_retailfrom);
            this.tv_pay_status= (TextView) itemView.findViewById(R.id.tv_pay_status);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(String id,double amount,String date,int retailfrom,int status) {
            this.tv_order_id.setText("("+id+")");
            this.tv_accounts_pay_amount.setText(amount+"");
            this.tv_pay_date.setText(date);
            if(retailfrom==1){
                this.tv_order_retailfrom.setText("POS订单");
            }else{
                this.tv_order_retailfrom.setText("PC订单");
            }

            if(status==1){
                this.tv_pay_status.setText("支付成功");
                this.tv_pay_status.setTextColor(mContext.getResources().getColor(R.color.forestgreen));
            }else{
                this.tv_pay_status.setText("未支付");
                this.tv_pay_status.setTextColor(mContext.getResources().getColor(R.color.gray_text));
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
