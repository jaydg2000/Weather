package com.gdc.weather.service;

import com.gdc.weather.service.model.WeatherServiceResponse;

/**
 * Created by jaydg on 7/12/2017.
 */

public interface WeatherServiceCallback {
    void onWeatherServiceCompleted(WeatherServiceResponse weatherServiceResponse);
    void onWeatherServiceFailed(Throwable t);
}
