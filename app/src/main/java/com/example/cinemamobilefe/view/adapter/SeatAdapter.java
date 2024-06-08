package com.example.cinemamobilefe.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;

import java.util.ArrayList;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder>{
    private final ArrayList<SeatModel> list;
    private final ArrayList<SeatSelectedModel> listSelected;

    private final Context context;
    private final SparseBooleanArray selectedItems = new SparseBooleanArray();
    private final ArrayList<SeatModel> selectedSeats = new ArrayList<>(); // To store selected seatsAR

    public SeatAdapter(ArrayList<SeatModel> list, ArrayList<SeatSelectedModel> listSelected, Context context) {
        this.list = list;
        this.listSelected = listSelected;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SeatModel seat = list.get(position);
        // Check if the seat is in the listSelected and set the background accordingly
        boolean isBooked = false;
        for (SeatSelectedModel selectedSeat : listSelected) {
            if (seat.get_id().equals(selectedSeat.getSeatModel().get_id())) {
                isBooked = true;
                break;
            }
        }
        // Set the background based on the initial selection state
        if (isBooked) {
            holder.itemView.setBackgroundResource(R.drawable.bgr_seat_booked);
            holder.itemView.setOnClickListener(null);  // Disable click for booked seats
            holder.seatNumberTextView.setText("");
        } else if (selectedItems.get(position, false)) {
            holder.itemView.setBackgroundResource(R.drawable.bgr_seat_selected);
            holder.seatNumberTextView.setText(seat.getRowName() + seat.getSeatNumber());
        } else {
//            if (list.get(position).getRowName().equalsIgnoreCase("G") || list.get(position).getRowName().equalsIgnoreCase("H") || list.get(position).getRowName().equalsIgnoreCase("I")) {
//                holder.itemView.setBackgroundResource(R.drawable.bgr_seat_vip);
//            } else if (list.get(position).getRowName().equalsIgnoreCase("J") || list.get(position).getRowName().equalsIgnoreCase("K")) {
//                holder.itemView.setBackgroundResource(R.drawable.bgr_seat_sweetbox);
//            } else {
//                holder.itemView.setBackgroundResource(R.drawable.bgr_seat_availible);
//            }
            holder.seatNumberTextView.setText(seat.getRowName() + seat.getSeatNumber());
            holder.itemView.setBackgroundResource(R.drawable.bgr_seat_availible);
        }

        if (!isBooked) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle the selection state
                    if (selectedItems.get(position, false)) {
                        selectedItems.delete(position);
                        holder.itemView.setBackgroundResource(R.drawable.bgr_seat_availible);
                        selectedSeats.remove(seat); // Remove from selected seats
                    } else {
                        selectedItems.put(position, true);
                        holder.itemView.setBackgroundResource(R.drawable.bgr_seat_selected);
                        selectedSeats.add(seat); // Add to selected seats
                    }
//                Toast.makeText(context, "Selected seat: " + selectedSeats, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView seatNumberTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumberTextView = itemView.findViewById(R.id.tv_seat);
        }
    }
    public ArrayList<SeatModel> getSelectedSeats() {
        return selectedSeats;
    }
}
