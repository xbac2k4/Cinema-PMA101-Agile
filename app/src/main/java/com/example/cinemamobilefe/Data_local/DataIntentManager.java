package com.example.cinemamobilefe.Data_local;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.cinemamobilefe.model.MovieModel;
import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataIntentManager {
    public static final String DATA_INTENT_MOVIE = "DATA_INTENT_MOVIE";
    public static final String DATA_INTENT_SHOWTIMES = "DATA_INTENT_SHOWTIMES";
    public static final String DATA_INTENT_SEAT_SELECTED = "DATA_INTENT_SEAT_SELECTED";

    public static String setShowtimes(ShowtimesModel showtimesModel){
        Gson gson = new Gson();
        String strJson = gson.toJson(showtimesModel);
        return strJson;
    }
    public static ShowtimesModel getShowtimes(String strJson){
        Gson gson = new Gson();
        ShowtimesModel showtimesModel = gson.fromJson(strJson, ShowtimesModel.class);
        return showtimesModel;
    }
    public static String setMovie(MovieModel movieModel){
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(movieModel);
        return strJsonUser;
    }
    public static MovieModel getMovie(String strJsonUser){
        Gson gson = new Gson();
        MovieModel movieModel = gson.fromJson(strJsonUser, MovieModel.class);
        return movieModel;
    }
    public static String setListSeatSelected(ArrayList<SeatModel> list){
        Gson gson = new Gson();
        String strJson = gson.toJson(list);
        return strJson;
    }
    public static ArrayList<SeatModel> getListSeatSelected(String strJson) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SeatModel>>() {}.getType();
        ArrayList<SeatModel> myObjectList = gson.fromJson(strJson, type);
        return myObjectList;
    }
}
