package org.example.serverside.dao.impl;

import org.example.serverside.dao.IBasketDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDAO implements IBasketDAO {
    private final Connection con;
    private final BasketItemDAO basketItemDAO;

    public BasketDAO() {
        try {
            con = DBConnection.getConnection();
            basketItemDAO = new BasketItemDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Basket getBasketById(int basketId) {
        Basket basket = null;

        String sql = "SELECT * FROM baskets WHERE id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, basketId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                basket = new Basket(basketId, rs.getInt("user_id"), basketItemDAO.getItemsByBasketId(basketId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return basket;
    }

    @Override
    public Basket getBasketByUserId(int userId) {
        Basket basket = null;
        String sql = "SELECT * FROM baskets WHERE user_id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                basket = new Basket(rs.getInt("id"), userId, basketItemDAO.getItemsByBasketId(rs.getInt("id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return basket;
    }

    @Override
    public int createBasket(int userId) {
        String sql = "INSERT INTO baskets (user_id) VALUES (?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    @Override
    public boolean deleteBasket(int basketId) {
        String sql = "DELETE FROM baskets WHERE id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, basketId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBasket(Basket basket) {
        String sql = "UPDATE baskets SET user_id = ? WHERE id = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, basket.getUserId());
            ps.setInt(2, basket.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
