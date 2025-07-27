package org.example.serverside.dao;

import org.example.serverside.model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> getAllProducts();

    Product getProduct(int id);

    boolean addProduct(Product product);

    boolean removeProduct(int id);

    boolean updateProduct(Product product);
}
