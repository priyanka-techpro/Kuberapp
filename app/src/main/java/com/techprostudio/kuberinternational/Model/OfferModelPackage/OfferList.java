package com.techprostudio.kuberinternational.Model.OfferModelPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferList {
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_text")
    @Expose
    private String offerText;
    @SerializedName("offer_image")
    @Expose
    private String offerImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("offer_valid")
    @Expose
    private String offerValid;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("price_tag")
    @Expose
    private String priceTag;
    @SerializedName("used_per_customer")
    @Expose
    private String usedPerCustomer;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("price_tag_text")
    @Expose
    private String priceTagText;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }

    public String getUsedPerCustomer() {
        return usedPerCustomer;
    }

    public void setUsedPerCustomer(String usedPerCustomer) {
        this.usedPerCustomer = usedPerCustomer;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPriceTagText() {
        return priceTagText;
    }

    public void setPriceTagText(String priceTagText) {
        this.priceTagText = priceTagText;
    }

}
