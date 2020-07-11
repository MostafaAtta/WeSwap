package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

public class Area {

    int id;

    @SerializedName("area_name")
    String areaName;

    public int getId() {
        return id;
    }

    public String getAreaName() {
        return areaName;
    }
}
