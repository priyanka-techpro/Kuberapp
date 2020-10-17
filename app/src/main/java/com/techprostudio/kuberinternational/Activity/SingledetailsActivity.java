package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
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
import com.techprostudio.kuberinternational.Adapter.FilterAdapter;
import com.techprostudio.kuberinternational.Adapter.Variationadapter;
import com.techprostudio.kuberinternational.Model.AddtoCartModel;
import com.techprostudio.kuberinternational.Model.SingleProductPackage.SingleProductMainModel;
import com.techprostudio.kuberinternational.Model.SingleProductPackage.VariationProductSingle;
import com.techprostudio.kuberinternational.Model.wishlistAdd.AddWishListMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Adapter.Variationadapter.variationid;

public class SingledetailsActivity extends AppCompatActivity {
    ImageView back,img_cart,plus,minus,img_notify;
    public static ImageView heartdeep,heartsingle;
    RelativeLayout ll_bag,main,cart_count;
    RecyclerView variation;
    private List<VariationProductSingle> variationarrlist;
    private Variationadapter variationAdapter;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    String productid,productname;
    TextView tv_count,title_single;
    public static ImageView image_top;
    public static String producname;
    public static TextView discount_single,title_single_product,product_name,mrp_single,quantity_single,description,usage,details,qty;
    ProgressDialog progressDialog,progressDialog1,progressDialog2;
    int productQty;
    public int count = 0;
    String productidresp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singledetails);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        ll_bag=findViewById(R.id.ll_bag);
        discount_single=findViewById(R.id.discount_single);
        title_single_product=findViewById(R.id.title_single);
        product_name=findViewById(R.id.product_name);
        mrp_single=findViewById(R.id.mrp_single);
        quantity_single=findViewById(R.id.quantity_single);
        description=findViewById(R.id.description);
        usage=findViewById(R.id.usage);
        details=findViewById(R.id.details);
        variation=findViewById(R.id.variation);
        main=findViewById(R.id.main);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);
        image_top=findViewById(R.id.image_top);
        qty=findViewById(R.id.qty);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
        heartsingle=findViewById(R.id.heartsingle);
        title_single=findViewById(R.id.title_single);
        img_notify=findViewById(R.id.img_notify);
        heartdeep=findViewById(R.id.heartdeep);


        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(SingledetailsActivity.this).getUserId();
        productid=getIntent().getExtras().getString("productid");
        productname=getIntent().getExtras().getString("productname");
        title_single.setText(productname);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingledetailsActivity.this, CartActivity.class));
            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingledetailsActivity.this, WishListActivity.class));
            }
        });
        ll_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetAccess.isConnected(SingledetailsActivity.this)) {
                    String quantity=qty.getText().toString();
                    addtocart(productidresp,customerid,variationid,quantity);
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
        });
        variationarrlist = new ArrayList<>();
        if (InternetAccess.isConnected(SingledetailsActivity.this)) {
            variationdata(productid,customerid);

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
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count= Integer.parseInt(qty.getText().toString());
                count = count + 1;
                qty.setText(String.valueOf(count));
                productQty = Integer.parseInt(qty.getText().toString());
                if (productQty>=2)
                {
                    //minus_jar.setEnabled(false);
                    Log.e("QTYyy",""+productQty);

                }
                else
                {
                    //minus_jar.setEnabled(true);
                    Log.e("QTYyy111",""+productQty);

                }
                String actualPrice = Variationadapter.price;
                float actprice = Float.parseFloat(actualPrice);
                float price = 0;
                price = actprice * count;
                String priceActual = String.format("%.0f", price);
                mrp_single.setText("Rs." + priceActual+"/");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count= Integer.parseInt(qty.getText().toString());
                if(count>1)
                {
                    count--;
                    qty.setText(String.valueOf(count));
                    String actualPrice = Variationadapter.price;
                    float actprice = Float.parseFloat(actualPrice);
                    float price = 0;
                    price = actprice * count;
                    String priceActual = String.format("%.0f", price);
                    mrp_single.setText("Rs." + priceActual+"/");
                    productQty = Integer.parseInt(qty.getText().toString());
                    if (productQty>=2)
                    {
                        //minus_jar.setEnabled(false);
                        Log.e("QTYyy",""+productQty);

                    }
                    else
                    {
                        //minus_jar.setEnabled(true);
                        Log.e("QTYyy111",""+productQty);

                    }

                }
            }
        });
        heartsingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetAccess.isConnected(SingledetailsActivity.this)) {

                    heartsingle.setImageDrawable(getResources().getDrawable(R.drawable.heartbluefill));
                    addtoWishList(productidresp,variationid,customerid);
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
        });
    }

    private void addtoWishList(String productidresp, String variationid, String customerid) {
        Call<AddWishListMainModel> call=apiInterface.addtoWishList(Config.header,productidresp,variationid,customerid);
        progressDialog2 = new ProgressDialog(SingledetailsActivity.this);
        progressDialog2.setMessage("Please wait...");
        progressDialog2.show();
        call.enqueue(new Callback<AddWishListMainModel>() {
            @Override
            public void onResponse(Call<AddWishListMainModel> call, Response<AddWishListMainModel> response) {
                progressDialog2.dismiss();
                if(response.body().isStatus() == true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(SingledetailsActivity.this,msg , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(SingledetailsActivity.this,msg , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddWishListMainModel> call, Throwable t) {
                progressDialog2.dismiss();
            }
        });
    }

    private void addtocart(String productidresp, String customerid, String variationid, String quantity)
    {
        Call<AddtoCartModel> call=apiInterface.AddtoCart(Config.header,productidresp,customerid,variationid,quantity);
        progressDialog1 = new ProgressDialog(SingledetailsActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<AddtoCartModel>() {
            @Override
            public void onResponse(Call<AddtoCartModel> call, Response<AddtoCartModel> response) {
                progressDialog1.dismiss();
                if(response.body().getStatus()==true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(SingledetailsActivity.this,msg , Toast.LENGTH_SHORT).show();
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
                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(SingledetailsActivity.this,msg , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddtoCartModel> call, Throwable t) {
                progressDialog1.dismiss();

            }
        });
    }

    private void variationdata(String productid, String customerid) {
        Call<SingleProductMainModel> call=apiInterface.getSingleProducts(Config.header,productid,customerid);
        progressDialog = new ProgressDialog(SingledetailsActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<SingleProductMainModel>() {
            @Override
            public void onResponse(Call<SingleProductMainModel> call, Response<SingleProductMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true){
                    productidresp=response.body().getProductData().get(0).getProductId();
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
//
                    producname=response.body().getProductData().get(0).getName();
                    description.setText(response.body().getProductData().get(0).getDescription());
                    usage.setText(response.body().getProductData().get(0).getUsages());
                    details.setText(response.body().getProductData().get(0).getMaterials());
                    variationarrlist=response.body().getProductData().get(0).getVariationProducts();
                    variationAdapter = new Variationadapter(SingledetailsActivity.this,variationarrlist);
                    RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(SingledetailsActivity.this,2);
                    variation.setLayoutManager(mLayoutManager1);
                    variation.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
                    variation.setItemAnimator(new DefaultItemAnimator());
                    variation.setAdapter(variationAdapter);
                    variationAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(SingledetailsActivity.this, "no data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SingleProductMainModel> call, Throwable t) {
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