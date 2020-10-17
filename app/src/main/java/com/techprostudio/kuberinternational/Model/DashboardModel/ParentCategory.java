package com.techprostudio.kuberinternational.Model.DashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentCategory {
    @SerializedName("parent_category_id")
    @Expose
    private String parentCategoryId;
    @SerializedName("parent_category_name")
    @Expose
    private String parentCategoryName;
    @SerializedName("parent_category_icon")
    @Expose
    private String parentCategoryIcon;

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public String getParentCategoryIcon() {
        return parentCategoryIcon;
    }

    public void setParentCategoryIcon(String parentCategoryIcon) {
        this.parentCategoryIcon = parentCategoryIcon;
    }

}
