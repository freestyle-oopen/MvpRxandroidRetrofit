package com.ren.kai.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ren.kai.ui.contract.FragmentContract;
import com.ren.kai.R;
import com.ren.kai.modle.FragmentTestModle;
import com.ren.kai.presenter.FragmentPersenter;
import com.ren.kai.ui.activity.Main2Activity;
import com.ren.kai.utils.Logger;
import com.ren.kai.utils.Toastor;
import butterknife.BindView;

/**
 * Created by renyukai on 2017/6/7.
 */

public class FragmentTest extends BaseFragment<FragmentPersenter ,FragmentTestModle> implements FragmentContract.View {

    @Override
    public int layoutResId() {
        Logger.i("test","===========layoutResId============");
        return R.layout.test1;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {

        Logger.i("test","===========onBind============");
      getmPresenter().requestWheather();
    }

    @Override
    public void showMessage(String msg) {
        Toastor.show(msg);
    }




}
