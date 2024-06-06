package com.example.cinemamobilefe.view.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityMovieDetailsBinding;
import com.example.cinemamobilefe.databinding.ActivitySeatBinding;
import com.example.cinemamobilefe.view.adapter.SeatAdapter;

import java.util.ArrayList;

public class SeatActivity extends AppCompatActivity {
    ActivitySeatBinding binding;
    String id, date, roomName, timeName, id_movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivitySeatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentShowtimes();
        binding.rcvSeat.setLayoutManager(new GridLayoutManager(this, 8)); // 9 columns

        ArrayList<String> seatNumbers = generateSeatNumbers();
        SeatAdapter seatAdapter = new SeatAdapter(seatNumbers, SeatActivity.this);
        binding.rcvSeat.setAdapter(seatAdapter);

        setSupportActionBar(binding.toolbar);

        // Set custom title size
        SpannableString title = new SpannableString("CINEMA Nhóm 5 Agile");
        title.setSpan(new AbsoluteSizeSpan(18, true), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 20sp
        title.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Bold

        // Set custom subtitle size
        SpannableString subtitle = new SpannableString(roomName + "  " + date + " " + timeName);
        subtitle.setSpan(new AbsoluteSizeSpan(14, true), 0, subtitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 16sp

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
//
//        getSupportActionBar().setTitle("CINEMA Nhóm 5 Agile");
//        getSupportActionBar().setSubtitle(roomName + "  " + date + " " + timeName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private ArrayList<String> generateSeatNumbers() {
        ArrayList<String> seatNumbers = new ArrayList<>();
        String[] rowLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};

        for (String rowLetter : rowLetters) {
            for (int j = 1; j <= 8; j++) { // 9 columns
                seatNumbers.add(rowLetter + j);
            }
        }

        return seatNumbers;
    }
    private void getIntentShowtimes() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        date = bundle.getString("date");
        roomName = bundle.getString("roomName");
        timeName = bundle.getString("timeName");
        id_movie = bundle.getString("id_movie");
    }
}