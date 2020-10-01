package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import com.techprostudio.kuberinternational.Adapter.FilterAdapter;
import com.techprostudio.kuberinternational.Adapter.SubcategoryAdapter;
import com.techprostudio.kuberinternational.Fragment.CategoryFragment;
import com.techprostudio.kuberinternational.Model.FilterSection.CategoryListFilter;
import com.techprostudio.kuberinternational.Model.FilterSection.FilterMainModel;
import com.techprostudio.kuberinternational.Model.SubProductMain.ProductList_product;
import com.techprostudio.kuberinternational.Model.SubProductMain.SubProductMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Adapter.FilterAdapter.subproductcategoryid;

public class SubProductActivity extends AppCompatActivity {
  public static  RecyclerView filterlist,subcategorylist;
    private List<CategoryListFilter> filterModelList;
    private List<ProductList_product> subcategoryModelList;
    private FilterAdapter filterAdapter;
    private SubcategoryAdapter subcategoryAdapter;

    ImageView back,img_cart;
    String categoryid,categoryname;
    TextView title_single;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    RelativeLayout main,cart_count;
    TextView tv_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_product);
        filterlist=findViewById(R.id.filterlist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        tv_count=findViewById(R.id.tv_count);
        cart_count=findViewById(R.id.cart_count);
        title_single=findViewById(R.id.title_single);
        categoryid=getIntent().getExtras().getString("categoryid");
        categoryname=getIntent().getExtras().getString("categoryname");
        String customerid=new AppPreference(SubProductActivity.this).getUserId();
        title_single.setText(categoryname);
        filterModelList=new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubProductActivity.this, CartActivity.class));
            }
        });
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        subcategorylist=findViewById(R.id.subcategorylist);

        if (InternetAccess.isConnected(SubProductActivity.this)) {
            filterdata(categoryid,customerid);
        } else {
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



        subcategoryModelList=new ArrayList<>();

    }
    private void subcategorydata(String subproductcategoryid, String customerid) {
        Log.e("catid",""+subproductcategoryid);
        Call<SubProductMainModel> call=apiInterface.getFilterProducts(Config.header,subproductcategoryid,customerid);
        call.enqueue(new Callback<SubProductMainModel>() {
            @Override
            public void onResponse(Call<SubProductMainModel> call, Response<SubProductMainModel> response) {
                if(response.body().getStatus()==true)
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
                    subcategoryModelList=response.body().getProductList().get(0).getProductList();
                    subcategoryAdapter = new SubcategoryAdapter(SubProductActivity.this,subcategoryModelList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SubProductActivity.this,2);
                    subcategorylist.setLayoutManager(mLayoutManager);
                    subcategorylist.addItemDecoration(new CategoryFragment.GridSpacingItemDecoration(2, dpToPx(0), true));
                    subcategorylist.setItemAnimator(new DefaultItemAnimator());
                    subcategorylist.setAdapter(subcategoryAdapter);
                    subcategoryAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(SubProductActivity.this, "no data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubProductMainModel> call, Throwable t) {

            }
        });


    }

    private void filterdata(String categoryid, String customerid) {
        Call<FilterMainModel> call=apiInterface.getFilterCategory(Config.header,categoryid,customerid);
        call.enqueue(new Callback<FilterMainModel>() {
            @Override
            public void onResponse(Call<FilterMainModel> call, Response<FilterMainModel> response) {
                if(response.body().getStatus()==true){
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
                    filterModelList=response.body().getCategoryList();
                    filterAdapter = new FilterAdapter(SubProductActivity.this,filterModelList);
                    LinearLayoutManager horizontaLayoutManagaer = new LinearLayoutManager(SubProductActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    filterlist.setLayoutManager(horizontaLayoutManagaer);
                    filterlist.setAdapter(filterAdapter);
                    filterAdapter.notifyDataSetChanged();
                 //   subcategorydata(subproductcategoryid,customerid);
                }
                else {
                    Toast.makeText(SubProductActivity.this, "no data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FilterMainModel> call, Throwable t) {

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