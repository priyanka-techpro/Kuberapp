package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.R;

public class ConfirmpasswordActivity extends AppCompatActivity {
    TextView gotosignin;
    RelativeLayout gotosignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpassword);
        gotosignup=findViewById(R.id.gotosignup);
        gotosignin=findViewById(R.id.gotosignin);
        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmpasswordActivity.this, SigninActivity.class));

            }
        });

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmpasswordActivity.this, SigninActivity.class));

            }
        });
    }
}