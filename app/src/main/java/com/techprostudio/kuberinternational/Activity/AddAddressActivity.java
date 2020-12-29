package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Model.AddAddressModel.AddaddressMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

public class AddAddressActivity extends AppCompatActivity {
    ImageView back,img_cart;

    RelativeLayout confirmaddress;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    RelativeLayout main_ll;
    ProgressDialog progressDialog;
    EditText ed_name,ed_phone,ed_pincode,ed_address,ed_loc,ed_land,ed_district,ed_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        confirmaddress=findViewById(R.id.confirmaddress);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        ed_name=findViewById(R.id.ed_name);
        ed_phone=findViewById(R.id.ed_phone);
        ed_pincode=findViewById(R.id.ed_pincode);
        ed_address=findViewById(R.id.ed_address);
        ed_loc=findViewById(R.id.ed_loc);
        ed_land=findViewById(R.id.ed_land);
        ed_district=findViewById(R.id.ed_district);
        ed_state=findViewById(R.id.ed_state);
        main_ll=findViewById(R.id.main_ll);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(AddAddressActivity.this).getUserId();

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
                    Toast.makeText(AddAddressActivity.this, "Please enter your fullname", Toast.LENGTH_SHORT).show();
                    focusView = ed_name;
                    cancel = true;
                }
                 else if(TextUtils.isEmpty(mobile_number)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    focusView = ed_phone;
                    cancel = true;
                }
                else if(isPhoneValid(mobile_number)){
                    Toast.makeText(AddAddressActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                    focusView = ed_phone;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(address_line_one)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    focusView = ed_address;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(address_line_two)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    focusView = ed_loc;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(land_mark)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your landmark", Toast.LENGTH_SHORT).show();
                    focusView = ed_land;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(city)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your city", Toast.LENGTH_SHORT).show();
                    focusView = ed_district;
                    cancel = true;
                }
                else if(TextUtils.isEmpty(pin)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your pincode", Toast.LENGTH_SHORT).show();
                    focusView = ed_pincode;
                    cancel = true;
                }
                else if(pin.length() < 6){
                    Toast.makeText(AddAddressActivity.this, "Please enter valid pincode", Toast.LENGTH_SHORT).show();
                    focusView = ed_pincode;
                    cancel = true;
                }
                 else if(TextUtils.isEmpty(state)){
                    Toast.makeText(AddAddressActivity.this, "Please enter your state", Toast.LENGTH_SHORT).show();
                    focusView = ed_state;
                    cancel = true;
                }
                else{
                    if (InternetAccess.isConnected(AddAddressActivity.this)) {

                        addAddress(customerid,full_name,mobile_number,address_line_one,address_line_two,land_mark,city,pin,state);
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


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddAddressActivity.this, CartActivity.class));
            }
        });
    }

    private void addAddress(String customerid, String full_name, String mobile_number, String address_line_one, String address_line_two, String land_mark, String city, String pin, String state)
    {
        Call<AddaddressMainModel> call= apiInterface.Addaddress(Config.header,customerid
                ,full_name,mobile_number,address_line_one,
                address_line_two,land_mark,city,pin,state);
        progressDialog = new ProgressDialog(AddAddressActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<AddaddressMainModel>() {
            @Override
            public void onResponse(Call<AddaddressMainModel> call, Response<AddaddressMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(AddAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddAddressActivity.this, AddressActivity.class));
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(AddAddressActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AddaddressMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private boolean isPhoneValid(String phone) {

        return phone.length() < 9;
    }
}