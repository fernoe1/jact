package org.example.serverside.dao;

import org.example.serverside.model.Category;

import java.util.List;

public interface ICategoryDAO {
    List<Category> getMainCategories();

    List<Category> getSubCategories(int id);

    boolean addCategory(Category category);
}
