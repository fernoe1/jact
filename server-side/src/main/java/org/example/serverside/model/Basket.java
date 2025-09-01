package org.example.serverside.model;

import java.util.List;

public class Basket {
    private int id;
    private int userId;
    private List<BasketItem> sneakers;

    public Basket() {}

    public Basket(int id, int userId, List<BasketItem> sneakers) {
        this.id = id;
        this.userId = userId;
        this.sneakers = sneakers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setSneakers(List<BasketItem> sneakers) {
        this.sneakers = sneakers;
    }

    public List<BasketItem> getSneakers() {
        return sneakers;
    }
}
