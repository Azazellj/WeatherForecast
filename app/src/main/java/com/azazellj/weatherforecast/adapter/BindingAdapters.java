package com.azazellj.weatherforecast.adapter;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.azazellj.weatherforecast.Constants;
import com.azazellj.weatherforecast.MainActivity;
import com.azazellj.weatherforecast.data.CityInfoEntity;
import com.azazellj.weatherforecast.network.location.LocationHttpClient;
import com.azazellj.weatherforecast.utils.AppUtils;
import com.azazellj.weatherforecast.view.activity.SearchCitiesActivity;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Response;

/**
 * Created by azazellj on 12.04.16.
 */


@SuppressWarnings("unused")
public class BindingAdapters {
    private static final Response<CityInfoEntity> RESPONSE_EMPTY = null;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageReference) {
        if (imageReference == null) return;

        Log.d("loadImage", imageReference);
        int w = AppUtils.getPxFromDpValue(150);

        LocationHttpClient locationHttpClient = new LocationHttpClient();
        locationHttpClient.getInterceptor().addQueryKeyAndValue("maxwidth", String.valueOf(w));
        locationHttpClient.getInterceptor().addQueryKeyAndValue("photoreference", imageReference);
        OkHttpClient client = locationHttpClient.get();
        Picasso.Builder picassoBuilder = new Picasso.Builder(imageView.getContext());
        picassoBuilder.downloader(new OkHttp3Downloader(client));
        Picasso picasso = picassoBuilder.build();

        picasso.load("https://maps.googleapis.com/maps/api/place/photo").into(imageView);
    }

    @BindingAdapter({"bind:lat", "bind:lng"})
    public static void shortClick(View view, double lat, double lng) {
        view.setOnClickListener(v -> startMapActivity(view, lat, lng));
    }

    private static void startMapActivity(View view, double lat, double lng) {
        if (lat == Constants.POSITION_UNKNOWN && lng == Constants.POSITION_UNKNOWN) return;

        SearchCitiesActivity activity = ((SearchCitiesActivity) view.getContext());
        Intent resultIntent = new Intent(view.getContext(), MainActivity.class);
        resultIntent.putExtra(MainActivity.KEY_LATITUDE, lat);
        resultIntent.putExtra(MainActivity.KEY_LONGITUDE, lng);

        activity.setResult(Activity.RESULT_OK, resultIntent);
        activity.finish();

//        view.getContext().startActivity(resultIntent);
    }
}
