package com.techprostudio.kuberinternational.Model.SingleProductPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceDataSingle {

    @SerializedName("original_price")
    @Expose
    private Double originalPrice;
    @SerializedName("after_discount_price")
    @Expose
    private Double afterDiscountPrice;

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public void setAfterDiscountPrice(Double afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }
}
