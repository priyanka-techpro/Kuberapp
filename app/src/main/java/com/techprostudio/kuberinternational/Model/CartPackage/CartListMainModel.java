package com.techprostudio.kuberinternational.Model.CartPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartListMainModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("cart_list")
    @Expose
    private List<CartList> cartList = null;
    @SerializedName("cart_price_data")
    @Expose
    private CartPriceData cartPriceData;

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

    public List<CartList> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartList> cartList) {
        this.cartList = cartList;
    }

    public CartPriceData getCartPriceData() {
        return cartPriceData;
    }

    public void setCartPriceData(CartPriceData cartPriceData) {
        this.cartPriceData = cartPriceData;
    }
}
