package com.example.mob_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<TemperatureData> temperatureDataList = new ArrayList<>();
        temperatureDataList.add(new TemperatureData("2023-03-24", 9.3, 12.4));
        temperatureDataList.add(new TemperatureData("2023-03-25", 7.8, 11.9));
        temperatureDataList.add(new TemperatureData("2023-03-26", 2.6, 9.6));

        recyclerView.setAdapter(new TemperatureDataAdapter(temperatureDataList));
        String url = "https://api.open-meteo.com/v1/forecast?&";
        sharedPreferences = getContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
        boolean unit_f = sharedPreferences.getBoolean("unit_f", false);
        boolean loc = sharedPreferences.getBoolean("loc", false);

        if (loc) {
            url = url + "latitude=51.69&longitude=5.22&";
        }else{
            url = url + "latitude=52.37&longitude=4.90&";
        }
        url = url + "daily=temperature_2m_max,temperature_2m_min&timezone=Europe%2FLondon";
        if (unit_f){
            url = url + "&temperature_unit=fahrenheit";
        }

        new TemperatureDataFetcher(recyclerView).execute(url);


    }
}