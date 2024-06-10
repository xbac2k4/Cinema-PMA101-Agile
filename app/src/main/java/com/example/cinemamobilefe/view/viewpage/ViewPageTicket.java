package com.example.cinemamobilefe.view.viewpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemamobilefe.view.fragment.FragmentMovieAboutToWatch;
import com.example.cinemamobilefe.view.fragment.FragmentMovieWatched;

public class ViewPageTicket extends FragmentStateAdapter {

    public ViewPageTicket(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new FragmentMovieAboutToWatch();
            case 1: return new FragmentMovieWatched();
        }
        return new FragmentMovieAboutToWatch();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
