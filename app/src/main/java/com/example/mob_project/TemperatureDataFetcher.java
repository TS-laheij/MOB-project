package com.example.mob_project;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TemperatureDataFetcher extends AsyncTask<String, Void, ArrayList<TemperatureData>> {

    RecyclerView recyclerView;

    public TemperatureDataFetcher(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Override
    protected ArrayList doInBackground(String... urls) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        ArrayList<TemperatureData> temperatureDataList = new ArrayList<>();

        try {
            URL url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String jsonString = builder.toString();

                JSONObject jsonObject = new JSONObject(jsonString);

                JSONObject dailyObject = jsonObject.getJSONObject("daily");

                JSONArray timeArray = dailyObject.getJSONArray("time");
                JSONArray maxTempArray = dailyObject.getJSONArray("temperature_2m_max");
                JSONArray minTempArray = dailyObject.getJSONArray("temperature_2m_min");

                for (int i = 0; i < timeArray.length(); i++) {
                    String date = timeArray.getString(i);
                    double maxTemp = maxTempArray.getDouble(i);
                    double minTemp = minTempArray.getDouble(i);
                    temperatureDataList.add(new TemperatureData(date, minTemp, maxTemp));
                }

                stream.close();

            }
        } catch (IOException | JSONException e) {
            Log.e("---- DEBUG TemperatureDataFetcher ----", "Error fetching temperature data", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("---- DEBUG TemperatureDataFetcher ----", "Error closing reader", e);
                }
            }
        }
        return temperatureDataList;
    }

    @Override
    protected void onPostExecute(ArrayList<TemperatureData> temperatureDataList){
        super.onPostExecute(temperatureDataList);

        this.recyclerView.setAdapter(new TemperatureDataAdapter(temperatureDataList));
    }
}
