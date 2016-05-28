package com.azazellj.weatherforecast.network.base;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by azazellj on 28.05.16.
 */
public abstract class BaseHttpClient<I extends Interceptor> {
    private I mInterceptor;

    public OkHttpClient get() {
        return new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
    }

    public I getInterceptor() {
        return mInterceptor;
    }

    public void setInterceptor(I mInterceptor) {
        this.mInterceptor = mInterceptor;
    }
}
