package aiyagirl.nanchen.com.myapplication.net;

import aiyagirl.nanchen.com.myapplication.entity.Wether;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by renyukai on 2017/6/12.
 */

public interface ServiceInterface {

    @GET("data/sk/101010100.html")
   Observable<Wether> loadWheater();
}
