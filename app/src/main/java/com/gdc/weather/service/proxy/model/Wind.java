package com.gdc.weather.service.proxy.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Wind {

    @Expose
    private String chill;
    @Expose
    private String direction;
    @Expose
    private String speed;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}