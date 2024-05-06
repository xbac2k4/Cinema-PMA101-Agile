package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemamobilefe.service.repository.CategoryRepository;
import com.example.cinemamobilefe.service.response.CategoryResponse;


public class CategoryViewModel extends ViewModel {
    private LiveData<CategoryResponse> liveData;
    private CategoryRepository categoryRepository;
    public LiveData<CategoryResponse> getLiveData() {
        return liveData;
    }
    public CategoryViewModel(Context context) {
        categoryRepository = new CategoryRepository(context);
        liveData = categoryRepository.getAllCategory();
    }
}
