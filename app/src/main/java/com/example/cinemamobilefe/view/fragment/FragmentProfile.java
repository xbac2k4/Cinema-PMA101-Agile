package com.example.cinemamobilefe.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.DialogChangePasswordBinding;
import com.example.cinemamobilefe.databinding.DialogDetailsProfileBinding;
import com.example.cinemamobilefe.databinding.FragmentProfileBinding;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.service.repository.UserRepository;
import com.example.cinemamobilefe.view.activity.LoginActivity;
import com.example.cinemamobilefe.viewmodel.TicketViewModel;
import com.example.cinemamobilefe.viewmodel.UserViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FragmentProfile extends Fragment {
    private UserViewModel userViewModel;
    private File file;
    UserModel userModel;
    ImageView img_hinhanh;
    private FragmentProfileBinding binding;
    private ActivityResultLauncher<Intent> getImageLauncher;
    private ArrayList<File> ds_image;
    private Uri tempSelectedImageUri;

    public FragmentProfile() {
        // Required empty public constructor
        ds_image = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userModel = DataLocalManager.getUser();
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.layoutDangxuat.setOnClickListener(v -> showLogoutConfirmationDialog());
        binding.layoutTtTaikhoan.setOnClickListener(v -> showInformationDialog(userModel));
        binding.layoutDoiMk.setOnClickListener(v -> showChangePasswordDialog(userModel));
        // Khởi tạo ActivityResultLauncher
        // Initialize the ActivityResultLauncher
        /*getImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK && result.getData() != null) {
                        tempSelectedImageUri = result.getData().getData();
                        // Tạo MultipartBody.Part từ Uri đã chọn
                        MultipartBody.Part imagePart = createMultipartBodyPartFromUri(tempSelectedImageUri, "image");
                        // Gọi phương thức uploadAvatar để tải lên hình ảnh
                        uploadAvatar(imagePart, DataLocalManager.getUser());
                    }
                }
        );*/
        // Handle click event on imgAvt to choose image

        binding.imgAvt.setOnClickListener(v -> chooseImage());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserModel userModel = DataLocalManager.getUser();
        binding.tvUsername.setText(userModel.getUsername());
        updateUI(userModel);

    }

    private void updateUI(UserModel user) {
        binding.tvUsername.setText(user.getUsername());
        String url = user.getAvatar();
        if (tempSelectedImageUri != null) {
            // Hiển thị ảnh từ đường dẫn URI đã chọn
            Glide.with(getContext())
                    .load(tempSelectedImageUri)
                    .centerCrop()
                    .error(R.drawable.ic_avt_df)
                    .into(binding.imgAvt);
        } else {
            // Hiển thị ảnh từ đường dẫn URL
            String newUrl = url.replace("localhost", "10.0.2.2");
            Glide.with(getContext())
                    .load(newUrl)
                    .centerCrop()
                    .error(R.drawable.ic_avt_df)
                    .into(binding.imgAvt);
        }

    }


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

    private void showInformationDialog(UserModel userModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_details_profile, null);
        builder.setView(dialogView);

        DialogDetailsProfileBinding binding2 = DialogDetailsProfileBinding.bind(dialogView);
        // Lưu giá trị ban đầu của các trường thông tin
        String initialUsername = userModel.getUsername();
        String initialPhoneNumber = userModel.getPhoneNumber();

        binding2.edtUsername.setText(userModel.getUsername());
        binding2.tvEmail.setText(maskEmail(userModel.getEmail()));
        binding2.edtPhone.setText(maskPhoneNumber(userModel.getPhoneNumber())); // Sửa lại phương thức

        String url = userModel.getAvatar();
        String newUrl = url.replace("localhost", "10.0.2.2");
        Glide.with(getContext())
                .load(newUrl).centerCrop()
                .error(R.drawable.ic_avt_df)
                .into(binding2.imgAvt);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        binding2.btnOk.setOnClickListener(v -> {
            String username = binding2.edtUsername.getText().toString().trim();
            String phoneNumber = binding2.edtPhone.getText().toString().trim();
            // Kiểm tra xem có thay đổi nào không
            AtomicBoolean hasChanged = new AtomicBoolean(false);
            if (!username.isEmpty() && !phoneNumber.isEmpty()) {
                if (!username.equals(initialUsername) || !phoneNumber.equals(initialPhoneNumber)) {
                    hasChanged.set(true);
                }
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            // Validate số điện thoại
            if (!phoneNumber.matches("^(\\+|0)[0-9]{9,11}$")) {
                Toast.makeText(getContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            UserRepository userRepository = new UserRepository(getContext());
            userRepository.updateUserInfo(userModel.getId(), username, phoneNumber).observe(getViewLifecycleOwner(), userResponse -> {
                if (userResponse != null && userResponse.getStatus() == 200) {
                    if (hasChanged.get()) {
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    }
                    userModel.setUsername(username);
                    userModel.setPhoneNumber(phoneNumber);
                    DataLocalManager.setUser(userModel);
                    // Cập nhật giao diện người dùng
                    updateUI(userModel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Đã xảy ra lỗi khi gửi yêu cầu", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    private void showChangePasswordDialog(UserModel userModel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_change_password, null);

        builder.setView(dialogView);

        DialogChangePasswordBinding binding3 = DialogChangePasswordBinding.bind(dialogView);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        binding3.btnChangePassword.setOnClickListener(v -> {
            String currentPassword = binding3.edtCurrentPassword.getText().toString().trim();
            String newPassword = binding3.edtNewPassword.getText().toString().trim();
            String confirmNewPassword = binding3.edtConfirmNewPassword.getText().toString().trim();
            // Kiểm tra xem mật khẩu mới có ít nhất 8 ký tự không
            if (newPassword.length() < 8) {
                Toast.makeText(getContext(), "Mật khẩu mới phải ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (newPassword.equals(currentPassword)) {
                Toast.makeText(getContext(), "Mật khẩu mới phải khác mật khẩu cũ", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!newPassword.equals(confirmNewPassword)) {
                Toast.makeText(getContext(), "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gửi yêu cầu thay đổi mật khẩu lên server
            UserRepository userRepository = new UserRepository(getContext());
            userRepository.changePassword(userModel.getId(), currentPassword, newPassword).observe(getViewLifecycleOwner(), userResponse -> {
                if (userResponse != null && userResponse.getStatus() == 200) {
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Đã xảy ra lỗi khi gửi yêu cầu", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // Method to choose image and upload avatar
    // Phương thức chọn ảnh và tải lên avatar
    /*private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getImageLauncher.launch(intent);
    }*/
    private RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"),value);
    }
    private void chooseImage() {
//        if (ContextCompat.checkSelfPermission(RegisterActivity.this,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        Log.d("123123", "chooseAvatar: " +123123);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        getImage.launch(intent);
//        }else {
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//
//        }
    }
    private void uploadAvatar(UserModel userModel) {
        Map<String , RequestBody> mapRequestBody = new HashMap<>();
        MultipartBody.Part multipartBodyPart;
        if (file !=  null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            multipartBodyPart = MultipartBody.Part.createFormData("image", file.getName(),requestFile);
        } else {
            multipartBodyPart = null;
        }
        if (multipartBodyPart != null) {
            // Gửi yêu cầu cập nhật avatar
//            UserRepository userRepository = new UserRepository(requireContext());
//            userRepository.updateAvatar(userModel.getId(), multipartBodyPart).observe(getViewLifecycleOwner(), userResponse -> {
//                if (userResponse != null && userResponse.getStatus() == 200) {
//                    // Cập nhật thông tin đường dẫn ảnh mới trong đối tượng UserModel
////                    String newAvatarUrl = userResponse.getData().toString(); // Giả sử phương thức trả về đường dẫn ảnh mới là getAvatarUrl()
////                    userModel.setAvatar(newAvatarUrl);
////                    DataLocalManager.setUser(userModel);
////
////                    // Cập nhật ảnh trên ImageView sau khi tải lên thành công
////                    Glide.with(requireContext())
////                            .load(newAvatarUrl) // Sử dụng đường dẫn ảnh mới từ API
////                            .centerCrop()
////                            .error(R.drawable.ic_avt_df)
////                            .into(binding.imgAvt);
//                    Toast.makeText(requireContext(), "Cập nhật avatar thành công", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(requireContext(), "Đã xảy ra lỗi khi cập nhật avatar", Toast.LENGTH_SHORT).show();
//                }
//            });
            ViewModelUser(userModel, multipartBodyPart);
        } else {
            Toast.makeText(requireContext(), "Không có đường dẫn ảnh được chọn", Toast.LENGTH_SHORT).show();
        }
    }


/*    @Nullable
    private MultipartBody.Part createMultipartBodyPartFromUri(Uri uri, String name) {
        File file = new File(getActivity().getCacheDir(), name + "." + getFileExtension(uri));
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = getActivity().getContentResolver().openInputStream(uri);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            // Tạo MultipartBody.Part từ file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            return MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/
    ActivityResultLauncher<Intent> getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == Activity.RESULT_OK) {

                    Intent data = o.getData();
                    Uri tempUri = data.getData();

                    if (data.getData() != null) {
                        // Trường hợp chỉ chọn một hình ảnh
                        Uri imageUri = data.getData();

                        tempUri = imageUri;

                        // Thực hiện các xử lý với imageUri
                        file = createFileFormUri(imageUri, "image" );
                    }

                    if (tempUri != null) {
                        Glide.with(getContext())
                                .load(tempUri)
                                .thumbnail(Glide.with(getContext()).load(R.drawable.noimageicon))
                                .centerCrop()
//                                    .circleCrop()
                                .skipMemoryCache(true)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                .skipMemoryCache(true)
                                .into(binding.imgAvt);
                        uploadAvatar(userModel);
                    }

                }
            }
        });
    private File createFileFormUri (Uri path, String name) {
        File _file = new File(getContext().getCacheDir(), name + ".png");
        try {
            InputStream in = getContext().getContentResolver().openInputStream(path);
            OutputStream out = new FileOutputStream(_file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) >0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
            Log.d("123123", "createFileFormUri: " +_file);
            return _file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
/*    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }*/
    private void ViewModelUser(UserModel user, MultipartBody.Part image) {
        UserViewModel userViewModel = new UserViewModel(getContext(), user, image);
        userViewModel.getLiveData().observe(getActivity(), response -> {
            if (response != null && response.getData() != null) {
                Toast.makeText(getContext(), "Update avatar isSuccessful", Toast.LENGTH_SHORT).show();
                DataLocalManager.setUser(response.getData());
                userModel = DataLocalManager.getUser();
            }
        });
    }
    private String maskEmail(String email) {
        // Tách phần tên và domain của email
        int atIndex = email.indexOf('@');
        if (atIndex <= 2) {
            // Nếu địa chỉ email không hợp lệ hoặc không có đủ ký tự để che giấu, trả về nguyên bản
            return email;
        }

        String namePart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);

        // Tạo phần tên bị che giấu
        StringBuilder maskedName = new StringBuilder();
        maskedName.append(namePart.charAt(0)); // Ký tự đầu tiên
        maskedName.append(namePart.charAt(1)); // Ký tự thứ hai
        for (int i = 2; i < namePart.length(); i++) {
            maskedName.append('*'); // Thay thế các ký tự ở giữa bằng *
        }

        // Ghép phần tên bị che giấu với phần domain
        return maskedName.toString() + domainPart;
    }
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() <= 3) {
            // Nếu số điện thoại không hợp lệ hoặc không có đủ ký tự để che giấu, trả về nguyên bản
            return phoneNumber;
        }

        int visibleLength = phoneNumber.length() - 7;
        String visiblePart = phoneNumber.substring(0, visibleLength);
        String maskedPart = phoneNumber.substring(visibleLength).replaceAll("\\d", "*");

        return visiblePart + maskedPart;
    }
}
