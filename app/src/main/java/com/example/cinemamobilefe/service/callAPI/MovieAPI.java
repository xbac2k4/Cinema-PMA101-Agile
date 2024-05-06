package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.service.response.MovieResponse;
import com.example.cinemamobilefe.model.MovieModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {
    @GET("movie/get-movie")
    Call<MovieResponse<ArrayList<MovieModel>>> getMovie();
}
