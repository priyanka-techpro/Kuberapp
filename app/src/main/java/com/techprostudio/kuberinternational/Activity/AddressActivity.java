package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class AddressActivity extends AppCompatActivity {
    LinearLayout ll_uncheck;
    boolean check = false;
    RelativeLayout gotochangeaddress,proceedtopayment;
    ImageView back,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ll_uncheck=findViewById(R.id.ll_uncheck);
        gotochangeaddress=findViewById(R.id.gotochangeaddress);
        proceedtopayment=findViewById(R.id.proceedtopayment);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, CartActivity.class));
            }
        });

        gotochangeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, ChangeAddressActivity.class));

            }
        });
        proceedtopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, PaymentActivity.class));

            }
        });

        ll_uncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check == false)
                {
                    ll_uncheck.setBackgroundResource(R.drawable.checked);

                    check =true;
                }
                else
                {
                    ll_uncheck.setBackgroundResource(R.drawable.unchecked);

                    check =false;
                }


            }
        });
    }
}