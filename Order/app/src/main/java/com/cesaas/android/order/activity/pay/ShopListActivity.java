package com.cesaas.android.order.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.pay.ShopListAdapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.bean.pay.ShopBean;
import com.cesaas.android.order.bean.pay.ShopData;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.net.pay.CreatePayNet;
import com.cesaas.android.order.net.pay.PayFormStoreNet;
import com.cesaas.android.order.utils.AbAppUtil;
import com.cesaas.android.order.utils.OtherUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class ShopListActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    private ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        EventBus.getDefault().register(this);//订阅
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();

        mRecyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                ShopData data=new ShopData();
                Intent intent = new Intent();
                intent.putExtra("EnCode", data.shopData().get(position).getEnCode());
                intent.putExtra("ShopName", data.shopData().get(position).getName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initData() {
        ShopData data=new ShopData();
        adapter=new ShopListAdapter(data.shopData(),mContext);
        mRecyclerView.setAdapter(adapter);
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

//    @OnClick({R.id.btn_select_shop})
//    public void login(View v) {
//        switch (v.getId()){
//            case R.id.btn_select_shop:
//
//                break;
//        }
//    }


    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final LoginBean msg) {


        }

}

