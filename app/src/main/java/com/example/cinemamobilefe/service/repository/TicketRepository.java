package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.model.Ticket;
import com.example.cinemamobilefe.model.TicketDetailsModel;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.model.TicketPost;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.TicketAPI;
import com.example.cinemamobilefe.service.callAPI.TicketDetailAPI;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketRepository {
    private Context context;
    public TicketRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<SeatResponse<TicketPost>> postTicket(TicketPost ticketPost) {
        final MutableLiveData<SeatResponse<TicketPost>> mutableLiveData = new MutableLiveData<>();

        TicketAPI ticketAPI = RetrofitInstance.getRetrofitInstance().create(TicketAPI.class);
        ticketAPI.postTicket(ticketPost).enqueue(new Callback<SeatResponse<TicketPost>>() {
            @Override
            public void onResponse(Call<SeatResponse<TicketPost>> call, Response<SeatResponse<TicketPost>> response) {
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
            public void onFailure(Call<SeatResponse<TicketPost>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
    public LiveData<SeatResponse<ArrayList<Ticket>>> getTicketByUser(String id_user) {
        final MutableLiveData<SeatResponse<ArrayList<Ticket>>> mutableLiveData = new MutableLiveData<>();

        TicketAPI ticketAPI = RetrofitInstance.getRetrofitInstance().create(TicketAPI.class);
        ticketAPI.getTicketByUser(id_user).enqueue(new Callback<SeatResponse<ArrayList<Ticket>>>() {
            @Override
            public void onResponse(Call<SeatResponse<ArrayList<Ticket>>> call, Response<SeatResponse<ArrayList<Ticket>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        mutableLiveData.postValue(response.body());
//                        Log.d(TAG, "dataSeatSelected: " + response.body());
                    }
                } else {
                    mutableLiveData.postValue(null);
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatResponse<ArrayList<Ticket>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
