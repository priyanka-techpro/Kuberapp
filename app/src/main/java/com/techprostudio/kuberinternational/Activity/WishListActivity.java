package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.techprostudio.kuberinternational.R;

public class WishListActivity extends AppCompatActivity {
    RecyclerView wishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        wishlist=findViewById(R.id.wishlist);
    }
}