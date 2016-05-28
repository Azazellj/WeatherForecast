package com.azazellj.weatherforecast.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.azazellj.weatherforecast.data.CitiesResponseEntity;
import com.azazellj.weatherforecast.utils.AppUtils;

/**
 * Created by azazellj on 28.05.16.
 */
public class CityPredictionHolder extends BaseHolder<CitiesResponseEntity.Predictions> {
    public CityPredictionHolder(View view) {
        super(view);

        RecyclerView.LayoutParams mParams = (RecyclerView.LayoutParams) getView().getLayoutParams();
        mParams.setMargins(0, 0, 0, AppUtils.getPxFromDpValue(4));
    }
}
