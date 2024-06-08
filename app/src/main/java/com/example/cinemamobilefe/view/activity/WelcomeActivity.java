package com.example.cinemamobilefe.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resizeView(binding.imvLogo, binding.imvLogo.getWidth(), binding.imvLogo.getHeight(), 250, 250, -200);
                AnimationTextLogo(binding.tvLogo);
                AnimationTextLogo1(binding.tvLogo2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3200);
            }
        }, 1000);
    }
    private void resizeView(final View view, int startWidth, int startHeight, int newWidth, int newHeight, int moveLeft) {
        // Animate width
        ValueAnimator widthAnimator = ValueAnimator.ofInt(startWidth, newWidth);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = (int) animation.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });

        // Animate height
        ValueAnimator heightAnimator = ValueAnimator.ofInt(startHeight, newHeight);
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) animation.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });

        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), binding.imvLogo.getTranslationX() + moveLeft);

        widthAnimator.setDuration(1200);  // Duration in milliseconds
        heightAnimator.setDuration(1200); // Duration in milliseconds
        moveAnimator.setDuration(1200);

        widthAnimator.start();
        heightAnimator.start();
        moveAnimator.start();
    }
    private void AnimationTextLogo(View view) {
        //
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 180f, 360f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f); // Scale X từ 1 lên 2
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f); // Scale Y từ 1 lên 2

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, rotationAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(1200); // Độ dài tổng của animation (milliseconds)
        animatorSet.start();
    }
    private void AnimationTextLogo1(View view) {
        view.setAlpha(1f);
        // Lấy kích thước màn hình
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        // Đặt TextView bắt đầu từ bên phải màn hình
        view.setTranslationX(screenWidth);

        // Tạo ObjectAnimator để di chuyển TextView vào giữa
        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(view, "translationX", 0);
        moveAnimator.setDuration(1000); // Thời gian di chuyển (milliseconds)

        // Tạo ObjectAnimator để thay đổi scaleX và scaleY từ 1 đến 1.5
        ObjectAnimator scaleXUpAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f);
        ObjectAnimator scaleYUpAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f);
        scaleXUpAnimator.setDuration(2000); // Thời gian scale lên (milliseconds)
        scaleYUpAnimator.setDuration(2000); // Thời gian scale lên (milliseconds)

        // Tạo ObjectAnimator để thay đổi scaleX và scaleY từ 1.5 về 1
        ObjectAnimator scaleXDownAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.2f, 1f);
        ObjectAnimator scaleYDownAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1.2f, 1f);
        scaleXDownAnimator.setDuration(2000); // Thời gian scale xuống (milliseconds)
        scaleYDownAnimator.setDuration(2000); // Thời gian scale xuống (milliseconds)

        // Tạo AnimatorSet để sắp xếp thứ tự các hoạt ảnh
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(moveAnimator)
                .before(scaleXUpAnimator)
                .with(scaleYUpAnimator)
                .before(scaleXDownAnimator)
                .with(scaleYDownAnimator);

        animatorSet.start();
    }
}