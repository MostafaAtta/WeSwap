package com.atta.weswap.model;

import java.io.Serializable;

public class Subcategory implements Serializable {


    private String subcategory, image;

    private int id, catId ;

    public Subcategory(int id, String subcategory, int catId, String image) {
        this.subcategory = subcategory;
        this.image = image;
        this.id = id;
        this.catId = catId;
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
}
