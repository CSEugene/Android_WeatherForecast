package com.example.eugene.replacing_fragment;

/**
 * Created by Eugene on 6/5/2016.
 */
public class CityModel {
    public CityModel (){
        CityName = "Thủ Đô Hà Nội";
        CityId = 1581129;
    }

    public CityModel(String cityName, int cityId) {
        CityName = cityName;
        CityId = cityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    protected int CityId;
    protected String CityName;

}
