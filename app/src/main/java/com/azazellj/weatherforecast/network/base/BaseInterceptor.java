package com.azazellj.weatherforecast.network.base;

import android.support.annotation.Nullable;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by azazellj on 28.05.16.
 */
public abstract class BaseInterceptor implements Interceptor {
    private HashMap<String, String> mQueryParams = Maps.newHashMap();

    public abstract void init();

    public BaseInterceptor() {
        init();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder builder = request.url().newBuilder();

        //adding params if needed
        if (!mQueryParams.isEmpty()) {
            for (String key : mQueryParams.keySet()) {
                builder.addQueryParameter(key, getQueryParamValue(key));
            }
        }

        HttpUrl url = builder.build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

    @Nullable
    public String getQueryParamValue(String key) {
        return mQueryParams.get(key);
    }

    public boolean containsQueryParam(String key) {
        return mQueryParams.containsKey(key);
    }

    public void addQueryKeyAndValue(String key, String value) {
        if (!containsQueryParam(key)) {
            mQueryParams.put(key, value);
        }
    }
}