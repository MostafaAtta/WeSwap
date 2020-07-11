package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

public class Condition {

    int id;

    @SerializedName("condition_name")
    String conditionName;

    public int getId() {
        return id;
    }

    public String getConditionName() {
        return conditionName;
    }
}
