package com.sanika.beachapplication.model;


import com.google.gson.annotations.SerializedName;

public class Hotel {
    private String placeId;
    private String name;
    private String vicinity;

    // Getters
    public String getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }
}
