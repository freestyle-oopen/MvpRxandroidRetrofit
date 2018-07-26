package com.ren.kai;


import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.ren.kai.ui.fragment.FragmentTest;
import com.ren.kai.ui.fragment.FragmentTest2;
import com.ren.kai.ui.fragment.FragmentTest3;
import com.ren.kai.utils.Logger;


public class MainActivity extends AppCompatActivity {

    private RadioGroup group;
    private FragmentManager fm;
    private FragmentTest fragment1;
    private FragmentTest2 fragment2;
    private FragmentTest3 fragment3;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.argb(100,0,0,0));
        }
        setContentView(R.layout.activity_main);
        group = (RadioGroup) findViewById(R.id.tab_group);
        fm = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
         fragment1=new FragmentTest();
         fragmentTransaction.add(R.id.content,fragment1,"1").commit();
         currentFragment=fragment1;
        group.check(R.id.tab1);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                switch (i){
                    case R.id.tab1:
                        if(fragment1==null){
                            fragment1=new FragmentTest();
                        }
                        if(fragment1.isAdded()){
                            fragmentTransaction1.hide(currentFragment).show(fragment1).commit();
                        }else{
                            fragmentTransaction1.hide(currentFragment).add(R.id.content,fragment1,"1").commit();
                        }
                        currentFragment=fragment1;
                        break;
                    case R.id.tab2:
                        if(fragment2==null){
                            fragment2=new FragmentTest2();
                        }
                        if(fragment2.isAdded()){
                            fragmentTransaction1.hide(currentFragment).show(fragment2).commit();
                        }else{
                            fragmentTransaction1.hide(currentFragment).add(R.id.content,fragment2,"2").commit();
                        }
                        currentFragment=fragment2;
                        break;
                    case R.id.tab3:
                        if(fragment3==null){
                            fragment3=new FragmentTest3();
                        }
                        if(fragment3.isAdded()){
                            fragmentTransaction1.hide(currentFragment).show(fragment3).commit();
                        }else{
                            fragmentTransaction1.hide(currentFragment).add(R.id.content,fragment3,"3").commit();
                        }
                        currentFragment=fragment3;
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        fragment1=null;
        fragment2=null;
        fragment3=null;
        super.onDestroy();
    }
}
