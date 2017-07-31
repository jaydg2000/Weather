package com.gdc.weather.repository;

import com.gdc.weather.exception.InvalidWeatherApiResponse;
import com.gdc.weather.exception.ServiceOperationException;
import com.gdc.weather.mapper.WeatherReportMapper;
import com.gdc.weather.model.WeatherReport;
import com.gdc.weather.service.WeatherService;
import com.gdc.weather.service.WeatherServiceCallback;
import com.gdc.weather.service.model.WeatherServiceResponse;

public abstract class WeatherReportRepository implements WeatherServiceCallback {
    private final WeatherReportMapper weatherReportMapper;
    private WeatherService weatherService;

    public WeatherReportRepository(
            WeatherService weatherService,
            WeatherReportMapper weatherReportMapper) {

        this.weatherService = weatherService;
        this.weatherReportMapper = weatherReportMapper;
    }

    public void retrieveWeatherReport(String city, String state) {
        try {
            weatherService.getWeather(city, state, this);
        } catch (InvalidWeatherApiResponse | ServiceOperationException ex) {
            // TODO: what do I want to do here?
        }
    }

    public abstract void onWeatherReportRetrieved(WeatherReport weatherReport);

    private WeatherReport mapWeatherReport(WeatherServiceResponse response) {
        if (response == null) {
            return null;
        }
        return weatherReportMapper.map(response);
    }

    @Override
    public void onWeatherServiceCompleted(WeatherServiceResponse weatherServiceResponse) {
        WeatherReport weatherReport = mapWeatherReport(weatherServiceResponse);
        onWeatherReportRetrieved(weatherReport);
    }

    @Override
    public void onWeatherServiceFailed(Throwable t) {

    }

}
