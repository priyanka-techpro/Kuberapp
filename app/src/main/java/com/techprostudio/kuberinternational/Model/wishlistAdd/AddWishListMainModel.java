package com.techprostudio.kuberinternational.Model.wishlistAdd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddWishListMainModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private int cartCount;
    @SerializedName("wish_list")
    @Expose
    private List<WishList> wishList = null;

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

    public List<WishList> getWishList() {
        return wishList;
    }

    public void setWishList(List<WishList> wishList) {
        this.wishList = wishList;
    }
}
