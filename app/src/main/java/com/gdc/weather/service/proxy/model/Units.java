package com.gdc.weather.service.proxy.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Units {

    @Expose
    private String distance;
    @Expose
    private String pressure;
    @Expose
    private String speed;
    @Expose
    private String temperature;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
