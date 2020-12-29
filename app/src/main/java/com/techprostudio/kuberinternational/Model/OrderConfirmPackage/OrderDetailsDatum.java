package com.techprostudio.kuberinternational.Model.OrderConfirmPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsDatum {
    @SerializedName("order_details_id")
    @Expose
    private String orderDetailsId;
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
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("unit_data")
    @Expose
    private String unitData;
    @SerializedName("original_price")
    @Expose
    private String originalPrice;
    @SerializedName("total_original_price")
    @Expose
    private Double totalOriginalPrice;
    @SerializedName("total_save")
    @Expose
    private Integer totalSave;
    @SerializedName("total_after_discount_price")
    @Expose
    private Double totalAfterDiscountPrice;
    @SerializedName("total_gst_price")
    @Expose
    private Double totalGstPrice;

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitData() {
        return unitData;
    }

    public void setUnitData(String unitData) {
        this.unitData = unitData;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getTotalOriginalPrice() {
        return totalOriginalPrice;
    }

    public void setTotalOriginalPrice(Double totalOriginalPrice) {
        this.totalOriginalPrice = totalOriginalPrice;
    }

    public Integer getTotalSave() {
        return totalSave;
    }

    public void setTotalSave(Integer totalSave) {
        this.totalSave = totalSave;
    }

    public Double getTotalAfterDiscountPrice() {
        return totalAfterDiscountPrice;
    }

    public void setTotalAfterDiscountPrice(Double totalAfterDiscountPrice) {
        this.totalAfterDiscountPrice = totalAfterDiscountPrice;
    }

    public Double getTotalGstPrice() {
        return totalGstPrice;
    }

    public void setTotalGstPrice(Double totalGstPrice) {
        this.totalGstPrice = totalGstPrice;
    }
}
