package com.gdc.weather.exception;

/**
 * Created by jaydg on 7/8/2017.
 */

public class ServiceOperationException extends Exception {
    public ServiceOperationException(Exception ex) {
        super("The weather api call was unsuccessful.", ex);
    }
}
