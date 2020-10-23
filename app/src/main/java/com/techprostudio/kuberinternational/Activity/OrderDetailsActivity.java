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
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.OrderDetailsAdapter;
import com.techprostudio.kuberinternational.Model.OrderCancelledModel;
import com.techprostudio.kuberinternational.Model.OrderDetailsPackage.OrderDetailsDatum;
import com.techprostudio.kuberinternational.Model.OrderDetailsPackage.OrderDetailsModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    RelativeLayout cancel_order;
    RecyclerView order_detailslist;
    List<OrderDetailsDatum> orderDetailsModelArrayList;
    OrderDetailsAdapter orderDetailsAdapter;
    ImageView back,img_cart;
    RelativeLayout main;
    Snackbar mSnackbar;
    String orderid;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    TextView ttl_amnt,order_no,placedon,deliveryon,payment_method,ph_no,sub_total_amt,shippingchrge,discount_amt,rndoff;
    ImageView mbl;
    String order_id_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        cancel_order=findViewById(R.id.cancel_order);
        order_detailslist=findViewById(R.id.order_detailslist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        order_no=findViewById(R.id.order_no);
        placedon=findViewById(R.id.placedon);
        deliveryon=findViewById(R.id.deliveryon);
        discount_amt=findViewById(R.id.discount_amt);
        mbl=findViewById(R.id.mbl);
        sub_total_amt=findViewById(R.id.sub_total_amt);
        rndoff=findViewById(R.id.rndoff);
        ph_no=findViewById(R.id.ph_no);
        shippingchrge=findViewById(R.id.shippingchrge);
        ttl_amnt=findViewById(R.id.ttl_amnt);
        payment_method=findViewById(R.id.payment_method);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid = new AppPreference(OrderDetailsActivity.this).getUserId();
        orderid=getIntent().getExtras().getString("orderid");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this, CartActivity.class));
            }
        });
        if (InternetAccess.isConnected(OrderDetailsActivity.this))
        {
            cartitems(customerid,orderid);
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
        orderDetailsModelArrayList=new ArrayList<>();

        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrder(customerid,order_id_delete);
            }
        });
    }

    private void cancelOrder(String customerid, String order_id_delete) {
        Call<OrderCancelledModel> call=apiInterface.CancelOrder(Config.header,customerid,order_id_delete);
        progressDialog1 = new ProgressDialog(OrderDetailsActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<OrderCancelledModel>() {
            @Override
            public void onResponse(Call<OrderCancelledModel> call, Response<OrderCancelledModel> response) {
                progressDialog1.dismiss();
                if(response.body().getStatus() == true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(OrderDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OrderDetailsActivity.this, OrderHistoryActivity.class));
                }
                else
                    {
                    String msg=response.body().getMessage();
                    Toast.makeText(OrderDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderCancelledModel> call, Throwable t) {
                progressDialog1.dismiss();
            }
        });
    }

    private void cartitems(String customerid, String orderid) {
        Call<OrderDetailsModel> call=apiInterface.getOrderDetails(Config.header,customerid,orderid);
        progressDialog = new ProgressDialog(OrderDetailsActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<OrderDetailsModel>() {
            @Override
            public void onResponse(Call<OrderDetailsModel> call, Response<OrderDetailsModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true){
                    order_id_delete=response.body().getOrderHistory().getOrderId();
                    Log.e("jhgvbjhb",response.body().getOrderHistory().getOrderId());
                    String Orderno=response.body().getOrderHistory().getOrderNumber();
                    order_no.setText("Order "+Orderno);
                    String placed=response.body().getOrderHistory().getOrderedOn();
                    placedon.setText("Placed on:"+placed);
                    String delivered=response.body().getOrderHistory().getDeliveryDate();
                    deliveryon.setText("Delivery on:"+delivered);
                    if(response.body().getOrderHistory().getOrderStatus().equals("Processing"))
                    {
                        cancel_order.setVisibility(View.VISIBLE);

                        mbl.setBackground(getResources().getDrawable(R.drawable.processing));
                        ph_no.setText(response.body().getOrderHistory().getOrderStatus());
                    }
                    else if(response.body().getOrderHistory().getOrderStatus().equals("Cancelled"))
                    {
                        mbl.setBackground(getResources().getDrawable(R.drawable.redbox));
                       ph_no.setText(response.body().getOrderHistory().getOrderStatus());
                       ph_no.setTextColor(getResources().getColor(R.color.red));
                        cancel_order.setVisibility(View.GONE);
                    }
                    else
                    {
                        mbl.setBackground(getResources().getDrawable(R.drawable.delivered));
                        ph_no.setText(response.body().getOrderHistory().getOrderStatus());
                        cancel_order.setVisibility(View.GONE);
                    }
                    sub_total_amt.setText("Rs."+response.body().getOrderSummary().getOrderAmount());
                   // shippingchrge.setText("Rs."+response.body().getOrderSummary().);
                    discount_amt.setText("Rs."+response.body().getOrderSummary().getDiscountAmount());
                    rndoff.setText("Rs."+response.body().getOrderSummary().getGstAmount());
                    ttl_amnt.setText("Rs."+response.body().getOrderSummary().getPayableAmount());
                    payment_method.setText("Payment Method : "+response.body().getOrderHistory().getOrderType());
                    orderDetailsModelArrayList=response.body().getOrderHistory().getOrderDetailsData();
                    orderDetailsAdapter=new OrderDetailsAdapter(OrderDetailsActivity.this,orderDetailsModelArrayList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(OrderDetailsActivity.this,1);
                    order_detailslist.setLayoutManager(mLayoutManager);
                    order_detailslist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                    order_detailslist.setItemAnimator(new DefaultItemAnimator());
                    order_detailslist.setAdapter(orderDetailsAdapter);
                    orderDetailsAdapter.notifyDataSetChanged();
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(OrderDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsModel> call, Throwable t) {
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