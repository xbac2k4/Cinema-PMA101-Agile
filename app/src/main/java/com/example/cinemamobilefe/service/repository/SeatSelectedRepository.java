package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.SeatAPI;
import com.example.cinemamobilefe.service.callAPI.SeatSeletedAPI;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatSelectedRepository {
    private Context context;
    public SeatSelectedRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<SeatResponse<ArrayList<SeatSelectedModel>>> getSeatSelectedByShowtimes(String id_showtimes) {
        final MutableLiveData<SeatResponse<ArrayList<SeatSelectedModel>>> mutableLiveData = new MutableLiveData<>();

        SeatSeletedAPI seatSeletedAPI = RetrofitInstance.getRetrofitInstance().create(SeatSeletedAPI.class);
        seatSeletedAPI.getSeatSelectedByShowtimes(id_showtimes).enqueue(new Callback<SeatResponse<ArrayList<SeatSelectedModel>>>() {
            @Override
            public void onResponse(Call<SeatResponse<ArrayList<SeatSelectedModel>>> call, Response<SeatResponse<ArrayList<SeatSelectedModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
                        Log.d(TAG, "dataSeatSelected: " + response.body());
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatResponse<ArrayList<SeatSelectedModel>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
    public LiveData<SeatResponse<SeatSelectedPost>> postSeatSelected(SeatSelectedPost seatSelectedPost) {
        final MutableLiveData<SeatResponse<SeatSelectedPost>> mutableLiveData = new MutableLiveData<>();

        SeatSeletedAPI seatSeletedAPI = RetrofitInstance.getRetrofitInstance().create(SeatSeletedAPI.class);
        seatSeletedAPI.postSeatSelected(seatSelectedPost).enqueue(new Callback<SeatResponse<SeatSelectedPost>>() {
            @Override
            public void onResponse(Call<SeatResponse<SeatSelectedPost>> call, Response<SeatResponse<SeatSelectedPost>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
                        Log.d(TAG, "dataSeatSelected: " + response.body());
                    }
                } else {
                    mutableLiveData.postValue(null);
                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatResponse<SeatSelectedPost>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
