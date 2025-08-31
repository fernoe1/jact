package org.example.serverside.model;

public class Sneaker {
    private int id;
    private String name;
    private String brand;
    private String fit;
    private double price;
    private String[] images;

    public Sneaker() {

    }

    public Sneaker(int id, String name, String brand, String fit, double price, String[] images) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.fit = fit;
        this.price = price;
        this.images = images;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public String getFit() {
        return fit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getImages() {
        return images;
    }
}
