package com.techprostudio.kuberinternational.Model.SubProductMain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationProduct {
    @SerializedName("variation_product_id")
    @Expose
    private String variationProductId;
    @SerializedName("variation_product_image")
    @Expose
    private String variationProductImage;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;
    @SerializedName("variation_product_data")
    @Expose
    private VariationProductData variationProductData;

    public String getVariationProductId() {
        return variationProductId;
    }

    public void setVariationProductId(String variationProductId) {
        this.variationProductId = variationProductId;
    }

    public String getVariationProductImage() {
        return variationProductImage;
    }

    public void setVariationProductImage(String variationProductImage) {
        this.variationProductImage = variationProductImage;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public VariationProductData getVariationProductData() {
        return variationProductData;
    }

    public void setVariationProductData(VariationProductData variationProductData) {
        this.variationProductData = variationProductData;
    }

}
