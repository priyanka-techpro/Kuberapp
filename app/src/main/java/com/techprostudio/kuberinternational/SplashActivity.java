package com.techprostudio.kuberinternational;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Activity.NumberVerifyActivity;
import com.techprostudio.kuberinternational.Activity.OtpVerifyActivity;
import com.techprostudio.kuberinternational.Activity.SigninActivity;

public class SplashActivity extends AppCompatActivity {
RelativeLayout gotologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gotologin=findViewById(R.id.gotologin);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, NumberVerifyActivity.class));

            }
        });
    }
}