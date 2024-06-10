package com.example.cinemamobilefe.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.FragmentHomeBinding;
import com.example.cinemamobilefe.databinding.FragmentTicketsBinding;
import com.example.cinemamobilefe.view.viewpage.ViewPageTicket;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FragmentTickets extends Fragment {
    FragmentTicketsBinding binding;
    ViewPageTicket viewPageTicket;

    public FragmentTickets() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPageTicket = new ViewPageTicket(getActivity());
        binding.viewPage2.setAdapter(viewPageTicket);
        new TabLayoutMediator(binding.tabLayout, binding.viewPage2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: tab.setText("Movie about to watch"); break;
                    case 1: tab.setText("Movie watched"); break;
                }
            }
        }).attach();
    }
}