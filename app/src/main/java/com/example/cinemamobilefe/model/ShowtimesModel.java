package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

public class ShowtimesModel {
//    date: { type: String, required: true },
//    id_room: { type: Scheme.Types.ObjectId, ref: 'room' },
//    id_time: { type: Scheme.Types.ObjectId, ref: 'time' },
//    id_movie: { type: Scheme.Types.ObjectId, ref: 'movie' },
    @SerializedName("_id")
    private String _id;
    @SerializedName("date")
    private String date;
    @SerializedName("id_room")
    private RoomModel roomModel;
    @SerializedName("id_time")
    private TimeModel timeModel;
    @SerializedName("id_movie")
    private MovieModel movieModel;

    public ShowtimesModel() {
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

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public TimeModel getTimeModel() {
        return timeModel;
    }

    public void setTimeModel(TimeModel timeModel) {
        this.timeModel = timeModel;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
