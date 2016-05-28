package com.azazellj.weatherforecast.network.weather;

import com.azazellj.weatherforecast.data.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by azazellj on 27.05.16.
 */
public interface WeatherService {
    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("lat") double lat, @Query("lon") double lng);
}
