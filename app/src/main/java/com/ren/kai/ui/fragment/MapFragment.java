package com.ren.kai.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.ren.kai.app.App;
import com.ren.kai.ui.contract.MapContract;
import com.ren.kai.R;
import com.ren.kai.modle.MapModle;
import com.ren.kai.presenter.MapPresenter;
import com.ren.kai.utils.Logger;
import com.ren.kai.utils.Toastor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import butterknife.BindView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by renyukai on 2017/6/7.
 */

public class MapFragment extends BaseFragment<MapPresenter,MapModle> implements MapContract.View {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @Override
    public int layoutResId() {
        Logger.i("test","===========layoutResId============");
        return R.layout.map_fragment;
    }

    @Override
    public void onBind(Bundle savedInstanceState) {
        Logger.i("test","===========onBind============");
     // getmPresenter().requestWheather();
      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              App.getApp().removeAllExclude(getActivity());
          }
      });
      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              App.getApp().exitAPP();
          }
      });
      button3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
/*            String path=  Environment.getExternalStorageDirectory().getPath()+"/kfysy/";
              File file = new File(path);
             File[] files={file};
              Observable<File> from1 = Observable.from(files);
              from1.flatMap(new Func1<File, Observable<File>>() {
                          @Override
                          public Observable<File> call(File file) {
                              return Observable.from(file.listFiles());
                          }
                      })
                      .filter(new Func1<File, Boolean>() {
                          @Override
                          public Boolean call(File file) {
                              return file.getName().endsWith(".txt");
                          }
                      })
                      .map(new Func1<File, String>() {
                          @Override
                          public String call(File file) {
                              String s=null;
                              try {
                                  FileReader fr=new FileReader(file);
                                  BufferedReader br=new BufferedReader(fr);
                                   s = br.readLine();
                              } catch (FileNotFoundException e) {
                                  e.printStackTrace();

                              } catch (IOException e) {
                                  e.printStackTrace();

                              }
                              return s;
                          }
                      })
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Action1<String>() {
                          @Override
                          public void call(String bitmap) {
                              Logger.i("tests","==s="+bitmap);
                              Toastor.show(bitmap);
                          }
                      });*/
          }
      });
    }
}

