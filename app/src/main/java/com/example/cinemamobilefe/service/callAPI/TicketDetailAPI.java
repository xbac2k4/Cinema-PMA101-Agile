package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.service.response.SeatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TicketDetailAPI {
    @POST("detailticket/add-detailticket")
    Call<SeatResponse<TicketDetailsPost>> postTicketDetails(@Body TicketDetailsPost ticketDetailsPost);
}
