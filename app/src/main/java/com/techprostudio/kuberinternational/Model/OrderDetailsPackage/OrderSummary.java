package com.techprostudio.kuberinternational.Model.OrderDetailsPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSummary {
    @SerializedName("order_amount")
    @Expose
    private String orderAmount;
    @SerializedName("discount_amount")
    @Expose
    private String discountAmount;
    @SerializedName("price_sum")
    @Expose
    private String priceSum;
    @SerializedName("gst_amount")
    @Expose
    private String gstAmount;
    @SerializedName("delivery_charge_amount")
    @Expose
    private String deliveryChargeAmount;
    @SerializedName("payable_amount")
    @Expose
    private String payableAmount;
    @SerializedName("customer_order_address")
    @Expose
    private String customerOrderAddress;

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(String priceSum) {
        this.priceSum = priceSum;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getDeliveryChargeAmount() {
        return deliveryChargeAmount;
    }

    public void setDeliveryChargeAmount(String deliveryChargeAmount) {
        this.deliveryChargeAmount = deliveryChargeAmount;
    }

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getCustomerOrderAddress() {
        return customerOrderAddress;
    }

    public void setCustomerOrderAddress(String customerOrderAddress) {
        this.customerOrderAddress = customerOrderAddress;
    }
}
