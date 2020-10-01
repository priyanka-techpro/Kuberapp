package com.techprostudio.kuberinternational.Model.SubProductMain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListMain {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_icon")
    @Expose
    private String categoryIcon;
    @SerializedName("category_active_status")
    @Expose
    private String categoryActiveStatus;
    @SerializedName("product_list")
    @Expose
    private List<ProductList_product> productList = null;

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

    public String getCategoryActiveStatus() {
        return categoryActiveStatus;
    }

    public void setCategoryActiveStatus(String categoryActiveStatus) {
        this.categoryActiveStatus = categoryActiveStatus;
    }

    public List<ProductList_product> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList_product> productList) {
        this.productList = productList;
    }
}
