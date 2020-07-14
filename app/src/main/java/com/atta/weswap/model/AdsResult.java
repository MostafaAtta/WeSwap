package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AdsResult {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("ads")
    private ArrayList<Ad> ads;


    public Boolean getError() {
        return error;
    }

    public ArrayList<Ad> getAds() {
        return ads;
    }

    public AdsResult(Boolean error, ArrayList<Ad> ads) {
        this.error = error;
        this.ads = ads;
    }
}
