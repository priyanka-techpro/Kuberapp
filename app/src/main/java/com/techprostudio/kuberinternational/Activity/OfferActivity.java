package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.OfferAdapter;
import com.techprostudio.kuberinternational.Model.OfferModelPackage.OfferList;
import com.techprostudio.kuberinternational.Model.OfferModelPackage.OfferMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity {
    RecyclerView offerslist;
    List<OfferList> offerModelArrayList;
    OfferAdapter offerAdapter;
    ImageView back,img_cart,img_notify;
    RelativeLayout cart_count,main_ll;
    TextView tv_count;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        offerslist=findViewById(R.id.offerslist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        img_notify=findViewById(R.id.img_notify);
        tv_count=findViewById(R.id.tv_count);
        cart_count=findViewById(R.id.cart_count);
        main_ll=findViewById(R.id.main_ll);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);

        String customerid=new AppPreference(OfferActivity.this).getUserId();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OfferActivity.this, CartActivity.class));
            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OfferActivity.this, WishListActivity.class));
            }
        });
        if (InternetAccess.isConnected(OfferActivity.this)) {

            offeritems(customerid);

        }
        else {

            mSnackbar = Snackbar
                    .make(main_ll, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }
        offerModelArrayList=new ArrayList<>();

    }
    private void offeritems(String customerid) {
        Call<OfferMainModel> call=apiInterface.getoffer(Config.header,customerid);
        progressDialog = new ProgressDialog(OfferActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OfferMainModel>() {
            @Override
            public void onResponse(Call<OfferMainModel> call, Response<OfferMainModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus() == true)
                {
                    String cartCount = String.valueOf(response.body().getCartCount());
                    Config.cart = cartCount;
                    if (cartCount.equals("0")) {
                        cart_count.setVisibility(View.GONE);
                        tv_count.setVisibility(View.GONE);
                        Toast.makeText(OfferActivity.this, "Please ensure that your cart is not empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        cart_count.setVisibility(View.VISIBLE);
                        tv_count.setVisibility(View.VISIBLE);
                        tv_count.setText(Config.cart);
                        String msg=response.body().getMessage();
                        Toast.makeText(OfferActivity.this, msg, Toast.LENGTH_SHORT).show();
                        offerModelArrayList=response.body().getOfferList();
                        offerAdapter=new OfferAdapter(OfferActivity.this,offerModelArrayList);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(OfferActivity.this,1);
                        offerslist.setLayoutManager(mLayoutManager);
                        offerslist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                        offerslist.setItemAnimator(new DefaultItemAnimator());
                        offerslist.setAdapter(offerAdapter);
                        offerAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(OfferActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OfferMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}