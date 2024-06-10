package com.example.cinemamobilefe.view.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemamobilefe.Data_local.DataIntentManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityBookingBinding;
import com.example.cinemamobilefe.databinding.ActivityLoginBinding;
import com.example.cinemamobilefe.model.MovieModel;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.model.TimeModel;
import com.example.cinemamobilefe.service.onClick.OnClickSelectDate;
import com.example.cinemamobilefe.service.onClick.OnClickSelectTime;
import com.example.cinemamobilefe.service.response.ShowtimesResponse;
import com.example.cinemamobilefe.view.adapter.DateAdapter;
import com.example.cinemamobilefe.view.adapter.TimeAdapter;
import com.example.cinemamobilefe.viewmodel.MovieViewModel;
import com.example.cinemamobilefe.viewmodel.ShowtimesViewModel;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {
    ActivityBookingBinding binding;
    ArrayList<String[]> listDate = new ArrayList<String[]>();
    String newDate;
    String newToday;
    String id, image, name, start_date, duration, description, directors, name_category;
    ArrayList<ShowtimesModel> list = new ArrayList<>();
    TimeAdapter timeAdapter;
    MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentMovie();
        AddListDate(listDate);
        DateAdapter dateAdapter = new DateAdapter(listDate, this);
        binding.rcvDate.setAdapter(dateAdapter);
        binding.tvDdMmYyyy.setText(newDate);
        ViewModelShowTimes(newToday, movieModel.get_id());
        dateAdapter.setOnClickListen(new OnClickSelectDate() {
            @Override
            public void selectItem(String dayOfWeek, String day, String month, String year) {
                newDate = dayOfWeek + ", " + day + " tháng " + month + " năm " + year;
                binding.tvDdMmYyyy.setText(newDate);
                ViewModelShowTimes(day + "/" + month + "/" + year, movieModel.get_id());
            }
        });
        // toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(movieModel.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_24_black);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void AddListDate(ArrayList<String[]> list) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
//        Toast.makeText(this, "" + formatter, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 7; i++) {
            String formattedDate = today.plusDays(i).format(formatter);
            String[] parts = formattedDate.split(", ");
            String dayOfWeek = parts[0];
            String[] dateParts = parts[1].split("/");
            String day = dateParts[0];
            String month = dateParts[1];
            String year = dateParts[2];

            list.add(new String[]{dayOfWeek, day, month, year});
            if (i == 0) {
                newDate = dayOfWeek + ", " + day + " tháng " + month + " năm " + year;
                newToday =  day + "/" + month + "/" + year;
            }
        }
    }
    private void ViewModelShowTimes(String date, String id_movie) {
//        Toast.makeText(this, "ok1", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Date: " + date, Toast.LENGTH_SHORT).show();
        ShowtimesViewModel showtimesViewModel = new ShowtimesViewModel(this, date);
        showtimesViewModel.getLiveData().observe(BookingActivity.this, listShowtimes -> {
            if (listShowtimes != null && listShowtimes.getData() != null) {
                // Xử lý dữ liệu showtimesResponse ở đây
//                list = listShowtimes.getData();
                ArrayList<ShowtimesModel> newListShowtimes = new ArrayList<>();
                for (ShowtimesModel showtimes : listShowtimes.getData()) {
                    if (showtimes.getMovieModel().get_id().toLowerCase().contains(id_movie.toLowerCase())) {
                        newListShowtimes.add(showtimes);
//                        Log.d(TAG, "ViewModelShowTimes: " + showtimes.get_id());
                    }
                }
                timeAdapter = new TimeAdapter( newListShowtimes, BookingActivity.this);
                binding.rcvTime.setAdapter(timeAdapter);
                timeAdapter.setOnClickListen(new OnClickSelectTime() {
                    @Override
                    public void selectItem(ShowtimesModel showtimesModel) {
//                        Toast.makeText(BookingActivity.this, "Đã chọn lịch chiếu", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookingActivity.this, SeatActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("id", showtimesModel.get_id());
//                        bundle.putString("date", showtimesModel.getDate());
//                        bundle.putString("roomName", showtimesModel.getRoomModel().getRoomName());
//                        bundle.putString("timeName", showtimesModel.getTimeModel().getTimeName());
//                        bundle.putString("id_movie", showtimesModel.getMovieModel().get_id());
//                        intent.putExtras(bundle);
                        intent.putExtra(DataIntentManager.DATA_INTENT_SHOWTIMES, DataIntentManager.setShowtimes(showtimesModel));
                        intent.putExtra("date", newDate);
                        startActivity(intent);
                    }
                });
//                Toast.makeText(this, "list size: " + list.size(), Toast.LENGTH_SHORT).show();
                if (newListShowtimes.size() == 0) {
                    binding.tvNoScreenings.setVisibility(View.VISIBLE);
                    binding.layoutTenRap.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvNoScreenings.setVisibility(View.INVISIBLE);
                    binding.layoutTenRap.setVisibility(View.VISIBLE);
                }
//                Toast.makeText(BookingActivity.this, "Có dữ liệu", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "Responsedata1: " + new Gson().toJson(listShowtimes.getData()));
            } else {
                // Xử lý khi không có dữ liệu
//                Toast.makeText(BookingActivity.this, "Lại null", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Responsedata2: " + new Gson().toJson(listShowtimes));
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
}