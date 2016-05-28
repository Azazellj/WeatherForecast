package com.azazellj.weatherforecast.network.weather;

import com.azazellj.weatherforecast.network.base.BaseHttpClient;

/**
 * Created by azazellj on 28.05.16.
 */
public class WeatherHttpClient extends BaseHttpClient<WeatherInterceptor> {
    public WeatherHttpClient() {
        setInterceptor(new WeatherInterceptor());
    }
}
