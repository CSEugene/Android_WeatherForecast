package com.example.eugene.replacing_fragment;

/**
 * Created by Eugene on 6/2/2016.
 */
/**
 * Đối tượng thành phố để chọn gồm tên và ID
 * tên để hiện lên Dialog cho người dùng lựa chọn
 * ID đề làm tham số truyền vào link lấy dữ liệu giúp lấy 1 cách chính xác
 * không bị lẫn giữa tên các nơi trùng nhau
 * */
public class ItemDataCity {

    protected String cityName;
    protected String temp;
    protected int imageId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public ItemDataCity() {
        cityName = "Hà Nội";
        temp = "30";
        imageId = R.drawable.cloudy1;
    }
    public ItemDataCity(String cityName, String temp, int imageId) {
        this.cityName = cityName;
        this.temp = temp;
        this.imageId = imageId;
    }



}
