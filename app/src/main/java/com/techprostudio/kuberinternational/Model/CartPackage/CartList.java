package com.techprostudio.kuberinternational.Model.CartPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList {
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("variation_id")
    @Expose
    private String variationId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("unit_data")
    @Expose
    private String unitData;
    @SerializedName("single_product_save")
    @Expose
    private String singleProductSave;
    @SerializedName("total_save")
    @Expose
    private Integer totalSave;
    @SerializedName("original_price")
    @Expose
    private Double originalPrice;
    @SerializedName("total_original_price")
    @Expose
    private Double totalOriginalPrice;
    @SerializedName("total_after_discount_price")
    @Expose
    private Double totalAfterDiscountPrice;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("total_gst_price")
    @Expose
    private Double totalGstPrice;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getUnitData() {
        return unitData;
    }

    public void setUnitData(String unitData) {
        this.unitData = unitData;
    }

    public String getSingleProductSave() {
        return singleProductSave;
    }

    public void setSingleProductSave(String singleProductSave) {
        this.singleProductSave = singleProductSave;
    }

    public Integer getTotalSave() {
        return totalSave;
    }

    public void setTotalSave(Integer totalSave) {
        this.totalSave = totalSave;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getTotalOriginalPrice() {
        return totalOriginalPrice;
    }

    public void setTotalOriginalPrice(Double totalOriginalPrice) {
        this.totalOriginalPrice = totalOriginalPrice;
    }

    public Double getTotalAfterDiscountPrice() {
        return totalAfterDiscountPrice;
    }

    public void setTotalAfterDiscountPrice(Double totalAfterDiscountPrice) {
        this.totalAfterDiscountPrice = totalAfterDiscountPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getTotalGstPrice() {
        return totalGstPrice;
    }

    public void setTotalGstPrice(Double totalGstPrice) {
        this.totalGstPrice = totalGstPrice;
    }

}
