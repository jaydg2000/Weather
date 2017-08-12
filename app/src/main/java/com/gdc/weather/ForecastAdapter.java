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

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ForecastViewHolder viewHolder = (ForecastViewHolder) holder;
        WeatherData forecast = this.forecasts.get(position);

        int conditionCode = forecast.getConditionCode();
        viewHolder.imageViewForecastIcon.setImageResource(
                ConditionsIconResourceLocator.findResourceForConditionsCode(conditionCode));

        viewHolder.textViewForecastDay.setText(forecast.getDayOfWeek());
        viewHolder.textViewForecastCondition.setText(forecast.getConditionText());
        viewHolder.textViewForecastLow.setText(UIFormatHelper.formatTemperature(forecast.getLowTemperature()));
        viewHolder.textViewForecastHigh.setText(UIFormatHelper.formatTemperature(forecast.getHighTemperature()));
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
        @BindView(R.id.imageViewForecastIcon)
        ImageView imageViewForecastIcon;
        @BindView(R.id.textViewForecastDay)
        TextView textViewForecastDay;
        @BindView(R.id.textViewForecastCondition)
        TextView textViewForecastCondition;
        @BindView(R.id.textViewForecastHigh)
        TextView textViewForecastHigh;
        @BindView(R.id.textViewForecastLow)
        TextView textViewForecastLow;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}