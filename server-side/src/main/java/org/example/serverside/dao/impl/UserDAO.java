package org.example.serverside.dao.impl;

import org.example.serverside.dao.IUserDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
    private final Connection con;

    public UserDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByName(String name) {
        User user = null;

        String sql = "SELECT * FROM users WHERE name = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        String sql = "SELECT * FROM users WHERE email = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
