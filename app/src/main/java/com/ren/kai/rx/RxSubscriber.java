package com.ren.kai.rx;

import android.content.Context;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

import com.ren.kai.view.WaitingDialog;
import rx.Subscriber;

/**
 * Created by Renyukai onShrinkScreen 2016/12/9.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private final WeakReference<Context> mContext;
    private String mMessage;

    private WaitingDialog mWaitingDialog;

    public RxSubscriber() {
        this(null, null);
    }

    public RxSubscriber(Context context, String message) {
        super();
        mContext = new WeakReference<Context>(context);
        mMessage = message;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mContext != null && mContext.get() != null) {
            if (mWaitingDialog == null)
                mWaitingDialog = new WaitingDialog(mContext.get());
            if (!TextUtils.isEmpty(mMessage))
                mWaitingDialog.setMessage(mMessage);
            mWaitingDialog.show();
        }
    }

    @Override
    public final void onCompleted() {
        onFinal();
    }

    @Override
    public final void onError(Throwable e) {
        onFailed(e);
        onFinal();
    }

    @Override
    public final void onNext(T t) {
        onSucceed(t);
    }

    public abstract void onSucceed(T t);

    public void onFailed(Throwable e) {
    }

    public void onFinal() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing())
            mWaitingDialog.dismiss();

    }

}
