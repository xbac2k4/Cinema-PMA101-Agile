package com.example.cinemamobilefe.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cinemamobilefe.databinding.ActivityRegisterBinding;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.repository.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    UserRepository userRepository;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository = new UserRepository(this);

        binding.btnRegister.setOnClickListener(v -> onClickRegister());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("REGISTER");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void onClickRegister() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        String cfpassword = binding.edtCfpassword.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty() && !cfpassword.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            if (password.equals(cfpassword)){

                if (password.length() < 8) {
                    Toast.makeText(this, "Mật khẩu phải ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra định dạng email
                if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra định dạng số điện thoại
                if (!phone.matches("^(\\+|0)[0-9]{9,11}$")) {
                    Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserModel user = new UserModel();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(username);
                user.setPhoneNumber(phone);

                userRepository.register(user).observe(this, userModelUserResponse ->  {
                    if (userModelUserResponse != null ) {
                        if (userModelUserResponse.getStatus() == 200) {
                            handleLoginSuccess(userModelUserResponse.getData());
                        } else if (userModelUserResponse.getStatus() == -2) {
                            Toast.makeText(this, userModelUserResponse.getMessenger(), Toast.LENGTH_SHORT).show();
                        } else {
                            handleLoginFailure();
                        }
                    }
//                    if (userModelUserResponse != null && userModelUserResponse.getData() != null) {
//                        Toast.makeText(this, "" + userModelUserResponse.getStatus(), Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
//                        handleLoginFailure();
//                    }
                });
            } else {
                Toast.makeText(this, "Mật khẩu xác nhận lại không trùng khớp", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
        }
    }


    private void handleLoginSuccess(UserModel userModel) {
//        Log.d("RegisterActivity", "Đăng ký thành công, đăng nhập lại để tiếp tục");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("email", userModel.getEmail());
        intent.putExtra("password", userModel.getPassword());
        Toast.makeText(this, "Đăng ký thành công, đăng nhập lại để tiếp tục!", Toast.LENGTH_SHORT).show();

        startActivity(intent);
        finish();
    }

    private void handleLoginFailure() {
        Log.e("RegisterActivity", "Đăng ký thất bại");
        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }
}