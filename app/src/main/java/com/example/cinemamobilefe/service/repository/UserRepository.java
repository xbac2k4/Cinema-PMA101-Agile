package com.example.cinemamobilefe.service.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.UserAPI;
import com.example.cinemamobilefe.service.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private Context context;

    public UserRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<UserResponse<UserModel>> login(UserModel user) {
        final MutableLiveData<UserResponse<UserModel>> mutableLiveData = new MutableLiveData<>();

        UserAPI userAPI = RetrofitInstance.getRetrofitInstance().create(UserAPI.class);

        userAPI.login(user).enqueue(new Callback<UserResponse<UserModel>>() {
            @Override
            public void onResponse(Call<UserResponse<UserModel>> call, Response<UserResponse<UserModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                    Log.e("UserReposi", "Login request fai"+response.body());

                } else {
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserModel>> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.e("UserRepository", "Login request failed", t);
            }
        });

        return mutableLiveData;
    }
}
