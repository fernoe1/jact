package org.example.serverside.dao;

import org.example.serverside.model.Sneaker;

public interface ISneakerDAO {
    boolean addSneaker(Sneaker sneaker);

    Sneaker getSneakerById(int id);
}
