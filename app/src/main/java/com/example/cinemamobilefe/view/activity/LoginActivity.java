package com.example.cinemamobilefe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemamobilefe.Data_local.DataLocalManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository = new UserRepository(this);

        binding.btnLogin.setOnClickListener(v -> onClickLogin());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void onClickLogin() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

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
    }
}
