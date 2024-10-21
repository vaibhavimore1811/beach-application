package com.sanika.beachapplication.model;



import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    private TwnoOtherDetailsModel main;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("wind")
    private WindModel wind;

    @SerializedName("name")
    private String cityName;

    public TwnoOtherDetailsModel getMain() {
        return main;
    }

    public void setMain(TwnoOtherDetailsModel main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WindModel getWind() {
        return wind;
    }

    public void setWind(WindModel wind) {
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
