package com.techprostudio.kuberinternational.Model.WishListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDataWish {
    @SerializedName("unit_data")
    @Expose
    private UnitDataWish unitData;
    @SerializedName("price_data")
    @Expose
    private PriceDataWish priceData;
    @SerializedName("discount_data")
    @Expose
    private DiscountDataWish discountData;
    @SerializedName("gst_data")
    @Expose
    private GstDataWish gstData;

    public UnitDataWish getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitDataWish unitData) {
        this.unitData = unitData;
    }

    public PriceDataWish getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceDataWish priceData) {
        this.priceData = priceData;
    }

    public DiscountDataWish getDiscountData() {
        return discountData;
    }

    public void setDiscountData(DiscountDataWish discountData) {
        this.discountData = discountData;
    }

    public GstDataWish getGstData() {
        return gstData;
    }

    public void setGstData(GstDataWish gstData) {
        this.gstData = gstData;
    }

}
