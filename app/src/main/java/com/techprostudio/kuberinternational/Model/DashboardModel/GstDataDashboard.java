package com.techprostudio.kuberinternational.Model.DashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GstDataDashboard {
    @SerializedName("gst_price")
    @Expose
    private String gstPrice;
    @SerializedName("final_price_plus_gst")
    @Expose
    private String finalPricePlusGst;

    public String getGstPrice() {
        return gstPrice;
    }

    public void setGstPrice(String gstPrice) {
        this.gstPrice = gstPrice;
    }

    public String getFinalPricePlusGst() {
        return finalPricePlusGst;
    }

    public void setFinalPricePlusGst(String finalPricePlusGst) {
        this.finalPricePlusGst = finalPricePlusGst;
    }
}
