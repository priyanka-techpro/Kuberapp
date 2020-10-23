package com.techprostudio.kuberinternational.Model.OfferModelPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferMainModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private int cartCount;
    @SerializedName("offer_list")
    @Expose
    private List<OfferList> offerList = null;

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

    public List<OfferList> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferList> offerList) {
        this.offerList = offerList;
    }

}
