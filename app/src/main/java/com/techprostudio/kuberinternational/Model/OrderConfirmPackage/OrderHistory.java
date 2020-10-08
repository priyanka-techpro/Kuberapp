package com.techprostudio.kuberinternational.Model.OrderConfirmPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistory {
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("ordered_on")
    @Expose
    private String orderedOn;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("total_rs")
    @Expose
    private String totalRs;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("order_details_data")
    @Expose
    private List<OrderDetailsDatum> orderDetailsData = null;

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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<OrderDetailsDatum> getOrderDetailsData() {
        return orderDetailsData;
    }

    public void setOrderDetailsData(List<OrderDetailsDatum> orderDetailsData) {
        this.orderDetailsData = orderDetailsData;
    }
}
