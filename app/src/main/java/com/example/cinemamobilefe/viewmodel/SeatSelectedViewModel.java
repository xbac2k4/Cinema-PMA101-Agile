package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.service.repository.SeatRepository;
import com.example.cinemamobilefe.service.repository.SeatSelectedRepository;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

public class SeatSelectedViewModel {
    private LiveData<SeatResponse<ArrayList<SeatSelectedModel>>> liveData;
    private SeatSelectedRepository seatSelectedRepository;
    public LiveData<SeatResponse<ArrayList<SeatSelectedModel>>> getLiveData() {
        return liveData;
    }
    public SeatSelectedViewModel(Context context, String id_showtimes) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        seatSelectedRepository = new SeatSelectedRepository(context);
        liveData = seatSelectedRepository.getSeatSelectedByShowtimes(id_showtimes);
    }
}
