package com.techprostudio.kuberinternational.Model.SubProductMain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceData {
    @SerializedName("original_price")
    @Expose
    private String originalPrice;
    @SerializedName("after_discount_price")
    @Expose
    private String afterDiscountPrice;

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public void setAfterDiscountPrice(String afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }

}
