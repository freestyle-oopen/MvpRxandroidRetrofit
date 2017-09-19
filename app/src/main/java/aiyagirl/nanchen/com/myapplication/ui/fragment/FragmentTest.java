package aiyagirl.nanchen.com.myapplication.ui.fragment;

import android.os.Bundle;

import aiyagirl.nanchen.com.myapplication.R;
import aiyagirl.nanchen.com.myapplication.entity.Wether;
import aiyagirl.nanchen.com.myapplication.modle.FragmentTestModle;
import aiyagirl.nanchen.com.myapplication.presenter.FragmentPersenter;
import aiyagirl.nanchen.com.myapplication.ui.contract.FragmentContract;
import aiyagirl.nanchen.com.myapplication.utils.Toastor;

/**
 * Created by renyukai on 2017/6/7.
 */

public class FragmentTest extends BaseFragment<FragmentPersenter ,FragmentTestModle> implements FragmentContract.View{


    @Override
    public int layoutResId() {
        return R.layout.test1;
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
