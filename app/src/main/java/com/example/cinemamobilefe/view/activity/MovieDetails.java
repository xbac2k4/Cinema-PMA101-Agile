package com.example.cinemamobilefe.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityMainBinding;
import com.example.cinemamobilefe.databinding.ActivityMovieDetailsBinding;

public class MovieDetails extends AppCompatActivity {
    ActivityMovieDetailsBinding binding;
    String id, image, name, show_date, duration, evaluate, description, directors, name_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Listener();
    }
    private void Listener() {
//        setSupportActionBar(binding.toolBar);
//        getSupportActionBar().setTitle(null);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        binding.toolBar.setNavigationIcon(R.drawable.ic_back_24);
//        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();;
//            }
//        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getIntentMovie();
        getTextDisplayInView();

    }
    private void getIntentMovie() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        image = bundle.getString("image");
        name = bundle.getString("name");
        show_date = bundle.getString("date");
        duration = bundle.getString("duration");
        evaluate = bundle.getString("evaluate");
        description = bundle.getString("description");
        directors = bundle.getString("directors");
        name_category = bundle.getString("name_category");
    }
    private void getTextDisplayInView() {
        Glide.with(this)
                .load(image)
                .centerCrop()
                .into(binding.imgBanner);
        Glide.with(this)
                .load(image)
                .centerCrop()
                .into(binding.imgImage);
        binding.tvNameMovie.setText(name);
        binding.tvShowDate.setText(show_date);
        binding.tvDuration.setText(duration);
        binding.tvEvaluate.setText(evaluate);
        binding.tvDirectors.setText(directors);
        binding.tvDescription.setText(description);
        binding.tvNameCategory.setText(name_category);
    }
}