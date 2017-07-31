package com.gdc.weather.service.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaydg on 7/5/2017.
 */

public class WeatherServiceResponse {
    private int conditionCode;
    private String city;
    private String state;
    private String conditionText;
    private float temperature;
    private float lowTemperature;
    private float highTemperature;
    private float humidity;
    private List<WeatherServiceForecastResponse> forecasts;

    public WeatherServiceResponse(
            int conditionCode,
            String conditionText,
            String city,
            String state,
            float temperature,
            float humidity)
    {
        forecasts = new ArrayList<>();
        this.conditionCode = conditionCode;
        this.conditionText = conditionText;
        this.state = state;
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public void addForecast(WeatherServiceForecastResponse forecast) {
        this.forecasts.add(forecast);
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public String getConditionText() {
        return conditionText;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public List<WeatherServiceForecastResponse> getForecasts() {
        return Collections.unmodifiableList(forecasts);
    }
}
