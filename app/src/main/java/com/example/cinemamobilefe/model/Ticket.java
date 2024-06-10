package com.example.cinemamobilefe.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Ticket {
    @SerializedName("_id")
    private String _id;
    @SerializedName("date")
    private String date;
    @SerializedName("id_detailstickets")
    private ArrayList<TicketDetailsModel> id_ticketdetails;
    @SerializedName("id_user")
    private UserModel id_user;

    public Ticket() {
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

    public ArrayList<TicketDetailsModel> getId_ticketdetails() {
        return id_ticketdetails;
    }

    public void setId_ticketdetails(ArrayList<TicketDetailsModel> id_ticketdetails) {
        this.id_ticketdetails = id_ticketdetails;
    }

    public UserModel getId_user() {
        return id_user;
    }

    public void setId_user(UserModel id_user) {
        this.id_user = id_user;
    }
}
