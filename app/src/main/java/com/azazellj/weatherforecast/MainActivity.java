package com.azazellj.weatherforecast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.azazellj.weatherforecast.data.WeatherResponse;
import com.azazellj.weatherforecast.network.weather.WeatherAPI;
import com.azazellj.weatherforecast.view.activity.SearchCitiesActivity;
import com.azazellj.weatherforecast.view.dialog.WeatherDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        TabLayout.OnTabSelectedListener {

    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    private static final int TAB_CUSTOM = 0;
    private static final int TAB_GEO = 1;
    private static final int TAB_SEARCH = 2;

    private static final int CODE_SEARCH = 100;

    private double latitude = Constants.POSITION_UNKNOWN;
    private double longitude = Constants.POSITION_UNKNOWN;
    private boolean searchWithGeo = false;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @InjectView(R.id.toolbar)
    public Toolbar mToolBar;
    @InjectView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @InjectView(R.id.fab)
    public FloatingActionButton fab;

    private Marker mMarker;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        mToolBar.setNavigationOnClickListener(view -> finish());

        fab.setOnClickListener(view -> getWeather());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mTabLayout.setOnTabSelectedListener(this);
    }

    private void getWeather() {
        if (getMarker() == null) {
            Toast.makeText(getBaseContext(), R.string.choose_location, Toast.LENGTH_SHORT).show();
            return;
        }

        new WeatherAPI().getWeather(getMarker().getPosition().latitude, getMarker().getPosition().longitude,
                new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        WeatherDialog weatherDialog = new WeatherDialog();
                        weatherDialog.setWeather(response.body());
                        weatherDialog.show(getFragmentManager(), weatherDialog.getTag());
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        Toast.makeText(getBaseContext(), R.string.request_error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case TAB_GEO:
                initLocationListener();
                break;
            case TAB_SEARCH:
                removeLocationListener();
                startActivityForResult(new Intent(this, SearchCitiesActivity.class), CODE_SEARCH);
                break;
            case TAB_CUSTOM:
                removeLocationListener();
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setMap(googleMap);
        googleMap.setOnMapLongClickListener(this);

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //skipping now
            return;
        }

        if (searchWithGeo) {
            initLocationListener();
        }
    }

    private LocationListener mLocationListener = location -> {
        if (location != null) {
            setMarker(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    };

    private void initLocationListener() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(6000);

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //skipping now
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location != null) {
            setMarker(new LatLng(location.getLatitude(), location.getLongitude()));
            getMarker().setVisible(false);
        }
    }

    private void removeLocationListener() {
        disableGeoSearch();

        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected() || mGoogleApiClient.isConnecting())
            return;

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, mLocationListener);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //skipping now
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, mLocationListener);
        } else {
            setMarker(new LatLng(location.getLatitude(), location.getLongitude()));
            getMarker().setVisible(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_SEARCH && resultCode == RESULT_OK) {
            latitude = data.getDoubleExtra(KEY_LATITUDE, Constants.POSITION_UNKNOWN);
            longitude = data.getDoubleExtra(KEY_LONGITUDE, Constants.POSITION_UNKNOWN);

            setMarker(new LatLng(latitude, longitude));
            getWeather();
        }


        if (resultCode != RESULT_OK) {
            setTab(TAB_CUSTOM);
        }
    }

    private void setTab(int tabId) {
        mTabLayout.getTabAt(tabId).select();
    }

    //simple//
    private GoogleMap getMap() {
        return this.mGoogleMap;
    }

    private void setMap(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
    }

    private void animateCameraToPlace() {
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(getMarker().getPosition(), 15.0f));
    }

    private Marker getMarker() {
        return this.mMarker;
    }

    private void setMarker(LatLng latLng) {
        removeMarker();

        mMarker = getMap().addMarker(new MarkerOptions().position(latLng));
        animateCameraToPlace();
    }

    private void removeMarker() {
        if (mMarker != null) mMarker.remove();
        mMarker = null;
    }

    private void disableGeoSearch() {
        searchWithGeo = false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        setTab(TAB_CUSTOM);
        setMarker(latLng);
        getWeather();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getPosition() == TAB_SEARCH) {
            onTabSelected(tab);
        }
    }

    //unused//
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //empty now
    }

    @Override
    public void onConnectionSuspended(int i) {
        //empty now
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //empty now
    }
}
