package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class OrderConfirmationActivity extends AppCompatActivity {
    RelativeLayout ll_orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        ll_orderList=findViewById(R.id.ll_orderList);
        ll_orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderConfirmationActivity.this, OrderHistoryActivity.class));

            }
        });
    }
}