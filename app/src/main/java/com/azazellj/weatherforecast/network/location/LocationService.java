package com.azazellj.weatherforecast.network.location;

import com.azazellj.weatherforecast.data.CitiesResponseEntity;
import com.azazellj.weatherforecast.data.CityInfoEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by azazellj on 27.05.16.
 */
public interface LocationService {
    @GET("/maps/api/place/autocomplete/json")
    Call<CitiesResponseEntity> getCitiesList(@Query("input") String searchString, @Query("types") String types, @Query("language") String language);

    @GET("/maps/api/place/details/json")
    Call<CityInfoEntity> getCityDetails(@Query("placeid") String placeID);

    @GET("/maps/api/place/photo")
    Call<Object> getPhoto(@Query("maxwidth") int maxWidth, @Query("photoreference") String photoReference);

    @GET("/maps/api/place/photo")
    Call<Object> getPhoto(@QueryMap Map<String, String> params);
}
