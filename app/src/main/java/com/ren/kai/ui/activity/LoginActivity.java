package com.ren.kai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ren.kai.R;
import com.ren.kai.modle.LoginModle;
import com.ren.kai.presenter.LoginPresenter;
import com.ren.kai.ui.contract.LoginContract;


import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModle> implements LoginContract.View,View.OnClickListener {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.but_login)
    Button butLogin;
    @Override
    public int layoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {
        butLogin.setOnClickListener(this);
        getPresenter().login();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
                default:

                    break;
        }
    }
}
