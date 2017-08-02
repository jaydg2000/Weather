package com.gdc.weather.service;

import com.gdc.weather.exception.InvalidWeatherApiResponse;
import com.gdc.weather.exception.ServiceOperationException;
import com.gdc.weather.service.model.WeatherServiceResponse;

public interface WeatherService {
    void getWeather(String city, String state, WeatherServiceCallback weatherServiceCallback)
            throws InvalidWeatherApiResponse, ServiceOperationException;
}
