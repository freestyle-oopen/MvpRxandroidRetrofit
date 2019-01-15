package com.ren.kai.ui.fragment;

import android.os.Bundle;

import com.ren.kai.R;
import com.ren.kai.modle.UserModle;
import com.ren.kai.presenter.UserPresenter;
import com.ren.kai.ui.contract.UserContract;

/**
 * Created by Administrator on 2017/6/7.
 */

public class UserFragment extends BaseFragment<UserPresenter, UserModle> implements UserContract.View {
    @Override
    public int layoutResId() {
        return R.layout.user_fragment;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {

    }

}
