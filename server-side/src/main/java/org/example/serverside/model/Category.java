package org.example.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String name;
    private int parentId;

    public Category() {

    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public Category(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }
}
