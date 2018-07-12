package com.cesaas.android.order.activity.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.adapter.ViewPagerAadapter;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.utils.Skip;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderCenterActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;

    LinearLayout mLlBaseTitleBack;


    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_center);

        ButterKnife.bind(this);

        initTabLayout();

//        EventBus.getDefault().register(this);//订阅

        mTvBaseTitle.setText("订单中心");
        mLlBaseTitleBack= (LinearLayout) findViewById(R.id.ll_base_title_back);
        mLlBaseTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.mBack(mActivity);
            }
        });
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAadapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);//解除订阅
        super.onDestroy();
    }
}
