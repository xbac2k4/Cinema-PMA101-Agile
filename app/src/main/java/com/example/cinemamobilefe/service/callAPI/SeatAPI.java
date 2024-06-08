package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeatAPI {
    @GET("seat/get-all-seat")
    Call<SeatResponse<ArrayList<SeatModel>>> getAllSeat();
}
