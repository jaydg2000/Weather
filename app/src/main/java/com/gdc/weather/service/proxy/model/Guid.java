package com.gdc.weather.service.proxy.model;

import java.util.HashMap;
import java.util.Map;

public class Guid {

    private String isPermaLink;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
