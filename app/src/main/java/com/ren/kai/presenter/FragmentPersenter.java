package com.ren.kai.presenter;

import com.ren.kai.ui.contract.FragmentContract;
import com.ren.kai.entity.Wether;
import com.ren.kai.rx.RxSubscriber;
import com.ren.kai.utils.Logger;

import rx.Subscriber;

/**
 * Created by renyukai on 2017/6/12.
 */

public class FragmentPersenter extends FragmentContract.Presenter {

   @Override
    public void requestWheather() {
      getRxManager().add(getModle().getWhear().subscribe(new RxSubscriber<Wether>() {
          @Override
          public void onStart() {
              super.onStart();
          }

          @Override
          public void onSucceed(Wether wether) {
              getView().showMessage(wether.getWeatherinfo().getCity());
          }

          @Override
          public void onFailed(Throwable e) {
              super.onFailed(e);
          }
      }));
    }

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
