package com.example.cinemamobilefe.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.cinemamobilefe.model.Ticket;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.model.TicketPost;
import com.example.cinemamobilefe.service.repository.TicketDetailsRepository;
import com.example.cinemamobilefe.service.repository.TicketRepository;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

public class TicketViewModel {
    private LiveData<SeatResponse<TicketPost>> liveData;
    private LiveData<SeatResponse<ArrayList<Ticket>>> liveData1;
    private TicketRepository ticketRepository;
    public LiveData<SeatResponse<TicketPost>> getLiveData() {
        return liveData;
    }
    public LiveData<SeatResponse<ArrayList<Ticket>>> getLiveData1() {
        return liveData1;
    }
    public TicketViewModel(Context context, TicketPost ticketPost) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        ticketRepository = new TicketRepository(context);
        liveData = ticketRepository.postTicket(ticketPost);
    }
    public TicketViewModel(Context context, String id_user) {
//        Toast.makeText(context, "Date2: " + date, Toast.LENGTH_SHORT).show();
        ticketRepository = new TicketRepository(context);
        liveData1 = ticketRepository.getTicketByUser(id_user);
    }
}
