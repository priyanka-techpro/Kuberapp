package com.techprostudio.kuberinternational.Model.ParentCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryList {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_icon")
    @Expose
    private String categoryIcon;
    @SerializedName("category_status")
    @Expose
    private String categoryStatus;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
