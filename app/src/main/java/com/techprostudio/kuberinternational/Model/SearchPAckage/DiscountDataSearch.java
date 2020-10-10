package com.techprostudio.kuberinternational.Model.SearchPAckage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountDataSearch {

    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("discount_type_text")
    @Expose
    private String discountTypeText;
    @SerializedName("discount_limit")
    @Expose
    private String discountLimit;
    @SerializedName("discount_amount")
    @Expose
    private String discountAmount;

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountTypeText() {
        return discountTypeText;
    }

    public void setDiscountTypeText(String discountTypeText) {
        this.discountTypeText = discountTypeText;
    }

    public String getDiscountLimit() {
        return discountLimit;
    }

    public void setDiscountLimit(String discountLimit) {
        this.discountLimit = discountLimit;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }
}