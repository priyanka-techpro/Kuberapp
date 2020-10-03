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
import com.techprostudio.kuberinternational.Adapter.AddressListAdapter;
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressList;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class ChangeAddressActivity extends AppCompatActivity {
    RelativeLayout gotoaddaddress,main;
    RecyclerView addresslist;
    List<AddressList> addressListModelArrayList;
    AddressListAdapter addressListAdapter;
    ImageView back,img_cart;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        gotoaddaddress=findViewById(R.id.gotoaddaddress);
        addresslist=findViewById(R.id.addresslist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(ChangeAddressActivity.this).getUserId();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeAddressActivity.this, CartActivity.class));
            }
        });
        addressListModelArrayList=new ArrayList<>();
        if (InternetAccess.isConnected(ChangeAddressActivity.this)) {

            addressitems(customerid);
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

        gotoaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeAddressActivity.this, AddAddressActivity.class));

            }
        });
    }

    private void addressitems(String customerid) {
        Call<AddressMainModel> call=apiInterface.getAddressList(Config.header,customerid);
        progressDialog = new ProgressDialog(ChangeAddressActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<AddressMainModel>() {
            @Override
            public void onResponse(Call<AddressMainModel> call, Response<AddressMainModel> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == true) {
                    if(response.body().getAddressList().size() == 0)
                    {
                        Toast.makeText(ChangeAddressActivity.this, "No addresslist found.", Toast.LENGTH_SHORT).show();
                    }
                else
                    {
                        addressListModelArrayList=response.body().getAddressList();
                        addressListAdapter=new AddressListAdapter(ChangeAddressActivity.this,addressListModelArrayList);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ChangeAddressActivity.this,1);
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