package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.service.repository.SeatRepository;
import com.example.cinemamobilefe.service.repository.SeatSelectedRepository;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

public class SeatSelectedViewModel {
    private LiveData<SeatResponse<ArrayList<SeatSelectedModel>>> liveData;
    private LiveData<SeatResponse<SeatSelectedPost>> liveData1;
    private SeatSelectedRepository seatSelectedRepository;
    public LiveData<SeatResponse<ArrayList<SeatSelectedModel>>> getLiveData() {
        return liveData;
    }
    public LiveData<SeatResponse<SeatSelectedPost>> getLiveData1() {
        return liveData1;
    }
    public SeatSelectedViewModel(Context context, String id_showtimes) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        seatSelectedRepository = new SeatSelectedRepository(context);
        liveData = seatSelectedRepository.getSeatSelectedByShowtimes(id_showtimes);
    }

    public SeatSelectedViewModel(Context context, SeatSelectedPost seatSelectedPost) {
        seatSelectedRepository = new SeatSelectedRepository(context);
        liveData1 = seatSelectedRepository.postSeatSelected(seatSelectedPost);
    }
}
