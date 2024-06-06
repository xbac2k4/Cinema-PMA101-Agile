package com.example.cinemamobilefe.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.model.CategoryModel;
import com.example.cinemamobilefe.service.onClick.OnClickSelectCategory;
import com.example.cinemamobilefe.service.onClick.OnClickSelectDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder>{
    private final ArrayList<String[]> list;
    private final Context context;
    private String dateValidate;
    private OnClickSelectDate onClickListen;

    public OnClickSelectDate getOnClickListen() {
        return onClickListen;
    }

    public void setOnClickListen(OnClickSelectDate onClickListen) {
        this.onClickListen = onClickListen;
    }

    private int selectedItemPosition = RecyclerView.SCROLLBAR_POSITION_DEFAULT;

    public DateAdapter(ArrayList<String[]> list, Context context) {
        this.list = list;
        this.context = context;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", new Locale("vi", "VN"));
        String formattedDate = today.plusDays(0).format(formatter);
        String[] parts = formattedDate.split(", ");
        String dayOfWeek = parts[0];
        String[] dateParts = parts[1].split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];
        dateValidate = day + "/" + month + "/" + year;
//        Toast.makeText(context, dateValidate, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] dateInfo = list.get(position);
        String dayOfWeek = dateInfo[0];
        String day = dateInfo[1];
        String month = dateInfo[2];
        String year = dateInfo[3];
        if (position == selectedItemPosition) {
//            holder.tv_date.setBackgroundColor(Color.parseColor("#B2BA2F2F"));
            holder.tv_date.setBackgroundResource(R.drawable.bgr_date);
            holder.tv_dayOfWeek.setTextColor(Color.parseColor("#B2BA2F2F"));
        } else {
            holder.tv_date.setBackgroundColor(Color.TRANSPARENT);
            holder.tv_dayOfWeek.setTextColor(Color.WHITE);
        }
        holder.tv_date.setText(day);
//        Toast.makeText(context, dayOfWeek + ", " + day + " tháng " + month + " năm " + year, Toast.LENGTH_SHORT).show();
        if (dateValidate.equalsIgnoreCase(day + "/" + month + "/" + year)) {
            holder.tv_dayOfWeek.setText("Hôm nay");
        } else {
            switch (dayOfWeek) {
                case "Thứ Hai":
                    holder.tv_dayOfWeek.setText("T2");
                    break;
                case "Thứ Ba":
                    holder.tv_dayOfWeek.setText("T3");
                    break;
                case "Thứ Tư":
                    holder.tv_dayOfWeek.setText("T4");
                    break;
                case "Thứ Năm":
                    holder.tv_dayOfWeek.setText("T5");
                    break;
                case "Thứ Sáu":
                    holder.tv_dayOfWeek.setText("T6");
                    break;
                case "Thứ Bảy":
                    holder.tv_dayOfWeek.setText("T7");
                    break;
                case "Chủ Nhật":
                    holder.tv_dayOfWeek.setText("CN");
                    break;
                default:
                    System.out.println("Ngày không hợp lệ");
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousSelected = selectedItemPosition;
                selectedItemPosition = holder.getAdapterPosition(); // Cập nhật đối tượng được chọn mới
                notifyItemChanged(previousSelected); // Huỷ chọn đối tượng trước
                notifyItemChanged(selectedItemPosition); // Chọn đối tượng mới
                onClickListen.selectItem(dayOfWeek, day, month, year);
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
        TextView tv_date, tv_dayOfWeek;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_dayOfWeek = itemView.findViewById(R.id.tv_dayOfWeek);
        }
    }
}
