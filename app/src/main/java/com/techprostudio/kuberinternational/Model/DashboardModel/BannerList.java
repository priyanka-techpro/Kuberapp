package com.techprostudio.kuberinternational.Model.DashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerList {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("banner_name")
    @Expose
    private String bannerName;
    @SerializedName("banner_text")
    @Expose
    private String bannerText;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
