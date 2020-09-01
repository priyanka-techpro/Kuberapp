package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.SplashActivity;

public class SigninActivity extends AppCompatActivity {
    TextView gotosignup;
    RelativeLayout gotophoneverify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        gotosignup=findViewById(R.id.gotosignup);
        gotophoneverify=findViewById(R.id.gotophoneverify);
        gotophoneverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this,NumberVerifyActivity.class));

            }
        });
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this,SignupActivity.class));

            }
        });
    }
}