package com.techprostudio.kuberinternational.Model.FilterSection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterMainModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("category_list")
    @Expose
    private List<CategoryListFilter> categoryList = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public List<CategoryListFilter> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListFilter> categoryList) {
        this.categoryList = categoryList;
    }

}
