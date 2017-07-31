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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForecast extends Fragment {

    private List<WeatherData> forecasts;
    private RecyclerView recyclerViewForecasts;

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

        recyclerViewForecasts = (RecyclerView) view.findViewById(R.id.recyclerViewForecast);
        return view;
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
