package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
//    image: { type: String },
//    name: { type: String, required: true },
//    show_date: { type: String, required: true },
//    duration: { type: String, required: true },
//    evaluate: { type: Number, required: true, default: 5 },
//    description: { type: String, required: true },
//    directors: { type: String, required: true },
//    id_category: { type: Scheme.Types.ObjectId, ref: 'category' },
    String _id, image, name, start_date, end_date, duration, description, directors;
    @SerializedName("id_category")
    CategoryModel id_category;

    public MovieModel(String image) {
        this.image = image;
    }

    public MovieModel() {
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public CategoryModel getId_category() {
        return id_category;
    }

    public void setId_category(CategoryModel id_category) {
        this.id_category = id_category;
    }
}
