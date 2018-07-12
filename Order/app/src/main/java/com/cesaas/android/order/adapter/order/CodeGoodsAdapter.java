package com.cesaas.android.order.adapter.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.bean.goods.CodeGoodsBean;
import com.cesaas.android.order.listener.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Author FGB
 * Description 条码商品Adapter
 * Created 2017/4/14 23:47
 * Version 1.0
 */
public class CodeGoodsAdapter extends SwipeMenuAdapter<CodeGoodsAdapter.DefaultViewHolder>{

    private List<CodeGoodsBean> codeGoodsList;

    public CodeGoodsAdapter(List<CodeGoodsBean> codeGoodsList){
        this.codeGoodsList=codeGoodsList;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_code_goods, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(codeGoodsList.get(position).getTitle(),
                codeGoodsList.get(position).getStyleCode(),
                codeGoodsList.get(position).getShopCount(),
                codeGoodsList.get(position).getPrice(),
                codeGoodsList.get(position).getPayMent());
    }

    @Override
    public int getItemCount() {
        return codeGoodsList == null ? 0 : codeGoodsList.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_goods_title,tv_goods_style_code,tv_goods_number,tv_goods_price,tv_goods_real_pay_price;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_goods_title = (TextView) itemView.findViewById(R.id.tv_goods_title);
            tv_goods_style_code= (TextView) itemView.findViewById(R.id.tv_goods_style_code);
            tv_goods_number= (TextView) itemView.findViewById(R.id.tv_goods_number);
            tv_goods_price= (TextView) itemView.findViewById(R.id.tv_goods_price);
            tv_goods_real_pay_price= (TextView) itemView.findViewById(R.id.tv_goods_real_pay_price);
        }

        public void setData(String goodsTitle,String goodsStyleCode,int goodsNumber,double goodsPrice,double goodsRealPayPrice) {
            this.tv_goods_title.setText(goodsTitle);
            this.tv_goods_style_code.setText(goodsStyleCode);
            this.tv_goods_number.setText(goodsNumber+"");
            this.tv_goods_price.setText(goodsPrice+"");
            this.tv_goods_real_pay_price.setText(goodsRealPayPrice+"");
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
