package com.techprostudio.kuberinternational.Model.SubProductMain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationProductData {
    @SerializedName("unit_data")
    @Expose
    private UnitData unitData;
    @SerializedName("price_data")
    @Expose
    private PriceData priceData;
    @SerializedName("discount_data")
    @Expose
    private DiscountData discountData;
    @SerializedName("gst_data")
    @Expose
    private GstData gstData;

    public UnitData getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitData unitData) {
        this.unitData = unitData;
    }

    public PriceData getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceData priceData) {
        this.priceData = priceData;
    }

    public DiscountData getDiscountData() {
        return discountData;
    }

    public void setDiscountData(DiscountData discountData) {
        this.discountData = discountData;
    }

    public GstData getGstData() {
        return gstData;
    }

    public void setGstData(GstData gstData) {
        this.gstData = gstData;
    }

}
