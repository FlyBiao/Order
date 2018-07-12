package com.cesaas.android.order.activity.pay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cesaas.android.order.R;
import com.cesaas.android.order.activity.user.LoginActivity;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.bean.pay.ResultCreatePayBean;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.dialog.MyAlertDialog;
import com.cesaas.android.order.net.pay.CreatePayNet;
import com.cesaas.android.order.net.pay.PayFormStoreNet;
import com.cesaas.android.order.utils.AbAppUtil;
import com.cesaas.android.order.utils.AbDateUtil;
import com.cesaas.android.order.utils.OtherUtil;
import com.cesaas.android.order.utils.RandomUtils;
import com.cesaas.android.order.utils.Skip;
import com.cesaas.android.order.utils.ToastUtils;
import com.sing.datetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class CreatePayActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.ll_base_title_back)
    LinearLayout back;
    @BindView(R.id.tv_base_title)
    TextView title;
    @BindView(R.id.tv_base_title_right)
    TextView right;
    @BindView(R.id.btn_select_shop)
    Button btn_select_shop;
    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    @BindView(R.id.et_shop_encode)
    EditText et_shop_encode;
    @BindView(R.id.et_order_no)
    EditText et_order_no;
    @BindView(R.id.et_payment)
    EditText et_payment;
    @BindView(R.id.et_pay_type)
    EditText et_pay_type;
    @BindView(R.id.et_pay_no)
    EditText et_pay_no;
    @BindView(R.id.et_trace_audit)
    EditText et_trace_audit;
    @BindView(R.id.et_bank_no)
    EditText et_bank_no;
    @BindView(R.id.et_voucher_record)
    EditText et_voucher_record;
    @BindView(R.id.et_sheet_category)
    EditText et_sheet_category;
    @BindView(R.id.et_bank_type)
    EditText et_bank_type;
    @BindView(R.id.et_create_time)
    EditText et_create_time;
    @BindView(R.id.btn_select_time)
    Button btn_select_time;

    private CreatePayNet createPayNet;
    private PayFormStoreNet payFormStoreNet;

    private int cardCategory=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pay);
        EventBus.getDefault().register(this);//订阅
        ButterKnife.bind(this);
        title.setText("创建流水");
        right.setVisibility(View.VISIBLE);
        right.setText("确定");
        et_order_no.setText(RandomUtils.getCurrentTimeMM() + RandomUtils.getFourRandom()+"");
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

    @OnClick({R.id.btn_select_shop,R.id.tv_base_title_right,R.id.btn_select_time,R.id.ll_base_title_back})
    public void login(View v) {
        switch (v.getId()){
            case R.id.btn_select_shop:
                Intent intent = new Intent(mContext, ShopListActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.tv_base_title_right:
                if(!TextUtils.isEmpty(et_shop_encode.getText().toString())){
                    if(!TextUtils.isEmpty(et_payment.getText().toString())){
                        createPay();
                    }else{
                        ToastUtils.getLongToast(mContext,"请输入金额！");
                    }
                }else{
                    ToastUtils.getLongToast(mContext,"请选择EnCode！");
                }
                break;
            case R.id.btn_select_time:
                getDateSelect(btn_select_time);
                break;
            case R.id.ll_base_title_back:
                Skip.mBack(mActivity);
                break;
        }
    }


    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final ResultCreatePayBean msg) {
        if(msg.isSuccess()!=false){
            ToastUtils.getLongToast(mContext,"创建流水成功!");
            Skip.mNext(mActivity,PayListActivity.class,true);
        }else{
            ToastUtils.getLongToast(mContext,"创建流水失败："+msg.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(data!=null){
                et_shop_encode.setText(data.getStringExtra("EnCode"));
                tv_shop_name.setText(data.getStringExtra("ShopName"));
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (++monthOfYear) + "-" + dayOfMonth;
        et_create_time.setText(date+" "+AbDateUtil.getHMS(AbDateUtil.getCurrentTime()));
    }

    /**
     * 日期选择选择
     * @param v
     */
    public void getDateSelect(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(CreatePayActivity.this,now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
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

    /**
     * 退出当前用户
     */
    public void createPay() {
        new MyAlertDialog(mContext).mInitShow("创建流水", "是否需要马上创建流水? 请确保信息是否正确!",
                "我知道", "点错了", new MyAlertDialog.ConfirmListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        if(et_pay_type.getText().toString().equals("2")){
                            createPayNet=new CreatePayNet(mContext);
                            createPayNet.setData(
                                    et_order_no.getText().toString(),Double.parseDouble(et_payment.getText().toString()),2
                                    ,et_pay_no.getText().toString(),et_trace_audit.getText().toString(),"","微信支付",et_voucher_record.getText().toString()
                                    ,prefs.getString("ShopId"),"",Integer.parseInt(et_sheet_category.getText().toString()),cardCategory,et_shop_encode.getText().toString(),et_create_time.getText().toString());
                        }else if(et_pay_type.getText().toString().equals("3")){
                            createPayNet=new CreatePayNet(mContext);
                            createPayNet.setData(
                                    et_order_no.getText().toString(),Double.parseDouble(et_payment.getText().toString()),3
                                    ,et_pay_no.getText().toString(),et_trace_audit.getText().toString(),"","支付宝支付",et_voucher_record.getText().toString()
                                    ,prefs.getString("ShopId"),"",Integer.parseInt(et_sheet_category.getText().toString()),cardCategory,et_shop_encode.getText().toString(),et_create_time.getText().toString());
                        }else if(et_pay_type.getText().toString().equals("4")){
                            if(et_bank_type.getText().toString().equals("1")){//借记卡
                                createPayNet=new CreatePayNet(mContext);
                                createPayNet.setData(
                                        et_order_no.getText().toString(),Double.parseDouble(et_payment.getText().toString()),4,et_pay_no.getText().toString()
                                        ,et_trace_audit.getText().toString(),et_bank_no.getText().toString(),"银联刷卡",et_voucher_record.getText().toString(),prefs.getString("ShopId")
                                        ,"银行",2,1,et_shop_encode.getText().toString(),et_create_time.getText().toString());
                            }else if(et_bank_type.getText().toString().equals("2")){//信用卡
                                createPayNet=new CreatePayNet(mContext);
                                createPayNet.setData(
                                        et_order_no.getText().toString(),Double.parseDouble(et_payment.getText().toString()),4,et_pay_no.getText().toString()
                                        ,et_trace_audit.getText().toString(),et_bank_no.getText().toString(),"银联刷卡",et_voucher_record.getText().toString(),prefs.getString("ShopId")
                                        ,"银行",2,2,et_shop_encode.getText().toString(),et_create_time.getText().toString());

                            }else if (et_bank_type.getText().toString().equals("3")){//贷记卡
                                createPayNet=new CreatePayNet(mContext);
                                createPayNet.setData(
                                        et_order_no.getText().toString(),Double.parseDouble(et_payment.getText().toString()),4,et_pay_no.getText().toString()
                                        ,et_trace_audit.getText().toString(),et_bank_no.getText().toString(),"银联刷卡",et_voucher_record.getText().toString(),prefs.getString("ShopId")
                                        ,"银行",2,3,et_shop_encode.getText().toString(),et_create_time.getText().toString());
                            }else{

                            }

                        }else {

                        }
                    }

                }, null);
    }

}

