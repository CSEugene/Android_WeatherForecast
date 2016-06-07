package com.example.eugene.replacing_fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Eugene on 5/27/2016.
 */
public class Fragment1 extends android.support.v4.app.Fragment {
    Handler handler;
    private Activity mActivity;
    //Dự báo 7 ngày trong tuần dùng RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    String BACKGROUND_COLOR_ID = "backgroundColorId";

    ProgressDialog progress;
    String weekDay;
    //Tao list đối tượng chứa dữ liệu
    private ArrayList<ItemData> myDataset = new ArrayList<ItemData>();

    //Điều chỉnh định dạng hiển thị ngày, giờ, ...
    SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE", Locale.US);
    SimpleDateFormat monthDayFormat = new SimpleDateFormat("dd/M", Locale.US);
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE dd/M", Locale.US);

    Calendar updatedOn = Calendar.getInstance();
    TimeZone timeZone = updatedOn.getTimeZone();


//    String weekDay = weekDayFormat.format(calendar.getTime());
//    String monthDay = monthDayFormat.format(calendar.getTime());


    //
    TextView todayWeather;
    TextView cityField;
    TextView updatedField;
    ImageView weatherIcon;
    TextView detailsField_pressure;
    TextView detailsField_humidity;
    TextView detailsField_windSpeed;
    TextView currentTemperatureField;
    View v;

    //
    ItemData itemData1 = new ItemData();
    ItemData itemData2 = new ItemData();
    ItemData itemData3 = new ItemData();
    ItemData itemData4 = new ItemData();
    ItemData itemData5 = new ItemData();
    ItemData itemData6 = new ItemData();
    ItemData itemData7 = new ItemData();

    ItemData[] itemDatas = {itemData1, itemData2, itemData3,
            itemData4, itemData5, itemData6, itemData7};

    public Fragment1() {
        handler = new Handler();
    }

    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getContext();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_test, container, false);
        View toolbar_layout = inflater.inflate(R.layout.toolbar, container, false);


        weekDayFormat.setTimeZone(timeZone);
        monthDayFormat.setTimeZone(timeZone);
        dayFormat.setTimeZone(timeZone);






        updateWeatherData(new CityPreference(getContext()).getCity());

//        ItemData itemData = new ItemData("sd","asd","asd",R.drawable.cloudy1);


        for(ItemData i : itemDatas) {
            myDataset.add(i);
        }
        Log.d("LOG", "Dataset");

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Dùng Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.i("Error", "OK");

        // Set adapter
        mAdapter = new MyAdapter(myDataset, 1);
        mRecyclerView.setAdapter(mAdapter);


        //Sử dụng custom font
        RelativeLayout fragment1_layout = (RelativeLayout) v.findViewById(R.id.fragment1_layout);
        OverrideFont o = new OverrideFont();
        o.overrideFont(getContext(), fragment1_layout);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Lấy các view
        detailsField_pressure = (TextView) v.findViewById(R.id.tv_pressure);
        detailsField_humidity = (TextView) v.findViewById(R.id.tv_humidity);
        detailsField_windSpeed = (TextView) v.findViewById(R.id.tv_windspeed);
        currentTemperatureField = (TextView) v.findViewById(R.id.tv_temp);
        weatherIcon = (ImageView) v.findViewById(R.id.img_weather);

        todayWeather = (TextView) v.findViewById(R.id.tvToday_Weather);
    }

    private void overrideColor(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideColor(context, child);
                }
            } else if (v instanceof ImageView) {
                ((ImageView) v).setColorFilter(Color.parseColor("#000000"));
            }
        } catch (Exception e) {
        }
    }

    //Update dữ liệu thời tiết
    public void updateWeatherData(final int city) {
        context = ((MainActivity)getActivity()).getContext();
//        progress = ProgressDialog.show(context, "Collecting Data",
//                "Please wait...", true);
        Thread t = new Thread(new Runnable() {
            public void run() {

                final JSONObject jsonOneDay = RemoteFetch.getJSON(context, city, 1);
                final JSONObject jsonSevenDay = RemoteFetch.getJSON(context, city, 2);
                if (jsonOneDay == null && jsonSevenDay == null) {
                    Log.d("JSON", "null");
                    handler.post(new Runnable() {
                        public void run() {

                            if (context!=null)
                                Toast.makeText(context, "No data found",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            //Render dữ liệu thời tiết hiện tại
                            renderTodayWeather(jsonOneDay);
                            //Render dữ liệu thời tiết 7 ngày
                            renderNextSevenDayWeather(jsonSevenDay);
//                            progress.dismiss();
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

    public void renderNextSevenDayWeather(JSONObject json) {

        try {
            int length = json.getJSONArray("list").length();
            Log.d("City", length + "");
            for (int i = 1; i < length; i++) {
                String temp_min = String.format("%.0f", json.getJSONArray("list")
                        .getJSONObject(i).getJSONObject("temp").getDouble("min"));
                String temp_max = String.format("%.0f", json.getJSONArray("list")
                        .getJSONObject(i).getJSONObject("temp").getDouble("max"));

                long updatedOnRaw = json.getJSONArray("list")
                        .getJSONObject(i).getLong("dt");
                updatedOn.setTimeInMillis(updatedOnRaw * 1000);
                itemDatas[i - 1].setWeek_day(weekDayFormat.format(updatedOn.getTime()));
                itemDatas[i - 1].setMonth_day(monthDayFormat.format(updatedOn.getTime()));
                itemDatas[i - 1].setTemp(temp_min + "°C" + " - " + temp_max + "°C");
                int idx = json.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getInt("id");
                Log.d("City", "IM " + idx);
                itemDatas[i - 1].setImageId(setWeatherIconId(idx));
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderTodayWeather(JSONObject json) {

        try {
            //Lấy dữ liệu từ chuỗi JSON đưa về từ openweathermap api
            String cityName = json.getString("name");
            String country = json.getJSONObject("sys").getString("country");
            Log.d("City", "OK");
            JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");
            long updatedOnRaw = json.getLong("dt");
            updatedOn.setTimeInMillis(updatedOnRaw * 1000);

            weekDay = dayFormat.format(updatedOn.getTime());

            //cityField.setText(cityName + "-" + country);
            todayWeather.setText(weekDay.toUpperCase()
                    + "\n" + weather.getString("description").substring(0, 1).toUpperCase()
                    + weather.getString("description").substring(1));
            detailsField_humidity.setText(main.getString("humidity") + "%");
            detailsField_pressure.setText(main.getString("pressure") + "hPa");
            detailsField_windSpeed.setText(wind.getString("speed") + "m/s");
            currentTemperatureField.setText(
                    String.format("%.0f", main.getDouble("temp")) + "°C");
            ((MainActivity) getActivity()).setActionBarTitle(cityName + " - " + country);

            Long sunrise = json.getJSONObject("sys").getLong("sunrise") * 1000;
            Long sunset = json.getJSONObject("sys").getLong("sunset") * 1000;
            setWeatherIcon(weatherIcon, weather.getInt("id"), sunrise, sunset);
            mAdapter.notifyDataSetChanged();

            //setWeatherIcon(weatherIcon, (details.getInt("id")) / 100, true);
        } catch (Exception e) {
            Log.d("City", e.getMessage());
            Log.e("Weather Today", "One or more fields not found in the JSON data");
        }
    }
    //Đặt icon cho thời tiết hiện tại
    private void setWeatherIcon(ImageView im, int idx, long sunrise, long sunset) {
        RelativeLayout main_layout = (RelativeLayout)getActivity().findViewById(R.id.main_layout);
        Bundle bundle = new Bundle();
        int background_id = R.drawable.background;
        int id = idx / 100;
        if (idx == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                im.setImageResource(R.drawable.sunny1);
                main_layout.setBackgroundResource(R.drawable.bg_sunny);
            } else {
                im.setImageResource(R.drawable.clear_night1);
            }
        }
        switch (id) {
            case 2:
                background_id = R.drawable.bg_thunder;
                im.setImageResource(R.drawable.thunder1);
                main_layout.setBackgroundResource(background_id);

                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
            case 3:
                background_id = R.drawable.bg_drizzle;
                im.setImageResource(R.drawable.drizzle1);
                main_layout.setBackgroundResource(R.drawable.bg_drizzle);

                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
            case 5:
                background_id = R.drawable.bg_rain;
                im.setImageResource(R.drawable.rainy1);
                main_layout.setBackgroundResource(R.drawable.bg_rain);
                bundle.putInt(BACKGROUND_COLOR_ID,background_id );

                break;
            case 6:
                background_id = R.drawable.bg_snow;
                im.setImageResource(R.drawable.snowy1);
                main_layout.setBackgroundResource(R.drawable.bg_snow);
                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
            case 7:
                background_id = R.drawable.bg_foggy;
                im.setImageResource(R.drawable.foggy1);
                main_layout.setBackgroundResource(R.drawable.bg_foggy);
                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
            case 8:
                background_id = R.drawable.bg_cloudy;
                im.setImageResource(R.drawable.cloudy1);
                main_layout.setBackgroundResource(R.drawable.bg_cloudy);
                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
            default:
                im.setImageResource(R.drawable.cloudy1);
                main_layout.setBackgroundResource(R.drawable.bg_cloudy);
                bundle.putInt(BACKGROUND_COLOR_ID,background_id );
                break;
        }
    }

    //Đặt icon id cho thời tiết 7 ngày kế tiếp
    private int setWeatherIconId(int idx) {
        int id = idx / 100;
        switch (id) {
            case 2:
                return (R.drawable.ic_thunder);
            case 3:
                return (R.drawable.ic_drizzle);
            case 5:
                return (R.drawable.ic_rainy);
            case 6:
                return (R.drawable.ic_snowy);
            case 7:
                return (R.drawable.ic_foggy);
            case 8:
                return (R.drawable.ic_cloudy);
            default:
                return (R.drawable.ic_foggy);
        }
    }


    //Khi thay đổi thành phố, update dữ liệu
    public void changeCity(int city) {
        updateWeatherData(city);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    //Che nút search
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);

//        searchItem.expandActionView();
    }
}
