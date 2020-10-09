package com.techprostudio.kuberinternational.Model.CartPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartPriceData {
//    @SerializedName("total_cart_price")
//    @Expose
//    private Double totalCartPrice;
//    @SerializedName("total_payable_price")
//    @Expose
//    private Double totalPayablePrice;
//    @SerializedName("total_save_price")
//    @Expose
//    private Double totalSavePrice;
//    @SerializedName("total_gst_price")
//    @Expose
//    private Double totalGstPrice;
//    @SerializedName("delivery_charge")
//    @Expose
//    private Integer deliveryCharge;
//    @SerializedName("final_payable_price")
//    @Expose
//    private String finalPayablePrice;
//
//    public Double getTotalCartPrice() {
//        return totalCartPrice;
//    }
//
//    public void setTotalCartPrice(Double totalCartPrice) {
//        this.totalCartPrice = totalCartPrice;
//    }
//
//    public Double getTotalPayablePrice() {
//        return totalPayablePrice;
//    }
//
//    public void setTotalPayablePrice(Double totalPayablePrice) {
//        this.totalPayablePrice = totalPayablePrice;
//    }
//
//    public Double getTotalSavePrice() {
//        return totalSavePrice;
//    }
//
//    public void setTotalSavePrice(Double totalSavePrice) {
//        this.totalSavePrice = totalSavePrice;
//    }
//
//    public Double getTotalGstPrice() {
//        return totalGstPrice;
//    }
//
//    public void setTotalGstPrice(Double totalGstPrice) {
//        this.totalGstPrice = totalGstPrice;
//    }
//
//    public Integer getDeliveryCharge() {
//        return deliveryCharge;
//    }
//
//    public void setDeliveryCharge(Integer deliveryCharge) {
//        this.deliveryCharge = deliveryCharge;
//    }
//
//    public String getFinalPayablePrice() {
//        return finalPayablePrice;
//    }
//
//    public void setFinalPayablePrice(String finalPayablePrice) {
//        this.finalPayablePrice = finalPayablePrice;
//    }
@SerializedName("total_cart_price")
@Expose
private Double totalCartPrice;
    @SerializedName("total_payable_price")
    @Expose
    private Double totalPayablePrice;
    @SerializedName("total_save_price")
    @Expose
    private Double totalSavePrice;
    @SerializedName("total_gst_price")
    @Expose
    private Double totalGstPrice;
    @SerializedName("delivery_charge")
    @Expose
    private Integer deliveryCharge;
    @SerializedName("final_payable_price")
    @Expose
    private String finalPayablePrice;

    public Double getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(Double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public Double getTotalPayablePrice() {
        return totalPayablePrice;
    }

    public void setTotalPayablePrice(Double totalPayablePrice) {
        this.totalPayablePrice = totalPayablePrice;
    }

    public Double getTotalSavePrice() {
        return totalSavePrice;
    }

    public void setTotalSavePrice(Double totalSavePrice) {
        this.totalSavePrice = totalSavePrice;
    }

    public Double getTotalGstPrice() {
        return totalGstPrice;
    }

    public void setTotalGstPrice(Double totalGstPrice) {
        this.totalGstPrice = totalGstPrice;
    }

    public Integer getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Integer deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getFinalPayablePrice() {
        return finalPayablePrice;
    }

    public void setFinalPayablePrice(String finalPayablePrice) {
        this.finalPayablePrice = finalPayablePrice;
    }

}

