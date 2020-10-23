package com.techprostudio.kuberinternational.Network;


import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Model.AddAddressModel.AddaddressMainModel;
import com.techprostudio.kuberinternational.Model.AddressDetails.AddressDetailsModel;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressMainModel;
import com.techprostudio.kuberinternational.Model.AddtoCartModel;
import com.techprostudio.kuberinternational.Model.CartPackage.CartListMainModel;
import com.techprostudio.kuberinternational.Model.DashboardModel.DashboardMainModel;
import com.techprostudio.kuberinternational.Model.FaqMainModel;
import com.techprostudio.kuberinternational.Model.FaqModelPackage.FaqMainSubModel;
import com.techprostudio.kuberinternational.Model.FilterSection.FilterMainModel;
import com.techprostudio.kuberinternational.Model.LoginModel;
import com.techprostudio.kuberinternational.Model.OfferModelPackage.OfferMainModel;
import com.techprostudio.kuberinternational.Model.OrderCancelledModel;
import com.techprostudio.kuberinternational.Model.OrderConfirmPackage.OrderConfirmModel;
import com.techprostudio.kuberinternational.Model.OrderDetailsPackage.OrderDetailsModel;
import com.techprostudio.kuberinternational.Model.OrderHistoryPackage.OrderHistoryModel;
import com.techprostudio.kuberinternational.Model.OtpSection.OtpModel;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryMainModel;
import com.techprostudio.kuberinternational.Model.ProfileDetails.ProfileDetailsMain;
import com.techprostudio.kuberinternational.Model.ProfileUpdatePackage.ProfileUpdateMain;
import com.techprostudio.kuberinternational.Model.RegistrationModel;
import com.techprostudio.kuberinternational.Model.SearchPAckage.SearchMainModel;

import com.techprostudio.kuberinternational.Model.SingleProductPackage.SingleProductMainModel;
import com.techprostudio.kuberinternational.Model.SubProductMain.SubProductMainModel;
import com.techprostudio.kuberinternational.Model.WishListModel.WishListMainModel;
import com.techprostudio.kuberinternational.Model.wishlistAdd.AddWishListMainModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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



    @FormUrlEncoded
    @POST("products/single_product")
    Call<SingleProductMainModel> getSingleProducts(@Header("X-API-KEY") String header,
                                                   @Field("product_id") String category_id,
                                                   @Field("customer_id") String customer_id);



    @FormUrlEncoded
    @POST("products/add_to_cart")
    Call<AddtoCartModel> AddtoCart(@Header("X-API-KEY") String header,
                                   @Field("product_id") String product_id,
                                   @Field("customer_id") String customer_id,
                                   @Field("veriation_id") String veriation_id,
                                   @Field("quantity") String quantity
                                   );

    @GET("products/cart_list/{customer_id}")
    Call<CartListMainModel> CartList(@Header("X-API-KEY") String header,@Path("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("products/cart_item_delete")
    Call<CartListMainModel> deleteCartItem(@Header("X-API-KEY") String header,
                                             @Field("cart_id") String cart_id,
                                             @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("products/update_cart_item")
    Call<CartListMainModel> UpdateCartItem(@Header("X-API-KEY") String header,
                                             @Field("cart_id") String cart_id,
                                             @Field("customer_id") String customer_id,
                                             @Field("quantity") String quantity);

    @FormUrlEncoded
    @POST("user/add_customer_address")
    Call<AddaddressMainModel> Addaddress(@Header("X-API-KEY") String header,
                                             @Field("user_id") String user_id,
                                             @Field("full_name") String full_name,
                                             @Field("mobile_number") String mobile_number,
                                             @Field("address_line_one") String address_line_one,
                                             @Field("address_line_two") String address_line_two,
                                             @Field("land_mark") String land_mark,
                                             @Field("city") String city,
                                             @Field("pin") String pin,
                                             @Field("state") String state);

    @FormUrlEncoded
    @POST("user/customar_address_list")
    Call<AddressMainModel> getAddressList(@Header("X-API-KEY") String header,
                                          @Field("customer_id") String customer_id);

    @GET("user/company_help_content")
    Call<FaqMainModel> GetFaqContents(@Header("X-API-KEY") String header);

    @GET("user/company_faq_content")
    Call<FaqMainSubModel> GetFaqContentsSub(@Header("X-API-KEY") String header);

    @FormUrlEncoded
    @POST("user/user_profile_data")
    Call<ProfileDetailsMain> getProfileDetails(@Header("X-API-KEY") String header,
                                               @Field("customer_id") String customer_id);
    @FormUrlEncoded
    @POST("user/address_delete")
    Call<AddressMainModel> deleteAddress(@Header("X-API-KEY") String header,
                                               @Field("customer_id") String customer_id,
                                               @Field("address_id") String address_id
                                         );

    @Multipart
    @POST("user/profile_update")
    Call<ProfileUpdateMain> UpdateProfile(@Header("X-API-KEY") String header,
                                          @Part("customer_id") RequestBody customer_id,
                                          @Part("email") RequestBody email,
                                          @Part("full_name") RequestBody full_name,
                                          @Part MultipartBody.Part pic_url,
                                          @Part("second_phone") RequestBody second_phone,
                                          @Part("phone") RequestBody phone);

    @FormUrlEncoded
    @POST("user/customer_address")
    Call<AddressDetailsModel> editAddress(@Header("X-API-KEY") String header,
                                            @Field("customer_id") String customer_id,
                                            @Field("address_id") String address_id
    );
    @FormUrlEncoded
    @POST("user/address_update")
    Call<AddaddressMainModel> UpdateAddress(@Header("X-API-KEY") String header,
                                         @Field("customer_id") String customer_id,
                                         @Field("address_id") String address_id,
                                         @Field("name") String name,
                                         @Field("phone") String phone,
                                         @Field("address_1") String address_1,
                                         @Field("city") String city,
                                         @Field("state") String state,
                                         @Field("pin_code") String pin_code,
                                         @Field("landmark") String landmark,
                                         @Field("address_2") String address_2
    );

    @FormUrlEncoded
    @POST("order/cart_checkout")
    Call<OrderConfirmModel> placeOrder(@Header("X-API-KEY") String header,
                                        @Field("customer_id") String customer_id,
                                        @Field("address_id") String address_id,
                                        @Field("payment_type") String payment_type
    );

     @FormUrlEncoded
    @POST("order/order_history_list")
    Call<OrderHistoryModel> getOrderHistory(@Header("X-API-KEY") String header,
                                            @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("order/single_order_history")
    Call<OrderDetailsModel> getOrderDetails(@Header("X-API-KEY") String header,
                                            @Field("customer_id") String customer_id,
                                            @Field("order_id") String order_id);
    @FormUrlEncoded
    @POST("order/order_cancel")
    Call<OrderCancelledModel> CancelOrder(@Header("X-API-KEY") String header,
                                          @Field("customer_id") String customer_id,
                                          @Field("order_id") String search_text);

    @FormUrlEncoded
    @POST("products/product_search")
    Call<SearchMainModel> searchProduct(@Header("X-API-KEY") String header,
                                        @Field("product_name") String product_name,
                                        @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("products/add_wish_list")
    Call<AddWishListMainModel> addtoWishList(@Header("X-API-KEY") String header,
                                             @Field("product_id") String product_id,
                                             @Field("veriation_id") String veriation_id,
                                             @Field("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("products/wish_list")
    Call<WishListMainModel> getwishlist(@Header("X-API-KEY") String header,
                                          @Field("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("products/delete_wish_product")
    Call<WishListMainModel> deletefromWishlist(@Header("X-API-KEY") String header,
                                        @Field("wish_id") String wish_id,
                                          @Field("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("user/dashboard")
    Call<DashboardMainModel> getDashboardProduct(@Header("X-API-KEY") String header,
                                                 @Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("products/offer_list")
    Call<OfferMainModel> getoffer(@Header("X-API-KEY") String header,
                                           @Field("customer_id") String customer_id);

}
