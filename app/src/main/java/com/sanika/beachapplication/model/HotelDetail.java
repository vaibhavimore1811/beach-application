package com.sanika.beachapplication.model;
import java.util.List;

public class HotelDetail {
    private String name;
    private String formatted_phone_number; // Phone number
    private List<Photo> photos; // List of photos
    private String description; // Description of the hotel

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFormattedPhoneNumber() {
        return formatted_phone_number;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }
}

