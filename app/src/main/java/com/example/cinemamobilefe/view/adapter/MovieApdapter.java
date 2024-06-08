package com.example.cinemamobilefe.view.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            String url = movieModel.getImage();
//            Log.d(TAG, "onBindViewHolder: " + url);
            String newUrl = url.replace("localhost", "10.0.2.2");
            Glide.with(context)
                    .load(newUrl)
                    .error(R.drawable.image_error)
                    .into(holder.roundedImageView);
            holder.tv_name_movie.setText(movieModel.getName());
            holder.tv_start_date.setText(movieModel.getStart_date());
            holder.tv_duration.setText(movieModel.getDuration() + " phút");
            holder.btn_book_now.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_name_movie, tv_start_date, tv_duration, tv_evaluate;
        Button btn_book_now;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.item_movie);
            tv_name_movie = itemView.findViewById(R.id.tv_name_movie);
            tv_start_date = itemView.findViewById(R.id.tv_start_date);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_evaluate = itemView.findViewById(R.id.tv_evaluate);
            btn_book_now = itemView.findViewById(R.id.btn_book_now);
        }
    }
}
