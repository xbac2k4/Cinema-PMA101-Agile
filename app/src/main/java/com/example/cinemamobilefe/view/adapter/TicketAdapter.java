package com.example.cinemamobilefe.view.adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.model.Ticket;
import com.example.cinemamobilefe.model.TicketDetailsModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{
    private final Context context;
    private final ArrayList<TicketDetailsModel> listTicket;

    public TicketAdapter(Context context, ArrayList<TicketDetailsModel> listTicket) {
        this.context = context;
        this.listTicket = listTicket;
//        Log.d(TAG, "TicketAdapter: " + new Gson().toJson(listTicket));
//        Toast.makeText(context, "" + listTicket.size(), Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TicketDetailsModel ticketDetails = listTicket.get(position);
//        Log.d(TAG, "Object ticketdetail: " + new Gson().toJson(ticketDetails));
        holder.tv_movie_name.setText(ticketDetails.getMovieName());
        holder.tv_room_seat.setText(ticketDetails.getRoomName() + "  " + ticketDetails.getSeatName());
        holder.tv_date_time.setText(ticketDetails.getDate() + "  " + ticketDetails.getTimeName());
        holder.tv_price.setText(ticketDetails.getPrice() + " đ");
        holder.tv_id_ticket_detail.setText(ticketDetails.get_id());
    }

    @Override
    public int getItemCount() {
        if (listTicket != null) {
            return listTicket.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_movie_name, tv_date_time, tv_price, tv_id_ticket_detail, tv_details, tv_room_seat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_movie_name = itemView.findViewById(R.id.tv_movie_name);
            tv_date_time = itemView.findViewById(R.id.tv_date_time);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_id_ticket_detail = itemView.findViewById(R.id.tv_id_ticket_detail);
            tv_room_seat = itemView.findViewById(R.id.tv_room_seat);
            tv_details = itemView.findViewById(R.id.tv_details);
        }
    }
}
