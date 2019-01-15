package com.ren.kai.net;

import com.ren.kai.entity.HttpResult;
import com.ren.kai.entity.Wether;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by renyukai on 2017/6/12.
 */

public interface ServiceInterface {

    @GET("data/sk/101010100.html")
    Observable<HttpResult<Wether>> loadWheaters();
}
