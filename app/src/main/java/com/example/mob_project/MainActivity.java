package com.example.mob_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TemperatureData> temperatureDataList = new ArrayList<>();
        temperatureDataList.add(new TemperatureData("2023-03-24", 9.3, 12.4));
        temperatureDataList.add(new TemperatureData("2023-03-25", 7.8, 11.9));
        temperatureDataList.add(new TemperatureData("2023-03-26", 2.6, 9.6));

        recyclerView.setAdapter(new TemperatureDataAdapter(temperatureDataList));
    }
}