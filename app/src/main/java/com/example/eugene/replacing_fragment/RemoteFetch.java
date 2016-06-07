package com.example.eugene.replacing_fragment;

/**
 * Created by Eugene on 5/15/2016.
 */

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteFetch {
    //For 8 days
    private static final String OPEN_WEATHER_MAP_API_FOR_ONE_DAY =
            "http://api.openweathermap.org/data/2.5/weather?id=%d&units=metric";

    private static final String OPEN_WEATHER_MAP_API_FOR_SEVEN_DAY =
            "http://api.openweathermap.org/data/2.5/forecast/daily?id=%d&units=metric&cnt=9";
//                    + "&appid=0a185f21d36d5f979eaa9dd52cf9805d";

    private static final String OPEN_WEATHER_MAP_API_FOR_EACH_THREE_HOURS =
            "http://api.openweathermap.org/data/2.5/forecast?id=%d&units=metric&cnt=5";


    //Type = 1: 1 day
    //Type = 2: 7 day
    //Type = 3: each three hours in a day
    public static JSONObject getJSON(Context context, int city, int type){
        Log.d("City", city + "");
        try {
            URL url;
            if (type == 1) {
                Log.d("JSON", "Oneday");
                url = new URL(String.format(OPEN_WEATHER_MAP_API_FOR_ONE_DAY, city));
                Log.d("JSON", url.toString());
            } else  if (type == 2){
                url = new URL(String.format(OPEN_WEATHER_MAP_API_FOR_SEVEN_DAY, city));
                Log.d("JSON", "7day");
            } else {
                url = new URL(String.format(OPEN_WEATHER_MAP_API_FOR_EACH_THREE_HOURS, city));
                Log.d("JSON", "3 hour");
            }
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("x-api-key",
                   context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            Log.d("City", " " + e.getMessage());
            return null;
        }
    }
}
