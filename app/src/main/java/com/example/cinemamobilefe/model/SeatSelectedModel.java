package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class SeatSelectedModel {
    String _id, price;
    @SerializedName("id_showtimes")
    ShowtimesModel showtimesModel;
    @SerializedName("id_seat")
    SeatModel seatModel;

    public SeatSelectedModel() {
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

    public ShowtimesModel getShowtimesModel() {
        return showtimesModel;
    }

    public void setShowtimesModel(ShowtimesModel showtimesModel) {
        this.showtimesModel = showtimesModel;
    }

    public SeatModel getSeatModel() {
        return seatModel;
    }

    public void setSeatModel(SeatModel seatModel) {
        this.seatModel = seatModel;
    }
}
