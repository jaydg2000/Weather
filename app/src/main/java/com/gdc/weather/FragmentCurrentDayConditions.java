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
import com.gdc.weather.ui.UIFormatHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaydg on 7/3/2017.
 */

public class FragmentCurrentDayConditions extends Fragment {
    private CurrentDayConditions currentDayConditions;

    @BindView(R.id.imageViewCurrentDayConditionIcon) ImageView imageViewConditionsIcon;
    @BindView(R.id.textViewLocation) TextView textViewLocation;
    @BindView(R.id.textViewCurrentDayShortConditionText) TextView textViewConditions;
    @BindView(R.id.textViewCurrentDayTemp) TextView textViewTemperature;
    @BindView(R.id.textViewCurrentDayLow) TextView textViewLowTemperature;
    @BindView(R.id.textViewCurrentDayHigh) TextView textViewHighTemperature;
    @BindView(R.id.textViewCurrentDayHumidity) TextView textViewHumidity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_current_day_conditions, container, false);
        ButterKnife.bind(this, view);

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
        textViewTemperature.setText(UIFormatHelper.formatTemperature(
                currentDayConditions.getTemperature()));
        textViewLowTemperature.setText(UIFormatHelper.formatTemperature(
                currentDayConditions.getLowTemperature()));
        textViewHighTemperature.setText(UIFormatHelper.formatTemperature(
                currentDayConditions.getHighTemperature()));
        textViewHumidity.setText(UIFormatHelper.formatPercentage(
                currentDayConditions.getHumidity()));
        imageViewConditionsIcon.setImageResource(
                ConditionsIconResourceLocator.findResourceForConditionsCode(conditionCode));
        textViewConditions.setText(currentDayConditions.getConditionsText());
    }

    private boolean canBindData() {
        return currentDayConditions != null && this.isResumed();
    }
}
