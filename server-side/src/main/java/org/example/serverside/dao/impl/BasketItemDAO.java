package org.example.serverside.dao.impl;

import org.example.serverside.dao.IBasketItemDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.BasketItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketItemDAO implements IBasketItemDAO {
    private final Connection con;
    private final SneakerDAO sneakerDAO;

    public BasketItemDAO() {
        try {
            con = DBConnection.getConnection();
            sneakerDAO = new SneakerDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addItem(BasketItem item) {
        String sql = "INSERT INTO basket_items (basket_id, sneaker_id, quantity, price) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, item.getBasketId());
            ps.setInt(2, item.getSneaker().getId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItemQuantity(int itemId, int quantity) {
        String sql = "UPDATE basket_items SET quantity = ? WHERE id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, itemId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeBasketItem(int itemId) {
        String sql = "DELETE FROM basket_items WHERE id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean clearBasket(int basketId) {
        String sql = "DELETE FROM basket_items WHERE basket_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, basketId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BasketItem> getItemsByBasketId(int basketId) {
        List<BasketItem> sneakers = new ArrayList<BasketItem>();
        String sql = "SELECT * FROM basket_items WHERE basket_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, basketId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sneakers.add(new BasketItem(rs.getInt("id"), rs.getInt("basket_id"),
                        sneakerDAO.getSneakerById(rs.getInt("sneaker_id")), rs.getInt("quantity"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sneakers;
    }
}
