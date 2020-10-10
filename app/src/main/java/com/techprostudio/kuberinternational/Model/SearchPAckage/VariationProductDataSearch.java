package com.techprostudio.kuberinternational.Model.SearchPAckage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationProductDataSearch {
    @SerializedName("unit_data")
    @Expose
    private UnitDataSearch unitData;
    @SerializedName("price_data")
    @Expose
    private PriceDataSearch priceData;
    @SerializedName("discount_data")
    @Expose
    private DiscountDataSearch discountData;
    @SerializedName("gst_data")
    @Expose
    private GstDataSearch gstData;

    public UnitDataSearch getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitDataSearch unitData) {
        this.unitData = unitData;
    }

    public PriceDataSearch getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceDataSearch priceData) {
        this.priceData = priceData;
    }

    public DiscountDataSearch getDiscountData() {
        return discountData;
    }

    public void setDiscountData(DiscountDataSearch discountData) {
        this.discountData = discountData;
    }

    public GstDataSearch getGstData() {
        return gstData;
    }

    public void setGstData(GstDataSearch gstData) {
        this.gstData = gstData;
    }
}
