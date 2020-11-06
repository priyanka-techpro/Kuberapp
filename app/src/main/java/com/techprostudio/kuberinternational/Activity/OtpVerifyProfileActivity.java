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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Model.OtpProfileUpdateMain;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

public class OtpVerifyProfileActivity extends AppCompatActivity {
    RelativeLayout verifyotp;
    String otptype;
    RelativeLayout ll_main;
    EditText ed_one,ed_two,ed_three,ed_four;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify_profile);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        verifyotp=findViewById(R.id.verifyotp);
        ll_main=findViewById(R.id.ll_main);
        ed_one=findViewById(R.id.ed_one);
        ed_two=findViewById(R.id.ed_two);
        ed_three=findViewById(R.id.ed_three);
        ed_four=findViewById(R.id.ed_four);
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
        String customerid=new AppPreference(OtpVerifyProfileActivity.this).getUserId();
        otptype=getIntent().getExtras().getString("otptype");

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetAccess.isConnected(OtpVerifyProfileActivity.this)) {

                    String otp=ed_one.getText().toString()+ed_two.getText().toString()+ed_three.getText().toString()+ed_four.getText().toString();

                 checkOtp(otp,customerid,otptype);
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

    private void checkOtp(String otp, String customerid, String otptype) {
        Call<OtpProfileUpdateMain> call=apiInterface.checkOtpProfile(Config.header,customerid,otp,otptype);
        progressDialog = new ProgressDialog(OtpVerifyProfileActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OtpProfileUpdateMain>() {
            @Override
            public void onResponse(Call<OtpProfileUpdateMain> call, Response<OtpProfileUpdateMain> response) {
                progressDialog.dismiss(); 
                if(response.body().isStatus() == true)
                {
                 String msg=response.body().getMessage();
                 Toast.makeText(OtpVerifyProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(OtpVerifyProfileActivity.this,ProfileActivity.class);
                startActivity(i);
                finish();
                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(OtpVerifyProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpProfileUpdateMain> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}