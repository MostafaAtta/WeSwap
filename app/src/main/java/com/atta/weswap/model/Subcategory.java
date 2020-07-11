package com.atta.weswap.model;

import java.io.Serializable;

public class Subcategory implements Serializable {


    private String subcategory, image;

    private int id, catId, numOfAds;

    public Subcategory(int id, String subcategory, int catId, String image, int numOfAds) {
        this.subcategory = subcategory;
        this.image = image;
        this.id = id;
        this.catId = catId;
        this.numOfAds = numOfAds;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getCatId() {
        return catId;
    }

    public int getNumOfAds() {
        return numOfAds;
    }
}
