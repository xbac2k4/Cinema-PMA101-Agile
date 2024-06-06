package com.example.cinemamobilefe.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;

import java.util.ArrayList;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder>{
    private final ArrayList<String> list;
    private final Context context;
    private final SparseBooleanArray selectedItems = new SparseBooleanArray();
    private final ArrayList<String> selectedSeats = new ArrayList<>(); // To store selected seats

    public SeatAdapter(ArrayList<String> list, Context context) {
        this.list = list;
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
        holder.seatNumberTextView.setText(list.get(position));
        // Set the background based on the selection state
        if (selectedItems.get(position, false)) {
            holder.itemView.setBackgroundResource(R.drawable.bgr_seat_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bgr_seat_availible); // Your default background
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selection state
                if (selectedItems.get(position, false)) {
                    selectedItems.delete(position);
                    holder.itemView.setBackgroundResource(R.drawable.bgr_seat_availible);
                    selectedSeats.remove(list.get(position)); // Remove from selected seats
                } else {
                    selectedItems.put(position, true);
                    holder.itemView.setBackgroundResource(R.drawable.bgr_seat_selected);
                    selectedSeats.add(list.get(position)); // Add to selected seats
                }
//                Toast.makeText(context, "Selected seat: " + selectedSeats, Toast.LENGTH_SHORT).show();
            }
        });
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
    public ArrayList<String> getSelectedSeats() {
        return selectedSeats;
    }
}
