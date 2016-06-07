package com.example.eugene.replacing_fragment;

import android.graphics.drawable.Drawable;

/**
 * Created by Eugene on 6/2/2016.
 */
public class ItemData  {

    protected String week_day;
    protected String month_day;
    protected String temp;
    protected int imageId;
    public ItemData() {
        week_day = "MON";
        month_day = "1/6";
        temp = "30";
        imageId = R.drawable.ic_drizzle;
    }
    public ItemData(String week_day, String month_day, String temp, int imageId) {
        this.week_day = week_day;
        this.month_day = month_day;
        this.temp = temp;
        this.imageId = imageId;
    }

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public String getMonth_day() {
        return month_day;
    }

    public void setMonth_day(String month_day) {
        this.month_day = month_day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
