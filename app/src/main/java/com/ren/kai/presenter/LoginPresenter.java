package com.ren.kai.presenter;

import android.Manifest;
import android.util.Log;

import com.ren.kai.rx.rxpermissions.EachPermissionObserver;
import com.ren.kai.ui.contract.LoginContract;

import java.util.List;

public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login() {
        requestEachPermissions(new EachPermissionObserver() {

            @Override
            public void result(List<String> notPassPermission, boolean isRefuse) {
                Log.i("teststest",isRefuse+"=====00000000000000000====="+notPassPermission.size());
            }
        },Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE);
    }
}
