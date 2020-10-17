package com.techprostudio.kuberinternational.Model.DashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationProductDataDashboard {
    @SerializedName("unit_data")
    @Expose
    private UnitDataDashboard unitData;
    @SerializedName("price_data")
    @Expose
    private PriceDataDashboard priceData;
    @SerializedName("discount_data")
    @Expose
    private DiscountDataDashboard discountData;
    @SerializedName("gst_data")
    @Expose
    private GstDataDashboard gstData;

    public UnitDataDashboard getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitDataDashboard unitData) {
        this.unitData = unitData;
    }

    public PriceDataDashboard getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceDataDashboard priceData) {
        this.priceData = priceData;
    }

    public DiscountDataDashboard getDiscountData() {
        return discountData;
    }

    public void setDiscountData(DiscountDataDashboard discountData) {
        this.discountData = discountData;
    }

    public GstDataDashboard getGstData() {
        return gstData;
    }

    public void setGstData(GstDataDashboard gstData) {
        this.gstData = gstData;
    }
}
