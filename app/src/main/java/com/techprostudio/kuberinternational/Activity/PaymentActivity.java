package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Model.CartPackage.CartListMainModel;
import com.techprostudio.kuberinternational.Model.OrderConfirmPackage.OrderConfirmModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import static com.techprostudio.kuberinternational.Adapter.AddressListAdapter.AddressId;

public class PaymentActivity extends AppCompatActivity {
    TextView gotoofferpage;
    RelativeLayout proceedtopay,main_ll;
    ImageView back,img_cart,img_notify;
    RelativeLayout net_ll,cash_ll,subtotal_ll,cart_count;
    RadioButton choose_net,choose_cash;
    String paymentMode="";
    String addressid;
    ApiInterface apiInterface;
    Snackbar mSnackbar;
    ProgressDialog progressDialog,progressDialog1;
    TextView sub_total_amt,discount_amt,shippingchrge,rndoff,ttl_amnt,tv_count,offer_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        proceedtopay=findViewById(R.id.proceedtopay);
        gotoofferpage=findViewById(R.id.gotoofferpage);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        net_ll=findViewById(R.id.net_ll);
        cash_ll=findViewById(R.id.cash_ll);
        choose_net=findViewById(R.id.choose_net);
        choose_cash=findViewById(R.id.choose_cash);
        main_ll=findViewById(R.id.main_ll);
        sub_total_amt=findViewById(R.id.sub_total_amt);
        discount_amt=findViewById(R.id.discount_amt);
        shippingchrge=findViewById(R.id.shippingchrge);
        rndoff=findViewById(R.id.rndoff);
        ttl_amnt=findViewById(R.id.ttl_amnt);
        subtotal_ll=findViewById(R.id.subtotal_ll);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);
        img_notify=findViewById(R.id.img_notify);
        offer_place=findViewById(R.id.offer_place);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(PaymentActivity.this).getUserId();
        addressid=AddressId;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, CartActivity.class));
            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, WishListActivity.class));
            }
        });

        gotoofferpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, OfferActivity.class));

            }
        });

        if (InternetAccess.isConnected(PaymentActivity.this)) {

            cartitems(customerid);
            subtotal_ll.setVisibility(View.VISIBLE);
        }
        else {
            subtotal_ll.setVisibility(View.GONE);
            mSnackbar = Snackbar
                    .make(main_ll, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }
        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //confirm

               if(paymentMode.equals(""))
               {
                   Toast.makeText(PaymentActivity.this, "Please select a payment mode.", Toast.LENGTH_SHORT).show();
               }
               else {
                   if (InternetAccess.isConnected(PaymentActivity.this)) {

                       orderconfirm(customerid, addressid, paymentMode);
                   } else {
                       mSnackbar = Snackbar
                               .make(main_ll, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                                       setAction("Ok", new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               mSnackbar.dismiss();

                                           }
                                       });
                       mSnackbar.show();
                   }
               }
            }
        });
        choose_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choose_net.isChecked())
                {
                    choose_cash.setSelected(false);
                    choose_net.setSelected(true);
                    choose_net.setBackgroundDrawable(getResources().getDrawable(R.drawable.checkedradio));
                    choose_cash.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheckedradio));
                    paymentMode="OL";
                }
            }
        });
        choose_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choose_cash.isChecked()) {
                    choose_cash.setSelected(true);
                    choose_net.setSelected(false);
                    choose_net.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheckedradio));
                    choose_cash.setBackgroundDrawable(getResources().getDrawable(R.drawable.checkedradio));
                    paymentMode = "COD";
                }
            }
        });
    }

    private void orderconfirm(String customerid, String addressid, String paymentMode) {
        Call<OrderConfirmModel> call=apiInterface.placeOrder(Config.header,customerid,addressid,paymentMode);
        progressDialog = new ProgressDialog(PaymentActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OrderConfirmModel>() {
            @Override
            public void onResponse(Call<OrderConfirmModel> call, Response<OrderConfirmModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true) {
                    Config.offerid="0";
                    String msges=response.body().getMessage();
                    String orderid=response.body().getOrderHistory().getOrderNumber();
                   // Toast.makeText(PaymentActivity.this, msges, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PaymentActivity.this, OrderConfirmationActivity.class);
                    i.putExtra("msg",msges );
                    i.putExtra("id",orderid );
                    startActivity(i);
                }
                else
                    {
                        String msges=response.body().getMessage();
                        Toast.makeText(PaymentActivity.this, msges, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderConfirmModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private void cartitems(String customerid) {
        Call<CartListMainModel> call=apiInterface.CartList(Config.header,Config.offerid,customerid);
        progressDialog1 = new ProgressDialog(PaymentActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<CartListMainModel>() {
            @Override
            public void onResponse(Call<CartListMainModel> call, Response<CartListMainModel> response) {
                progressDialog1.dismiss();
                if(response.body().isStatus()==true)
                {
                    if(response.body().getCartCount()== 0){
                        String msg=response.body().getMessage();
                        Toast.makeText(PaymentActivity.this, msg, Toast.LENGTH_SHORT).show();
                        subtotal_ll.setVisibility(View.GONE);
                    }
                    else {
                        subtotal_ll.setVisibility(View.VISIBLE);
                        if(Config.offerid == "0")
                        {
                            offer_place.setText("No offer has been selected");

                        }
                        else
                        {
                            offer_place.setText(response.body().getOfferTag().getPriceTagText()+" off has been applied.");

                        }
                    //    offer_place.setText(response.body().getOfferTag().getPriceTagText()+" off");
                        String cartCount = String.valueOf(response.body().getCartCount());
                        Config.cart = cartCount;
                        if (cartCount.equals("0")) {
                            cart_count.setVisibility(View.GONE);
                            tv_count.setVisibility(View.GONE);
                        } else {
                            cart_count.setVisibility(View.VISIBLE);
                            tv_count.setVisibility(View.VISIBLE);
                            tv_count.setText(Config.cart);
                        }
                        sub_total_amt.setText("Rs " + response.body().getCartPriceData().getTotalPayablePrice());
                        discount_amt.setText("Rs " + response.body().getCartPriceData().getTotalSavePrice());
                        shippingchrge.setText("Rs " + response.body().getCartPriceData().getDeliveryCharge());
                        rndoff.setText("Rs " + response.body().getCartPriceData().getTotalGstPrice());
                        ttl_amnt.setText("Rs " + response.body().getCartPriceData().getFinalPayablePrice());

                    }
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(PaymentActivity.this, msg, Toast.LENGTH_SHORT).show();
                    subtotal_ll.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<CartListMainModel> call, Throwable t) {
                progressDialog1.dismiss();
            }
        });

    }

}