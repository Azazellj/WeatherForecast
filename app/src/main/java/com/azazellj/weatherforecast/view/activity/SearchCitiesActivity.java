package com.azazellj.weatherforecast.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.azazellj.weatherforecast.R;
import com.azazellj.weatherforecast.adapter.CitiesListAdapter;
import com.azazellj.weatherforecast.data.CitiesResponseEntity;
import com.azazellj.weatherforecast.network.location.LocationAPI;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCitiesActivity extends AppCompatActivity {
    private final static Response<CitiesResponseEntity> RESPONSE_EMPTY = null;

    @InjectView(R.id.searchCitiesFieldLayout)
    public TextInputLayout mSearchCitiesFieldLayout;
    @InjectView(R.id.searchCitiesField)
    public EditText mSearchCitiesField;
    @InjectView(R.id.searchCitiesRecycleView)
    public RecyclerView mSearchCitiesRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cities);
        ButterKnife.inject(this);

        LinearLayoutManager commentsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearchCitiesRecycleView.setLayoutManager(commentsLayoutManager);

        mSearchCitiesField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getCitiesList(s.toString());
            }
        });
    }

    private void hideResults() {
        mSearchCitiesRecycleView.setVisibility(View.GONE);
    }

    private void showResults() {
        mSearchCitiesRecycleView.setVisibility(View.VISIBLE);
    }


    private void getCitiesList(String search) {
        hideResults();

        new LocationAPI().getCitiesList(search, new Callback<CitiesResponseEntity>() {
            @Override
            public void onResponse(Call<CitiesResponseEntity> call, Response<CitiesResponseEntity> response) {
                parseResponse(response);
            }

            @Override
            public void onFailure(Call<CitiesResponseEntity> call, Throwable t) {
                parseResponse(RESPONSE_EMPTY);
            }
        });
    }

    private void parseResponse(Response<CitiesResponseEntity> response) {
        if (response == RESPONSE_EMPTY || !response.isSuccessful()) {
            Toast.makeText(this, R.string.request_error, Toast.LENGTH_SHORT).show();
            return;
        }


        List<CitiesResponseEntity.Predictions> predictions = response.body().getPredictions();
        CitiesListAdapter citiesListAdapter = new CitiesListAdapter();
        citiesListAdapter.setItems(predictions);
        mSearchCitiesRecycleView.setAdapter(citiesListAdapter);

        showResults();
    }
}
