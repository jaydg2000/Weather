package com.gdc.weather.service.model;

/**
 * Created by jaydg on 7/5/2017.
 */

public class WeatherServiceForecastResponse {
    private int conditionsCode;
    private String conditionText;
    private String dayOfWeekText;
    private String dateDisplay;
    private float highTemperature;
    private float lowTemperature;

    public WeatherServiceForecastResponse(
            int conditionsCode,
            String conditionText,
            String dayOfWeekText,
            String dateDisplay,
            float highTemperature,
            float lowTemperature) {

        this.conditionsCode = conditionsCode;
        this.conditionText = conditionText;
        this.dayOfWeekText = dayOfWeekText;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.dateDisplay = dateDisplay;
    }

    public int getConditionsCode() {
        return conditionsCode;
    }

    public String getConditionText() {
        return conditionText;
    }

    public String getDayOfWeekText() {
        return dayOfWeekText;
    }

    public String getDateDisplay() {
        return dateDisplay;
    }

    public float getHighTemperature() {
        return highTemperature;
    }

    public float getLowTemperature() {
        return lowTemperature;
    }
}
