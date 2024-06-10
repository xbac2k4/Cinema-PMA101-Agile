package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class TicketDetailsModel {
//    "date": "08/06/2024",
//            "roomName": "P02",
//            "timeName": "9:00",
//            "price": 170000,
//            "seatName": "A2",
//            "seatType": 2,
//            "movieName": "LẬT MẶT: 48H",
//            "id_movie": {
//        "$oid": "66433266fee7ca18484f5db7"
//    },
//            "id_seatselected": {
//        "$oid": "6662bbb1a20718c911fdc46c"
//    }
    private String _id, date, roomName, timeName, price, seatName, seatType, movieName;
    private boolean status;
    @SerializedName("id_movie")
    private MovieModel id_movie;
    @SerializedName("id_seatselected")
    private SeatSelectedModel id_seatselected;

    public TicketDetailsModel() {
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

    public MovieModel getId_movie() {
        return id_movie;
    }

    public void setId_movie(MovieModel id_movie) {
        this.id_movie = id_movie;
    }

    public SeatSelectedModel getId_seatselected() {
        return id_seatselected;
    }

    public void setId_seatselected(SeatSelectedModel id_seatselected) {
        this.id_seatselected = id_seatselected;
    }
}
