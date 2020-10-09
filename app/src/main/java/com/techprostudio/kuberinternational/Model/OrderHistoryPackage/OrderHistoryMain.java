package com.techprostudio.kuberinternational.Model.OrderHistoryPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryMain {
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("ordered_on")
    @Expose
    private String orderedOn;
    @SerializedName("delivery_on")
    @Expose
    private String deliveryOn;
    @SerializedName("total_rs")
    @Expose
    private String totalRs;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

    public String getDeliveryOn() {
        return deliveryOn;
    }

    public void setDeliveryOn(String deliveryOn) {
        this.deliveryOn = deliveryOn;
    }

    public String getTotalRs() {
        return totalRs;
    }

    public void setTotalRs(String totalRs) {
        this.totalRs = totalRs;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
