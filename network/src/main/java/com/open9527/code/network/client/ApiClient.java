package com.open9527.code.network.client;

import android.util.Log;

import androidx.annotation.NonNull;


import com.open9527.code.network.BuildConfig;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/23 19:12.
 * E-Mail Address ：open_9527@163.com.
 * DESC :ApiClient.
 */
public class ApiClient {
    private static final String TAG = "ApiClient";
    private static final Map<Class, Object> mServices = new HashMap<>();

    private ApiClient() {
    }

    private static final OkHttpClient mClient;
    private static final Retrofit mRetrofit;


    static {
//        File cacheFile = new File(Utils.getApp().getCacheDir(), "hantalk_caheData");
        //设置缓存大小
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 14);//google建议放到这里
        mClient = new OkHttpClient.Builder()
                //断网重连
//                .retryOnConnectionFailure(true)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
//                .cache(cache)//添加缓存
//                .addNetworkInterceptor(new CacheInterceptor())
                //应用拦截器.addInterceptor(mInterceptor)
                //网络拦截器.addNetworkInterceptor(mInterceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AddHeaderInterceptor())
                .addInterceptor(LogInterceptor())
                .proxy(Proxy.NO_PROXY)//避免被抓包
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    /**
     * addHeader
     */
    public static class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            String url = request.url().toString();
            requestBuilder.addHeader(ApiConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
            request = requestBuilder.url(url).build();
            Log.i(TAG, "url--->" + chain.request().url().toString());
            return chain.proceed(request);
        }
    }

    /**
     * log
     */
    public static HttpLoggingInterceptor LogInterceptor() {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> {
//            LogUtils.i("ApiService", "请求信息:----->" + message);
        });
        return logInterceptor;
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getApiService(Class<T> clazz) {
        Object obj = mServices.get(clazz);
        if (obj != null) {
            return (T) obj;
        }
        T t = mRetrofit.create(clazz);
        mServices.put(clazz, t);
        return t;
    }

}
