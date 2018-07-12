package com.cesaas.android.order.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cesaas.android.order.R;
import com.cesaas.android.order.base.BaseActivity;


/**
 * 页面跳转类
 * @author FlyBiao
 *
 */
public class Skip {

	/**
	 * 不关闭当前页跳转
	 * 
	 * @param context
	 * @param cls
	 */
	public static void mNext(Activity context, Class<?> cls) {
		if (cls != null) {
			context.startActivity(new Intent(context, cls));
			context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
		}
	}

	public static void mNext(Activity context, Class<?> cls, boolean exit) {
		context.startActivity(new Intent(context, cls));
		if (exit) {
			context.finish();
			try {
				BaseActivity.activityList.remove(context);
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
	}

	/**
	 * 跳转到下一页面
	 * 
	 * @param context
	 * @param cls
	 *            转入页
	 * @param enterAnim
	 * @param exitAnim
	 * @param exit
	 *            是否关闭当前页
	 */
	public static void mNext(Activity context, Class<?> cls, int enterAnim, int exitAnim, boolean exit) {
		context.startActivity(new Intent(context, cls));
		if (exit) {
			context.finish();
			try {
				BaseActivity.activityList.remove(context);
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		context.overridePendingTransition(enterAnim, exitAnim);
	}


	/**
	 * 带返回值的跳转
	 * 
	 * @param context
	 * @param cls
	 * @param requestCode
	 */
	public static void mActivityResults(Activity context, Class<?> cls, int requestCode,String intentCode) {
		
		Intent intent = new Intent(context, cls);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		intent.putExtra("intentCode",intentCode);
		context.startActivityForResult(intent, requestCode);
		context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
	}
	

	/**
	 * 传值的跳转
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void mNextFroData(Activity context, Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
		context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
	}

	public static void mNextFroData(Activity context, Class<?> cls, String key, String val) {
		Intent intent = new Intent(context, cls);
		intent.putExtra(key, val);
		context.startActivity(intent);
		context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
	}

	public static void mNextFroData(Activity context, Class<?> cls, Bundle bundle, int enterAnim) {
		Intent intent = new Intent(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
		context.overridePendingTransition(enterAnim, R.anim.activity_not_anim);
	}
	
	
	public static void mNext(Activity context, Class<?> cls, boolean exit,Bundle bundle) {
		Intent intent = new Intent(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
		if (exit) {
			context.finish();
			try {
				BaseActivity.activityList.remove(context);
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
	}
	

	/**
	 * 传值的跳转
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void mNextFroData(Activity context, Class<?> cls, Bundle bundle, boolean exit) {
		Intent intent = new Intent(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
		if (exit) {
			context.finish();
			context.overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_no_anim);
			System.gc();
		}
	}

	/**
	 * 退出当前页返回到上一页
	 * 
	 * @param context
	 */
	public static void mBack(Activity context) {
		context.finish();
		context.overridePendingTransition(R.anim.activity_no_anim, R.anim.activity_slide_out_right);
		try {
			BaseActivity.activityList.remove(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.gc();
	}

	/**
	 * 自定义动画退出当前页
	 * 
	 * @param context
	 * @param enterAnim
	 * @param exitAnim
	 */
	public static void mBack(Activity context, int enterAnim, int exitAnim) {
		context.finish();
		context.overridePendingTransition(enterAnim, exitAnim);
		try {
			BaseActivity.activityList.remove(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打开浏览器访问页面
	 * 
	 * @param context
	 * @param url
	 */
	public static void mStartView(Activity context, String url) {
		Intent intent = new Intent();
		intent.setData(Uri.parse(url));
		intent.setAction(Intent.ACTION_VIEW);
		context.startActivity(intent); // 启动浏览器
	}
}
