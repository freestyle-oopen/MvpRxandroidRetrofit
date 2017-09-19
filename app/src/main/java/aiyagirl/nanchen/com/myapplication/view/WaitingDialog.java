package aiyagirl.nanchen.com.myapplication.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import aiyagirl.nanchen.com.myapplication.R;


public class WaitingDialog extends ProgressDialog {

    public WaitingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(context.getText(R.string.loading_tip));
    }

    public WaitingDialog(Context context, int theme) {
        super(context, theme);
    }

}
