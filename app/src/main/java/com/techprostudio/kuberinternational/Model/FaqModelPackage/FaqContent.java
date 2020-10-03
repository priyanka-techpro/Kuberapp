package com.techprostudio.kuberinternational.Model.FaqModelPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FaqContent {
    @SerializedName("faq_id")
    @Expose
    private String faqId;
    @SerializedName("faq_question")
    @Expose
    private String faqQuestion;
    @SerializedName("faq_answer")
    @Expose
    private String faqAnswer;

    public String getFaqId() {
        return faqId;
    }

    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }

}
