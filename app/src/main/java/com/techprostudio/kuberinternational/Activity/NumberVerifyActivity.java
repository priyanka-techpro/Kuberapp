package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Model.LoginModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;

public class NumberVerifyActivity extends AppCompatActivity {
    RelativeLayout gotophoneverify,ll_main;
    EditText ed_phone_verify;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verify);
        gotophoneverify=findViewById(R.id.gotophoneverify);
        ed_phone_verify=findViewById(R.id.ed_phone_verify);
        ll_main=findViewById(R.id.ll_main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        gotophoneverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = ed_phone_verify.getText().toString();
                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(NumberVerifyActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();

                    focusView = ed_phone_verify;
                    cancel = true;
                }
                else if(!isPhoneValid(phone))
                {
                    Toast.makeText(NumberVerifyActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();

                    focusView = ed_phone_verify;
                    cancel = true;
                }
                else {
                    if (InternetAccess.isConnected(NumberVerifyActivity.this)) {

                        numberrVerify(phone);
                    } else {
                        mSnackbar = Snackbar
                                .make(ll_main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
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
    }

    private void numberrVerify(String phone) {

        Call<LoginModel> call = apiInterface.CheckPhone("KUBERINT@321",phone);
        progressDialog = new ProgressDialog(NumberVerifyActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response)
            {
                progressDialog.dismiss();
                if(response.body().getStatus() == true) {
                    String usertype = response.body().getCustomerType();
                    if (usertype.equals("new_customer"))
                    {
                        String msg=response.body().getMessage();
                        Toast.makeText(NumberVerifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                        String phonenumber=response.body().getPhoneNumber();
                        Intent i =new Intent(NumberVerifyActivity.this,SignupActivity.class);
                        i.putExtra("phone",phonenumber);
                        i.putExtra("customertype",usertype);
                        startActivity(i);
                    }
                    else
                        {
                            String otp=response.body().getOtp();
                            Toast.makeText(NumberVerifyActivity.this,"Your login otp is "+otp,Toast.LENGTH_SHORT).show();
                            String customerid = response.body().getCustomerId();
                            Intent i =new Intent(NumberVerifyActivity.this,OtpVerifyActivity.class);
                            i.putExtra("custid",customerid);
                            i.putExtra("customertype",usertype);
                            startActivity(i);

                    }
                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(NumberVerifyActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private boolean isPhoneValid(String phone) {

        return phone.length() > 9;
    }
}