package com.gdc.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdc.weather.model.WeatherData;
import com.gdc.weather.service.proxy.model.Forecast;
import com.gdc.weather.ui.UIFormatHelper;

import java.util.List;

/**
 * Created by jaydg on 7/4/2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter {

    private List<WeatherData> forecasts = null;

    public ForecastAdapter(List<WeatherData> forecasts) {
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
        View view = holder.itemView;
        WeatherData forecast = this.forecasts.get(position);
        ImageView imageViewForecastIcon = (ImageView) view.findViewById(R.id.imageViewForecastIcon);
        TextView textViewForecastDay = null;
        TextView textViewForecastCondition;
        TextView textViewForecastHigh;
        TextView textViewForecastLow;

        int conditionCode = forecast.getConditionCode();
        imageViewForecastIcon.setImageResource(
                ConditionsIconResourceLocator.findResourceForConditionsCode(conditionCode));

        textViewForecastDay = (TextView) view.findViewById(R.id.textViewForecastDay);
        textViewForecastCondition = (TextView) view.findViewById(R.id.textViewForecastCondition);
        textViewForecastHigh = (TextView) view.findViewById(R.id.textViewForecastHigh);
        textViewForecastLow = (TextView) view.findViewById(R.id.textViewForecastLow);

        textViewForecastDay.setText(forecast.getDayOfWeek());
        textViewForecastCondition.setText(forecast.getConditionText());
        textViewForecastLow.setText(UIFormatHelper.formatTemperature(forecast.getLowTemperature()));
        textViewForecastHigh.setText(UIFormatHelper.formatTemperature(forecast.getHighTemperature()));
    }

    @Override
    public int getItemCount() {
        if (forecasts == null) {
            return 0;
        } else {
            return forecasts.size();
        }
    }

    // TODO: This is a duplicate. Move to a helper.
    private String formatTemperature(float temperature) {
        return Float.toString(temperature);
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
