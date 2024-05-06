package com.example.cinemamobilefe.service.response;

import com.example.cinemamobilefe.model.CategoryModel;

import java.util.ArrayList;

public class CategoryResponse {
    private int status;
    private String messenger;
    private ArrayList<CategoryModel> data;

    public CategoryResponse() {
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

//    public CategoryModel getData() {
//        return data;
//    }
//
//    public void setData(CategoryModel data) {
//        this.data = data;
//    }

    public ArrayList<CategoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryModel> data) {
        this.data = data;
    }
}
