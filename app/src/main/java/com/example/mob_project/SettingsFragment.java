package com.example.mob_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    private Switch slider;
    private Switch location;
    private TextView setting_label, location_label;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        slider = view.findViewById(R.id.switch1);
        location = view.findViewById(R.id.switch2);
        location_label = view.findViewById(R.id.textView);
        setting_label = view.findViewById(R.id.unitTV);

        sharedPreferences = getContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
        boolean unit_f = sharedPreferences.getBoolean("unit_f", false);
        boolean loc = sharedPreferences.getBoolean("loc", false);

        if (loc) {
            location.setChecked(true);
            location_label.setText("Vlijmen");
        }else{
            location.setChecked(false);
            location_label.setText("Amsterdam");
        }

        if (unit_f) {
            slider.setChecked(true);
            setting_label.setText("Fahrenheit");
        }else{
            slider.setChecked(false);
            setting_label.setText("Celcius");
        }

        slider.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked){
                editor.putBoolean("unit_f", true);
                editor.apply();
                setting_label.setText("Fahrenheit");
            }else{
                editor.putBoolean("unit_f", false);
                editor.apply();
                setting_label.setText("Celcius");
            }
        });

        location.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked){
                editor.putBoolean("loc", true);
                editor.apply();
                location_label.setText("Vlijmen");
            } else {
                editor.putBoolean("loc", false);
                editor.apply();
                location_label.setText("Amsterdam");
            }
        });

        return view;
    }

}