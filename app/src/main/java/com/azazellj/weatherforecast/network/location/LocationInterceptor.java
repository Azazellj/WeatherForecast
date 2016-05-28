package com.azazellj.weatherforecast.network.location;

import com.azazellj.weatherforecast.network.base.BaseInterceptor;

/**
 * Created by azazellj on 28.05.16.
 */
public class LocationInterceptor extends BaseInterceptor {
    @Override
    public void init() {
        addQueryKeyAndValue("key", "AIzaSyDs0QWe-g9zDK8aw9KZSRv_0VDqDHhcnI8");
    }
}
