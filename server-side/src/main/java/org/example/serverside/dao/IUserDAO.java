package org.example.serverside.dao;

import org.example.serverside.model.User;

public interface IUserDAO {
    boolean addUser(User user);

    User getUserByName(String name);

    User getUserByEmail(String email);
}
