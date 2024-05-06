package com.example.cinemamobilefe.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.service.onClick.OnClickSelectMovie;
import com.example.cinemamobilefe.model.MovieModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MovieApdapter extends RecyclerView.Adapter<MovieApdapter.ViewHolder>{
    private List<MovieModel> list;
    private ViewPager2 viewPager2;
    private Context context;
    private OnClickSelectMovie onClickSelectMovie;

    public OnClickSelectMovie getOnClickSelectMovie() {
        return onClickSelectMovie;
    }

    public void setOnClickSelectMovie(OnClickSelectMovie onClickSelectMovie) {
        this.onClickSelectMovie = onClickSelectMovie;
    }

    public MovieApdapter(List<MovieModel> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
        context = viewPager2.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list != null && list.size() > position) {
            MovieModel movieModel = list.get(position);
            Glide.with(context)
                    .load(movieModel.getImage())
                    .into(holder.roundedImageView);
            holder.tv_name_movie.setText(movieModel.getName());
            holder.tv_show_date.setText(movieModel.getShow_date());
            holder.tv_evaluate.setText(movieModel.getEvaluate());
            holder.tv_duration.setText(movieModel.getDuration());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickSelectMovie.selectItem(movieModel);
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
        RoundedImageView roundedImageView;
        TextView tv_name_movie, tv_show_date, tv_duration, tv_evaluate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.item_movie);
            tv_name_movie = itemView.findViewById(R.id.tv_name_movie);
            tv_show_date = itemView.findViewById(R.id.tv_show_date);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_evaluate = itemView.findViewById(R.id.tv_evaluate);
        }
    }
}
