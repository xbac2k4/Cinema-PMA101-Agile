package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.service.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("category/get-category")
    Call<CategoryResponse> getCategory();
}
