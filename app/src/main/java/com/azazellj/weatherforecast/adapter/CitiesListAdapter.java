package com.azazellj.weatherforecast.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azazellj.weatherforecast.BR;
import com.azazellj.weatherforecast.R;
import com.azazellj.weatherforecast.adapter.holder.CityPredictionHolder;
import com.azazellj.weatherforecast.data.CitiesResponseEntity;
import com.azazellj.weatherforecast.data.CityInfoEntity;
import com.azazellj.weatherforecast.network.location.LocationAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by azazellj on 28.05.16.
 */
public class CitiesListAdapter extends BaseAdapter<CitiesResponseEntity.Predictions, CityPredictionHolder> {
    private static final Response<CityInfoEntity> RESPONSE_EMPTY = null;

    @Override
    public CityPredictionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_city, parent, false);

        return new CityPredictionHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CityPredictionHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        CitiesResponseEntity.Predictions item = getItem(position);

        getDetailedInfo(binding, item.getPlaceId());

        binding.setVariable(BR.showLoaders, true);
        binding.setVariable(BR.cityName, item.getDescription());
        binding.executePendingBindings();
    }

    private void getDetailedInfo(ViewDataBinding binding, String placeID) {
        new LocationAPI().getCityDetails(placeID, new Callback<CityInfoEntity>() {
            @Override
            public void onResponse(Call<CityInfoEntity> call, Response<CityInfoEntity> response) {
                parseResponse(response, binding);
            }

            @Override
            public void onFailure(Call<CityInfoEntity> call, Throwable t) {
                parseResponse(RESPONSE_EMPTY, binding);
            }
        });
    }

    private void parseResponse(Response<CityInfoEntity> response, ViewDataBinding binding) {
        if (response == RESPONSE_EMPTY || !response.isSuccessful()) {
            Toast.makeText(binding.getRoot().getContext(), R.string.request_error, Toast.LENGTH_SHORT).show();
            return;
        }

        CityInfoEntity cityInfo = response.body();
        List<CityInfoEntity.Result.Photos> photos = cityInfo.getResult().getPhotos();
        boolean isPhotoAvailable = photos != null && !photos.isEmpty();

        binding.setVariable(BR.showLoaders, false);
        binding.setVariable(BR.location, cityInfo.getResult().getGeometry().getLocation());
        binding.setVariable(BR.isPhotoAvailable, isPhotoAvailable);

        if (isPhotoAvailable) {
            binding.setVariable(BR.imagePath, photos.get(0).getPhotoReference());
        }

        binding.executePendingBindings();
    }

}
