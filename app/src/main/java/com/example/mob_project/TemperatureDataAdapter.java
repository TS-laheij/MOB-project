package com.example.mob_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class TemperatureDataAdapter extends RecyclerView.Adapter<TemperatureDataViewHolder> {
    private List<TemperatureData> temperatureDataList;
    private Context context;
    SharedPreferences sharedPreferences;
    public TemperatureDataAdapter(List<TemperatureData> temperatureDataList){
        this.temperatureDataList = temperatureDataList;
    }
    @Override
    public TemperatureDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.temperature_item, parent, false);
        return new TemperatureDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemperatureDataViewHolder holder, int position){
        holder.date_text_view.setText(temperatureDataList.get(position).getDate());
        holder.min_temp_text_view.setText(String.valueOf(temperatureDataList.get(position).getMinTemp()));
        holder.max_temp_text_view.setText(String.valueOf(temperatureDataList.get(position).getMaxTemp()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            sharedPreferences = context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
            boolean unit_f = sharedPreferences.getBoolean("unit_f", false);
            boolean loc = sharedPreferences.getBoolean("loc", false);
            String location, unit;


            if (loc) {
                location = "Vlijmen";
            } else{
                location = "Amsterdam";
            }

            if (unit_f) {
                unit = " Fahrenheit ";
            } else {
                unit = " Celcius ";
            }

            Intent i = new Intent();
            i.setType("vnd.android.cursor.item/event");
            i.putExtra(CalendarContract.Events.TITLE, "Het weer vandaag in " + location);
            i.putExtra(CalendarContract.Events.DESCRIPTION, "Vandaag is het minimaal " + String.valueOf(temperatureDataList.get(holder.getAdapterPosition()).getMinTemp()) + unit + " en maximaal " + String.valueOf(temperatureDataList.get(holder.getAdapterPosition()).getMaxTemp()) + unit);

            i.putExtra(CalendarContract.Events.ALL_DAY, true);
            String old = temperatureDataList.get(holder.getAdapterPosition()).getDate();

            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
            LocalDate dateTime = LocalDate.parse(old, parseFormatter);
            long time = (dateTime.atTime(LocalTime.NOON).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time);
            i.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, time);
            i.setAction(Intent.ACTION_EDIT);
            context.startActivity(i);


        } });
    }

    @Override
    public int getItemCount(){
        return temperatureDataList.size();
    }

    public TemperatureData getItem(int position){
        return temperatureDataList.get(position);
    }
}
