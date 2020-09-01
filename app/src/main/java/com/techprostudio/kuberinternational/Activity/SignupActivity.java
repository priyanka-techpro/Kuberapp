package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.SplashActivity;

public class SignupActivity extends AppCompatActivity {
    RelativeLayout gotoconfmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        gotoconfmpassword=findViewById(R.id.gotoconfmpassword);
        gotoconfmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, DashboardActivity.class));
            }
        });
    }
}