package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.response.UserResponse;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("user/get-user")
    Call<UserResponse<ArrayList<UserModel>>> getUser();

    @POST("user/login")
    Call<UserResponse<UserModel>> login(@Body UserModel user);

    @POST("user/register")
    Call<UserResponse<UserModel>> register(@Body UserModel user);

    @PUT("user/change-password/{id}")
    Call<UserResponse<UserModel>> changePassword(@Path("id") String id, @Body Map<String, String> passwordData);

    @PUT("user/update-info/{id}")
    Call<UserResponse<UserModel>> updateUserInfo(@Path("id") String id, @Body Map<String, String> userInfoData);

    @Multipart
    @PUT("user/update-avatar/{id}")
    Call<UserResponse<UserModel>> updateAvatar(@Path("id") String userId, @Part MultipartBody.Part avatar);

}
