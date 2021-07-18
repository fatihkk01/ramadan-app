package com.fatihkirik.ramadanapp.Models;

import com.google.gson.annotations.SerializedName;

public class Prayer {
    @SerializedName("Aksam")
    private String iftar;

    @SerializedName("Imsak")
    private String sahur;

    public Prayer() {
    }

    public Prayer(String iftar, String sahur) {
        this.iftar = iftar;
        this.sahur = sahur;
    }

    public String getIftar() {
        return iftar;
    }

    public void setIftar(String iftar) {
        this.iftar = iftar;
    }

    public String getSahur() {
        return sahur;
    }

    public void setSahur(String sahur) {
        this.sahur = sahur;
    }
}
