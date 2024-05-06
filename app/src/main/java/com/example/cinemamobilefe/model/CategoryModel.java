package com.example.cinemamobilefe.model;

public class CategoryModel {
    private String _id, image, name;

    public CategoryModel() {
    }

    public CategoryModel(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
