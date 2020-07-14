package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubcategoriesResult {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("subcategories")
    private ArrayList<Subcategory> subcategories;

    @SerializedName("brands")
    private ArrayList<Brand> brands;


    public SubcategoriesResult(Boolean error, ArrayList<Subcategory> subcategories, ArrayList<Brand> brands) {
        this.error = error;
        this.subcategories = subcategories;
        this.brands = brands;
    }

    public Boolean getError() {
        return error;
    }

    public ArrayList<Subcategory> getSubcategories() {
        return subcategories;
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }
}
