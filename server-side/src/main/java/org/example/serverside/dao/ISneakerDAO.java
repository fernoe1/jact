package org.example.serverside.dao;

import org.example.serverside.model.Sneaker;

import java.util.List;

public interface ISneakerDAO {
    boolean addSneaker(Sneaker sneaker);

    Sneaker getSneakerById(int id);

    List<Sneaker> getSneakers();
}
