package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

public class Brand {

    int id;

    @SerializedName("brand")
    String brand;

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
}
