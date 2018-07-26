package com.ren.kai.presenter;

import com.ren.kai.entity.Wether;
import com.ren.kai.rx.RxSubscriber;
import com.ren.kai.ui.contract.Main2Contract;
import com.ren.kai.utils.Logger;

public class Main2Presenter extends Main2Contract.Presenter {
    @Override
    public void requestWheather() {
        getRxManager().add(getModle().getWhear().subscribe(new RxSubscriber<Wether>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSucceed(Wether wether) { Logger.i("hahaha","===============4444================"+getView());
                getView().showMessage(wether.getWeatherinfo().getCity());

            }

            @Override
            public void onFailed(Throwable e) {
                Logger.i("hahaha","===============3333================="+getView());
                getView().showMessage("sssssssssss");
            }
        }));
    }

    @Override
    public void onDestroy() {
        Logger.i("hahaha","===============00000================="+getView());
        super.onDestroy();
        Logger.i("hahaha","===============11111================="+getView());
    }
}
