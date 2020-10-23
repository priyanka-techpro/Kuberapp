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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.OrderHistoryAdapter;
import com.techprostudio.kuberinternational.Model.OrderHistoryPackage.OrderHistoryMain;
import com.techprostudio.kuberinternational.Model.OrderHistoryPackage.OrderHistoryModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {
    RecyclerView orderlist;
    List<OrderHistoryMain> orderHistoryModelArrayList;
    OrderHistoryAdapter orderHistoryAdapter;
    ImageView back,img_cart;
    RelativeLayout main;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        orderlist=findViewById(R.id.orderlist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(OrderHistoryActivity.this).getUserId();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, DashboardActivity.class));
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, CartActivity.class));
            }
        });
        if (InternetAccess.isConnected(OrderHistoryActivity.this))
        {
            getOrderHistory(customerid);
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
        orderHistoryModelArrayList=new ArrayList<>();


    }

    private void getOrderHistory(String customerid) {
        Call<OrderHistoryModel> call=apiInterface.getOrderHistory(Config.header,customerid);
        progressDialog = new ProgressDialog(OrderHistoryActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OrderHistoryModel>() {
            @Override
            public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                    orderHistoryModelArrayList=response.body().getOrderHistory();
                    orderHistoryAdapter=new OrderHistoryAdapter(OrderHistoryActivity.this,orderHistoryModelArrayList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(OrderHistoryActivity.this,1);
                    orderlist.setLayoutManager(mLayoutManager);
                    orderlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                    orderlist.setItemAnimator(new DefaultItemAnimator());
                    orderlist.setAdapter(orderHistoryAdapter);
                    orderHistoryAdapter.notifyDataSetChanged();
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(OrderHistoryActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderHistoryModel> call, Throwable t) {
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