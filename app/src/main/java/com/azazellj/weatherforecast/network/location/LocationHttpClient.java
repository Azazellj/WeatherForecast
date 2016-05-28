package com.azazellj.weatherforecast.network.location;

import com.azazellj.weatherforecast.network.base.BaseHttpClient;

/**
 * Created by azazellj on 28.05.16.
 */
public class LocationHttpClient extends BaseHttpClient<LocationInterceptor> {
    public LocationHttpClient() {
        setInterceptor(new LocationInterceptor());
    }
}
