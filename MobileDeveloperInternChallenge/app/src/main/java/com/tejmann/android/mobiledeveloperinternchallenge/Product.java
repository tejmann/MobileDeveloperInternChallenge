package com.tejmann.android.mobiledeveloperinternchallenge;

import android.graphics.Bitmap;

public class Product {
    private String name;
    private String availability;
    private String title;
    private Bitmap imaage;
    private String imageUrl;


    public Product(String name, String availability, String title, Bitmap imaage, String imageUrl) {
        this.name = name;
        this.availability = availability;
        this.title = title;
        this.imaage = imaage;
        this.imageUrl = imageUrl;
    }

    public Product(String name, String availability, String title, String imageUrl) {
        this.name = name;
        this.availability = availability;
        this.title = title;
        this.imageUrl = imageUrl;
        this.imaage=null;
    }

    public String getName() {
        return name;
    }

    public String getAvailability() {
        return availability;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getImaage() {
        return imaage;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
