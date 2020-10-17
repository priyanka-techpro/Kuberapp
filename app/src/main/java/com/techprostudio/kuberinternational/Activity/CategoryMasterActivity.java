package com.techprostudio.kuberinternational.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.MainCategoryAdapter;
import com.techprostudio.kuberinternational.Adapter.SliderAdapter;
import com.techprostudio.kuberinternational.Model.DashboardModel.BannerList;
import com.techprostudio.kuberinternational.Model.DashboardModel.DashboardMainModel;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryList;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryMainModel;
import com.techprostudio.kuberinternational.Model.SliderItem;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class CategoryMasterActivity extends AppCompatActivity {
    RecyclerView categorymainlist;
    private List<CategoryList> categoryModelList;
    private MainCategoryAdapter categoryAdapter;
    ViewPager2 myViewPager2;
    private Handler sliderHandler = new Handler();
    ImageView back,img_cart,img_notify;
    RelativeLayout main,cart_count;
    TextView tv_count;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    List<BannerList> sliderItems;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_master);
        categorymainlist=findViewById(R.id.categorymainlist);
        myViewPager2=findViewById(R.id.viewpagerone);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        main=findViewById(R.id.main);
        tv_count=findViewById(R.id.tv_count);
        cart_count=findViewById(R.id.cart_count);
        img_notify=findViewById(R.id.img_notify);
        apiInterface= ApiClient.getRetrofitClient().create(ApiInterface.class);
        String customerid=new AppPreference(CategoryMasterActivity.this).getUserId();
        categoryModelList=new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryMasterActivity.this, CartActivity.class));
            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryMasterActivity.this, WishListActivity.class));
            }
        });

//        List<SliderItem> sliderItems = new ArrayList<>();
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        viewpagerone.setAdapter(new SliderAdapter(sliderItems,viewpagerone));
//        viewpagerone.setClipToPadding(false);
//        viewpagerone.setClipChildren(false);
//        viewpagerone.setOffscreenPageLimit(3);
//        viewpagerone.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.85f + r * 0.15f);
//            }
//        });
//        viewpagerone.setPageTransformer(compositePageTransformer);
//
//        viewpagerone.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable);
//                sliderHandler.postDelayed(sliderRunnable,3000);
//            }
//        });
        sliderItems = new ArrayList<>();
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
        getBanner(customerid);
        if (InternetAccess.isConnected(CategoryMasterActivity.this)) {
            categorydata(customerid);
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



    }
    private void getBanner(String customerid) {
        Call<DashboardMainModel> call=apiInterface.getDashboardProduct(Config.header,customerid);

        call.enqueue(new Callback<DashboardMainModel>() {
            @Override
            public void onResponse(Call<DashboardMainModel> call, Response<DashboardMainModel> response) {
                if(response.body().isStatus() == true)
                {
                    sliderItems=response.body().getBannerList();
                    myViewPager2.setAdapter(new SliderAdapter(sliderItems,myViewPager2,CategoryMasterActivity.this));
                    myViewPager2.setClipToPadding(false);
                    myViewPager2.setClipChildren(false);
                    myViewPager2.setOffscreenPageLimit(3);
                    myViewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                    compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                        @Override
                        public void transformPage(@NonNull View page, float position) {
                            float r = 1 - Math.abs(position);
                            page.setScaleY(0.85f + r * 0.15f);
                        }
                    });
                    myViewPager2.setPageTransformer(compositePageTransformer);

                    myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            sliderHandler.removeCallbacks(sliderRunnable);
                            sliderHandler.postDelayed(sliderRunnable,3000);
                        }
                    });
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(CategoryMasterActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashboardMainModel> call, Throwable t) {

            }
        });
    }

    private void categorydata(String customerid) {

        Call<CategoryMainModel> call=apiInterface.getParentCategory(Config.header,customerid);
        progressDialog = new ProgressDialog(CategoryMasterActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<CategoryMainModel>() {
            @Override
            public void onResponse(Call<CategoryMainModel> call, Response<CategoryMainModel> response) {
                progressDialog.dismiss();

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
                    categoryModelList=response.body().getCategoryList();
                    categoryAdapter = new MainCategoryAdapter(CategoryMasterActivity.this,categoryModelList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(CategoryMasterActivity.this,4);
                    categorymainlist.setLayoutManager(mLayoutManager);
                    categorymainlist.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(0), true));
                    categorymainlist.setItemAnimator(new DefaultItemAnimator());
                    categorymainlist.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(CategoryMasterActivity.this, "no data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryMainModel> call, Throwable t) {
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
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            myViewPager2.setCurrentItem(myViewPager2.getCurrentItem() + 1);
        }
    };
}