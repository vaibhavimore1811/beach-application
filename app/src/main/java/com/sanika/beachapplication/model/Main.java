package com.sanika.beachapplication.model;


import com.google.gson.annotations.SerializedName;

// Main class to hold temperature and other main details
public class Main {
    @SerializedName("temp")
    private float temperature;

    @SerializedName("pressure")
    private int pressure;

    @SerializedName("humidity")
    private int humidity;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

// Weather class to hold weather descriptions
