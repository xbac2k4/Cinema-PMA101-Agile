package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SeatSeletedAPI {
    @GET("seatselected/get-seatselected-by-showtimes")
    Call<SeatResponse<ArrayList<SeatSelectedModel>>> getSeatSelectedByShowtimes(@Query(value = "id_showtimes", encoded = true) String idShowtimes);
    @POST("seatselected/add-seatselected")
    Call<SeatResponse<SeatSelectedPost>> postSeatSelected(@Body SeatSelectedPost seatSelectedPost);
}
