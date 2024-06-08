package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.repository.UserRepository;
import com.example.cinemamobilefe.service.response.UserResponse;

public class UserViewModel {
    private LiveData<UserResponse<UserModel>> liveData;
    private UserRepository userRepository;

    public LiveData<UserResponse<UserModel>> getLiveData() {
        return liveData;
    }

    public UserViewModel(Context context) {
        userRepository = new UserRepository(context);
    }

    public void login(UserModel user) {
        liveData = userRepository.login(user);
    }
}
