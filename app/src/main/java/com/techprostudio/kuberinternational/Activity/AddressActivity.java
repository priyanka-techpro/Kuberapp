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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.AddressListAdapter;
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressList;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressMainModel;
import com.techprostudio.kuberinternational.Model.CartPackage.CartListMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Adapter.AddressListAdapter.AddressId;

public class AddressActivity extends AppCompatActivity {
    LinearLayout ll_uncheck;
    boolean check = false;
    RelativeLayout gotochangeaddress,proceedtopayment;
    ImageView back,img_cart,img_notify;
    RelativeLayout gotoaddaddress,main,subtotal_ll,cart_count;
    RecyclerView addresslist;
    List<AddressList> addressListModelArrayList;
    AddressListAdapter addressListAdapter;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    TextView sub_total_amt,discount_amt,shippingchrge,rndoff,ttl_amnt,tv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ll_uncheck=findViewById(R.id.ll_uncheck);
        proceedtopayment=findViewById(R.id.proceedtopayment);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        sub_total_amt=findViewById(R.id.sub_total_amt);
        discount_amt=findViewById(R.id.discount_amt);
        shippingchrge=findViewById(R.id.shippingchrge);
        rndoff=findViewById(R.id.rndoff);
        ttl_amnt=findViewById(R.id.ttl_amnt);
        subtotal_ll=findViewById(R.id.subtotal_ll);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);
        img_notify=findViewById(R.id.img_notify);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, CartActivity.class));
            }
        });

        gotoaddaddress=findViewById(R.id.gotoaddaddress);
        addresslist=findViewById(R.id.addresslist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(AddressActivity.this).getUserId();
        addressListModelArrayList=new ArrayList<>();
        if (InternetAccess.isConnected(AddressActivity.this)) {

            addressitems(customerid);
            cartitems(customerid);
            subtotal_ll.setVisibility(View.VISIBLE);
        }
        else {
            subtotal_ll.setVisibility(View.GONE);
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

        gotoaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));

            }
        });
        proceedtopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AddressId.equals("0")){
                    Toast.makeText(AddressActivity.this, "Please provide address before confirming order.", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(AddressActivity.this, PaymentActivity.class));
                }
            }
        });

        ll_uncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check == false)
                {
                    ll_uncheck.setBackgroundResource(R.drawable.checked);

                    check =true;
                }
                else
                {
                    ll_uncheck.setBackgroundResource(R.drawable.unchecked);

                    check =false;
                }


            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this, WishListActivity.class));

            }
        });
    }
    private void addressitems(String customerid) {
        Call<AddressMainModel> call=apiInterface.getAddressList(Config.header,customerid);
        progressDialog = new ProgressDialog(AddressActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<AddressMainModel>() {
            @Override
            public void onResponse(Call<AddressMainModel> call, Response<AddressMainModel> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == true) {
                    if(response.body().getAddressList().size() == 0)
                    {
                        Toast.makeText(AddressActivity.this, "No addresslist found.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        addressListModelArrayList=response.body().getAddressList();
                        addressListAdapter=new AddressListAdapter(AddressActivity.this,addressListModelArrayList);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AddressActivity.this,1);
                        addresslist.setLayoutManager(mLayoutManager);
                        addresslist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
                        addresslist.setItemAnimator(new DefaultItemAnimator());
                        addresslist.setAdapter(addressListAdapter);
                        addressListAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<AddressMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private void cartitems(String customerid) {
        Call<CartListMainModel> call=apiInterface.CartList(Config.header,Config.offerid,customerid);
        progressDialog1 = new ProgressDialog(AddressActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<CartListMainModel>() {
            @Override
            public void onResponse(Call<CartListMainModel> call, Response<CartListMainModel> response) {
                progressDialog1.dismiss();
                if(response.body().isStatus()==true)
                {
                    if(response.body().getCartCount()== 0){
                        String msg=response.body().getMessage();
                        Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                        subtotal_ll.setVisibility(View.GONE);
                    }
                    else {
                        subtotal_ll.setVisibility(View.VISIBLE);
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

                    }
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                    subtotal_ll.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<CartListMainModel> call, Throwable t) {
                progressDialog1.dismiss();
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