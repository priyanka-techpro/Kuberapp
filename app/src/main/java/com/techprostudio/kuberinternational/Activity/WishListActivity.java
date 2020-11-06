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
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Adapter.WishListAdapter;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressList;
import com.techprostudio.kuberinternational.Model.WishListModel.WishListMain;
import com.techprostudio.kuberinternational.Model.WishListModel.WishListMainModel;
import com.techprostudio.kuberinternational.Model.wishlistAdd.WishList;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity extends AppCompatActivity {
    RecyclerView wishlist;
    WishListAdapter wishListAdapter;
    List<WishListMain> wishListMains;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    public static RelativeLayout main,cart_count;
    ProgressDialog progressDialog;
    public static TextView tv_count;
    ImageView img_share,img_cart,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        wishlist=findViewById(R.id.wishlist);
        main=findViewById(R.id.main);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);
        img_share=findViewById(R.id.img_share);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        wishListMains=new ArrayList<>();
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(WishListActivity.this).getUserId();
        if (InternetAccess.isConnected(WishListActivity.this)) {

            getWishlist(customerid);
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
//        img_share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(WishListActivity.this, WishListActivity.class));
//
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WishListActivity.this, CartActivity.class));

            }
        });

    }

    private void getWishlist(String customerid) {
    Call<WishListMainModel> call=apiInterface.getwishlist(Config.header,customerid);
    progressDialog = new ProgressDialog(WishListActivity.this);
    progressDialog.setMessage("Please wait...");
    progressDialog.show();
    call.enqueue(new Callback<WishListMainModel>() {
        @Override
        public void onResponse(Call<WishListMainModel> call, Response<WishListMainModel> response) {
            progressDialog.dismiss();
            if(response.body().isStatus()==true)
            {
                String msg=response.body().getMessage();
                Toast.makeText(WishListActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                wishListMains=response.body().getWishList();
                wishListAdapter = new WishListAdapter(WishListActivity.this, wishListMains);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(WishListActivity.this, 1);
                wishlist.setLayoutManager(mLayoutManager);
                wishlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                wishlist.setItemAnimator(new DefaultItemAnimator());
                wishlist.setAdapter(wishListAdapter);
                wishListAdapter.notifyDataSetChanged();
            }
            else
                {
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
                String msg=response.body().getMessage();
                Toast.makeText(WishListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<WishListMainModel> call, Throwable t) {
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

    @Override
    protected void onResume() {
        super.onResume();

    }
}