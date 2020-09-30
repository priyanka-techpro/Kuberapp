package com.techprostudio.kuberinternational.Network;


import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Model.LoginModel;

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
    Call<JsonObject> UserRegistration(@Header("X-API-KEY") String header,
                                @Field("full_name") String full_name,
                                @Field("email") String email,
                                @Field("phone") String phone,
                                @Field("address") String address,
                                @Field("device_token") String device_token);


}
