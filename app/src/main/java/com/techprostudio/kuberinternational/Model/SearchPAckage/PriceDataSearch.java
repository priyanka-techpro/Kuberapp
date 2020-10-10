package com.techprostudio.kuberinternational.Model.SearchPAckage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceDataSearch {
    @SerializedName("original_price")
    @Expose
    private float originalPrice;
    @SerializedName("after_discount_price")
    @Expose
    private float afterDiscountPrice;

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public float getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public void setAfterDiscountPrice(int afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }
}
