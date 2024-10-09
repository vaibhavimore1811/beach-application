package com.sanika.beachapplication.model;

public class Beach {
    private String display_name; // Name of the beach
    private String description;   // Description of the beach
    private String imageUrl;      // URL for an image of the beach (optional)
    // Add more fields as needed

    // Constructor
    public Beach(String display_name, String description, String imageUrl) {
        this.display_name = display_name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getDisplayName() {
        return display_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters if needed
    public void setDisplayName(String display_name) {
        this.display_name = display_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
