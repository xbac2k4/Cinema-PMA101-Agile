package com.example.cinemamobilefe.view.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cinemamobilefe.Data_local.DataIntentManager;
import com.example.cinemamobilefe.service.onClick.OnClickSelectCategory;
import com.example.cinemamobilefe.service.onClick.OnClickSelectMovie;
import com.example.cinemamobilefe.databinding.FragmentHomeBinding;
import com.example.cinemamobilefe.model.CategoryModel;
import com.example.cinemamobilefe.model.MovieModel;
import com.example.cinemamobilefe.view.activity.MovieDetails;
import com.example.cinemamobilefe.view.adapter.CategoryAdapter;
import com.example.cinemamobilefe.view.adapter.MovieApdapter;
import com.example.cinemamobilefe.viewmodel.CategoryViewModel;
import com.example.cinemamobilefe.viewmodel.MovieViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    FragmentHomeBinding binding;
    ArrayList<SlideModel> imageList = new ArrayList<>();
    MovieApdapter apdapterMovie;
    ArrayList<MovieModel> listGetImage = new ArrayList<>();

    CategoryAdapter adapterCategory;
    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageSlide();
//        ViewModelCategory();
        ViewModelMovie();
    }

    private void ImageSlide() {
        imageList.add(new SlideModel("https://i.ytimg.com/vi/fVWlCV9_n7w/maxresdefault.jpg", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.ytimg.com/vi/2K8EpM-piDw/maxresdefault.jpg", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.ytimg.com/vi/Gah3ahVcCWQ/maxresdefault.jpg", "And people do that.", ScaleTypes.CENTER_CROP));

        binding.imageSlider.setImageList(imageList);
    }
    private void SliderMovie(List<MovieModel> listMovie) {
        apdapterMovie = new MovieApdapter(listMovie, binding.vp2Movie);
        apdapterMovie.setOnClickSelectMovie(new OnClickSelectMovie() {
            @Override
            public void selectItem(MovieModel movieModel) {
                Intent intent = new Intent(getContext(), MovieDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", movieModel.get_id());
//                bundle.putString("image", movieModel.getImage());
//                bundle.putString("name", movieModel.getName());
//                bundle.putString("start_date", movieModel.getStart_date());
//                bundle.putString("duration", movieModel.getDuration());
//                bundle.putString("description", movieModel.getDescription());
//                bundle.putString("directors", movieModel.getDirectors());
//                bundle.putString("name_category", movieModel.getId_category().getName());
//                Toast.makeText(getContext(), "Gson: " + strJsonMovie, Toast.LENGTH_SHORT).show();
//                intent.putExtras(bundle);
                intent.putExtra(DataIntentManager.DATA_INTENT_MOVIE, DataIntentManager.setMovie(movieModel));
                startActivity(intent);
            }
        });
        binding.vp2Movie.setAdapter(apdapterMovie);

        binding.vp2Movie.setOffscreenPageLimit(3);
        binding.vp2Movie.setClipChildren(false);
        binding.vp2Movie.setClipToPadding(false);
        binding.vp2Movie.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.14f);
            }
        });
        // Thêm OnPageChangeCallback để lấy vị trí trang hiện tại
        binding.vp2Movie.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

//                Toast.makeText(getContext(), "index: " + position, Toast.LENGTH_SHORT).show();

                // Kiểm tra nếu chỉ số hợp lệ trong danh sách
                if (position >= 0 && position < listGetImage.size()) {
                    String imageUrl = listGetImage.get(position).getImage();
                    String newUrl = imageUrl.replace("localhost", "10.0.2.2");

//                    Toast.makeText(getContext(), "- Image: " + imageUrl + "\n" + "- Index: " + position, Toast.LENGTH_SHORT).show();

                    Glide.with(getContext())
                            .load(newUrl)
                            .transform(new CenterCrop())
                            .into(binding.imgBgrHome);
                }
            }
        });
        binding.vp2Movie.setPageTransformer(transformer);
    }

//    private void ViewModelCategory() {
//        CategoryViewModel categoryViewModel = new CategoryViewModel(getContext());
//        binding.rcvTheLoai.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        categoryViewModel.getLiveData().observe(getActivity(), listCategory -> {
//            if (listCategory != null && listCategory.getData() != null) {
////                Toast.makeText(getContext(), "data: " + listCategory.getData() , Toast.LENGTH_SHORT).show();
//                adapterCategory = new CategoryAdapter(listCategory.getData());
//                binding.rcvTheLoai.setAdapter(adapterCategory);
//                adapterCategory.setOnClickListen(new OnClickSelectCategory() {
//                    @Override
//                    public void selectItem(CategoryModel categoryModel) {
//
//                    }
//                });
//            }
//        });
//    }
    private void ViewModelMovie() {
        MovieViewModel movieViewModel = new MovieViewModel(getContext());
        movieViewModel.getLiveData().observe(getActivity(), listMovie -> {
            if (listMovie != null && listMovie.getData() != null) {
//                Toast.makeText(getContext(), "OKOK", Toast.LENGTH_SHORT).show();
                SliderMovie(listMovie.getData());
                listGetImage = listMovie.getData();
                Log.d(TAG, "ViewModelMovie: " + listMovie.getMessenger());
                Log.d(TAG, "ViewModelMovie: " + listMovie.getData());
            }
        });
    }
}