package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class TicketDetailsPost {
    private String _id, date, roomName, timeName, price, seatName, seatType, movieName;
    private boolean status;
    @SerializedName("id_movie")
    private String id_movie;
    @SerializedName("id_seatselected")
    private String id_seatselected;

    public TicketDetailsPost() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getId_movie() {
        return id_movie;
    }

    public void setId_movie(String id_movie) {
        this.id_movie = id_movie;
    }

    public String getId_seatselected() {
        return id_seatselected;
    }

    public void setId_seatselected(String id_seatselected) {
        this.id_seatselected = id_seatselected;
    }
}
