package com.atta.weswap.model;

import java.io.Serializable;
import java.util.Map;

public class Ad implements Serializable {

    private int id, userId, categoryId, subcategoryId, conditionId, brandId, swapCategoryId,
            swapSubcategoryId, areaId;

    private String title, creationTime, description, phone, category, subcategory, condition, brand, swapCategory,
            swapSubcategory, area;

    private String[] imagesUrls;

    private Map<String, String> images, imagesNames;

    public Ad(int userId, int categoryId, int subcategoryId, int conditionId, int brandId, String title,
              String creationTime, String description, String phone, Map<String, String> images, Map<String,
            String> imagesNames, int swapCategoryId, int swapSubcategoryId, int areaId) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.swapCategoryId = swapCategoryId;
        this.swapSubcategoryId = swapSubcategoryId;
        this.conditionId = conditionId;
        this.brandId = brandId;
        this.title = title;
        this.creationTime = creationTime;
        this.description = description;
        this.phone = phone;
        this.images = images;
        this.imagesNames = imagesNames;
        this.areaId = areaId;
    }

    public Ad(int id, int userId, int categoryId, int subcategoryId, int conditionId, int brandId,
              String title, String creationTime, String description, String phone, String[] imagesUrls,
              String category, String subcategory, String condition, String brand, String swapCategory,
              String swapSubcategory, String area) {
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
        this.imagesUrls = imagesUrls;
        this.category = category;
        this.subcategory = subcategory;
        this.condition = condition;
        this.brand = brand;
        this.swapCategory = swapCategory;
        this.swapSubcategory = swapSubcategory;
        this.area = area;
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

    public int getSwapCategoryId() {
        return swapCategoryId;
    }

    public int getSwapSubcategoryId() {
        return swapSubcategoryId;
    }

    public String[] getImagesUrls() {
        return imagesUrls;
    }

    public int getAreaId() {
        return areaId;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getCondition() {
        return condition;
    }

    public String getBrand() {
        return brand;
    }

    public String getSwapCategory() {
        return swapCategory;
    }

    public String getSwapSubcategory() {
        return swapSubcategory;
    }

    public String getArea() {
        return area;
    }
}
