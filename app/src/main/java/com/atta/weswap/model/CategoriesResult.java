package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesResult {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("categories")
    private ArrayList<Category> categories;

    @SerializedName("areas")
    private ArrayList<Area> areas;

    @SerializedName("conditions")
    private ArrayList<Condition> conditions;


    public CategoriesResult(Boolean error, ArrayList<Category> categories) {
        this.error = error;
        this.categories = categories;
    }

    public Boolean getError() {
        return error;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }
}
