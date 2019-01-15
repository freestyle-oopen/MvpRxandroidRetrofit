package com.ren.kai.presenter;

import android.util.Log;

import com.ren.kai.ui.contract.MapContract;
import com.ren.kai.entity.Wether;
import com.ren.kai.rx.RxSubscriber;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by renyukai on 2017/6/12.
 */

public class MapPresenter extends MapContract.Presenter {

  /* @Override
    public void requestWheather() {
       Wether wether=new Wether();

       Subscription subscribe = Observable.just(wether).doOnNext(new Action1<Wether>() {
           @Override
           public void call(Wether wether) {

           }
       }).subscribe(new Subscriber<Wether>() {
           @Override
           public void onCompleted() {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(Wether wether) {

           }
       });
       subscribe.unsubscribe();*/
       /*Observable<Wether> whear = getModle().getWhear();
       whear.subscribe(new Action1<Wether>() {
           @Override
           public void call(Wether wether) {

           }
       });
       Subscription subscribe = getModle().getWhear().subscribe(new Observer<Wether>() {
           @Override
           public void onCompleted() {

           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(Wether wether) {

           }
       });*/
      /* getRxManager().add(getModle().getWhear().subscribe(new RxSubscriber<Wether>() {
          @Override
          public void onStart() {
              super.onStart();
          }

          @Override
          public void onSucceed(Wether wether) {

          }

          @Override
          public void onFailed(Throwable e) {
              super.onFailed(e);
          }
      }));
    }*/

   /* @Override
    public void requestWheather() {
        getRxManager().add(getModle().getWhear().subscribe(new Subscriber<Wether>() {
            @Override
            public void onCompleted() {
                Logger.i("nettest","=======onCompleted====="+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("nettest","=======onError====="+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Wether wether) {
                Logger.i("nettest","=======onNext====="+Thread.currentThread().getName());
            }

            @Override
            public void onStart() {
                Logger.i("nettest","=======onStart====="+Thread.currentThread().getName());
            }

        }));
    }*/
}
