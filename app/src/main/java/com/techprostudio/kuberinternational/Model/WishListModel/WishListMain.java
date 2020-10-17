package com.techprostudio.kuberinternational.Model.WishListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishListMain {
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("wish_id")
    @Expose
    private String wishId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("veriation_image")
    @Expose
    private String veriationImage;
    @SerializedName("product_data")
    @Expose
    private ProductDataWish productData;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getWishId() {
        return wishId;
    }

    public void setWishId(String wishId) {
        this.wishId = wishId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVeriationImage() {
        return veriationImage;
    }

    public void setVeriationImage(String veriationImage) {
        this.veriationImage = veriationImage;
    }

    public ProductDataWish getProductData() {
        return productData;
    }

    public void setProductData(ProductDataWish productData) {
        this.productData = productData;
    }
}
