package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.service.response.ShowtimesResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowtimesAPI {
    @GET("showtimes/get-showtimes-by-date")
    Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> getShowtimesByDate(@Query(value = "date", encoded = true) String date);
    @GET("showtimes/get-showtimes-by-movie")
    Call<ShowtimesResponse<ArrayList<ShowtimesModel>>> getShowtimesByMovie(@Query(value = "id_movie", encoded = true) String movie);

}
