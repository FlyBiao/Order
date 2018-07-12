package com.cesaas.android.order.activity.scan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cesaas.android.order.R;
import com.cesaas.android.order.utils.Skip;

import java.nio.ByteBuffer;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.core.zbar.ZBarView;

public class ZBarScanActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private static final String TAG = ZBarScanActivity.class.getSimpleName();
    private QRCodeView mQRCodeView;

    private LinearLayout llBaseBack;
    private TextView tvBaseTitle;

    private String intentCode;

    private static final int RESULT_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar_scan);
        init();

        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();

//
    }

    private void init() {

        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        intentCode=intent.getStringExtra("intentCode");

        llBaseBack= (LinearLayout) findViewById(R.id.ll_base_title_back);
        tvBaseTitle= (TextView) findViewById(R.id.tv_base_title);
        tvBaseTitle.setText("扫描二维码/条码");

        llBaseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.mBack(ZBarScanActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();

    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        mQRCodeView.stopSpot();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        if(result!=null){
            if(intentCode!=null && intentCode.equals("100")){//扫描商品条码订单
                intent.putExtra("resultCode",result);
                intent.putExtra("intentCode","100");
            }
        }

        vibrate();
        mQRCodeView.startSpot();

        setResult(RESULT_CODE, intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.open_flashlight:
                mQRCodeView.openFlashlight();
                break;
            case R.id.close_flashlight:
                mQRCodeView.closeFlashlight();
                break;
        }
    }

}
