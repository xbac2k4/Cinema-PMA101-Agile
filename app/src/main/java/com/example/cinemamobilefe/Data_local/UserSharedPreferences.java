package com.example.cinemamobilefe.Data_local;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPreferences {
    private static final String USER_SHARED_FERENCES = "USER_SHARED_FERENCES";
    private Context mContext;

    public UserSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void putStringValue(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(USER_SHARED_FERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getStringValue(String key){
        SharedPreferences sharedPreferences  = mContext.getSharedPreferences(USER_SHARED_FERENCES,
                Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"");
    }
}
