package com.example.cinemamobilefe.service.response;

public class MovieResponse<T> {
    private int status;
    private String messenger;
    private T data;

    public MovieResponse() {
    }

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

//    public ArrayList<MovieModel> getData() {
//        return data;
//    }
//
//    public void setData(ArrayList<MovieModel> data) {
//        this.data = data;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
