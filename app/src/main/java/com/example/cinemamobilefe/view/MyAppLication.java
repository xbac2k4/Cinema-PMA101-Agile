package com.example.cinemamobilefe.view;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemamobilefe.Data_local.DataLocalManager;

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
