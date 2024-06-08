package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.SeatAPI;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatRepository {
    private Context context;
    public SeatRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<SeatResponse<ArrayList<SeatModel>>> getAllSeat() {
        final MutableLiveData<SeatResponse<ArrayList<SeatModel>>> mutableLiveData = new MutableLiveData<>();

        SeatAPI seatAPI = RetrofitInstance.getRetrofitInstance().create(SeatAPI.class);
        seatAPI.getAllSeat().enqueue(new Callback<SeatResponse<ArrayList<SeatModel>>>() {
            @Override
            public void onResponse(Call<SeatResponse<ArrayList<SeatModel>>> call, Response<SeatResponse<ArrayList<SeatModel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(response.body());
                        Log.d(TAG, "dataSeat: " + response.body());
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatResponse<ArrayList<SeatModel>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
