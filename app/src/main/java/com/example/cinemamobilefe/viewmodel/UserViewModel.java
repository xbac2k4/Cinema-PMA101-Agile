package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.repository.TicketRepository;
import com.example.cinemamobilefe.service.repository.UserRepository;
import com.example.cinemamobilefe.service.response.UserResponse;

import okhttp3.MultipartBody;

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
    public void register(UserModel user) {
        liveData = userRepository.register(user);
    }
//
    public void updateUserInfo(String id, String username, String phoneNumber, LifecycleOwner lifecycleOwner) {
        liveData = userRepository.updateUserInfo(id, username, phoneNumber);
    }
    public UserViewModel(Context context, UserModel userModel, MultipartBody.Part image) {
        userRepository = new UserRepository(context);
        liveData = userRepository.updateAvatar(userModel, image);
    }
}
