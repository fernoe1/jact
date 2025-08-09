package org.example.serverside.dao.impl;

import org.example.serverside.dao.ICategoryDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private final Connection con;

    public CategoryDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getMainCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories WHERE parent_id IS NULL;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public List<Category> getSubCategories(int id) {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories WHERE parent_id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO categories (id, name, parent_id) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category.getId());
            ps.setString(2, category.getName());
            ps.setInt(3, category.getParentId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
