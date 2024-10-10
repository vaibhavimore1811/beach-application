package com.sanika.beachapplication.model;

import com.google.gson.annotations.SerializedName;

public  class Place {
    @SerializedName("name")
    private String name;
    @SerializedName("vicinity")
    private String vicinity;
    @SerializedName("geometry")
    private Geometry geometry;

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public static class Geometry {
        @SerializedName("location")
        private Location location;

        public Location getLocation() {
            return location;
        }

        public static class Location {
            @SerializedName("lat")
            private double lat;
            @SerializedName("lng")
            private double lng;

            public double getLat() {
                return lat;
            }

            public double getLng() {
                return lng;
            }
        }
    }
}
