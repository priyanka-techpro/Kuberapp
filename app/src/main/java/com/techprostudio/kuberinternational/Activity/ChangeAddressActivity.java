package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Model.AddAddressModel.AddaddressMainModel;
import com.techprostudio.kuberinternational.Model.AddressDetails.AddressDetailsModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;


public class ChangeAddressActivity extends AppCompatActivity {
    String addressid;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    RelativeLayout main_ll,confirmaddress;
    ImageView back;
    EditText ed_name,ed_phone,ed_pincode,ed_address,ed_loc,ed_land,ed_district,ed_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        main_ll=findViewById(R.id.main_ll);
        ed_name=findViewById(R.id.ed_name);
        ed_phone=findViewById(R.id.ed_phone);
        ed_pincode=findViewById(R.id.ed_pincode);
        ed_address=findViewById(R.id.ed_address);
        ed_loc=findViewById(R.id.ed_loc);
        ed_land=findViewById(R.id.ed_land);
        ed_district=findViewById(R.id.ed_district);
        ed_state=findViewById(R.id.ed_state);
        confirmaddress=findViewById(R.id.confirmaddress);
        back=findViewById(R.id.back);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        addressid=getIntent().getExtras().getString("addressid");
        String customerid=new AppPreference(ChangeAddressActivity.this).getUserId();
        if (InternetAccess.isConnected(ChangeAddressActivity.this)) {

         getAddressDetails(customerid,addressid);
        }
        else {
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirmaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name=ed_name.getText().toString();
                String mobile_number=ed_phone.getText().toString();
                String address_line_one=ed_address.getText().toString();
                String address_line_two=ed_loc.getText().toString();
                String land_mark=ed_land.getText().toString();
                String city=ed_district.getText().toString();
                String pin=ed_pincode.getText().toString();
                String state=ed_state.getText().toString();
                boolean cancel = false;
                View focusView = null;
                if(TextUtils.isEmpty(full_name)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your fullname", Toast.LENGTH_SHORT).show();
                    focusView = ed_name;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(mobile_number)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    focusView = ed_phone;
                    cancel = true;
                }
                else if(isPhoneValid(mobile_number)){
                    Toast.makeText(ChangeAddressActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                    focusView = ed_phone;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(address_line_one)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    focusView = ed_address;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(address_line_two)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    focusView = ed_loc;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(land_mark)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your landmark", Toast.LENGTH_SHORT).show();
                    focusView = ed_land;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(city)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your city", Toast.LENGTH_SHORT).show();
                    focusView = ed_district;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(pin)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your pincode", Toast.LENGTH_SHORT).show();
                    focusView = ed_pincode;
                    cancel = true;
                }
                else if(pin.length() < 6){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter valid pincode", Toast.LENGTH_SHORT).show();
                    focusView = ed_pincode;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(state)){
                    Toast.makeText(ChangeAddressActivity.this, "Please enter your state", Toast.LENGTH_SHORT).show();
                    focusView = ed_state;
                    cancel = true;
                }
                else{
                    if (InternetAccess.isConnected(ChangeAddressActivity.this))
                    {
                        updateAddress(customerid,addressid,full_name,mobile_number,address_line_one,city,state,pin,land_mark,address_line_two);
                    }
                    else {
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

    }

    private void updateAddress(String customerid, String addressid, String full_name, String mobile_number, String address_line_one, String city, String state, String pin, String land_mark, String address_line_two)
    {
        Call<AddaddressMainModel> call=apiInterface.UpdateAddress(Config.header,
                customerid,addressid,full_name,mobile_number,address_line_one,city,state,pin,land_mark,address_line_two);
        progressDialog1 = new ProgressDialog(ChangeAddressActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<AddaddressMainModel>() {
            @Override
            public void onResponse(Call<AddaddressMainModel> call, Response<AddaddressMainModel> response) {
                progressDialog1.dismiss();
                if(response.body().getStatus()==true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(ChangeAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangeAddressActivity.this, AddressActivity.class));
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(ChangeAddressActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AddaddressMainModel> call, Throwable t) {
                progressDialog1.dismiss();
            }
        });
    }

    private void getAddressDetails(String customerid, String addressid) {
        Call<AddressDetailsModel> call=apiInterface.editAddress(Config.header,customerid,addressid);
        progressDialog = new ProgressDialog(ChangeAddressActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<AddressDetailsModel>() {
            @Override
            public void onResponse(Call<AddressDetailsModel> call, Response<AddressDetailsModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true){
                    String name=response.body().getAddressData().getName();
                    ed_name.setText(name);
                    String phonenumber=response.body().getAddressData().getPhone();
                    ed_phone.setText(phonenumber);
                    String zipcode=response.body().getAddressData().getPinCode();
                    ed_pincode.setText(zipcode);
                    String addressone=response.body().getAddressData().getAddress1();
                    ed_address.setText(addressone);
                    String addresstwo=response.body().getAddressData().getAddress2();
                    ed_loc.setText(addresstwo);
                    String landmark=response.body().getAddressData().getLandmark();
                    ed_land.setText(landmark);
                    String city=response.body().getAddressData().getCity();
                    ed_district.setText(city);
                    String state=response.body().getAddressData().getState();
                    ed_state.setText(state);
                }
                else {
                    String msg=response.body().getMessage();
                    Toast.makeText(ChangeAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddressDetailsModel> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private boolean isPhoneValid(String phone) {

        return phone.length() < 9;
    }
}