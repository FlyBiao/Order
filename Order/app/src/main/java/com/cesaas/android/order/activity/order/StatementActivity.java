package com.cesaas.android.order.activity.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.order.SettleAccountsAdapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.SettleAccountsBean;
import com.cesaas.android.order.bean.order.SettleAccountsList;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbDateUtil;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.view.ListViewDecoration;
import com.lidroid.xutils.exception.HttpException;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 对账单
 */
public class StatementActivity extends BaseActivity {

    @BindView(R.id.ll_base_title_back)
    LinearLayout mLlBaseTitleBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_consume_amount)
    TextView mTvConsumeAmount;
    @BindView(R.id.tv_order_amount)
    TextView mTvOrderAmount;
    @BindView(R.id.rv_statement_view)
    SwipeMenuRecyclerView mRvStatementView;


    private List<SettleAccountsList> settleAccountsList;
    private SettleAccountsAdapter adapter;

    private StatisticsNet mStatisticsNet;

    private double payMent=0.0;
    private int payOrderCount;

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        mRvStatementView.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
        mRvStatementView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRvStatementView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRvStatementView.addItemDecoration(new ListViewDecoration());// 添加分割线。

        mStatisticsNet = new StatisticsNet(mContext);
        mStatisticsNet.setData(AbDateUtil.getCurrentDate("yyyy-MM-dd 00:00:00"), AbDateUtil.getCurrentDate("yyyy-MM-dd 23:59:59"));

        mTvBaseTitle.setText("对账单");

        mLlBaseTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.mBack(mActivity);
            }
        });
    }


    /**
     * Author FGB
     * Description 结算对账Net
     * Created 2017/4/10 20:11
     * Version 1.0
     */
    public class StatisticsNet extends BaseNet {
        public StatisticsNet(Context context) {
            super(context, true);
            this.uri = Urls.POS_STATISTICS;
        }

        public void setData(String start_date, String end_date) {
            try {

                data.put("start_date", start_date);
                data.put("end_date", end_date);
                data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

            } catch (Exception e) {
                e.printStackTrace();
            }
            mPostNet();
        }

        @Override
        protected void mSuccess(String rJson) {
            super.mSuccess(rJson);
            SettleAccountsBean bean = JsonUtils.fromJson(rJson, SettleAccountsBean.class);

            settleAccountsList=new ArrayList<>();
            settleAccountsList.addAll(bean.TModel);

            adapter=new SettleAccountsAdapter(settleAccountsList);
            mRvStatementView.setAdapter(adapter);


            for (int i=0;i<settleAccountsList.size();i++){
                payMent+=settleAccountsList.get(i).getPayMent();
                payOrderCount+=settleAccountsList.get(i).getOrderCount();
            }

            mTvConsumeAmount.setText(payMent+"");
            mTvOrderAmount.setText(payOrderCount+"");

        }

        @Override
        protected void mFail(HttpException e, String err) {
            super.mFail(e, err);
        }
    }
}
