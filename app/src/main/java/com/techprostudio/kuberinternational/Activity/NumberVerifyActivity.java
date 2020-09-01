package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class NumberVerifyActivity extends AppCompatActivity {
    RelativeLayout gotophoneverify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verify);
        gotophoneverify=findViewById(R.id.gotophoneverify);
        gotophoneverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NumberVerifyActivity.this,OtpVerifyActivity.class));

            }
        });
    }
}