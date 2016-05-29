package com.azazellj.weatherforecast.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.azazellj.weatherforecast.R;
import com.azazellj.weatherforecast.data.WeatherResponse;
import com.azazellj.weatherforecast.databinding.LayoutWeatherResultBinding;

/**
 * Created by azazellj on 29.05.16.
 */
public class WeatherDialog extends DialogFragment {
    private WeatherResponse weatherResponse;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme);

        LayoutWeatherResultBinding binding = LayoutWeatherResultBinding.inflate(getActivity().getLayoutInflater());
        binding.setWeather(weatherResponse);
        binding.setWeatherTitle(weatherResponse.getWeather().get(0));

        AlertDialog dialog = new AlertDialog.Builder(contextThemeWrapper)
                .setCustomTitle(null)
                .setView(binding.getRoot())
                .setPositiveButton(contextThemeWrapper.getString(R.string.ok), null)
                .create();

        dialog.show();

        return dialog;
    }

    public void setWeather(WeatherResponse weather) {
        this.weatherResponse = weather;
    }

}
