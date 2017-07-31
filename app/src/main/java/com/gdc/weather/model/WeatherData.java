package com.gdc.weather.model;

public class WeatherData {
    private String dayOfWeek;

    private int conditionCode;
    private String conditionText;
    private float lowTemperature;
    private float highTemperature;

    public WeatherData(
            String dayOfWeek,
            int conditionCode,
            String conditionText,
            float lowTemperature,
            float highTemperature) {

        this.dayOfWeek = dayOfWeek;
        this.conditionCode = conditionCode;
        this.conditionText = conditionText;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public String getConditionText() {
        return conditionText;
    }

    public float getLowTemperature() {
        return lowTemperature;
    }

    public float getHighTemperature() {
        return highTemperature;
    }
}
