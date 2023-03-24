package com.example.mob_project;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TemperatureDataViewHolder extends RecyclerView.ViewHolder {
    TextView date_text_view;
    TextView min_temp_text_view;
    TextView max_temp_text_view;

    public TemperatureDataViewHolder(View view) {
        super(view);

        date_text_view = view.findViewById(R.id.date_text_view);
        min_temp_text_view = view.findViewById(R.id.min_temp_text_view);
        max_temp_text_view = view.findViewById(R.id.max_temp_text_view);
    }
}
