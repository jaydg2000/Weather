package com.gdc.weather.model;

/**
 * Created by jaydg on 7/6/2017.
 */

public class CurrentDayConditions {
    private int conditionCode;
    private String locationText;
    private String conditionsText;
    private float temperature;
    private float humidity;
    private float lowTemperature;
    private float highTemperature;

    public CurrentDayConditions(
            int conditionCode,
            String locationText,
            String conditionsText,
            float temperature,
            float lowTemperature,
            float highTemperature,
            float humidity) {

        this.conditionCode = conditionCode;
        this.locationText = locationText;
        this.conditionsText = conditionsText;
        this.temperature = temperature;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.humidity = humidity;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public String getLocationText() {
        return locationText;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getLowTemperature() {
        return lowTemperature;
    }

    public float getHighTemperature() {
        return highTemperature;
    }

    public String getConditionsText() {
        return conditionsText;
    }

    public float getHumidity() {
        return humidity;
    }
}
