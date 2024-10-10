package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
     String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
