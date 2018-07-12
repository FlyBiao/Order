package com.cesaas.android.order.activity.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.order.AccountDetailAdapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.PayOrderDetailBean;
import com.cesaas.android.order.bean.order.PayOrderDetailBeanListBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.ToastUtils;
import com.cesaas.android.order.view.ListViewDecoration;
import com.lidroid.xutils.exception.HttpException;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountDetailActivity extends BaseActivity {

    @BindView(R.id.ll_base_title_back)
    LinearLayout mLlBaseTitleBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_consume_amount)
    TextView mTvConsumeAmount;
    @BindView(R.id.tv_cashier_staff)
    TextView mTvCashierStaff;
    @BindView(R.id.recycler_account_order_view)
    SwipeMenuRecyclerView mRecyclerAccountOrderView;
    @BindView(R.id.tv_base_title_right)
    TextView mTvBaseTitleRight;


    private String CreateName;
    private String orderId;//订单id
    private double payAmount;//支付ine
    private double orderAmount;//订单金额
    private int isRefund;//是否已退款 0：正常，1：已退款

    private List<PayOrderDetailBean> mPayOrderDetailBeanList;
    private AccountDetailAdapter mAccountDetailAdapter;
    private PayJournalNet mPayJournalNet;

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);

        mTvBaseTitle.setText("订单详情");
        mTvBaseTitleRight.setText("退款");
        mTvBaseTitleRight.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        orderId = bundle.getString("OrderId");
        CreateName = bundle.getString("CreateName");
        payAmount = bundle.getDouble("PayAmount");
        isRefund = bundle.getInt("IsRefund");

        mPayJournalNet = new PayJournalNet(mContext);
        mPayJournalNet.setData(orderId);

        initData();

    }

    private void initData() {
        mRecyclerAccountOrderView.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
        mRecyclerAccountOrderView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRecyclerAccountOrderView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRecyclerAccountOrderView.addItemDecoration(new ListViewDecoration());// 添加分割线。

        mTvCashierStaff.setText(CreateName);
        mTvConsumeAmount.setText(payAmount + "");

        //返回
        mLlBaseTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.mBack(mActivity);
            }
        });

        //退款
        mTvBaseTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getLongToast(mContext,"退款");
            }
        });

    }


    /**
     * Author FGB
     * Description
     * Created 2017/4/10 18:11
     * Version 1.0
     */
    public class PayJournalNet extends BaseNet {
        public PayJournalNet(Context context) {
            super(context, true);
            this.uri = Urls.PAY_JOURNAL;
        }

        public void setData(String orderId) {
            try {
                data.put("RetailId", orderId);
                data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));

            } catch (Exception e) {
                e.printStackTrace();
            }
            mPostNet(); // 开始请求网络
        }

        @Override
        protected void mSuccess(String rJson) {
            super.mSuccess(rJson);
            PayOrderDetailBeanListBean bean = JsonUtils.fromJson(rJson, PayOrderDetailBeanListBean.class);

            mPayOrderDetailBeanList = new ArrayList<>();
            mPayOrderDetailBeanList.addAll(bean.TModel);

            mAccountDetailAdapter = new AccountDetailAdapter(mPayOrderDetailBeanList, mContext);
            mRecyclerAccountOrderView.setAdapter(mAccountDetailAdapter);
        }

        @Override
        protected void mFail(HttpException e, String err) {
            super.mFail(e, err);
        }
    }
}
