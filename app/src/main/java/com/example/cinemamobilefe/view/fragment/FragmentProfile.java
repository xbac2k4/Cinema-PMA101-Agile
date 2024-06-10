package com.example.cinemamobilefe.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.FragmentProfileBinding;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.view.activity.LoginActivity;
import com.example.cinemamobilefe.viewmodel.MovieViewModel;
import com.example.cinemamobilefe.viewmodel.UserViewModel;

public class FragmentProfile extends Fragment {
    private UserViewModel userViewModel;
    private FragmentProfileBinding binding;

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.layoutDangxuat.setOnClickListener(v -> showLogoutConfirmationDialog());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserModel userModel = DataLocalManager.getUser();
//        Toast.makeText(getContext(), ""+userModel.getUsername(), Toast.LENGTH_SHORT).show();
//        ViewModelUser();
          binding.tvUsername.setText(userModel.getUsername());
//        Toast.makeText(getContext(), ""+userModel.getAvatar(), Toast.LENGTH_SHORT).show();
          updateUI(userModel);
    }

    private void updateUI(UserModel user) {

            String url = user.getAvatar();
//            Log.d(TAG, "onBindViewHolder: " + url);
            String newUrl = url.replace("localhost", "10.0.2.2");
            Glide.with(getContext())
                    .load(newUrl).centerCrop()
                    .error(R.drawable.ic_avt_df)
                    .into(binding.imgAvt);

    }

//    private void ViewModelUser() {
//        userViewModel = new UserViewModel(getContext());
//        userViewModel.getLiveData().observe(getActivity(), userResponse -> {
//            if (userResponse != null && userResponse.getData() != null) {
//                UserModel user = userResponse.getData(); // Assuming the user data is in the first element
//                updateUI(user);
//            } else {
//                Toast.makeText(getContext(), "Failed to get user data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    // Method to display logout confirmation dialog
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn thoát?")
                .setPositiveButton("Yes", (dialog, id) -> logoutClicked())
                .setNegativeButton("No", (dialog, id) -> {
                    // Do nothing, dialog will close automatically when clicking No
                });
        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method logoutClicked
    private void logoutClicked() {
        // Perform the steps to log out the user and redirect them to the login screen
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        DataLocalManager.removeUser();
        startActivity(intent);
        requireActivity().finish(); // Close FragmentProfile to prevent the user from going back
    }
}
