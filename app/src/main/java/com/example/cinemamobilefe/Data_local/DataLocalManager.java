package com.example.cinemamobilefe.Data_local;

import android.content.Context;

import com.example.cinemamobilefe.model.UserModel;
import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static DataLocalManager instance;
    private UserSharedPreferences userSharedPreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.userSharedPreferences = new UserSharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setUser(UserModel userModel){
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(userModel);
        DataLocalManager.getInstance().userSharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }
    public static UserModel getUser(){
        String strJsonUser = DataLocalManager.getInstance().userSharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        UserModel userModel = gson.fromJson(strJsonUser, UserModel.class);
        return userModel;
    }
}
