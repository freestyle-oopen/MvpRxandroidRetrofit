package aiyagirl.nanchen.com.myapplication;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import aiyagirl.nanchen.com.myapplication.ui.fragment.FragmentTest;
import aiyagirl.nanchen.com.myapplication.ui.fragment.FragmentTest2;
import aiyagirl.nanchen.com.myapplication.ui.fragment.FragmentTest3;


public class MainActivity extends AppCompatActivity {

    private FrameLayout content;
    private RadioGroup group;
    private FragmentManager fm;
    FragmentTest fragment1;
    private FragmentTest2 fragment2;
    private FragmentTest3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (FrameLayout) findViewById(R.id.content);
        group = (RadioGroup) findViewById(R.id.tab_group);
        fm = getSupportFragmentManager();
       final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if(fragment1==null){
            fragment1=new FragmentTest();
        }
        fragmentTransaction.replace(R.id.content,fragment1 , "1").commit();

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
                        fragmentTransaction1.replace(R.id.content,fragment1 , "1").commit();
                        break;
                    case R.id.tab2:
                        if(fragment2==null){
                            fragment2=new FragmentTest2();
                        }
                        fragmentTransaction1.replace(R.id.content,fragment2 , "2").commit();
                        break;
                    case R.id.tab3:
                        if(fragment3==null){
                            fragment3=new FragmentTest3();
                        }
                        fragmentTransaction1.replace(R.id.content,fragment3 , "3").commit();
                        break;
                }
            }
        });
    }
}
