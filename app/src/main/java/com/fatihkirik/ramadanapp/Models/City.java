package com.fatihkirik.ramadanapp.Models;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("SehirID")
    private int cityId;
    @SerializedName("SehirAdi")
    private String cityName;
    @SerializedName("SehirAdiEn")
    private String cityNameEN;

    public City() {
    }

    public City(String cityName, String cityNameEN) {
        this.cityName = cityName;
        this.cityNameEN = cityNameEN;
    }

    public City(int cityId, String cityName, String cityNameEN) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityNameEN = cityNameEN;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEN() {
        return cityNameEN;
    }

    public void setCityNameEN(String cityNameEN) {
        this.cityNameEN = cityNameEN;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
