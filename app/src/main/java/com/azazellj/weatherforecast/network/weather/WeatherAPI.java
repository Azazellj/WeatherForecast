package com.azazellj.weatherforecast.network.weather;

import com.azazellj.weatherforecast.data.WeatherResponse;
import com.azazellj.weatherforecast.network.base.BaseAPI;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by azazellj on 28.05.16.
 */
public class WeatherAPI extends BaseAPI<WeatherService> {
    @Override
    public void init() {
        WeatherHttpClient weatherHttpClient = new WeatherHttpClient();

        Retrofit instance = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/")
                .client(weatherHttpClient.get())
                .build();

        setRetrofit(instance);

        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        setService(weatherService);
    }

    public void getWeather(double lat, double lng, Callback<WeatherResponse> callback) {
        getService().getWeather(lat, lng).enqueue(callback);
    }
}
