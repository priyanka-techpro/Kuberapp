package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.Adapter.PaymentAdapter;
import com.techprostudio.kuberinternational.Model.PaymentModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    TextView gotoofferpage;
    RecyclerView paymentlist;
    ArrayList<PaymentModel> paymentModelArrayList;
    PaymentModel paymentModel;
    PaymentAdapter paymentAdapter;
    RelativeLayout proceedtopay;
    ImageView back,img_cart;
    RelativeLayout net_ll,cash_ll;
    RadioButton choose_net,choose_cash;
    String paymentMode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paymentlist=findViewById(R.id.paymentlist);
        proceedtopay=findViewById(R.id.proceedtopay);
        gotoofferpage=findViewById(R.id.gotoofferpage);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        net_ll=findViewById(R.id.net_ll);
        cash_ll=findViewById(R.id.cash_ll);
        choose_net=findViewById(R.id.choose_net);
        choose_cash=findViewById(R.id.choose_cash);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, CartActivity.class));
            }
        });

        gotoofferpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, OfferActivity.class));

            }
        });
        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //confirm
                startActivity(new Intent(PaymentActivity.this, OrderConfirmationActivity.class));

            }
        });
        net_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choose_cash.isChecked())
                {
                    choose_cash.setSelected(false);
                    choose_net.setSelected(true);
                    choose_net.setBackgroundDrawable(getResources().getDrawable(R.drawable.checkedradio));
                    choose_cash.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheckedradio));
                    paymentMode="COD";
                }
            }
        });
        cash_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_cash.setSelected(true);
                choose_net.setSelected(false);
                choose_net.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheckedradio));
                choose_cash.setBackgroundDrawable(getResources().getDrawable(R.drawable.checkedradio));
                paymentMode="COD";
            }
        });
    }

}