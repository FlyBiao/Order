package com.cesaas.android.order.dialog;

import android.content.Context;

import com.cesaas.android.order.R;
import com.cesaas.android.order.base.BaseDialog;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 21:13
 * Version 1.0
 */
public class WaitDialog extends BaseDialog {

    public WaitDialog(Context context) {
        super(context, R.style.dialog);
        // TODO Auto-generated constructor stub
        setContentView(R.layout.wait_dialog);
        setCanceledOnTouchOutside(false);
    }

    public void mStart() {
        show();
    }

    public void mStop() {
        cancel();
    }
}
