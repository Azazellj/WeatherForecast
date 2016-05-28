package com.azazellj.weatherforecast.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.azazellj.weatherforecast.Constants;
import com.azazellj.weatherforecast.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MapActivity extends AppCompatActivity
//        implements OnMapReadyCallback,
//        GoogleMap.OnMapLongClickListener,
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener
{

    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_GEO = "geo";

    private double latitude = Constants.POSITION_UNKNOWN;
    private double longitude = Constants.POSITION_UNKNOWN;
    private boolean search = false;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @InjectView(R.id.toolbar)
    public Toolbar mToolBar;
    private Marker mMarker;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.inject(this);

        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        mToolBar.setNavigationOnClickListener(view -> finish());

        latitude = getIntent().getDoubleExtra(KEY_LATITUDE, Constants.POSITION_UNKNOWN);
        longitude = getIntent().getDoubleExtra(KEY_LONGITUDE, Constants.POSITION_UNKNOWN);
        search = getIntent().getBooleanExtra(KEY_GEO, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }


}
