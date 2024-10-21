package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public class TwnoOtherDetailsModel {
    @SerializedName("temp")
    private double temp;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("feels_like") // This can represent perceived temperature
    private double feelsLike;

    // Add any other fields as necessary
    @SerializedName("water_temp")
    private double waterTemp; // Assuming this is provided by your API

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(double waterTemp) {
        this.waterTemp = waterTemp;
    }
}
