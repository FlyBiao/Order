package com.cesaas.android.order.global;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.cesaas.android.order.utils.AbDataPrefsUtil;
import com.cesaas.android.order.utils.AbPrefsUtil;
import com.cesaas.android.order.utils.PosSDKTools;
import com.cesaas.android.order.xutils.NetworkConnectivityListener;
import com.cesaas.android.order.xutils.NetworkUtil;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 20:13
 * Version 1.0
 */
public class App extends Application {

    private static App _instance;
    private static App myapp = null;
    public BitmapDisplayConfig bitmapConfig;
    public ExecutorService mExecutorService = null; // 线程池

    private int netType; // 网络类型
    private NetworkConnectivityListener mNetChangeReceiver;
    private NetworkConnectivityListener.NetworkCallBack mNetworkCallBack = new NetworkConnectivityListener.NetworkCallBack() {
        public void getSelfNetworkType(int type) {
            if (netType != type) {
                // setAvailable_http_host("");
            }
            setNetType(type);
        }
    };

    public static App mInstance() { // 单例实例化
        return myapp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myapp = this;

        mExecutorService = Executors.newFixedThreadPool(8);

        _instance = this;

        //初始化SharedPreference
        initPrefs();
        initImage();
        //初始化pos sdk，只需要在apk启动入口初始化一次，当应用完全退出是会自动调用sdk的onDestroy()
        PosSDKTools.initSdk(this);

        initNet();
    }

    private void initNet() {
        netType = NetworkUtil.getSelfNetworkType(this);
        mNetChangeReceiver = new NetworkConnectivityListener();
        mNetChangeReceiver.startListening(this);
        addNetworkCallBack(mNetworkCallBack);
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public void addNetworkCallBack(NetworkConnectivityListener.NetworkCallBack mNetworkCallBack) {
        mNetChangeReceiver.registerNetworkCallBack(mNetworkCallBack);
    }

    public void removeNetworkCallBack(NetworkConnectivityListener.NetworkCallBack mNetworkCallBack) {
        mNetChangeReceiver.unregisterNetworkCallBack(mNetworkCallBack);
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void initPrefs() {
        AbPrefsUtil.init(this, getPackageName() + "_preference",
                Context.MODE_PRIVATE);
        AbDataPrefsUtil.init(this, getPackageName() + "_net_data",
                Context.MODE_PRIVATE);
    }

    public static App getInstance() {
        return _instance;
    }

    private void initImage() {
        bitmapConfig = new BitmapDisplayConfig();
        // bigPicDisplayConfig.setShowOriginal(true); // 显示原始图片,不压缩, 尽量不要使用,
        // 图片太大时容易OOM。
        bitmapConfig.setBitmapConfig(Bitmap.Config.RGB_565);
        bitmapConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(this));
    }

}
