package com.example.cinemamobilefe.service.repository;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.RetrofitInstance;
import com.example.cinemamobilefe.service.callAPI.UserAPI;
import com.example.cinemamobilefe.service.response.UserResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

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

    public LiveData<UserResponse<UserModel>> register(UserModel user) {
        final MutableLiveData<UserResponse<UserModel>> mutableLiveData = new MutableLiveData<>();

        UserAPI userAPI = RetrofitInstance.getRetrofitInstance().create(UserAPI.class);

        userAPI.register(user).enqueue(new Callback<UserResponse<UserModel>>() {
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

    public LiveData<UserResponse<UserModel>> changePassword(String id, String currentPassword, String newPassword) {
        final MutableLiveData<UserResponse<UserModel>> mutableLiveData = new MutableLiveData<>();

        UserAPI userAPI = RetrofitInstance.getRetrofitInstance().create(UserAPI.class);

        // Tạo một map chứa mật khẩu hiện tại và mật khẩu mới
        Map<String, String> passwordData = new HashMap<>();
        passwordData.put("currentPassword", currentPassword);
        passwordData.put("newPassword", newPassword);

        userAPI.changePassword(id, passwordData).enqueue(new Callback<UserResponse<UserModel>>() {
            @Override
            public void onResponse(Call<UserResponse<UserModel>> call, Response<UserResponse<UserModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(null);
                    try {
                        Log.e("UserRepository", "Response error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserModel>> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.e("UserRepository", "Change password request failed", t);
            }
        });

        return mutableLiveData;
    }

    // Thêm phương thức cập nhật thông tin người dùng
    public LiveData<UserResponse<UserModel>> updateUserInfo(String id, String username, String phoneNumber) {
        final MutableLiveData<UserResponse<UserModel>> mutableLiveData = new MutableLiveData<>();

        UserAPI userAPI = RetrofitInstance.getRetrofitInstance().create(UserAPI.class);

        // Tạo một map chứa thông tin mới
        Map<String, String> userInfoData = new HashMap<>();
        userInfoData.put("username", username); // Sửa lại khóa để phù hợp với backend
        userInfoData.put("phoneNumber", phoneNumber); // Sửa lại khóa để phù hợp với backend

        userAPI.updateUserInfo(id, userInfoData).enqueue(new Callback<UserResponse<UserModel>>() {
            @Override
            public void onResponse(Call<UserResponse<UserModel>> call, Response<UserResponse<UserModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(null);
                    try {
                        Log.e("UserRepository", "Response error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserModel>> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.e("UserRepository", "Update user info request failed", t);
            }
        });

        return mutableLiveData;
    }

    public LiveData<UserResponse<UserModel>> updateAvatar(UserModel userModel, MultipartBody.Part multipartBody) {
        MutableLiveData<UserResponse<UserModel>> data = new MutableLiveData<>();

        UserAPI userAPI = RetrofitInstance.getRetrofitInstance().create(UserAPI.class);
        userAPI.updateAvatar(userModel.getId(), multipartBody).enqueue(new Callback<UserResponse<UserModel>>() {
            @Override
            public void onResponse(Call<UserResponse<UserModel>> call, Response<UserResponse<UserModel>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                    Log.d(TAG, "update anh: " + response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


}
