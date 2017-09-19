package aiyagirl.nanchen.com.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import aiyagirl.nanchen.com.myapplication.modle.BaseModle;
import aiyagirl.nanchen.com.myapplication.presenter.BasePresenter;
import aiyagirl.nanchen.com.myapplication.utils.InstanceUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by renyukai on 2017/6/13.
 */

public abstract class BaseActivity<P extends BasePresenter , M extends BaseModle> extends AppCompatActivity {
    private P mPresenter;
    private Unbinder mBind;

    public abstract int layoutResId();
    public abstract void onBind(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(layoutResId()==0){
            throw new RuntimeException("Activity--No layout is set");
        }
        setContentView(layoutResId());
        this.onBind(savedInstanceState);
        mPresenter = InstanceUtils.getInstance(this, 0);
        if (mPresenter != null) {
            mPresenter.onCreate(this);
        }
    }



    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mBind = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mBind = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mPresenter != null){
            mPresenter.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        if (mPresenter != null) mPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mPresenter != null) mPresenter.onPause();
        super.onPause();
    }


    @Override
    protected void onStop() {
        if (mPresenter != null) mPresenter.onStop();
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        if (mBind != null) mBind.unbind();
        super.onDestroy();
    }
}
