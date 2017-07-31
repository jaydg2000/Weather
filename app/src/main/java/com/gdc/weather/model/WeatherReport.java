package com.gdc.weather.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaydg on 7/6/2017.
 */

public class WeatherReport {
    private CurrentDayConditions currentDayConditions;
    private List<WeatherData> forecasts;

    public WeatherReport(CurrentDayConditions currentDayConditions) {
        forecasts = new ArrayList<>();
        this.currentDayConditions = currentDayConditions;
    }

    public CurrentDayConditions getCurrentDayConditions() {
        return currentDayConditions;
    }

    public void addForecast(WeatherData weatherData) {
        forecasts.add(weatherData);
    }

    public List<WeatherData> getForecasts() {
        return Collections.unmodifiableList(forecasts);
    }
}
