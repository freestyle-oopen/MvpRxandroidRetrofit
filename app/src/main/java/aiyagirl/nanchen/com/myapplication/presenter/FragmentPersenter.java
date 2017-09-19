package aiyagirl.nanchen.com.myapplication.presenter;

import aiyagirl.nanchen.com.myapplication.entity.Wether;
import aiyagirl.nanchen.com.myapplication.rx.RxSubscriber;
import aiyagirl.nanchen.com.myapplication.ui.contract.FragmentContract;

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
}
