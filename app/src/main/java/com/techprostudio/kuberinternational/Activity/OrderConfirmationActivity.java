package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.R;

public class OrderConfirmationActivity extends AppCompatActivity {
    RelativeLayout ll_orderList;
    TextView ordermsg,orderid;
    String ordrid,scsmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        ll_orderList=findViewById(R.id.ll_orderList);
        ordermsg=findViewById(R.id.ordermsg);
        orderid=findViewById(R.id.orderid);
        ordrid=getIntent().getExtras().getString("id");
        scsmsg=getIntent().getExtras().getString("msg");
       // ordermsg.setText(scsmsg);
        orderid.setText("Order("+ordrid+") placed successfully.");
        ll_orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderConfirmationActivity.this, OrderHistoryActivity.class));

            }
        });
    }
}