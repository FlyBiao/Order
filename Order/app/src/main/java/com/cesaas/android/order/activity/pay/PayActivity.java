package com.cesaas.android.order.activity.pay;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cesaas.android.order.MainActivity;
import com.cesaas.android.order.R;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.dialog.WaitDialog;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.net.pay.CreatePayNet;
import com.cesaas.android.order.net.pay.PayFormStoreNet;
import com.cesaas.android.order.net.user.LoginNet;
import com.cesaas.android.order.utils.AbAppUtil;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.AbStrUtil;
import com.cesaas.android.order.utils.MaxLengthWatcher;
import com.cesaas.android.order.utils.OtherUtil;
import com.cesaas.android.order.utils.Skip;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class PayActivity extends BaseActivity {


    @BindView(R.id.btn_create_pay)
    Button btn_create_pay;
    @BindView(R.id.btn_create_order)
    Button btn_create_order;

    private CreatePayNet createPayNet;
    private PayFormStoreNet payFormStoreNet;

    private int cardCategory=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        EventBus.getDefault().register(this);//订阅
        ButterKnife.bind(this);
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

    @OnClick({R.id.btn_create_pay,R.id.btn_create_order})
    public void login(View v) {

        switch (v.getId()){
            case R.id.btn_create_pay:
                Skip.mNext(mActivity,CreatePayActivity.class);
                //createPayNet=new CreatePayNet(mContext);
                //createPayNet.setData("1232132323",1000.0,3,"10002183102018012500000010","10002183102018012500000010","","支付宝支付","10002183102018012500000010","userShopId","",2,cardCategory,"enCode");
//                createPayNet=new CreatePayNet(mContext);
//                createPayNet.setData("orderno",690.0,4,"100240","10002518022018012500000000","6222083602007416607","银联刷卡","10002518022018012500000000","userShopId","中国工商银行",2,1);
                break;
            case R.id.btn_create_order:
//                payFormStoreNet=new PayFormStoreNet(mContext);
//                payFormStoreNet.setData("10002100812018012400000003","10002100812018012400000003",1497.0,"749940",3,1,"efa81eea");
                break;
        }
    }


    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final LoginBean msg) {


        }

}

