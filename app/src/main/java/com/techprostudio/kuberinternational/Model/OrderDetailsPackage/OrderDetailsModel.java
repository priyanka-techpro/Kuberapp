package com.techprostudio.kuberinternational.Model.OrderDetailsPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order_history")
    @Expose
    private OrderHistoryDetails orderHistory;
    @SerializedName("address_data")
    @Expose
    private AddressDataDetails addressData;
    @SerializedName("order_summary")
    @Expose
    private OrderSummary orderSummary;
    @SerializedName("invoice_data")
    @Expose
    private String invoiceData;

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

    public OrderHistoryDetails getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistoryDetails orderHistory) {
        this.orderHistory = orderHistory;
    }

    public AddressDataDetails getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressDataDetails addressData) {
        this.addressData = addressData;
    }

    public OrderSummary getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(OrderSummary orderSummary) {
        this.orderSummary = orderSummary;
    }

    public String getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(String invoiceData) {
        this.invoiceData = invoiceData;
    }

}
