package com.atta.weswap.model;

import java.io.Serializable;
import java.util.Map;

public class Ad implements Serializable {

    private int id, userId, categoryId, subcategoryId, conditionId, brandId;

    private String title, creationTime, description, phone;

    private Map<String, String> images, imagesNames;

    public Ad(int userId, int categoryId, int subcategoryId, int conditionId, int brandId, String title, String creationTime, String description, String phone, Map<String, String> images, Map<String, String> imagesNames) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.conditionId = conditionId;
        this.brandId = brandId;
        this.title = title;
        this.creationTime = creationTime;
        this.description = description;
        this.phone = phone;
        this.images = images;
        this.imagesNames = imagesNames;
    }

    public Ad(int id, int userId, int categoryId, int subcategoryId, int conditionId, int brandId, String title, String creationTime, String description, String phone, Map<String, String> images, Map<String, String> imagesNames) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.conditionId = conditionId;
        this.brandId = brandId;
        this.title = title;
        this.creationTime = creationTime;
        this.description = description;
        this.phone = phone;
        this.images = images;
        this.imagesNames = imagesNames;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public int getConditionId() {
        return conditionId;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getTitle() {
        return title;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public Map<String, String> getImagesNames() {
        return imagesNames;
    }
}
