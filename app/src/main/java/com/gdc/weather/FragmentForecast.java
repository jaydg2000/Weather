package com.gdc.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.weather.model.Forecast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForecast extends Fragment {

    public FragmentForecast() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_forecast, container, false);

        RecyclerView recyclerViewForecasts =
                (RecyclerView) view.findViewById(R.id.recyclerViewForecast);

        List<Forecast> forecasts = new ArrayList<>();
        for( int c=0; c<7; c++) {
            forecasts.add(new Forecast());
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewForecasts.setLayoutManager(layoutManager);
        recyclerViewForecasts.setAdapter(new ForecastAdapter(forecasts));
        return view;
    }
}
