package com.techprostudio.kuberinternational.Model.CartPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferTag {
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("offer_valid")
    @Expose
    private String offerValid;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("price_tag_text")
    @Expose
    private String priceTagText;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferValid() {
        return offerValid;
    }

    public void setOfferValid(String offerValid) {
        this.offerValid = offerValid;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getPriceTagText() {
        return priceTagText;
    }

    public void setPriceTagText(String priceTagText) {
        this.priceTagText = priceTagText;
    }

}
