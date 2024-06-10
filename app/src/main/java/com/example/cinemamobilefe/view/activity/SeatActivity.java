package com.example.cinemamobilefe.view.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinemamobilefe.Data_local.DataIntentManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivitySeatBinding;
import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.view.adapter.SeatAdapter;
import com.example.cinemamobilefe.viewmodel.SeatSelectedViewModel;
import com.example.cinemamobilefe.viewmodel.SeatViewModel;

import java.util.ArrayList;

public class SeatActivity extends AppCompatActivity {
    ActivitySeatBinding binding;
//    String id, date, roomName, timeName, id_movie;
    ArrayList<SeatModel> list = new ArrayList<>();
    SeatAdapter seatAdapter;
    ShowtimesModel showtimesModel;
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
        ViewModelSeat();
//
//        ArrayList<String> seatNumbers = generateSeatNumbers();
//        SeatAdapter seatAdapter = new SeatAdapter(seatNumbers, SeatActivity.this);
//        binding.rcvSeat.setAdapter(seatAdapter);

        setSupportActionBar(binding.toolbar);

        // Set custom title size
        SpannableString title = new SpannableString("CINEMA Nhóm 5 Agile");
        title.setSpan(new AbsoluteSizeSpan(18, true), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 20sp
        title.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Bold

        // Set custom subtitle size
        SpannableString subtitle = new SpannableString(showtimesModel.getRoomModel().getRoomName() + "  " + showtimesModel.getDate() + " " + showtimesModel.getTimeModel().getTimeName());
        subtitle.setSpan(new AbsoluteSizeSpan(14, true), 0, subtitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 16sp

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
//
//        getSupportActionBar().setTitle("CINEMA Nhóm 5 Agile");
//        getSupportActionBar().setSubtitle(roomName + "  " + date + " " + timeName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_24_black);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seatAdapter.getSelectedSeats().size() > 0) {
                    String date = getIntent().getStringExtra("date");
//                Toast.makeText(SeatActivity.this, "Ghế đã đặt: " + seatAdapter.getSelectedSeats(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SeatActivity.this, PaymentActivity.class);
                    intent.putExtra(DataIntentManager.DATA_INTENT_SEAT_SELECTED, DataIntentManager.setListSeatSelected(seatAdapter.getSelectedSeats()));
                    intent.putExtra(DataIntentManager.DATA_INTENT_SHOWTIMES, DataIntentManager.setShowtimes(showtimesModel));
                    intent.putExtra("date", date);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SeatActivity.this, "Please choose at least 1 seat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    private ArrayList<String> generateSeatNumbers() {
//        ArrayList<String> seatNumbers = new ArrayList<>();
//        String[] rowLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
//
//        for (String rowLetter : rowLetters) {
//            for (int j = 1; j <= 8; j++) { // 9 columns
//                seatNumbers.add(rowLetter + j);
//            }
//        }
//
//        return seatNumbers;
//    }
    private void getIntentShowtimes() {
//        Bundle bundle = getIntent().getExtras();
//        id = bundle.getString("id");
//        date = bundle.getString("date");
//        roomName = bundle.getString("roomName");
//        timeName = bundle.getString("timeName");
//        id_movie = bundle.getString("id_movie");
        String object_showtimes = getIntent().getStringExtra(DataIntentManager.DATA_INTENT_SHOWTIMES);
        showtimesModel = DataIntentManager.getShowtimes(object_showtimes);
    }
    private void ViewModelSeat() {
        SeatViewModel seatViewModel = new SeatViewModel(this);
        seatViewModel.getLiveData().observe(this, listSeatResponse -> {
            if (listSeatResponse != null && listSeatResponse.getData() != null) {
//                Toast.makeText(getContext(), "OKOK", Toast.LENGTH_SHORT).show();
                list = listSeatResponse.getData();
//                seatAdapter = new SeatAdapter(listSeatResponse.getData(), SeatActivity.this);
//                binding.rcvSeat.setAdapter(seatAdapter);
                ViewModelSeatSelected(showtimesModel.get_id());
                Log.d(TAG, "ViewModelSeat: " + listSeatResponse.getMessenger());
                Log.d(TAG, "ViewModelSeat: " + listSeatResponse.getData());
            }
        });
    }
    private void ViewModelSeatSelected(String id_showtimes) {
        SeatSelectedViewModel seatSelectedViewModel = new SeatSelectedViewModel(this, id_showtimes);
        seatSelectedViewModel.getLiveData().observe(this, listSeatResponse -> {
            if (listSeatResponse != null && listSeatResponse.getData() != null) {
//                Toast.makeText(getContext(), "OKOK", Toast.LENGTH_SHORT).show();
                seatAdapter = new SeatAdapter(list, listSeatResponse.getData(),SeatActivity.this);
                binding.rcvSeat.setAdapter(seatAdapter);
                Log.d(TAG, "ViewModelSeatSelected: " + listSeatResponse.getMessenger());
                Log.d(TAG, "ViewModelSeatSelected: " + listSeatResponse.getData());
            }
        });
    }
}