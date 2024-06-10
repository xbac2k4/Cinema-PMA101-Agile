package com.example.cinemamobilefe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.Dialog.LoadingDialog;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityLoginBinding;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.repository.UserRepository;
import com.example.cinemamobilefe.service.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    UserRepository userRepository;
    UserModel userModel;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        if (email != null) {
            binding.edtUsername.setText(email);
        }
        if (password != null) {
            binding.edtPassword.setText(password);
        }

        userRepository = new UserRepository(this);
        binding.btnLogin.setOnClickListener(v -> onClickLogin());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_24_black);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadingDialog = new LoadingDialog(this);
        binding.tvRegister.setOnClickListener(v-> onClickRegister());
    }
    private void onClickRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void onClickLogin() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        loadingDialog.show();

        if (!username.isEmpty() && !password.isEmpty()) {
            UserModel user = new UserModel();
            user.setEmail(username);
            user.setPassword(password);

            userRepository.login(user).observe(this, userModelUserResponse ->  {
                if (userModelUserResponse != null && userModelUserResponse.getData() != null) {
                    if (userModelUserResponse.getStatus() == 200) {
                        handleLoginSuccess(userModelUserResponse.getData());
                    } else {
                        handleLoginFailure();
                    }
                } else {
                    handleLoginFailure();
                }
            });
        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            loadingDialog.cancel();
        }
    }

    private void handleLoginSuccess(UserModel userModel) {
        Log.d("LoginActivity", "Đăng nhập thành công");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("auth", "hhh");

        DataLocalManager.setUser(userModel);

        startActivity(intent);
        finish();
    }

    private void handleLoginFailure() {
        Log.e("LoginActivity", "Đăng nhập thất bại");
        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        loadingDialog.cancel();
    }
}
