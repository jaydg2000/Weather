package com.gdc.weather.mapper;

import com.gdc.weather.model.CurrentDayConditions;
import com.gdc.weather.model.WeatherData;
import com.gdc.weather.model.WeatherReport;
import com.gdc.weather.service.model.WeatherServiceForecastResponse;
import com.gdc.weather.service.model.WeatherServiceResponse;

import java.util.List;

/**
 * Created by jaydg on 7/6/2017.
 */

public class WeatherReportMapper implements Mapper<WeatherServiceResponse, WeatherReport> {
    @Override
    public WeatherReport map(WeatherServiceResponse weatherServiceResponse) {
        WeatherServiceForecastResponse currentDayForecast
                = weatherServiceResponse.getForecasts().get(0);
        int conditionCode = weatherServiceResponse.getConditionCode();
        String conditionsText = weatherServiceResponse.getConditionText();
        String locationText = String.format("%s, %s", weatherServiceResponse.getCity(),
                weatherServiceResponse.getState());
        float temperature = weatherServiceResponse.getTemperature();
        float lowTemperature = currentDayForecast.getLowTemperature();
        float highTemperature = currentDayForecast.getHighTemperature();
        float humidity = weatherServiceResponse.getHumidity();

        CurrentDayConditions conditions =
                new CurrentDayConditions(
                        conditionCode,
                        locationText,
                        conditionsText,
                        temperature,
                        lowTemperature,
                        highTemperature,
                        humidity);

        WeatherReport weatherReport = new WeatherReport(conditions);

        List<WeatherServiceForecastResponse> forecasts = weatherServiceResponse.getForecasts();
        for(int c = 1; c < forecasts.size(); c++) {
            WeatherServiceForecastResponse forecastResponse = forecasts.get(c);
            int forecastConditionsCode = forecastResponse.getConditionsCode();
            String forecastConditionsText = forecastResponse.getConditionText();
            float forecastLowTemperature = forecastResponse.getLowTemperature();
            float forecastHighTemperature = forecastResponse.getHighTemperature();
            String dayOfWeek = forecastResponse.getDayOfWeekText();

            WeatherData forecastData = new WeatherData(
                    dayOfWeek,
                    forecastConditionsCode,
                    forecastConditionsText,
                    forecastLowTemperature,
                    forecastHighTemperature);
            weatherReport.addForecast(forecastData);
        }

        return weatherReport;
    }
}
