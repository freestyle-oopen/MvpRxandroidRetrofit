package aiyagirl.nanchen.com.myapplication.modle;

import aiyagirl.nanchen.com.myapplication.entity.Wether;
import aiyagirl.nanchen.com.myapplication.rx.RxHelper;
import aiyagirl.nanchen.com.myapplication.ui.contract.FragmentContract;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/7.
 */

public class FragmentTestModle implements FragmentContract.Modle {

    @Override
    public Observable<Wether> getWhear() {
        return RxHelper.wrap(RxHelper.getService()
                .loadWheater(), true);
    }
}
