package com.gdc.weather.service.proxy;

import com.gdc.weather.service.proxy.model.WeatherAPIResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by jaydg on 7/5/2017.
 */

public interface WeatherServiceProxy {
    @GET("/v1/public/yql")
    Call<WeatherAPIResponse> getWeather(@QueryMap Map<String,String> options);
}
