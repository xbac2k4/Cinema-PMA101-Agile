package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.service.repository.ShowtimesRepository;
import com.example.cinemamobilefe.service.response.ShowtimesResponse;

import java.util.ArrayList;

public class ShowtimesViewModel {
    private LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> liveData;
    private LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> liveShowtimesByMovie;
    private ShowtimesRepository showtimesRepository;
    public LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> getLiveData() {
        return liveData;
    }
    public LiveData<ShowtimesResponse<ArrayList<ShowtimesModel>>> getLiveShowtimesByMovie() {
        return liveShowtimesByMovie;
    }

    public ShowtimesViewModel(Context context, String date) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        showtimesRepository = new ShowtimesRepository(context);
        liveData = showtimesRepository.getShowtimesByDate(date);
    }
}
