package com.ren.kai.rx.rxpermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class GroupPermissionObserver implements Observer<Boolean> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Boolean aBoolean) {
        result(aBoolean);
    }

    @Override
    public void onError(Throwable e) {
        result(false);
    }

    @Override
    public void onComplete() {

    }
    /**
     * 统一结果返回
     * @param result
     */
   public abstract void result(Boolean result);
}
