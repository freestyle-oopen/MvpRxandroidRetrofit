package com.ren.kai.modle;

import com.ren.kai.entity.Wether;
import com.ren.kai.ui.contract.Main2Contract;
import com.ren.kai.rx.RxHelper;
import rx.Observable;

public class Main2Modle implements Main2Contract.Modle {
    @Override
    public Observable<Wether> getWhear() {
        return RxHelper.wrap(RxHelper.getService()
                .loadWheater(), true);
    }
}
