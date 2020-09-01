package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;

public class OtpVerifyActivity extends AppCompatActivity {
    RelativeLayout verifyotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        verifyotp=findViewById(R.id.verifyotp);
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpVerifyActivity.this,SignupActivity.class));

            }
        });
    }
}