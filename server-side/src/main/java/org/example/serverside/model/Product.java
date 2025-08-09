package org.example.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String title;
    private double price;
    private String description;
    private int categoryId;
    private String imageUri;

    public Product(int id, String title, double price, String description, int categoryId, String imageUri) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.imageUri = imageUri;
    }

    public Product(String title, double price, String description, int categoryId, String imageUri) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.imageUri = imageUri;
    }

    public Product() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }
}
