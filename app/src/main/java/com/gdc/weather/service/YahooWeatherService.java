package com.gdc.weather.service;

import android.util.Log;

import com.gdc.weather.WeatherApp;
import com.gdc.weather.exception.InvalidWeatherApiResponse;
import com.gdc.weather.exception.ServiceOperationException;
import com.gdc.weather.service.mapper.WeatherServiceResponseMapper;
import com.gdc.weather.service.model.WeatherServiceResponse;
import com.gdc.weather.service.proxy.WeatherServiceProxy;
import com.gdc.weather.service.proxy.YahooWeatherServiceInjector;
import com.gdc.weather.service.proxy.model.Query;
import com.gdc.weather.service.proxy.model.WeatherAPIResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YahooWeatherService implements WeatherService {

    private static final String PARAM_QUERY = "q";
    private static final String PARAM_FORMAT = "format";
    private static final String PARAM_ENV = "env";
    private static final String VALUE_FORMAT = "json";
    private static final String VALUE_ENV = "store://datatables.org/alltableswithkeys";
    private static final String VALUE_QUERY
            = "select * from weather.forecast"
            + " where woeid in"
            + " (select woeid from geo.places(1) where text = \"%s, %s\")";
    public static final String OPTION_ENCODING = "utf-8";

    private final WeatherServiceProxy weatherServiceProxy;
    private final WeatherServiceResponseMapper responseMapper;

    public YahooWeatherService() {

        this.weatherServiceProxy = YahooWeatherServiceInjector.provideWeatherService();
        this.responseMapper = new WeatherServiceResponseMapper();
    }

    @Override
    public void getWeather(
            String city,
            String state,
            final WeatherServiceCallback weatherServiceCallback)
            throws InvalidWeatherApiResponse, ServiceOperationException {

        String requestQuery = String.format(VALUE_QUERY, city, state);
        Map<String, String> options = new HashMap<>();

        options.put(PARAM_QUERY, requestQuery);
        options.put(PARAM_FORMAT, VALUE_FORMAT);
        options.put(PARAM_ENV, VALUE_ENV);


//        options.put(PARAM_QUERY, encodeParam(requestQuery));
//        options.put(PARAM_FORMAT, encodeParam(VALUE_FORMAT));
//        options.put(PARAM_ENV, encodeParam(VALUE_ENV));

        weatherServiceProxy.getWeather(options).enqueue(new Callback<WeatherAPIResponse>() {
            @Override
            public void onResponse(Call<WeatherAPIResponse> call, Response<WeatherAPIResponse> response) {
                Log.i(WeatherApp.TAG, call.request().url().toString());
                if (response.isSuccessful()) {
                    WeatherAPIResponse weatherAPIResponse = response.body();
                    Query query = weatherAPIResponse.getQuery();
                    WeatherServiceResponse weatherServiceResponse = responseMapper.map(query);
                    weatherServiceCallback.onWeatherServiceCompleted(weatherServiceResponse);
                }
            }

            @Override
            public void onFailure(Call<WeatherAPIResponse> call, Throwable t) {
                weatherServiceCallback.onWeatherServiceFailed(t);
            }
        });
    }

    private String encodeParam(String param) {
        try {
            String step1 = URLEncoder.encode(param, OPTION_ENCODING);
            String step2 = step1.replace("+", " ");
            return step2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return param;
        }
    }
}
