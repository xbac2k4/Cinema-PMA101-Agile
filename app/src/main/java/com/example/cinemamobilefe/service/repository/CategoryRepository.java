package com.example.cinemamobilefe.service.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.service.callAPI.CategoryAPI;
import com.example.cinemamobilefe.service.response.CategoryResponse;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private Context context;
    public CategoryRepository(Context context) {
        this.context = context.getApplicationContext();
    }
    public LiveData<CategoryResponse> getAllCategory() {
        final MutableLiveData<CategoryResponse> mutableLiveData = new MutableLiveData<>();

        CategoryAPI categoryAPI = RetrofitInstance.getRetrofitInstance().create(CategoryAPI.class);

        categoryAPI.getCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        Gson gson = new Gson();
                        CategoryResponse categoryResponse = gson.fromJson(gson.toJson(response.body()), CategoryResponse.class);
//                        Toast.makeText(context, "okOKO", Toast.LENGTH_SHORT).show();
                        mutableLiveData.postValue(categoryResponse);
//                        Log.d(TAG, "data1: " + response.body().getData());
//                        Log.d(TAG, "name: " + response.body().getData().get(0).getName());
                    }
                } else {
                    mutableLiveData.postValue(null);
                    Log.d(TAG, "data2: " + response.body());
//                    Toast.makeText(context, "No2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.d(TAG, "onFailure: " + t.getMessage());
//                Toast.makeText(context, "No1", Toast.LENGTH_SHORT).show();
            }
        });

        return mutableLiveData;
    }
}
