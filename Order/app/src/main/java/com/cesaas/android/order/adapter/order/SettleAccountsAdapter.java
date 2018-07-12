package com.cesaas.android.order.adapter.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.order.SettleAccountsList;
import com.cesaas.android.order.listener.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 20:20
 * Version 1.0
 */
public class SettleAccountsAdapter extends SwipeMenuAdapter<SettleAccountsAdapter.DefaultViewHolder> {

    private List<SettleAccountsList> settleAccountsList;

    public SettleAccountsAdapter(List<SettleAccountsList> settleAccountsList){
        this.settleAccountsList=settleAccountsList;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settle_accounts, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(settleAccountsList.get(position).getPayType(),settleAccountsList.get(position).getOrderCount(),settleAccountsList.get(position).getPayMent());
    }

    @Override
    public int getItemCount() {
        return settleAccountsList == null ? 0 : settleAccountsList.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_pay_way,tv_refund_amount,tv_pay_order_count,tv_cashier_pay_accounts,tv_payment;
        ImageView iv_settle_title;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.iv_settle_title= (ImageView) itemView.findViewById(R.id.iv_settle_title);
            this.tv_pay_way= (TextView) itemView.findViewById(R.id.tv_pay_way);
            this.tv_refund_amount= (TextView) itemView.findViewById(R.id.tv_refund_amount);
            this.tv_pay_order_count= (TextView) itemView.findViewById(R.id.tv_pay_order_count);
            this.tv_cashier_pay_accounts= (TextView) itemView.findViewById(R.id.tv_cashier_pay_accounts);
            this.tv_payment= (TextView) itemView.findViewById(R.id.tv_payment);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(int PayType,int orderCount,double PayMent) {
            this.tv_pay_order_count.setText(orderCount+"单");
            this.tv_cashier_pay_accounts.setText(PayMent+"");
            this.tv_payment.setText(PayMent+"");
            switch (PayType){
                case 2:
                    this.iv_settle_title.setImageResource(R.mipmap.weixin_pay);
                    this.tv_pay_way.setText("微信支付");
                    if(PayType==13){//退款
                        this.tv_refund_amount.setText(PayMent+"");
                    }else{
                        this.tv_refund_amount.setText("暂无退款");
                    }
                    break;
                case 3:
                    this.iv_settle_title.setImageResource(R.mipmap.weixin_pay);
                    this.tv_pay_way.setText("支付宝");
                    if(PayType==13){//退款
                        this.tv_refund_amount.setText(PayMent+"");
                    }else{
                        this.tv_refund_amount.setText("暂无退款");
                    }
                    break;
                case 4:
                    this.iv_settle_title.setImageResource(R.mipmap.weixin_pay);
                    this.tv_pay_way.setText("银联");
                    if(PayType==13){//退款
                        this.tv_refund_amount.setText(PayMent+"");
                    }else{
                        this.tv_refund_amount.setText("暂无退款");
                    }
                    break;
                case 5:
                    this.iv_settle_title.setImageResource(R.mipmap.weixin_pay);
                    this.tv_pay_way.setText("现金");
                    if(PayType==13){//退款
                        this.tv_refund_amount.setText(PayMent+"");
                    }else{
                        this.tv_refund_amount.setText("暂无退款");
                    }
                    break;
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
