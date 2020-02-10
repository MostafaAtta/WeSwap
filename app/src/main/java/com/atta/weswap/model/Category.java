package com.atta.weswap.model;

import java.io.Serializable;

public class Category implements Serializable {


    private String category, image;

    private int id ;

    public Category(int id, String category, String image) {
        this.category = category;
        this.image = image;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
