package com.example.mob_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TemperatureDataAdapter extends RecyclerView.Adapter<TemperatureDataViewHolder> {
    private List<TemperatureData> temperatureDataList;

    public TemperatureDataAdapter(List<TemperatureData> temperatureDataList){
        this.temperatureDataList = temperatureDataList;
    }
    @Override
    public TemperatureDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.temperature_item, parent, false);
        return new TemperatureDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemperatureDataViewHolder holder, int position){
        holder.date_text_view.setText(temperatureDataList.get(position).getDate());
        holder.min_temp_text_view.setText(String.valueOf(temperatureDataList.get(position).getMinTemp()));
        holder.max_temp_text_view.setText(String.valueOf(temperatureDataList.get(position).getMaxTemp()));
    }

    @Override
    public int getItemCount(){
        return temperatureDataList.size();
    }
}
