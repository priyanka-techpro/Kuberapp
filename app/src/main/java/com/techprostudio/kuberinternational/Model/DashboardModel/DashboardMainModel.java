package com.techprostudio.kuberinternational.Model.DashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardMainModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private int cartCount;
    @SerializedName("parent_category")
    @Expose
    private List<ParentCategory> parentCategory = null;
    @SerializedName("new_arrivals")
    @Expose
    private List<NewArrival> newArrivals = null;
    @SerializedName("banner_list")
    @Expose
    private List<BannerList> bannerList = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public List<ParentCategory> getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(List<ParentCategory> parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<NewArrival> getNewArrivals() {
        return newArrivals;
    }

    public void setNewArrivals(List<NewArrival> newArrivals) {
        this.newArrivals = newArrivals;
    }

    public List<BannerList> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerList> bannerList) {
        this.bannerList = bannerList;
    }

}
