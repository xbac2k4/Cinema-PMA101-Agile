package com.example.cinemamobilefe.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImagePickerActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICKER = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_IMAGE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_PICKER && data != null) {
            Uri selectedImageUri = data.getData();
            Intent resultIntent = new Intent();
            resultIntent.setData(selectedImageUri);
            setResult(RESULT_OK, resultIntent);
        }
        finish();
    }
}