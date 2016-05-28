package com.azazellj.weatherforecast.network.location;

import com.azazellj.weatherforecast.Constants;
import com.azazellj.weatherforecast.data.CitiesResponseEntity;
import com.azazellj.weatherforecast.data.CityInfoEntity;
import com.azazellj.weatherforecast.network.base.BaseAPI;

import java.util.Locale;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by azazellj on 28.05.16.
 */
public class LocationAPI extends BaseAPI<LocationService> {
    @Override
    public void init() {
        LocationHttpClient locationHttpClient = new LocationHttpClient();

        Retrofit instance = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com/")
                .client(locationHttpClient.get())
                .build();

        setRetrofit(instance);

        LocationService locationService = getRetrofit().create(LocationService.class);
        setService(locationService);
    }

    public void getCitiesList(String searchString, Callback<CitiesResponseEntity> callback) {
        getService().getCitiesList(searchString, Constants.PARAM_TYPE, Locale.getDefault().getDisplayLanguage()).enqueue(callback);
    }

    public void getCityDetails(String placeID, Callback<CityInfoEntity> callback) {
        getService().getCityDetails(placeID).enqueue(callback);
    }

    public void getPhoto(int maxWidth, String photoReference, Callback<Object> callback) {
        getService().getPhoto(maxWidth, photoReference).enqueue(callback);
    }

    public void getPhoto(Map<String, String> params, Callback<Object> callback) {
        getService().getPhoto(params).enqueue(callback);
    }
}
