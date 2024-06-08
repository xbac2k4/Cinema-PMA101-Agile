package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.response.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {
    @GET("user/get-user")
    Call<UserResponse<ArrayList<UserModel>>> getUser();

    @POST("user/login")
    Call<UserResponse<UserModel>> login(@Body UserModel user);
}
