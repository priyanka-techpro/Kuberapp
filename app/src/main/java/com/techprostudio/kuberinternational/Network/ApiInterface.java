package com.techprostudio.kuberinternational.Network;


import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Model.FilterSection.FilterMainModel;
import com.techprostudio.kuberinternational.Model.LoginModel;
import com.techprostudio.kuberinternational.Model.OtpSection.OtpModel;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryMainModel;
import com.techprostudio.kuberinternational.Model.RegistrationModel;
import com.techprostudio.kuberinternational.Model.SubProductMain.SubProductMainModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/check_phone")
    Call<LoginModel> CheckPhone(@Header("X-API-KEY") String header,
                                @Field("phone") String phone);

    @FormUrlEncoded
    @POST("user/customer_registration")
    Call<RegistrationModel> UserRegistration(@Header("X-API-KEY") String header,
                                             @Field("full_name") String full_name,
                                             @Field("email") String email,
                                             @Field("phone") String phone,
                                             @Field("address") String address,
                                             @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("user/verify_otp")
    Call<OtpModel> VerifyOtp(@Header("X-API-KEY") String header,
                             @Field("customer_id") String customer_id,
                             @Field("otp") String otp,
                             @Field("customer_type") String customer_type);


    @FormUrlEncoded
    @POST("user/logout")
    Call<JsonObject> logoutFun(@Header("X-API-KEY") String header,
                             @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("products/parent_category_list")
    Call<CategoryMainModel> getParentCategory(@Header("X-API-KEY") String header,
                                              @Field("customer_id") String customer_id);


    @FormUrlEncoded
    @POST("products/category_list")
    Call<FilterMainModel> getFilterCategory(@Header("X-API-KEY") String header,
                                            @Field("category_id") String category_id,
                                            @Field("customer_id") String customer_id);


    @FormUrlEncoded
    @POST("products/product_list")
    Call<SubProductMainModel> getFilterProducts(@Header("X-API-KEY") String header,
                                                @Field("category_id") String category_id,
                                                @Field("customer_id") String customer_id);





}
