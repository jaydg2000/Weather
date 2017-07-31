package com.gdc.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdc.weather.model.CurrentDayConditions;

/**
 * Created by jaydg on 7/3/2017.
 */

public class FragmentCurrentDayConditions extends Fragment {
    private CurrentDayConditions currentDayConditions;
    private ImageView imageViewConditionsIcon;
    private TextView textViewLocation;
    private TextView textViewConditions;
    private TextView textViewTemperature;
    private TextView textViewLowTemperature;
    private TextView textViewHighTemperature;
    private TextView textViewHumidity;
    private TextView textViewConditionsText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_current_day_conditions, container, false);

        imageViewConditionsIcon = (ImageView) view.findViewById(R.id.imageViewCurrentDayConditionIcon);
        textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        textViewConditions = (TextView) view.findViewById(R.id.textViewCurrentDayShortConditionText);
        textViewTemperature = (TextView) view.findViewById(R.id.textViewCurrentDayTemp);
        textViewLowTemperature = (TextView) view.findViewById(R.id.textViewCurrentDayLow);
        textViewHighTemperature = (TextView) view.findViewById(R.id.textViewCurrentDayHigh);
        textViewHumidity = (TextView) view.findViewById(R.id.textViewCurrentDayHumidity);
        textViewConditionsText = (TextView) view.findViewById(R.id.textViewLocation);

        return view;
    }

    public void setCurrentDayConditions(CurrentDayConditions currentDayConditions) {
        this.currentDayConditions = currentDayConditions;
        bindData();
    }

    private void bindData() {
        if (!canBindData()) {
            return;
        }

        int conditionCode = currentDayConditions.getConditionCode();

        textViewLocation.setText(currentDayConditions.getLocationText());
        textViewConditions.setText(currentDayConditions.getConditionsText());
        textViewTemperature.setText(formatTemperature(currentDayConditions.getTemperature()));
        textViewLowTemperature.setText(formatTemperature(currentDayConditions.getLowTemperature()));
        textViewHighTemperature.setText(formatTemperature(currentDayConditions.getHighTemperature()));
        textViewHumidity.setText(formatPercentage(currentDayConditions.getHumidity()));
        imageViewConditionsIcon.setImageResource(
                ConditionsIconResourceLocator.findResourceForConditionsCode(conditionCode));
        textViewConditions.setText(currentDayConditions.getConditionsText());
    }

    private String formatTemperature(float temperature) {
        return Float.toString(temperature);
    }

    private String formatPercentage(float percentage) {
        return Float.toString(percentage);
    }

    private boolean canBindData() {
        return currentDayConditions != null && this.isResumed();
    }
}
