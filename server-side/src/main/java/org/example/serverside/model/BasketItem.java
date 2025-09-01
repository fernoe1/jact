package org.example.serverside.model;

public class BasketItem {
    private int id;
    private int basketId;
    private Sneaker sneaker;
    private int quantity;
    private double price;

    public BasketItem() {}

    public BasketItem(int id, int basketId, Sneaker sneaker, int quantity, double price) {
        this.id = id;
        this.basketId = basketId;
        this.sneaker = sneaker;
        this.quantity = quantity;
        this.price = price;
    }

    public BasketItem(int basketId, Sneaker sneaker, int quantity, double price) {
        this.basketId = basketId;
        this.sneaker = sneaker;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setSneaker(Sneaker sneaker) {
        this.sneaker = sneaker;
    }

    public Sneaker getSneaker() {
        return sneaker;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
