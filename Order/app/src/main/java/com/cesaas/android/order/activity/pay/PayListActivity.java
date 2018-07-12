package com.cesaas.android.order.activity.pay;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.pay.PayListAdapter;
import com.cesaas.android.order.adapter.pay.ShopListAdapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.bean.pay.PayCallbackBean;
import com.cesaas.android.order.bean.pay.PayListBean;
import com.cesaas.android.order.bean.pay.ResultPayListBean;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.net.pay.PayListNet;
import com.cesaas.android.order.utils.AbAppUtil;
import com.cesaas.android.order.utils.AbDateUtil;
import com.cesaas.android.order.utils.OtherUtil;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.ToastUtils;
import com.sing.datetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class PayListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,DatePickerDialog.OnDateSetListener{

    @BindView(R.id.tv_base_title)
    TextView title;
    @BindView(R.id.tv_base_title_right)
    TextView right;
    @BindView(R.id.ll_base_title_back)
    LinearLayout back;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.btn_start_time)
    Button btn_start_time;
    @BindView(R.id.btn_end_time)
    Button btn_end_time;

    private int page=1;
    private static final int PAGE_SIZE = 6;
    private int delayMillis = 2000;
    private int pageIndex=1;
    private int mCurrentCounter = 0;
    private boolean isErr;
    private boolean mLoadMoreEndGone = false;

    private PayListAdapter adapter;
    private PayListNet payListNet;
    private List<PayListBean> payListBeens;
    private int timeType=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_list);
        EventBus.getDefault().register(this);//订阅
        ButterKnife.bind(this);
        title.setText("支付流水");
        right.setVisibility(View.VISIBLE);
        right.setText("按时间筛选");
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {
        payListNet=new PayListNet(mContext);
        payListNet.setData(AbDateUtil.getCurrentDate("yyyy-MM-dd 00:00:00"), AbDateUtil.getCurrentDate("yyyy-MM-dd 23:59:59"),page);
    }

    @OnClick({R.id.tv_base_title_right,R.id.btn_start_time,R.id.btn_end_time,R.id.ll_base_title_back})
    public void login(View v) {
        switch (v.getId()){
            case R.id.tv_base_title_right:
                payListNet=new PayListNet(mContext);
                payListNet.setData(btn_start_time.getText().toString(), btn_end_time.getText().toString(),page);
                break;
            case R.id.btn_start_time:
                timeType=1;
                getDateSelect(btn_start_time);
                break;
            case R.id.btn_end_time:
                timeType=2;
                getDateSelect(btn_end_time);
                break;
            case R.id.ll_base_title_back:
                Skip.mBack(mActivity);
                break;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (OtherUtil.isShouldHideInput(v, ev)) {
                AbAppUtil.hideSoftInput(mContext);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);//解除订阅
        super.onDestroy();
    }

    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final ResultPayListBean msg) {
            if(msg.isSuccess()!=false){
                if(msg.TModel!=null && msg.TModel.size()!=0){
                    payListBeens=new ArrayList<>();
                    payListBeens.addAll(msg.TModel);
                    adapter=new PayListAdapter(payListBeens,mContext);
                    mRecyclerView.setAdapter(adapter);
                }else{
                    ToastUtils.getLongToast(mContext,"还没有流水");
                    payListBeens=new ArrayList<>();
                    adapter=new PayListAdapter(payListBeens,mContext);
                    mRecyclerView.setAdapter(adapter);
                    btn_start_time.setText("开始时间");
                    btn_end_time.setText("结束时间");
                }

            }else{
                ToastUtils.getLongToast(mContext,"msg:"+msg.getMessage());
            }

        }

    @Override
    public void onRefresh() {
        adapter = new PayListAdapter(payListBeens,mContext);
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payListNet=new PayListNet(mContext);
                payListNet.setData(AbDateUtil.getCurrentDate("yyyy-MM-dd 00:00:00"), AbDateUtil.getCurrentDate("yyyy-MM-dd 23:59:59"),page);
                isErr = false;
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
            }
        }, delayMillis);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (++monthOfYear) + "-" + dayOfMonth;
        if(timeType==1){
            btn_start_time.setText(date+" 00:00:00");
        }
        else{
            btn_end_time.setText(date+" 23:59:59");
        }
    }

    /**
     * 日期选择选择
     * @param v
     */
    public void getDateSelect(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(PayListActivity.this,now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dpd.setThemeDark(false);// boolean,DarkTheme
        dpd.vibrate(true);// boolean,触摸震动
        dpd.dismissOnPause(false);// boolean,Pause时是否Dismiss
        dpd.showYearPickerFirst(false);// boolean,先选择年
        if (true) {// boolean,自定义颜色
            dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
        }
        if (true) {// boolean,设置标题
            dpd.setTitle("日期选择");
        }
        if (false) {// boolean,只能选择某些日期
            Calendar[] dates = new Calendar[13];
            for (int i = -6; i <= 6; i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.MONTH, i);
                dates[i + 6] = date;
            }
            dpd.setSelectableDays(dates);
        }
        if (true) {// boolean,部分高亮
            Calendar[] dates = new Calendar[13];
            for (int i = -6; i <= 6; i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.WEEK_OF_YEAR, i);
                dates[i + 6] = date;
            }
            dpd.setHighlightedDays(dates);
        }
        if (false) {// boolean,某些日期不可选
            Calendar[] dates = new Calendar[3];
            for (int i = -1; i <= 1; i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.DAY_OF_MONTH, i);
                dates[i + 1] = date;
            }
            dpd.setDisabledDays(dates);
        }
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

}

