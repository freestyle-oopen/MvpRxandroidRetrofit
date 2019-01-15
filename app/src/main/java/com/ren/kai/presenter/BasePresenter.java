package com.ren.kai.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.ren.kai.modle.BaseModle;
import com.ren.kai.rx.rxpermissions.EachPermissionObserver;
import com.ren.kai.rx.rxpermissions.GroupPermissionObserver;
import com.ren.kai.ui.contract.BaseView;
import com.ren.kai.utils.InstanceUtils;
import com.ren.kai.rx.RxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by Renyukai on 2017/6/12.
 */

public class BasePresenter<V extends BaseView, M extends BaseModle> {
    private V mView;
    private M mModle;
    private RxManager mRxManager;
    private RxPermissions rxPermissions;

    public void onCreate(@NonNull Activity activity) {
        this.mView = (V) activity;
        this.mModle = InstanceUtils.getInstance(activity, 1);

        if (!(activity instanceof BaseView)) {
            throw new IllegalArgumentException(activity.getClass().getSimpleName() + " is a not supported type which must implements BaseView(创建P失败)");
        }
        if (mModle == null) {
            throw new IllegalArgumentException("can not get the ParameterizedType of(创建P失败)" + activity.getClass().getSimpleName());
        }
    }

    public void onCreate(@NonNull Fragment fragment) {
        this.mView = (V) fragment;
        this.mModle = InstanceUtils.getInstance(fragment, 1);

        if (!(fragment instanceof BaseView)) {
            throw new IllegalArgumentException(fragment.getClass().getSimpleName() + " is a not supported type which must implements BaseView(创建P失败)");
        }
        if (mModle == null) {
            throw new IllegalArgumentException("can not get the ParameterizedType of(创建P失败)" + fragment.getClass().getSimpleName());
        }
    }

    public V getView() {
        return mView;
    }

    public M getModle() {
        return mModle;
    }

    public RxManager getRxManager() {
        if (mRxManager == null) {
            mRxManager = new RxManager();
        }
        return mRxManager;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    public void onResume() {}

    public void onPause() {}

    public void onStop() {}

    /**
     * 请求权限组
     *
     * @param observer
     * @param permissions
     */
    public void requestGroupPermissions(GroupPermissionObserver observer, String... permissions) {
        getRxPermission();
        rxPermissions.request(permissions)
                .subscribe(observer);
    }

    /**
     * 请求单个权限
     *
     * @param observer
     * @param permissions
     */
    public void requestEachPermissions(EachPermissionObserver observer, String... permissions) {
        getRxPermission();
        rxPermissions.requestEach(permissions)
                .subscribe(observer);
    }

    private void getRxPermission() {
        if (rxPermissions == null) {
            if (mView instanceof FragmentActivity) {
                rxPermissions = new RxPermissions((FragmentActivity) mView);
            } else if (mView instanceof Fragment) {
                rxPermissions = new RxPermissions((Fragment) mView);
            } else {
                throw new IllegalArgumentException("Failure to create RxPermissions:mView is not FragmentActivity or Fragment " + this.getClass().getSimpleName());
            }
        }
    }

    public void onDestroy() {
        if (mRxManager != null) {
            mRxManager.unsubscribe();
        }
        mView = null;
        mModle = null;
    }
}
