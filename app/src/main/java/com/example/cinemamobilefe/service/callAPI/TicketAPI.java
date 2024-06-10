package com.example.cinemamobilefe.service.callAPI;

import com.example.cinemamobilefe.model.Ticket;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.model.TicketPost;
import com.example.cinemamobilefe.service.response.SeatResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TicketAPI {
    @POST("ticket/add-ticket")
    Call<SeatResponse<TicketPost>> postTicket(@Body TicketPost ticketPost);
    @GET("ticket/get-ticket-by-user")
    Call<SeatResponse<ArrayList<Ticket>>> getTicketByUser(@Query("id_user") String user);
}
