package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.service.repository.MovieRepository;
import com.example.cinemamobilefe.service.response.MovieResponse;
import com.example.cinemamobilefe.model.MovieModel;

import java.util.ArrayList;

public class MovieViewModel {
    private LiveData<MovieResponse<ArrayList<MovieModel>>> liveData;
    private MovieRepository movieRepository;
    public LiveData<MovieResponse<ArrayList<MovieModel>>> getLiveData() {
        return liveData;
    }
    public MovieViewModel(Context context) {
        movieRepository = new MovieRepository(context);
        liveData = movieRepository.getAllMovie();
    }
}
