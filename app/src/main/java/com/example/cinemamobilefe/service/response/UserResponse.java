package com.example.cinemamobilefe.service.response;

import java.util.List;

public class UserResponse<T> {
    private int status;
    private String messenger;
    private T data; // Thay vì T data, sử dụng List<T> data để đảm bảo dữ liệu trả về là một danh sách
    private String token;
    private String refreshToken;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
