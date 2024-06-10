package com.example.cinemamobilefe.Data_local;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cinemamobilefe.model.UserModel;
import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static DataLocalManager instance;
    private MySharedPreferences userSharedPreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.userSharedPreferences = new MySharedPreferences(context);
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
        Log.d(TAG, "setUser: " + strJsonUser);
        DataLocalManager.getInstance().userSharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }
    public static UserModel getUser(){
        String strJsonUser = DataLocalManager.getInstance().userSharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        UserModel userModel = gson.fromJson(strJsonUser, UserModel.class);
        return userModel;
    }
    public static void removeUser(){
        DataLocalManager.getInstance().userSharedPreferences.removeValue(PREF_OBJECT_USER);
    }
}
