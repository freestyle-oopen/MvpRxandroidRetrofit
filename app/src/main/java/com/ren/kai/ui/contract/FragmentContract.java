package com.ren.kai.ui.contract;

import com.ren.kai.entity.Wether;
import com.ren.kai.modle.BaseModle;
import com.ren.kai.presenter.BasePresenter;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/12.
 */

public class FragmentContract {
    public interface Modle extends BaseModle{
     public abstract Observable<Wether>  getWhear();
    }

    public interface View extends BaseView {

    }

   public abstract static class Presenter extends BasePresenter<View, Modle> {

        public abstract void requestWheather();
    }
}
