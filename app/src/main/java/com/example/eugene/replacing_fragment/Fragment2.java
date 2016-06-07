package com.example.eugene.replacing_fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Eugene on 5/27/2016.
 */
public class Fragment2 extends android.support.v4.app.Fragment {

    //Dự báo hàng giờ
    private RecyclerView mRecyclerView;

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE", Locale.US);
    SimpleDateFormat monthDayFormat = new SimpleDateFormat("dd/M", Locale.US);
    SimpleDateFormat hourFormat = new SimpleDateFormat("h a", Locale.US);

    Calendar updatedOn = Calendar.getInstance();
    TimeZone timeZone = updatedOn.getTimeZone();

    private ArrayList<ItemData> myDataset = new ArrayList<ItemData>();
    Handler handler;

    TextView tvCityName;

    ItemData hourItemData1 = new ItemData();
    ItemData hourItemData2 = new ItemData();
    ItemData hourItemData3 = new ItemData();
    ItemData hourItemData4 = new ItemData();
    ItemData hourItemData5 = new ItemData();

    ItemData[] hourItemDatas = {hourItemData1, hourItemData2, hourItemData3,
            hourItemData4, hourItemData5};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    public Fragment2() {
        handler = new Handler();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, container, false);
        //tvCityName = (TextView)v.findViewById(R.id.tvCityName);

        weekDayFormat.setTimeZone(timeZone);
        monthDayFormat.setTimeZone(timeZone);
        hourFormat.setTimeZone(timeZone);


        updateWeatherData(new CityPreference(getContext()).getCity());

        //ItemData itemData = new ItemData("MON", "01/6", "30-35",R.drawable.ic_cloudy);
        myDataset.add(hourItemDatas[0]);
        myDataset.add(hourItemDatas[1]);
        myDataset.add(hourItemDatas[2]);
        myDataset.add(hourItemDatas[3]);
        myDataset.add(hourItemDatas[4]);



        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.i("Error", "OK");

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset, 2);
        mRecyclerView.setAdapter(mAdapter);

        setmAdapter(mAdapter);
        return v;


    }
    private void updateWeatherData(final int city) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                final JSONObject jsonThreeHours = RemoteFetch.getJSON(getActivity(), city, 3);
                if (jsonThreeHours == null) {
                    Log.d("JSON", "null");
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            renderEachThreeHoursWeather(jsonThreeHours);
                        }
                    });
                }
            }
        });
        t.start();
        try {
            t.join();
            Log.d("LOG", "Thread");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void renderEachThreeHoursWeather(JSONObject json) {
        try {
            int sum = 0;
            int length = json.getJSONArray("list").length();
            JSONObject city = json.getJSONObject("city");
            String cityName = city.getString("name");
            //tvCityName.setText(cityName);

            String countryName = city.getString("country");

            for (int i = 0; i < length; i++) {
                String temp_min = String.format("%.0f", json.getJSONArray("list")
                        .getJSONObject(i).getJSONObject("main").getDouble("temp_min"));
                String temp_max = String.format("%.0f", json.getJSONArray("list")
                        .getJSONObject(i).getJSONObject("main").getDouble("temp_max"));
                String temp = String.format("%.0f", json.getJSONArray("list")
                        .getJSONObject(i).getJSONObject("main").getDouble("temp"));

                ((HourlyActivity) getActivity()).setActionBarTitle(cityName + " - " + countryName);
                long updatedOnRaw = json.getJSONArray("list")
                        .getJSONObject(i).getLong("dt");
                updatedOn.setTimeInMillis(updatedOnRaw * 1000);
                hourItemDatas[i].setWeek_day(hourFormat.format(updatedOn.getTime()));
                hourItemDatas[i].setMonth_day(monthDayFormat.format(updatedOn.getTime()));
                //hourItemDatas[i].setTemp(temp_min + "°C" + " - " + temp_max + "°C");
                hourItemDatas[i].setTemp(temp + "°C");
                int idx = json.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getInt("id");
                Log.d("City", "IM " + idx);
                hourItemDatas[i].setImageId(setWeatherIconId(idx));

                mAdapter.notifyDataSetChanged();
                sum+=idx;
            }
            RelativeLayout hourly_layout = (RelativeLayout)getActivity().findViewById(R.id.hourly_layout);

            switch (sum/100) {
                case 2:
                    hourly_layout.setBackgroundResource(R.drawable.bg_thunder);
                    break;
                case 3:
                    hourly_layout.setBackgroundResource(R.drawable.bg_drizzle);
                    break;
                case 5:
                    hourly_layout.setBackgroundResource(R.drawable.bg_rain);
                    break;
                case 6:
                    hourly_layout.setBackgroundResource(R.drawable.bg_snow);
                    break;
                case 7:
                    hourly_layout.setBackgroundResource(R.drawable.bg_foggy);
                    break;
                case 8:
                    hourly_layout.setBackgroundResource(R.drawable.bg_cloudy);
                    break;
                default:
                    hourly_layout.setBackgroundResource(R.drawable.bg_foggy);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int setWeatherIconId(int idx) {
        int id = idx / 100;
        switch (id) {
            case 2:
                return (R.drawable.thunder1);
            case 3:
                return (R.drawable.drizzle1);
            case 5:
                return (R.drawable.rainy1);
            case 6:
                return (R.drawable.snowy1);
            case 7:
                return (R.drawable.foggy1);
            case 8:
                return (R.drawable.cloudy1);
            default:
                return (R.drawable.foggy1);
        }
    }


}
