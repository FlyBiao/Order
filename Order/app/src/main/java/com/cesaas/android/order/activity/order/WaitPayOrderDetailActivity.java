package com.cesaas.android.order.activity.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.order.WaitPayOrderAdapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.base.BaseNet;
import com.cesaas.android.order.bean.order.ResultWaitPayOrderDetailBean;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.global.Urls;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.JsonUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.ToastUtils;
import com.cesaas.android.order.view.ListViewDecoration;
import com.lidroid.xutils.exception.HttpException;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 未支付订单
 */
public class WaitPayOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_wait_order_total_amount)
    TextView mTvWaitOrderTotalAmount;
    @BindView(R.id.tv_wait_pay_amount)
    TextView mTvWaitPayAmount;
    @BindView(R.id.tv_retail_id)
    TextView mTvRetailId;
    @BindView(R.id.tv_caeate_cashier_staff)
    TextView mTvCaeateCashierStaff;
    @BindView(R.id.tv_order_create_time)
    TextView mTvOrderCreateTime;
    @BindView(R.id.ll_instantly_pay)
    LinearLayout mLlInstantlyPay;
    @BindView(R.id.ll_check_pay)
    LinearLayout mLlCheckPay;
    @BindView(R.id.recycler_wait_pay_order_view)
    SwipeMenuRecyclerView mRecyclerWaitPayOrderView;

    private List<ResultWaitPayOrderDetailBean.TModelEntity.ItemEntity> mItemEntityList;
    private WaitPayOrderAdapter mWaitPayOrderAdapter;


    private TextView mTitle;
    private LinearLayout mBacak;

    private String orderId;
    private WitPayOrderNet mWitPayOrderNet;

    private int retailCheck;//0：未支付，1：已支付
    private double payMoney;//支付金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_order_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        orderId = bundle.getString("OrderId");

        initView();
        initData();
    }

    private void initData() {
        mWitPayOrderNet = new WitPayOrderNet(mContext);
        mWitPayOrderNet.setData(orderId);
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.tv_base_title);
        mTitle.setText("订单详情");
        mBacak = (LinearLayout) findViewById(R.id.ll_base_title_back);
        mBacak.setOnClickListener(this);

        mRecyclerWaitPayOrderView.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
        mRecyclerWaitPayOrderView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRecyclerWaitPayOrderView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRecyclerWaitPayOrderView.addItemDecoration(new ListViewDecoration());// 添加分割线。

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

        switch (v.getId()) {
            case R.id.ll_base_title_back:
                Skip.mBack(mActivity);
                break;
        }
    }


    /**
     * Author FGB
     * Description 待支付订单
     * Created 2017/4/9 23:05
     * Version 1.0
     */
    public class WitPayOrderNet extends BaseNet {
        public WitPayOrderNet(Context context) {
            super(context, true);
            this.uri = Urls.WAIT_PAY_ORDER;
        }

        public void setData(String orderId) {
            try {
                data.put("RetailId", orderId);
                data.put("UserTicket", AbPrefsUtil.getInstance().getString(Constant.SPF_TOKEN));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPostNet(); // 开始请求网络
        }

        @Override
        protected void mSuccess(String rJson) {
            super.mSuccess(rJson);
            ResultWaitPayOrderDetailBean bean = JsonUtils.fromJson(rJson, ResultWaitPayOrderDetailBean.class);
            mTvWaitOrderTotalAmount.setText(bean.getTModel().getRetail().getRetailTotal() + "");
            mTvWaitPayAmount.setText(bean.getTModel().getRetail().getRetailPayment() + "");
            mTvRetailId.setText(bean.getTModel().getRetail().getRetailId() + "");
            mTvOrderCreateTime.setText(bean.getTModel().getRetail().getCreateTime());
            mTvCaeateCashierStaff.setText("F先生");

            retailCheck = bean.getTModel().getRetail().getRetailCheck();
            payMoney = bean.getTModel().getRetail().getRetailPayment();

            if (retailCheck == 0) {//未支付
                mLlInstantlyPay.setVisibility(View.VISIBLE);
            } else {
                //已支付
                mLlCheckPay.setVisibility(View.VISIBLE);
            }

            mItemEntityList=new ArrayList<>();
            mItemEntityList.addAll(bean.getTModel().getItem());
            mWaitPayOrderAdapter=new WaitPayOrderAdapter(mItemEntityList);
            mRecyclerWaitPayOrderView.setAdapter(mWaitPayOrderAdapter);
        }

        @Override
        protected void mFail(HttpException e, String err) {
            super.mFail(e, err);
            Log.i(Constant.TAG, "CheckAccountsNet===" + e + "********=err==" + err);
        }
    }


}
