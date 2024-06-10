package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.service.repository.SeatSelectedRepository;
import com.example.cinemamobilefe.service.repository.TicketDetailsRepository;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

public class TicketDetailsViewModel {
    private LiveData<SeatResponse<TicketDetailsPost>> liveData;
//    private LiveData<SeatResponse<SeatSelectedPost>> liveData1;
    private TicketDetailsRepository ticketDetailsRepository;
    public LiveData<SeatResponse<TicketDetailsPost>> getLiveData() {
        return liveData;
    }
//    public LiveData<SeatResponse<SeatSelectedPost>> getLiveData1() {
//        return liveData1;
//    }
    public TicketDetailsViewModel(Context context, TicketDetailsPost ticketDetailsPost) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        ticketDetailsRepository = new TicketDetailsRepository(context);
        liveData = ticketDetailsRepository.postTicketDetails(ticketDetailsPost);
    }

//    public TicketDetailsViewModel(Context context, SeatSelectedPost seatSelectedPost) {
//        seatSelectedRepository = new SeatSelectedRepository(context);
//        liveData1 = seatSelectedRepository.postSeatSelected(seatSelectedPost);
//    }
}
