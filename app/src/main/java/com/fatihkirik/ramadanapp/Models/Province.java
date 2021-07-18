package com.fatihkirik.ramadanapp.Models;

import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("IlceID")
    private int provinceId;

    @SerializedName("IlceAdi")
    private String provinceName;

    @SerializedName("IlceAdiEn")
    private String provinceNameEN;

    public Province() {
    }

    public Province(String provinceName, String provinceNameEN) {
        this.provinceName = provinceName;
        this.provinceNameEN = provinceNameEN;
    }

    public Province(int provinceId, String provinceName, String provinceNameEN) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.provinceNameEN = provinceNameEN;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceNameEN() {
        return provinceNameEN;
    }

    public void setProvinceNameEN(String provinceNameEN) {
        this.provinceNameEN = provinceNameEN;
    }

    @Override
    public String toString() {
        return provinceName;
    }
}
