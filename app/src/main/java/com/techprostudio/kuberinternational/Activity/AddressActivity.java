package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class AddressActivity extends AppCompatActivity {
    LinearLayout ll_uncheck;
    boolean check = false;
    RelativeLayout gotochangeaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ll_uncheck=findViewById(R.id.ll_uncheck);
        gotochangeaddress=findViewById(R.id.gotochangeaddress);
        gotochangeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, AddressActivity.class));

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