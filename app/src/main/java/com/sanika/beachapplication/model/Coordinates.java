package com.sanika.beachapplication.model;

import java.util.List;

public class Coordinates {
    private List<Double> coordinates; // Assuming coordinates are in the format [longitude, latitude]

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
