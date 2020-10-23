package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Model.LoginModel;
import com.techprostudio.kuberinternational.Model.OtpSection.OtpModel;
import com.techprostudio.kuberinternational.Model.OtpSection.UserDetails;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtpVerifyActivity extends AppCompatActivity {
    RelativeLayout verifyotp;
    String customerid,customertype;
    RelativeLayout ll_main;
    EditText ed_one,ed_two,ed_three,ed_four;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    TextView resend_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        verifyotp=findViewById(R.id.verifyotp);
        ll_main=findViewById(R.id.ll_main);
        ed_one=findViewById(R.id.ed_one);
        ed_two=findViewById(R.id.ed_two);
        ed_three=findViewById(R.id.ed_three);
        ed_four=findViewById(R.id.ed_four);
        resend_txt=findViewById(R.id.resend_txt);

        customerid=getIntent().getExtras().getString("custid");
        customertype=getIntent().getExtras().getString("customertype");

        ed_one.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_one.getText().toString().length()>0){
                    ed_one.clearFocus();
                    ed_two.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(ed_one.getText().toString().length()>0){
                    ed_one.clearFocus();
                    ed_two.requestFocus();
                }
            }
        });
        ed_two.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_two.getText().toString().length()>0){
                    ed_two.clearFocus();
                    ed_three.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(ed_two.getText().toString().length()>0){
                    ed_two.clearFocus();
                    ed_three.requestFocus();
                }
            }
        });
        ed_three.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_three.getText().toString().length()>0){
                    ed_three.clearFocus();
                    ed_four.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(ed_three.getText().toString().length()>0){
                    ed_three.clearFocus();
                    ed_four.requestFocus();
                }
            }
        });
        resend_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberrVerify(Config.phno);

            }
        });
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetAccess.isConnected(OtpVerifyActivity.this)) {

                    String otp=ed_one.getText().toString()+ed_two.getText().toString()+ed_three.getText().toString()+ed_four.getText().toString();

                    checkOtp(otp,customerid);
                }
                else {
                    mSnackbar = Snackbar.make(ll_main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
                    mSnackbar.show();
                }

               //

            }
        });
    }

    private void checkOtp(String otp, String customerid) {
        Call<OtpModel> call=apiInterface.VerifyOtp(Config.header,customerid,otp,customertype);
        progressDialog = new ProgressDialog(OtpVerifyActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OtpModel>() {
            @Override
            public void onResponse(Call<OtpModel> call, Response<OtpModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus().equals(true))
                {
                    String customerid=response.body().getUserDetails().getUserId();
                    new AppPreference(OtpVerifyActivity.this).saveUserID(customerid);
                    String user_name=response.body().getUserDetails().getUserName();
                    new AppPreference(OtpVerifyActivity.this).saveUserName(user_name);
                    String user_email=response.body().getUserDetails().getUserEmail();
                    new AppPreference(OtpVerifyActivity.this).saveUserEmail(user_email);
                    String user_phone=response.body().getUserDetails().getUserPhone();
                    new AppPreference(OtpVerifyActivity.this).saveUserPhone(user_phone);
                    String user_image=response.body().getUserDetails().getUserImage();
                    new AppPreference(OtpVerifyActivity.this).setUserImageUrl(user_image);
                    startActivity(new Intent(OtpVerifyActivity.this,DashboardActivity.class));
                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(OtpVerifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onFailure(Call<OtpModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private void numberrVerify(String phone) {

        Call<LoginModel> call = apiInterface.CheckPhone("KUBERINT@321",phone);
        progressDialog1 = new ProgressDialog(OtpVerifyActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response)
            {
                progressDialog1.dismiss();
                if(response.body().getStatus() == true) {
                    String usertype = response.body().getCustomerType();

                        String otp=response.body().getOtp();
                        Toast.makeText(OtpVerifyActivity.this,"Your login otp is "+otp,Toast.LENGTH_SHORT).show();
                        String customerid = response.body().getCustomerId();
                        Intent i =new Intent(OtpVerifyActivity.this,OtpVerifyActivity.class);
                        i.putExtra("custid",customerid);
                        i.putExtra("customertype",usertype);
                        startActivity(i);


                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(OtpVerifyActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                progressDialog1.dismiss();
            }
        });

    }

}