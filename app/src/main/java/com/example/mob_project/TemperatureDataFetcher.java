package com.example.mob_project;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class TemperatureDataFetcher extends AsyncTask<String, Void, ArrayList<TemperatureData>> {
    @Override
    protected ArrayList doInBackground(String... urls) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        return null;
    }
}
