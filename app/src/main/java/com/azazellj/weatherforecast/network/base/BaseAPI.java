package com.azazellj.weatherforecast.network.base;

import retrofit2.Retrofit;

/**
 * Created by azazellj on 27.05.16.
 */
public abstract class BaseAPI<T> {
    private Retrofit mRetrofit;
    private T mService;

    public abstract void init();

    public BaseAPI() {
        init();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void setRetrofit(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public T getService() {
        return mService;
    }

    public void setService(T mService) {
        this.mService = mService;
    }
}
