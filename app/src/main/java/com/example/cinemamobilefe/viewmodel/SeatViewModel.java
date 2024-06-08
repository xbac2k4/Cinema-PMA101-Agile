package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.service.repository.SeatRepository;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

public class SeatViewModel {
    private LiveData<SeatResponse<ArrayList<SeatModel>>> liveData;
    private SeatRepository seatRepository;
    public LiveData<SeatResponse<ArrayList<SeatModel>>> getLiveData() {
        return liveData;
    }
    public SeatViewModel(Context context) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        seatRepository = new SeatRepository(context);
        liveData = seatRepository.getAllSeat();
    }
}
