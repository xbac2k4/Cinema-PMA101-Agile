package com.example.cinemamobilefe.service.onClick;

import com.example.cinemamobilefe.model.ShowtimesModel;

import java.util.ArrayList;

public interface OnClickSelectSeat {
    void selectItem(ShowtimesModel showtimesModel, ArrayList<String> selected);
}
