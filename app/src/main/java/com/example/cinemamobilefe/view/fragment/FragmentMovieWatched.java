package com.example.cinemamobilefe.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.FragmentMovieAboutToWatchBinding;
import com.example.cinemamobilefe.databinding.FragmentMovieWatchedBinding;
import com.example.cinemamobilefe.model.TicketDetailsModel;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.view.adapter.TicketAdapter;
import com.example.cinemamobilefe.viewmodel.TicketViewModel;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class FragmentMovieWatched extends Fragment {
    FragmentMovieWatchedBinding binding;
    Context context;
    UserModel userModel;
    TicketAdapter ticketAdapter;
    ArrayList<TicketDetailsModel> list;
    CountDownLatch latch;
    public FragmentMovieWatched() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieWatchedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        userModel = DataLocalManager.getUser();
        list = new ArrayList<>();
//        Toast.makeText(context, "id_user: " + userModel.getId(), Toast.LENGTH_SHORT).show();
        ViewModelTicket(userModel.getId());
//        Log.d(TAG, "onViewCreated: " + list);
//        listTicket(list);

//        if (latch != null) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        latch.await(); // Đợi đến khi tất cả các task hoàn thành
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    requireActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            listTicket(list);
//                        }
//                    });
//                }
//            }).start();
//        }
    }
    private void ViewModelTicket(String id_user) {
        TicketViewModel ticketViewModel = new TicketViewModel(getContext(), id_user);
//        Toast.makeText(context, "ko dduowjc", Toast.LENGTH_SHORT).show();
        ticketViewModel.getLiveData1().observe(getActivity(), ticketResponse -> {
            if (ticketResponse != null && ticketResponse.getData() != null) {
//                latch = new CountDownLatch(ticketResponse.getData().size());
//                if (latch != null) {
                for (int i = 0; i < ticketResponse.getData().size(); i++) {
                    for (int j = 0; j < ticketResponse.getData().get(i).getId_ticketdetails().size(); j++) {
                        if (ticketResponse.getData().get(i).getId_ticketdetails().get(j).isStatus()) {
                            list.add(ticketResponse.getData().get(i).getId_ticketdetails().get(j));
                        }
//                            Log.d(TAG, "ViewModelTicket: " + new Gson().toJson(ticketResponse.getData().get(i).getId_ticketdetails().get(j)));
                    }

                }
//                    Log.d(TAG, "onViewCreated: " + list);
                listTicket(list);
                if (list.size() > 0) {
                    binding.tvNoData.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvNoData.setVisibility(View.VISIBLE);
                }
//                }
//                latch.countDown(); // Giảm đếm latch khi hoàn thành
            }
        });
    }
    private void listTicket(ArrayList<TicketDetailsModel> listTicketDetails) {
//        Log.d(TAG, "listTicket: " + listTicketDetails);
        ticketAdapter = new TicketAdapter(getContext(), listTicketDetails);
        binding.rcvMovieWatched.setAdapter(ticketAdapter);
    }
}