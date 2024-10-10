package com.sanika.beachapplication.model;

import java.util.List;

public class GeoapifyPlace {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public static class Properties {
        private String name; // Hotel name
        private String address_line1; // Hotel address
        private List<Photo> photos; // List of hotel photos

        public String getName() {
            return name;
        }

        public String getAddressLine1() {
            return address_line1;
        }

        public List<Photo> getPhotos() {
            return photos;
        }
    }

    public static class Photo {
        private String photo_reference;

        public String getPhotoReference() {
            return photo_reference;
        }
    }
}
