package com.gdc.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.weather.model.Forecast;

import java.util.List;

/**
 * Created by jaydg on 7/4/2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter {

    private List<Forecast> forecasts = null;

    public ForecastAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_forcast_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // bind data to layout
    }

    @Override
    public int getItemCount() {
        if (forecasts == null) {
            return 0;
        } else {
            return forecasts.size();
        }
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
