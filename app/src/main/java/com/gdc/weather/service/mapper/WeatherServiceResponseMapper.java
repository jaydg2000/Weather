package com.gdc.weather.service.mapper;

import com.gdc.weather.mapper.Mapper;
import com.gdc.weather.service.model.WeatherServiceForecastResponse;
import com.gdc.weather.service.model.WeatherServiceResponse;
import com.gdc.weather.service.proxy.model.Atmosphere;
import com.gdc.weather.service.proxy.model.Condition;
import com.gdc.weather.service.proxy.model.Forecast;
import com.gdc.weather.service.proxy.model.Location;
import com.gdc.weather.service.proxy.model.Query;

import java.util.List;

/**
 * Created by jaydg on 7/12/2017.
 */

public class WeatherServiceResponseMapper implements Mapper<Query, WeatherServiceResponse> {
    @Override
    public WeatherServiceResponse map(Query query) {
        Condition condition = query.getResults().getChannel().getItem().getCondition();
        Atmosphere atmosphere = query.getResults().getChannel().getAtmosphere();
        Location location = query.getResults().getChannel().getLocation();
        int code = Integer.parseInt(condition.getCode());
        float temperature = Float.parseFloat(condition.getTemp());
        float humidity = Float.parseFloat(atmosphere.getHumidity());
        String text = condition.getText();
        String city = location.getCity();
        String state = location.getRegion();

        WeatherServiceResponse destination
                = new WeatherServiceResponse(
                code,
                text,
                city,
                state,
                temperature,
                humidity);

        List<Forecast> forecasts = query.getResults().getChannel().getItem().getForecast();
        for (Forecast forecast : forecasts) {
            int forecastCode = Integer.parseInt(forecast.getCode());
            String forecastText = forecast.getText();
            String forecastDayOfWeekText = forecast.getDay();
            String forecastDateDisplay = forecast.getDate();
            float forecastHighTemperature = Float.parseFloat(forecast.getHigh());
            float forecastLowTemperature = Float.parseFloat(forecast.getLow());

            WeatherServiceForecastResponse destinationForecast
                    = new WeatherServiceForecastResponse(
                            forecastCode,
                    forecastText,
                    forecastDayOfWeekText,
                    forecastDateDisplay,
                    forecastHighTemperature,
                    forecastLowTemperature);

            destination.addForecast(destinationForecast);
        }

        return destination;
    }
}
