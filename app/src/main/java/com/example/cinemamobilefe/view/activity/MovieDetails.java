package com.example.cinemamobilefe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.Data_local.DataIntentManager;
import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityMainBinding;
import com.example.cinemamobilefe.databinding.ActivityMovieDetailsBinding;
import com.example.cinemamobilefe.model.MovieModel;
import com.example.cinemamobilefe.model.UserModel;

public class MovieDetails extends AppCompatActivity {
    ActivityMovieDetailsBinding binding;
    String id, image, name, start_date, duration, description, directors, name_category;
    MovieModel movieModel;
    UserModel userModel;

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
        userModel = DataLocalManager.getUser();
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Details");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_24_black);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getIntentMovie();
        getTextDisplayInView();
        binding.btnBooknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userModel != null) {
                    Intent intent = new Intent(MovieDetails.this, BookingActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                bundle.putString("image", image);
//                bundle.putString("name", name);
//                bundle.putString("start_date", start_date);
//                bundle.putString("duration", duration);
//                bundle.putString("description", description);
//                bundle.putString("directors", directors);
//                bundle.putString("name_category", name_category);
//                intent.putExtras(bundle);
                    String object_movie = getIntent().getStringExtra(DataIntentManager.DATA_INTENT_MOVIE);
                    intent.putExtra(DataIntentManager.DATA_INTENT_MOVIE, object_movie);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MovieDetails.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void getIntentMovie() {
//        Bundle bundle = getIntent().getExtras();
//        id = bundle.getString("id");
//        image = bundle.getString("image");
//        name = bundle.getString("name");
//        start_date = bundle.getString("start_date");
//        duration = bundle.getString("duration");
//        description = bundle.getString("description");
//        directors = bundle.getString("directors");
//        name_category = bundle.getString("name_category");
        String object_movie = getIntent().getStringExtra(DataIntentManager.DATA_INTENT_MOVIE);
        movieModel = DataIntentManager.getMovie(object_movie);
    }
    private void getTextDisplayInView() {
//        String newUrl = image.replace("localhost", "10.0.2.2");
//        Glide.with(this)
//                .load(newUrl)
//                .centerCrop()
//                .into(binding.imgBanner);
//        Glide.with(this)
//                .load(newUrl)
//                .centerCrop()
//                .into(binding.imgImage);
//        binding.tvNameMovie.setText(name);
//        binding.tvShowDate.setText(start_date);
//        binding.tvDuration.setText(duration + " phút");
//        binding.tvDirectors.setText(directors);
//        binding.tvDescription.setText(description);
//        binding.tvNameCategory.setText(name_category);
        String newUrl = movieModel.getImage().replace("localhost", "10.0.2.2");
        Glide.with(this)
                .load(newUrl)
                .centerCrop()
                .into(binding.imgBanner);
        Glide.with(this)
                .load(newUrl)
                .centerCrop()
                .into(binding.imgImage);
        binding.tvNameMovie.setText(movieModel.getName());
        binding.tvShowDate.setText(movieModel.getStart_date());
        binding.tvDuration.setText(movieModel.getDuration() + " phút");
        binding.tvDirectors.setText(movieModel.getDirectors());
        binding.tvDescription.setText(movieModel.getDescription());
        binding.tvNameCategory.setText(movieModel.getId_category().getName());
    }
}