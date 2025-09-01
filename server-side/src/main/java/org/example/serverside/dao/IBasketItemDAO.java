package org.example.serverside.dao;

import org.example.serverside.model.BasketItem;

import java.util.List;

public interface IBasketItemDAO {
    boolean addItem(BasketItem item);

    boolean updateItemQuantity(int itemId, int quantity);

    boolean removeBasketItem(int itemId);

    boolean clearBasket(int basketId);

    List<BasketItem> getItemsByBasketId(int basketId);
}
