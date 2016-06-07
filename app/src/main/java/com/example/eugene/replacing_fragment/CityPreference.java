package com.example.eugene.replacing_fragment;

/**
 * Created by Eugene on 5/15/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CityPreference {
    SharedPreferences prefs;
//    String PREF_NAME = "pref";

    public CityPreference(Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // Hà Nội
    int getCity(){
        return prefs.getInt("city", 0);

    }

    void setCity(int city){
        prefs.edit().putInt("city", city).commit();
    }
}
