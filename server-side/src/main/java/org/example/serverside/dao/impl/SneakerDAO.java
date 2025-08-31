package org.example.serverside.dao.impl;

import org.example.serverside.dao.ISneakerDAO;
import org.example.serverside.database.DBConnection;
import org.example.serverside.model.Sneaker;

import java.sql.*;

public class SneakerDAO implements ISneakerDAO {
    private final Connection con;

    public SneakerDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addSneaker(Sneaker sneaker) {
        String sql = "INSERT INTO sneakers (name, brand, fit, price, images) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sneaker.getName());
            ps.setString(2, sneaker.getBrand());
            ps.setString(3, sneaker.getFit());
            ps.setDouble(4, sneaker.getPrice());

            Array imagesVarchar = con.createArrayOf("varchar", sneaker.getImages());
            ps.setArray(5, imagesVarchar);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sneaker getSneakerById(int id) {
        Sneaker sneaker = null;

        String sql = "SELECT * FROM sneakers WHERE id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sneaker = new Sneaker(id, rs.getString("name"),
                        rs.getString("brand"), rs.getString("fit"),
                        rs.getDouble("price"), ((String[]) rs.getArray("images").getArray()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sneaker;
    }
}
