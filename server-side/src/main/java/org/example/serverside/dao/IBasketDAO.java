package org.example.serverside.dao;

import org.example.serverside.model.Basket;

public interface IBasketDAO {
    Basket getBasketById(int basketId);

    Basket getBasketByUserId(int userId);

    int createBasket(int userId);

    boolean deleteBasket(int basketId);

    boolean updateBasket(Basket basket);
}
