package com.ren.kai.rx;

import android.support.annotation.NonNull;

import java.net.SocketTimeoutException;

import com.ren.kai.app.App;
import com.ren.kai.entity.HttpResult;
import com.ren.kai.net.RetrofitUtil;
import com.ren.kai.net.ServiceInterface;
import com.ren.kai.utils.Logger;
import com.ren.kai.utils.NetWorkUtils;
import com.ren.kai.utils.Toastor;
import com.ren.kai.R;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Renyukai  2018/12/24.
 */

public class RxHelper {

    private static void doOnError(Throwable e) {
        Logger.e(e.toString());
        if (e instanceof BusinessException) {
            Toastor.show(e.getMessage());
        } else if (!NetWorkUtils.isNetConnected(App.getApp())) {
            Toastor.show(R.string.load_net_work_error_tip);
        } else if (e instanceof SocketTimeoutException) {
            Toastor.show(R.string.load_net_work_weak_tip);
        }
    }

    @NonNull
    public static <T> Observable<T> wrap(Observable<T> primitive) {
        return primitive.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                doOnError(throwable);
            }
        });
    }

    @NonNull
    public static <T> Observable<T> wrapResult(Observable<HttpResult<T>> primitive) {
        Observable<T> observable = primitive.map(new Func1<HttpResult<T>, T>() {
            @Override
            public T call(HttpResult<T> tHttpResult) {
                int resultCode = tHttpResult.getResultCode();
                if (resultCode != 1) {
                    if (tHttpResult.getResultMessage() != null && "".equals(tHttpResult.getResultMessage())) {
                        new BusinessException(tHttpResult.getResultMessage());
                    } else {
                        new BusinessException(resultCode);
                    }
                }
                return tHttpResult.getData();
            }
        });
        return wrap(observable);
    }

    public static <T> Observable<T> wrap(Observable<T> primitive, boolean retry) {
        Observable<T> tObservable = wrap(primitive);
        if (retry) {
            tObservable.retryWhen(new RetryWhenNetworkException());
        }
        return tObservable;
    }

    public static <T> Observable<T> wrap(Observable<T> primitive, int retryCount) {
        Observable<T> tObservable = wrap(primitive);
        if (retryCount > 0) {
            return tObservable.retryWhen(new RetryWhenNetworkException(retryCount));
        }
        return tObservable;
    }

    public static <T> Observable<T> wrap(Observable<T> primitive, Func1<Observable<? extends Throwable>, Observable<?>> func1) {
        Observable<T> tObservable = wrap(primitive);
        if (func1 != null) {
            return tObservable.retryWhen(func1);
        }
        return tObservable;
    }

    public static <T> Observable<T> wrapResult(Observable<HttpResult<T>> primitive, boolean retry) {
        Observable<T> tObservable = wrapResult(primitive);
        if (retry) {
            return tObservable.retryWhen(new RetryWhenNetworkException());
        }
        return tObservable;
    }

    public static <T> Observable<T> wrapResult(Observable<HttpResult<T>> primitive, int retryCount) {
        Observable<T> tObservable = wrapResult(primitive);
        if (retryCount > 0) {
            return tObservable.retryWhen(new RetryWhenNetworkException(retryCount));
        }
        return tObservable;
    }

    public static <T> Observable<T> wrapResult(Observable<HttpResult<T>> primitive, Func1<Observable<? extends Throwable>, Observable<?>> func1) {
        Observable<T> tObservable = wrapResult(primitive);
        if (func1 != null) {
            return tObservable.retryWhen(func1);
        }
        return tObservable;
    }
}
