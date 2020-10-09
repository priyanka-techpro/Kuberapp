package com.techprostudio.kuberinternational.Model.OrderHistoryPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistoryModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order_history")
    @Expose
    private List<OrderHistoryMain> orderHistory = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderHistoryMain> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderHistoryMain> orderHistory) {
        this.orderHistory = orderHistory;
    }

}
