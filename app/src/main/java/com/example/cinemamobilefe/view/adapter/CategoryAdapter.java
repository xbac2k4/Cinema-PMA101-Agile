package com.example.cinemamobilefe.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.service.onClick.OnClickSelectCategory;
import com.example.cinemamobilefe.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private final List<CategoryModel> list;
    private OnClickSelectCategory onClickListen;
    private int selectedItemPosition = RecyclerView.NO_POSITION;

    public OnClickSelectCategory getOnClickListen() {
        return onClickListen;
    }

    public void setOnClickListen(OnClickSelectCategory onClickListen) {
        this.onClickListen = onClickListen;
    }

    public CategoryAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel = list.get(position);
        holder.tv_name_category.setText(categoryModel.getName());

        if (position == selectedItemPosition) {
//            #52DF13
//            holder.layout.setBackgroundColor(Color.parseColor("#52DF13"));
            holder.layout.setBackgroundResource(R.drawable.background_item_category_true);
        } else {
//            holder.layout.setBackgroundColor(Color.parseColor("#66000000"));
            holder.layout.setBackgroundResource(R.drawable.background_item_category_false);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousSelected = selectedItemPosition;
                selectedItemPosition = holder.getAdapterPosition(); // Cập nhật đối tượng được chọn mới
                notifyItemChanged(previousSelected); // Huỷ chọn đối tượng trước
                notifyItemChanged(selectedItemPosition); // Chọn đối tượng mới
                onClickListen.selectItem(categoryModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_category;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_category = itemView.findViewById(R.id.tv_name_category);
            layout = itemView.findViewById(R.id.background_item_category);
        }
    }
}
