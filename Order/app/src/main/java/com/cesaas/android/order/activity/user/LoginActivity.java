package com.cesaas.android.order.activity.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesaas.android.order.MainActivity;
import com.cesaas.android.order.R;
import com.cesaas.android.order.base.BaseActivity;
import com.cesaas.android.order.bean.user.LoginBean;
import com.cesaas.android.order.dialog.WaitDialog;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.net.user.LoginNet;
import com.cesaas.android.order.utils.AbAppUtil;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.AbStrUtil;
import com.cesaas.android.order.utils.MaxLengthWatcher;
import com.cesaas.android.order.utils.OtherUtil;
import com.cesaas.android.order.utils.Skip;
import com.jauker.widget.BadgeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mIvShowPwd;
    @BindView(R.id.btn_select_user)
    Button btn_select_user;
    @BindView(R.id.tv_user)
    TextView tv_user;

    private WaitDialog dialog;
    private String user;
    private String pwd;

    private boolean isShowPwd=false;

    private LoginNet mLoginNet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);//订阅
        dialog = new WaitDialog(mContext);
        ButterKnife.bind(this);

        initData();
//		initExit();
        // SPF_ISLOGIN:是否登录
        if (AbPrefsUtil.getInstance().getBoolean(Constant.SPF_ISLOGIN)) {
            // 已登录 跳转到主页
            Skip.mNext(mActivity, MainActivity.class);
        }

    }


    private void initData() {
        //获取已登录的账号
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mEtMobile.setText(bundle.getString("Mobile"));
        }


        // 控制editText输入的手机号长度【注：13是因为把空格也算进去了】
        mEtMobile.addTextChangedListener(new MaxLengthWatcher(13,
                mEtMobile));

        AbStrUtil.editTextFilterChinese(mEtPassword);
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                OtherUtil.formatPhoneNum(mEtMobile, s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEtPassword.setText("");
            }
        });

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

    @OnClick({R.id.btn_login,R.id.iv_show_pwd,R.id.btn_select_user})
    public void login(View v) {

        switch (v.getId()){
            case R.id.btn_login:
                user = mEtMobile.getText().toString().trim();
                pwd = mEtPassword.getText().toString().trim();
                if (OtherUtil.phoneVerify(mContext, user)) {
                    if (OtherUtil.passwordVerify(mContext, pwd)) {

                        mLoginNet = new LoginNet(mContext, user, pwd);
                        mLoginNet.mPostNet();
                        dialog.mStart();
                    }
                }

                break;
            case R.id.iv_show_pwd:

                break;
            case R.id.btn_select_user:
                Intent intent = new Intent(mContext, UserListActivity.class);
                startActivityForResult(intent, 0);
                break;
        }

    }


    /**
     * 接收登录结果消息
     * @param msg 消息实体类
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(final LoginBean msg) {

            if(msg.isSuccess()==true){
                dialog.mStop();
                myapp.mExecutorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        String tel = mEtMobile.getText().toString().replace(" ", "");
                        prefs.putBoolean(Constant.SPF_ISLOGIN, msg.isSuccess());

                        if (msg.TModel != null)
                            prefs.putString(Constant.SPF_TOKEN,msg.TModel.getUserTicket());
                        prefs.putString(Constant.SPF_ACCOUNT, tel);
                        prefs.putString(Constant.SPF_TIME,String.valueOf(System.currentTimeMillis()));

                        // 登录成功后跳转到MainActivity
                        Skip.mNext(LoginActivity.this, MainActivity.class);

                        LoginActivity.this.finish();

                        try {

                            BaseActivity.activityList.remove(LoginActivity.this);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        overridePendingTransition(R.anim.activity_slide_in_right,
                                R.anim.activity_no_anim);
                    }
                });

            }else{
                dialog.mStop();
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(data!=null){
                mEtMobile.setText(data.getStringExtra("Mobile"));
                mEtPassword.setText(data.getStringExtra("Pwd"));
                tv_user.setText(data.getStringExtra("ShopName"));
            }
        }
    }

}

