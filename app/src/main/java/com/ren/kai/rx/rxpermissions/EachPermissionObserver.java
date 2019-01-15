package com.ren.kai.rx.rxpermissions;


import com.tbruyelle.rxpermissions2.Permission;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class EachPermissionObserver implements Observer<Permission> {
    private List<String> notPassPermission;
    private boolean isRefuse=false;
    @Override
    public void onSubscribe(Disposable d) {
        notPassPermission=new ArrayList<>();
    }

    @Override
    public void onNext(Permission permission) {
        if (permission.granted) {
            //用户已经同意该权限
        } else if (permission.shouldShowRequestPermissionRationale) {
            notPassPermission.add(permission.name);
            //用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
        } else {
            notPassPermission.add(permission.name);
            isRefuse=true;
            //用户拒绝了该权限，并且选中『不再询问』
        }
    }
    @Override
    public void onError(Throwable e) {
        result(null,isRefuse);
    }

    @Override
    public void onComplete() {
        result(notPassPermission,isRefuse);
    }

    /**
     * 统一结果返回
     * @param notPassPermission
     */
    public abstract void result(List<String> notPassPermission,boolean isRefuse);
}
