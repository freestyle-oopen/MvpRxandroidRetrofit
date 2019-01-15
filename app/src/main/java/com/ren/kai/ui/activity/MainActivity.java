package com.ren.kai.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ren.kai.R;
import com.ren.kai.app.App;
import com.ren.kai.modle.MainModle;
import com.ren.kai.presenter.MainPresenter;
import com.ren.kai.ui.contract.MainContract;
import com.ren.kai.ui.fragment.InspectionFragment;
import com.ren.kai.ui.fragment.MapFragment;
import com.ren.kai.ui.fragment.UserFragment;
import com.ren.kai.utils.DisplayUtils;
import com.ren.kai.utils.Toastor;

public class MainActivity extends BaseActivity<MainPresenter,MainModle> implements MainContract.View{

    private FragmentManager fm;
    private MapFragment mMapFragment;
    private InspectionFragment mInspectionFragment;
    private UserFragment mUserFragment;
    private Fragment currentFragment;
    private long exitTime=0L;
    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {
        RadioGroup group = findViewById(R.id.tab_group);
        fm = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        mMapFragment = new MapFragment();
        fragmentTransaction.add(R.id.content, mMapFragment, "1").commit();
        currentFragment = mMapFragment;
        group.check(R.id.tab1);

        initTabImg(DisplayUtils.dp2px(this, 23));//设置tab图片大小为25dp

        group.setOnCheckedChangeListener(rgListener);
    }

    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
            switch (i) {
                case R.id.tab1:
                    if (mMapFragment == null) {
                        mMapFragment = new MapFragment();
                    }
                    if (mMapFragment.isAdded()) {
                        fragmentTransaction1.hide(currentFragment).show(mMapFragment).commit();
                    } else {
                        fragmentTransaction1.hide(currentFragment).add(R.id.content, mMapFragment, "1").commit();
                    }
                    currentFragment = mMapFragment;
                    break;
                case R.id.tab2:
                    if (mInspectionFragment == null) {
                        mInspectionFragment = new InspectionFragment();
                    }
                    if (mInspectionFragment.isAdded()) {
                        fragmentTransaction1.hide(currentFragment).show(mInspectionFragment).commit();
                    } else {
                        fragmentTransaction1.hide(currentFragment).add(R.id.content, mInspectionFragment, "2").commit();
                    }
                    currentFragment = mInspectionFragment;
                    break;
                case R.id.tab3:
                    if (mUserFragment == null) {
                        mUserFragment = new UserFragment();
                    }
                    if (mUserFragment.isAdded()) {
                        fragmentTransaction1.hide(currentFragment).show(mUserFragment).commit();
                    } else {
                        fragmentTransaction1.hide(currentFragment).add(R.id.content, mUserFragment, "3").commit();
                    }
                    currentFragment = mUserFragment;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toastor.show("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                App.getApp().exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置tab图片的大小
     */
    private void initTabImg(int pxSize) {
        //定义底部标签图片大小
        Drawable drawable1 = getResources().getDrawable(R.drawable.tab_home);
        drawable1.setBounds(0, 0, pxSize, pxSize);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        RadioButton tab1 = findViewById(R.id.tab1);
        tab1.setCompoundDrawables(null, drawable1, null, null);//只放上面
        Drawable drawable2 = getResources().getDrawable(R.drawable.tab_home);
        drawable2.setBounds(0, 0, pxSize, pxSize);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        RadioButton tab2 = findViewById(R.id.tab2);
        tab2.setCompoundDrawables(null, drawable2, null, null);//只放上面
        Drawable drawable3 = getResources().getDrawable(R.drawable.tab_home);
        drawable3.setBounds(0, 0, pxSize, pxSize);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        RadioButton tab3 = findViewById(R.id.tab3);
        tab3.setCompoundDrawables(null, drawable3, null, null);//只放上面
    }

    @Override
    protected void onDestroy() {
        mMapFragment = null;
        mInspectionFragment = null;
        mUserFragment = null;
        super.onDestroy();
    }

}
