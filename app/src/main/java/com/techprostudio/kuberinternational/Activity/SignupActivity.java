package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.SplashActivity;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    RelativeLayout gotoconfmpassword,ll_main;
    EditText ed_username_signup,ed_email_signup,ed_address_signup;
    String phonenumber,devicetoken;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        gotoconfmpassword=findViewById(R.id.gotoconfmpassword);
        ed_username_signup=findViewById(R.id.ed_username_signup);
        ed_email_signup=findViewById(R.id.ed_email_signup);
        ed_address_signup=findViewById(R.id.ed_address_signup);
        ll_main=findViewById(R.id.ll_main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        phonenumber=getIntent().getExtras().getString("phone");
        devicetoken = new AppPreference(SignupActivity.this).getDeviceToken();

        gotoconfmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id=ed_email_signup.getText().toString();
                String adress=ed_address_signup.getText().toString();
                String usernm=ed_username_signup.getText().toString();
                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(email_id))
                {
                    Toast.makeText(SignupActivity.this, "Enter your email address", Toast.LENGTH_SHORT).show();

                    focusView = ed_email_signup;
                    cancel = true;
                }
                if (TextUtils.isEmpty(adress))
                {
                    Toast.makeText(SignupActivity.this, "Enter your address", Toast.LENGTH_SHORT).show();

                    focusView = ed_address_signup;
                    cancel = true;
                }
                if (TextUtils.isEmpty(usernm))
                {
                    Toast.makeText(SignupActivity.this, "Enter your email address", Toast.LENGTH_SHORT).show();

                    focusView = ed_username_signup;
                    cancel = true;
                }
                else if(!isEmailValid(email_id))
                {
                    Toast.makeText(SignupActivity.this, "Enter valid email address", Toast.LENGTH_SHORT).show();

                    focusView = ed_email_signup;
                    cancel = true;
                }
                else {
                    if (InternetAccess.isConnected(SignupActivity.this)) {
                        RegisterUser(usernm,email_id,phonenumber,adress,devicetoken);
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


              //  startActivity(new Intent(SignupActivity.this, DashboardActivity.class));
            }
        });
    }

    private void RegisterUser(String usernm, String email_id, String phonenumber, String adress, String devicetoken)
    {
        Call<JsonObject> call=apiInterface.UserRegistration(Config.header,usernm,email_id,phonenumber,adress,devicetoken);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JsonObject object = response.body();
                    JsonElement jsonObject = object;
                    JSONObject converintoJsonObject = new JSONObject(String.valueOf(jsonObject));
                    if (converintoJsonObject.getString("status").equals(true))
                    {
                        startActivity(new Intent(SignupActivity.this, OtpVerifyActivity.class));
                    }
                    else {

                    }
                }
                    catch(Exception e){

                    }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    private boolean isEmailValid(String email) {

        //   email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // return email.contains("@");

        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPhoneValid(String phone) {

        return phone.length() > 9;
    }


}