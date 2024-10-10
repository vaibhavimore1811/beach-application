package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public class BeachProperties {
    private String name;
    private String address_line; // Adjust based on the API's actual field names
    private String image; // New property for the image URL

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine() {
        return address_line;
    }

    public void setAddressLine(String address_line) {
        this.address_line = address_line;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
