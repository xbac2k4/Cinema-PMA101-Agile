package com.example.cinemamobilefe.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.model.MovieModel;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.service.onClick.OnClickSelectDate;
import com.example.cinemamobilefe.service.onClick.OnClickSelectTime;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
    private final ArrayList<ShowtimesModel> list;
    private final Context context;
    private OnClickSelectTime onClickListen;

    public OnClickSelectTime getOnClickListen() {
        return onClickListen;
    }

    public void setOnClickListen(OnClickSelectTime onClickListen) {
        this.onClickListen = onClickListen;
    }

    public TimeAdapter(ArrayList<ShowtimesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowtimesModel showtimesModel = list.get(position);
        holder.tv_time.setText(showtimesModel.getTimeModel().getTimeName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListen.selectItem(showtimesModel);
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
        TextView tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
