package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class SeatSelectedPost {
    String _id, price;
    @SerializedName("id_showtimes")
    String id_showtimes;
    @SerializedName("id_seat")
    String id_seat;

    public SeatSelectedPost() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId_showtimes() {
        return id_showtimes;
    }

    public void setId_showtimes(String id_showtimes) {
        this.id_showtimes = id_showtimes;
    }

    public String getId_seat() {
        return id_seat;
    }

    public void setId_seat(String id_seat) {
        this.id_seat = id_seat;
    }
}
