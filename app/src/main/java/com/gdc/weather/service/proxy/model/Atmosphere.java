package com.gdc.weather.service.proxy.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Atmosphere {

    @Expose
    private String humidity;
    @Expose
    private String pressure;
    @Expose
    private String rising;
    @Expose
    private String visibility;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
