package com.ren.kai.ui.activity;

import android.os.Bundle;

import com.ren.kai.R;
import com.ren.kai.utils.Toastor;
import com.ren.kai.modle.Main2Modle;
import com.ren.kai.presenter.Main2Presenter;
import com.ren.kai.ui.contract.Main2Contract;

public class Main2Activity extends BaseActivity<Main2Presenter,Main2Modle> implements Main2Contract.View {

    @Override
    public int layoutResId() {
        return R.layout.activity_main2;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {
        getmPresenter().requestWheather();
    }


    @Override
    public void showMessage(String msg) {
        Toastor.show(msg);
    }

}
