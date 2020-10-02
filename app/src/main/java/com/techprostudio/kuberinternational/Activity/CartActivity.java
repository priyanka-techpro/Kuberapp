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
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Model.CartPackage.CartList;
import com.techprostudio.kuberinternational.Model.CartPackage.CartListMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    public static TextView textredeem,tv_count;
    public static TextView sub_total_amt,discount_amt,shippingchrge,rndoff,ttl_amnt;
    public static RelativeLayout proceedtocheckout,subtotal_ll,cart_count;
    RecyclerView cartlist;
    List<CartList> cartListModelArrayList;
    ProgressDialog progressDialog;
    CartAdapter cartAdapter;
    public static ImageView back,img_cart;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    RelativeLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        textredeem=findViewById(R.id.textredeem);
        proceedtocheckout=findViewById(R.id.proceedtocheckout);
        cartlist=findViewById(R.id.cartlist);
        main=findViewById(R.id.main);
        sub_total_amt=findViewById(R.id.sub_total_amt);
        discount_amt=findViewById(R.id.discount_amt);
        shippingchrge=findViewById(R.id.shippingchrge);
        rndoff=findViewById(R.id.rndoff);
        ttl_amnt=findViewById(R.id.ttl_amnt);
        subtotal_ll=findViewById(R.id.subtotal_ll);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);

        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(CartActivity.this).getUserId();

        proceedtocheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, AddressActivity.class));

            }
        });

        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        String text = "<font color=#0b539d>Your order is eligible for rewards. </font> <font color=#555555>You can also choose to redeem at checkout</font>";
        textredeem.setText(Html.fromHtml(text));
        textredeem.setTextSize(16);
        cartListModelArrayList=new ArrayList<>();
        if (InternetAccess.isConnected(CartActivity.this)) {

            cartitems(customerid);
        }
        else {
            mSnackbar = Snackbar
                    .make(main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }


    }

    private void cartitems(String customerid) {
        Call<CartListMainModel> call=apiInterface.CartList(Config.header,customerid);
        progressDialog = new ProgressDialog(CartActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<CartListMainModel>() {
            @Override
            public void onResponse(Call<CartListMainModel> call, Response<CartListMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                    if(response.body().getCartCount().equals(0)){
                        String msg=response.body().getMessage();
                        Toast.makeText(CartActivity.this, msg, Toast.LENGTH_SHORT).show();
                        subtotal_ll.setVisibility(View.GONE);
                        proceedtocheckout.setVisibility(View.GONE);
                    }
                    else {
                        subtotal_ll.setVisibility(View.VISIBLE);
                        proceedtocheckout.setVisibility(View.VISIBLE);
                        String cartCount = String.valueOf(response.body().getCartCount());
                        Config.cart = cartCount;
                        if (cartCount.equals("0")) {
                            cart_count.setVisibility(View.GONE);
                            tv_count.setVisibility(View.GONE);
                        } else {
                            cart_count.setVisibility(View.VISIBLE);
                            tv_count.setVisibility(View.VISIBLE);
                            tv_count.setText(Config.cart);
                        }
                        sub_total_amt.setText("Rs " + response.body().getCartPriceData().getTotalPayablePrice());
                        discount_amt.setText("Rs " + response.body().getCartPriceData().getTotalSavePrice());
                        shippingchrge.setText("Rs " + response.body().getCartPriceData().getDeliveryCharge());
                        rndoff.setText("Rs " + response.body().getCartPriceData().getTotalGstPrice());
                        ttl_amnt.setText("Rs " + response.body().getCartPriceData().getFinalPayablePrice());
                        cartListModelArrayList = response.body().getCartList();
                        cartAdapter = new CartAdapter(CartActivity.this, cartListModelArrayList);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(CartActivity.this, 1);
                        cartlist.setLayoutManager(mLayoutManager);
                        cartlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                        cartlist.setItemAnimator(new DefaultItemAnimator());
                        cartlist.setAdapter(cartAdapter);
                        cartAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                String msg=response.body().getMessage();
                Toast.makeText(CartActivity.this, msg, Toast.LENGTH_SHORT).show();
                    subtotal_ll.setVisibility(View.GONE);
                    proceedtocheckout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CartListMainModel> call, Throwable t) {
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