package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.service.callAPI.MovieAPI;
import com.example.cinemamobilefe.service.response.MovieResponse;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.model.MovieModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private Context context;
    public MovieRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<MovieResponse<ArrayList<MovieModel>>> getAllMovie() {
        final MutableLiveData<MovieResponse<ArrayList<MovieModel>>> mutableLiveData = new MutableLiveData<>();

        MovieAPI movieAPI = RetrofitInstance.getRetrofitInstance().create(MovieAPI.class);

        movieAPI.getMovie().enqueue(new Callback<MovieResponse<ArrayList<MovieModel>>>() {
            @Override
            public void onResponse(Call<MovieResponse<ArrayList<MovieModel>>> call, Response<MovieResponse<ArrayList<MovieModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
//                        Log.d(TAG, "data1: " + response.body());
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Log.d(TAG, "data2: " + response.body());
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse<ArrayList<MovieModel>>> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.d(TAG, "onFailure: " + t.getMessage());
//                Toast.makeText(context, "No1", Toast.LENGTH_SHORT).show();
            }
        });

        return mutableLiveData;
    }
}
