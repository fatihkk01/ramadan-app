package com.fatihkirik.ramadanapp.Models;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("UlkeID")
    private int countryId;
    @SerializedName("UlkeAdi")
    private String countryName;
    @SerializedName("UlkeAdiEn")
    private String countryNameEN;

    public Country() {
    }

    public Country(String countryName, String countryNameEN) {
        this.countryName = countryName;
        this.countryNameEN = countryNameEN;
    }

    public Country(int countryId, String countryName, String countryNameEN) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryNameEN = countryNameEN;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryNameEN() {
        return countryNameEN;
    }

    public void setCountryNameEN(String countryNameEN) {
        this.countryNameEN = countryNameEN;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
