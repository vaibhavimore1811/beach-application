package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public class Beach {
    private String type;
    private BeachProperties properties;
    private Geometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BeachProperties getProperties() {
        return properties;
    }

    public void setProperties(BeachProperties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}

