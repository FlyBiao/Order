package com.cesaas.android.order.adapter.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.order.PayOrderDetailBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 18:08
 * Version 1.0
 */
public class AccountDetailAdapter  extends SwipeMenuAdapter<AccountDetailAdapter.DefaultViewHolder> {

    private List<PayOrderDetailBean> mPayOrderDetailBeanList;
    public static Context mContext;

    public AccountDetailAdapter(List<PayOrderDetailBean> mPayOrderDetailBeanList, Context mContext){
        this.mPayOrderDetailBeanList=mPayOrderDetailBeanList;
        this.mContext=mContext;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_check_account_detail, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mPayOrderDetailBeanList.get(position).getRetailId(),mPayOrderDetailBeanList.get(position).getTraceAuditNumber()
        ,mPayOrderDetailBeanList.get(position).getCreateTime(),mPayOrderDetailBeanList.get(position).getPayType());
    }

    @Override
    public int getItemCount() {
        return mPayOrderDetailBeanList == null ? 0 : mPayOrderDetailBeanList.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder{
        TextView tv_order_detail_id,tv_audit_number,tv_order_create_date,tv_pay_type;
        ImageView iv_pay_type_logo;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            this.tv_order_detail_id= (TextView) itemView.findViewById(R.id.tv_order_detail_id);
            this.tv_audit_number= (TextView) itemView.findViewById(R.id.tv_audit_number);
            this.tv_order_create_date= (TextView) itemView.findViewById(R.id.tv_order_create_date);
            this.tv_pay_type= (TextView) itemView.findViewById(R.id.tv_pay_type);
            this.iv_pay_type_logo= (ImageView) itemView.findViewById(R.id.iv_pay_type_logo);
        }

        public void setData(String orderId,String auditNumber,String date,int payTyle) {
            this.tv_order_detail_id.setText(orderId);
            this.tv_audit_number.setText(auditNumber+"");
            this.tv_order_create_date.setText(date+"");

            switch (payTyle){
                case 1:
                    this.tv_pay_type.setText("积分支付");
                    this.iv_pay_type_logo.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.point_pay));
                break;

                case 2:
                this.tv_pay_type.setText("微信");
                    this.iv_pay_type_logo.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.weixin_pay));
                break;

                case 3:
                this.tv_pay_type.setText("支付宝");
                    this.iv_pay_type_logo.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.ali_pay));
                break;

                case 4:
                this.tv_pay_type.setText("银联支付");
                    this.iv_pay_type_logo.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.union_pay));
                break;

                case 5:
                this.tv_pay_type.setText("现金支付");
                    this.iv_pay_type_logo.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.cash_pay));
                break;
            }
        }
    }
}
