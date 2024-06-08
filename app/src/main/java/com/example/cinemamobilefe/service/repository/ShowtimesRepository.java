package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.ShowtimesAPI;
import com.example.cinemamobilefe.service.response.CategoryResponse;
import com.example.cinemamobilefe.service.response.ShowtimesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowtimesRepository {
    private Context context;
    public ShowtimesRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> getShowtimesByDate(String date) {
        final MutableLiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> mutableLiveData = new MutableLiveData<>();

        ShowtimesAPI showtimesAPI = RetrofitInstance.getRetrofitInstance().create(ShowtimesAPI.class);
        showtimesAPI.getShowtimesByDate(date).enqueue(new Callback<ShowtimesResponse<ArrayList<ShowtimesModel>>>() {
            @Override
            public void onResponse(Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> call, Response<ShowtimesResponse<ArrayList<ShowtimesModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
//                        Log.d(TAG, "datashowtimes: " + response.body());
//                        Log.d(TAG, "RepositoryeDataShowtimes: " + new Gson().toJson(response.body()));
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Log.d(TAG, "datashowtime2: " + response.body());
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
    public LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> getShowtimesByMovie(String movie) {
        final MutableLiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> mutableLiveData = new MutableLiveData<>();

        ShowtimesAPI showtimesAPI = RetrofitInstance.getRetrofitInstance().create(ShowtimesAPI.class);
        showtimesAPI.getShowtimesByMovie(movie).enqueue(new Callback<ShowtimesResponse<ArrayList<ShowtimesModel>>>() {
            @Override
            public void onResponse(Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> call, Response<ShowtimesResponse<ArrayList<ShowtimesModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
                        Log.d(TAG, "datashowtimes: " + response.body());
//                        Log.d(TAG, "RepositoryeDataShowtimes: " + new Gson().toJson(response.body()));
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Log.d(TAG, "datashowtime2: " + response.body());
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
