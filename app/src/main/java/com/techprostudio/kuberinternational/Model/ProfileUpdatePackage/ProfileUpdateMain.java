package com.techprostudio.kuberinternational.Model.ProfileUpdatePackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUpdateMain {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("otp_type")
    @Expose
    private String otpType;
    @SerializedName("ph_update")
    @Expose
    private boolean phUpdate;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpType() {
        return otpType;
    }

    public void setOtpType(String otpType) {
        this.otpType = otpType;
    }

    public boolean isPhUpdate() {
        return phUpdate;
    }

    public void setPhUpdate(boolean phUpdate) {
        this.phUpdate = phUpdate;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
