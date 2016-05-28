package com.azazellj.weatherforecast.network.weather;

import com.azazellj.weatherforecast.network.base.BaseInterceptor;

/**
 * Created by azazellj on 28.05.16.
 */
public class WeatherInterceptor extends BaseInterceptor {
    @Override
    public void init() {
        addQueryKeyAndValue("APPID", "7ea42fae6e4aeca75c34bd5416f6a2bc");
        addQueryKeyAndValue("units", "metric");
    }
}
