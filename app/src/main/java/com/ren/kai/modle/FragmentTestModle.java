package com.ren.kai.modle;

import com.ren.kai.ui.contract.FragmentContract;
import com.ren.kai.entity.Wether;
import com.ren.kai.rx.RxHelper;
import rx.Observable;

/**
 * Created by Renyukai on 2017/9/7.
 */

public class FragmentTestModle implements FragmentContract.Modle {

    @Override
    public Observable<Wether> getWhear() {
        return RxHelper.wrap(RxHelper.getService().loadWheater(), true);
    }
}
