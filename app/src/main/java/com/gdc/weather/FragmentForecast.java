package com.gdc.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.weather.model.WeatherData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForecast extends Fragment {

    private Unbinder unbinder;
    private List<WeatherData> forecasts;
    @BindView(R.id.recyclerViewForecast) RecyclerView recyclerViewForecasts;

    public FragmentForecast() {
        forecasts = new ArrayList<>();
    }

    public void setForecasts(List<WeatherData> forecasts) {
        this.forecasts = forecasts;
        bindData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void bindData() {
        if (!canBindData()) {
            return;
        }

        recyclerViewForecasts.setAdapter(new ForecastAdapter(this.forecasts));
        recyclerViewForecasts.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private boolean canBindData() {
        return (this.forecasts != null && this.isResumed());
    }
}
