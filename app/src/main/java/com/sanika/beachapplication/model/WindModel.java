package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public class WindModel {
    @SerializedName("speed")
    private double speed;

    @SerializedName("deg") // Wind direction
    private int degree;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
