package com.gdc.weather.service.proxy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaydg on 7/6/2017.
 */

public class YahooWeatherServiceInjector {
    private static final String BASE_URL = "https://query.yahooapis.com";
    private static final String QUERYSTRING = "?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WeatherServiceProxy provideWeatherService() {
        return provideRetrofit(BASE_URL).create(WeatherServiceProxy.class);
    }
}
