package com.example.cinemamobilefe.view.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.Data_local.DataIntentManager;
import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.Dialog.LoadingDialog;
import com.example.cinemamobilefe.Dialog.LoadingDialogPaymentProcessing;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityPaymentBinding;
import com.example.cinemamobilefe.databinding.ActivitySeatBinding;
import com.example.cinemamobilefe.model.SeatModel;
import com.example.cinemamobilefe.model.SeatSelectedModel;
import com.example.cinemamobilefe.model.SeatSelectedPost;
import com.example.cinemamobilefe.model.ShowtimesModel;
import com.example.cinemamobilefe.model.TicketDetailsModel;
import com.example.cinemamobilefe.model.TicketDetailsPost;
import com.example.cinemamobilefe.model.TicketPost;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.onClick.OnClickSelectTime;
import com.example.cinemamobilefe.view.adapter.TimeAdapter;
import com.example.cinemamobilefe.viewmodel.SeatSelectedViewModel;
import com.example.cinemamobilefe.viewmodel.ShowtimesViewModel;
import com.example.cinemamobilefe.viewmodel.TicketDetailsViewModel;
import com.example.cinemamobilefe.viewmodel.TicketViewModel;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class PaymentActivity extends AppCompatActivity {
    ActivityPaymentBinding binding;
    ArrayList<SeatModel> listSeatModel;
    ShowtimesModel showtimesModel;
    String date;
    int paymentMethods;
    UserModel userModel;
    ArrayList<String> strTicketPosts = new ArrayList<>();
    CountDownLatch latch;

    LoadingDialogPaymentProcessing loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        getIntentData();
        displayData();
        loadingDialog = new LoadingDialogPaymentProcessing(PaymentActivity.this);
        //
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_24_black);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.layoutAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgTickAtm.setVisibility(View.VISIBLE);
                binding.imgTickMomo.setVisibility(View.INVISIBLE);
                paymentMethods = 0;
            }
        });
        binding.layoutMomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgTickMomo.setVisibility(View.VISIBLE);
                binding.imgTickAtm.setVisibility(View.INVISIBLE);
                paymentMethods = 1;
            }
        });
    }

    private void getIntentData() {
        listSeatModel = DataIntentManager.getListSeatSelected(getIntent().getStringExtra(DataIntentManager.DATA_INTENT_SEAT_SELECTED));
        showtimesModel = DataIntentManager.getShowtimes(getIntent().getStringExtra(DataIntentManager.DATA_INTENT_SHOWTIMES));
        date = getIntent().getStringExtra("date");
        userModel = DataLocalManager.getUser();
    }

    private void displayData() {
        int paymentTotal = 0;
        String strSeat = "";

        for (SeatModel model : listSeatModel) {
            paymentTotal += Integer.valueOf(model.getSeatType().getPrice());
            strSeat += model.getRowName() + model.getSeatNumber() + " ";
        }
        String url = showtimesModel.getMovieModel().getImage();
        String newUrl = url.replace("localhost", "10.0.2.2");
        Glide.with(this)
                .load(newUrl)
                .error(R.drawable.image_error)
                .into(binding.imgImage);
        binding.tvMovieName.setText(showtimesModel.getMovieModel().getName());
        binding.tvDate.setText(date);
        binding.tvSeat.setText("Seat: " + strSeat);
        binding.tvTime.setText(showtimesModel.getTimeModel().getTimeName());
        binding.tvRoom.setText(showtimesModel.getRoomModel().getRoomName());
        binding.tvTotalPaymant.setText("Total Payment: " + paymentTotal + " đ");
        //
        binding.tvQuantity.setText(String.valueOf(listSeatModel.size()));
        binding.tvTotal.setText(paymentTotal + " đ");
        //
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.chkSubmit.isChecked()) {
                    Toast.makeText(PaymentActivity.this, "Please agree to CINEMA terms of use", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadingDialog.show();
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("vi", "VN"));
                String formattedDate = today.plusDays(0).format(formatter);

                latch = new CountDownLatch(listSeatModel.size());

                for (SeatModel seatModel : listSeatModel) {
                    String seatType = seatModel.getSeatType().getSeatType();
                    String seatName = seatModel.getRowName() + seatModel.getSeatNumber();
                    SeatSelectedPost model = new SeatSelectedPost();
                    model.setId_seat(seatModel.get_id());
                    model.setId_showtimes(showtimesModel.get_id());
                    model.setPrice(seatModel.getSeatType().getPrice());

                    postViewModelSeat(model, seatType, seatName);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            latch.await(); // Đợi đến khi tất cả các task hoàn thành
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TicketPost ticketPost = new TicketPost();
                                ticketPost.setDate(formattedDate);
                                ticketPost.setId_user(userModel.getId());
                                ticketPost.setId_ticketdetails(strTicketPosts);
//                                Log.d(TAG, "kkkkk: " + strTicketPosts);
//                                Log.d(TAG, "ticketPost: " + new Gson().toJson(ticketPost.getId_ticketdetails()));

                                postViewModelTicket(ticketPost);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void postViewModelSeat(SeatSelectedPost seatSelectedPost, String seatType, String seatName) {
        SeatSelectedViewModel seatSelectedViewModel = new SeatSelectedViewModel(this, seatSelectedPost);
        seatSelectedViewModel.getLiveData1().observe(PaymentActivity.this, seatSelectedModelSeatResponse -> {
            if (seatSelectedModelSeatResponse != null && seatSelectedModelSeatResponse.getData() != null) {
                TicketDetailsPost ticketDetailsPost = new TicketDetailsPost();
                ticketDetailsPost.setId_seatselected(seatSelectedModelSeatResponse.getData().get_id());
                ticketDetailsPost.setId_movie(showtimesModel.getMovieModel().get_id());
                ticketDetailsPost.setDate(showtimesModel.getDate());
                ticketDetailsPost.setMovieName(showtimesModel.getMovieModel().getName());
                ticketDetailsPost.setRoomName(showtimesModel.getRoomModel().getRoomName());
                ticketDetailsPost.setTimeName(showtimesModel.getTimeModel().getTimeName());
                ticketDetailsPost.setSeatType(seatType);
                ticketDetailsPost.setSeatName(seatName);
                ticketDetailsPost.setStatus(false);
                ticketDetailsPost.setPrice(seatSelectedModelSeatResponse.getData().getPrice());
                postViewModelTicketDetails(ticketDetailsPost);
            } else {
                Log.d(MotionEffect.TAG, "Responsedata2: " + new Gson().toJson(seatSelectedModelSeatResponse));
            }
        });
    }

    private void postViewModelTicketDetails(TicketDetailsPost ticketDetailsPost) {
        TicketDetailsViewModel ticketDetailsViewModel = new TicketDetailsViewModel(this, ticketDetailsPost);
        ticketDetailsViewModel.getLiveData().observe(PaymentActivity.this, ticketDetailsPostResponse -> {
            if (ticketDetailsPostResponse != null && ticketDetailsPostResponse.getData() != null) {
                String id = ticketDetailsPostResponse.getData().get_id();
//                Log.d(TAG, "Responsedata1: " + new Gson().toJson(id));
                strTicketPosts.add(id);
//                Log.d(TAG, "Đã thêm id vào strTicketPosts: " + id);
//                Log.d(TAG, "Danh sách strTicketPosts hiện tại: " + new Gson().toJson(strTicketPosts));
            } else {
                Log.d(MotionEffect.TAG, "Responsedata2: " + new Gson().toJson(ticketDetailsPostResponse));
            }
            latch.countDown(); // Giảm đếm latch khi hoàn thành
        });
    }

    private void postViewModelTicket(TicketPost ticketPost) {
        TicketViewModel ticketViewModel = new TicketViewModel(this, ticketPost);
        ticketViewModel.getLiveData().observe(PaymentActivity.this, ticketPostResponse -> {
            if (ticketPostResponse != null && ticketPostResponse.getData() != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.cancel();
                        Toast.makeText(PaymentActivity.this, "Ticket Booking Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 5000);
            } else {
                Log.d(MotionEffect.TAG, "Responsedata2: " + new Gson().toJson(ticketPostResponse));
            }
        });
    }
}