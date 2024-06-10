package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TicketPost {
    private String _id, date;
    @SerializedName("id_detailstickets")
    private ArrayList<String> id_ticketdetails;
    @SerializedName("id_user")
    private String id_user;

    public TicketPost() {
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

    public ArrayList<String> getId_ticketdetails() {
        return id_ticketdetails;
    }

    public void setId_ticketdetails(ArrayList<String> id_ticketdetails) {
        this.id_ticketdetails = id_ticketdetails;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
