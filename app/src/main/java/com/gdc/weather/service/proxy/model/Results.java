package com.gdc.weather.service.proxy.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Results {

    @Expose
    private Channel channel;
    @Expose
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
