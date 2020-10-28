package com.techprostudio.kuberinternational.Model.CartPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartListMainModel {
//    @SerializedName("status")
//@Expose
//private Boolean status;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("cart_count")
//    @Expose
//    private Integer cartCount;
//    @SerializedName("cart_list")
//    @Expose
//    private List<CartList> cartList = null;
//    @SerializedName("cart_price_data")
//    @Expose
//    private CartPriceData cartPriceData;
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Integer getCartCount() {
//        return cartCount;
//    }
//
//    public void setCartCount(Integer cartCount) {
//        this.cartCount = cartCount;
//    }
//
//    public List<CartList> getCartList() {
//        return cartList;
//    }
//
//    public void setCartList(List<CartList> cartList) {
//        this.cartList = cartList;
//    }
//
//    public CartPriceData getCartPriceData() {
//        return cartPriceData;
//    }
//
//    public void setCartPriceData(CartPriceData cartPriceData) {
//        this.cartPriceData = cartPriceData;
//    }
@SerializedName("status")
@Expose
private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_count")
    @Expose
    private int cartCount;
    @SerializedName("cart_list")
    @Expose
    private List<CartList> cartList = null;
    @SerializedName("cart_price_data")
    @Expose
    private CartPriceData cartPriceData;
    @SerializedName("offer_tag")
    @Expose
    private OfferTag offerTag;

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

    public OfferTag getOfferTag() {
        return offerTag;
    }

    public void setOfferTag(OfferTag offerTag) {
        this.offerTag = offerTag;
    }

}
