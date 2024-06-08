package com.example.cinemamobilefe.model;

public class CategoryModel {
    private String _id, name;

    public CategoryModel() {
    }

    public CategoryModel(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
