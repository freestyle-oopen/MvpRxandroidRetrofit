package aiyagirl.nanchen.com.myapplication.ui.contract;

import aiyagirl.nanchen.com.myapplication.entity.Wether;
import aiyagirl.nanchen.com.myapplication.modle.BaseModle;
import aiyagirl.nanchen.com.myapplication.presenter.BasePresenter;
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
