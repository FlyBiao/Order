package com.cesaas.android.order.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.cesaas.android.order.R;
import com.cesaas.android.order.dialog.WaitDialog;
import com.cesaas.android.order.global.App;
import com.cesaas.android.order.global.Constant;
import com.cesaas.android.order.utils.AbDataPrefsUtil;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.BitmapHelp;
import com.cesaas.android.order.utils.Skip;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Activity 基类
 * 
 * @author Evan
 *
 */
public class BasesActivity extends FragmentActivity {

	/* Activity集合，便于管理 */
	public static ArrayList<Activity> activityList = new ArrayList<Activity>();
	protected Context mContext;
	protected Activity mActivity;
	protected String TAG;
	protected ImageView top_back;
	protected TextView top_title;
	protected App myapp;
	public AbPrefsUtil prefs;
	protected AbDataPrefsUtil dataPrefs;
	protected BitmapUtils bitmapUtils;
	protected Gson gson;
	protected Bundle bundle;
	protected WaitDialog mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LogUtils.customTagPrefix = "xUtilsSample"; // 方便调试时过滤 adb logcat 输出
		LogUtils.allowI = false; // 关闭 LogUtils.i(...) 的 adb log 输出
		LogUtils.allowD = false;
		LogUtils.allowE = false;

		ViewUtils.inject(this);
		activityList.add(this);
		mContext = this;
		mActivity = this;
		mDialog = new WaitDialog(mContext);
		myapp = App.mInstance();
		prefs = AbPrefsUtil.getInstance();
		dataPrefs = AbDataPrefsUtil.getInstance();
		gson=new Gson();
		bundle=new Bundle();
		//初始化控件
//		initData();
		
		//通过EventBus订阅事件
		EventBus.getDefault().register(this);
		
		TAG = getLocalClassName().toString();
		LogUtils.d("注册EventBus");


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		EventBus.getDefault().unregister(this);//取消EventBus订阅
		finish();//销毁当前页面
		super.onDestroy();
	}

//	protected void initData() {
//		top_title = (TextView) this.findViewById(R.id.top_title);
//		top_back = (ImageView) this.findViewById(R.id.top_back);
//		top_back.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				onExit();
//			}
//		});
//	}

	/* 取资源值 */
	protected String getRstring(int r) {
		return App.mInstance().getResources().getString(r);
	}

//	public void onEventMainThread(Message msg) {
//	}
//
//	public void onEventMainThread(HttpException err) { // 错误回调
//	}
//
//	public void onEventMainThread(ResultBean result) { // 成功请求结果回调
//	}

	public void onEventBackgroundThread(Message msg) {
	}

	public String getAccount() {
		return prefs.getString(Constant.SPF_ACCOUNT);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			onExit();
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	/* 退出Activity方法，同时清除列表 */
	protected void onExit() {
		Skip.mBack(mActivity);
		 if (activityList != null) {
		 for (int i = 0; i < activityList.size(); i++) {
		 if (activityList.equals(this)) {
		 activityList.remove(i);
		 }
		 }
		 }
		 this.finish();
	}

}
