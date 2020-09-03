package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class AddAddressActivity extends AppCompatActivity {
    RelativeLayout confirmaddress;
    ImageView back,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        confirmaddress=findViewById(R.id.confirmaddress);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        confirmaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddAddressActivity.this, AddressActivity.class));

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
}