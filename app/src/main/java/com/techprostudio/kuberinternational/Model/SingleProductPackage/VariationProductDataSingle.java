package com.techprostudio.kuberinternational.Model.SingleProductPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationProductDataSingle {

    @SerializedName("unit_data")
    @Expose
    private UnitDataSingle unitData;
    @SerializedName("price_data")
    @Expose
    private PriceDataSingle priceData;
    @SerializedName("discount_data")
    @Expose
    private DiscountDataSingle discountData;
    @SerializedName("gst_data")
    @Expose
    private GstDataSingle gstData;

    public UnitDataSingle getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitDataSingle unitData) {
        this.unitData = unitData;
    }

    public PriceDataSingle getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceDataSingle priceData) {
        this.priceData = priceData;
    }

    public DiscountDataSingle getDiscountData() {
        return discountData;
    }

    public void setDiscountData(DiscountDataSingle discountData) {
        this.discountData = discountData;
    }

    public GstDataSingle getGstData() {
        return gstData;
    }

    public void setGstData(GstDataSingle gstData) {
        this.gstData = gstData;
    }
}
