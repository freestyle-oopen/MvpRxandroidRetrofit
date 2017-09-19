package aiyagirl.nanchen.com.myapplication.rx;

import java.net.SocketTimeoutException;
import aiyagirl.nanchen.com.myapplication.R;
import aiyagirl.nanchen.com.myapplication.app.App;
import aiyagirl.nanchen.com.myapplication.net.ServiceInterface;
import aiyagirl.nanchen.com.myapplication.net.RetrofitUtil;
import aiyagirl.nanchen.com.myapplication.utils.Logger;
import aiyagirl.nanchen.com.myapplication.utils.NetWorkUtils;
import aiyagirl.nanchen.com.myapplication.utils.Toastor;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Renyukai onShrinkScreen 2016/12/23.
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

    public static <T> Observable<T> wrap(Observable<T> primitive, boolean retry) {
        Observable<T> ripe = (Observable<T>) primitive
                .compose(RxSchedulers.io_main())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        doOnError(throwable);
                    }
                });
        /*.doOnError(throwable -> doOnError(throwable));*/
        if (retry) {
            return ripe.retryWhen(new RetryWhenNetworkException());
        }
        return ripe;
    }

    public static <T> Observable<T> wrap(Observable<T> primitive, int retryCount) {
        Observable<T> ripe = (Observable<T>) primitive
                .compose(RxSchedulers.io_main())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        doOnError(throwable);
                    }
                });
        if (retryCount > 0) {
            return ripe.retryWhen(new RetryWhenNetworkException(retryCount));
        }
        return ripe;
    }

    public static <T> Observable<T> wrap(Observable<T> primitive, Func1<Observable<? extends Throwable>, Observable<?>> func1) {
        Observable<T> ripe = (Observable<T>) primitive
                .compose(RxSchedulers.io_main())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        doOnError(throwable);
                    }
                });
        if (func1 != null) {
            return ripe.retryWhen(func1);
        }
        return ripe;
    }

    public static <T> Observable<T> wrap(Observable<T> primitive) {
        return wrap(primitive, false);
    }

    public static ServiceInterface getService() {
        return RetrofitUtil.getInstance().getServiceInterface();
    }
}
