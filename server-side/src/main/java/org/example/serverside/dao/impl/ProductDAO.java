package org.example.serverside.dao.impl;

import org.example.serverside.dao.IProductDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private final Connection con;

    public ProductDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("title"),
                        rs.getDouble("price"), rs.getString("description"),
                        rs.getInt("category_id"), rs.getString("image_uri")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product getProduct(int id) {
        Product product = null;

        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("title"),
                        rs.getDouble("price"), rs.getString("description"),
                        rs.getInt("category_id"), rs.getString("image_uri"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (title, price, description, category, image_uri) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getCategoryId());
            ps.setString(5, product.getImageUri());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET title = ?, price = ?," +
                " description = ?, category = ?, image_uri = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getCategoryId());
            ps.setString(5, product.getImageUri());
            ps.setInt(6, product.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
