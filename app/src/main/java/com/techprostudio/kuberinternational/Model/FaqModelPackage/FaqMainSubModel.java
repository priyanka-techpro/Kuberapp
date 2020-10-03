package com.techprostudio.kuberinternational.Model.FaqModelPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaqMainSubModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("faq_content")
    @Expose
    private List<FaqContent> faqContent = null;

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

    public List<FaqContent> getFaqContent() {
        return faqContent;
    }

    public void setFaqContent(List<FaqContent> faqContent) {
        this.faqContent = faqContent;
    }
}
