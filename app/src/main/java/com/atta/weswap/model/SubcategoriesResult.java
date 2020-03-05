package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubcategoriesResult {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("subcategories")
    private ArrayList<Subcategory> subcategories;

    public SubcategoriesResult(Boolean error, ArrayList<Subcategory> subcategories) {
        this.error = error;
        this.subcategories = subcategories;
    }

    public Boolean getError() {
        return error;
    }

    public ArrayList<Subcategory> getSubcategories() {
        return subcategories;
    }
}
