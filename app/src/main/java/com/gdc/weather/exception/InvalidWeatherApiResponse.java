package com.gdc.weather.exception;

/**
 * Created by jaydg on 7/8/2017.
 */

public class InvalidWeatherApiResponse extends Exception {
    private String response;
    public InvalidWeatherApiResponse(String response, Exception ex) {
        super("The weather api response was unrecognizable.", ex);
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
