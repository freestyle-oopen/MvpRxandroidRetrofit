package com.ren.kai.net;

import android.util.Log;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renyukai on 2017/6/12.
 */

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private ServiceInterface serviceInterface;
    private Hashtable<String , String> headers=new Hashtable<>();
    private RetrofitUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.google.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .build();
        serviceInterface=retrofit.create(ServiceInterface.class);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        if (headers.size() > 0) {
                            for (String name : headers.keySet())
                                builder.addHeader(name, headers.get(name));
                        }
                        builder.addHeader("device", "android");
                        return chain.proceed(builder.build());
                    }
                })
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    public static RetrofitUtil getInstance(){
        if(retrofitUtil==null){
            retrofitUtil=new RetrofitUtil();
        }
        return retrofitUtil;
    }


    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
    //不懂
    public class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Log.i("LoggingInterceptor", "inside intercept callback");
            Request request = chain.request();
            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = "\n" + requestLog + "\n" + bodyToString(request);
            }
            Log.d("TAG", "request" + "\n" + requestLog);
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.d("TAG", "response only" + "\n" + bodyString);

            Log.d("TAG", "response" + "\n" + responseLog + "\n" + bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();

        }
    }


    //不懂
    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
