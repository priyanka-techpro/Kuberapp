package com.techprostudio.kuberinternational.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;

import com.techprostudio.kuberinternational.Adapter.MainCategoryAdapter;
import com.techprostudio.kuberinternational.Adapter.SliderAdapter;
import com.techprostudio.kuberinternational.Model.CategoryMainModel;
import com.techprostudio.kuberinternational.Model.CategoryModel;
import com.techprostudio.kuberinternational.Model.SliderItem;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryMasterActivity extends AppCompatActivity {
    RecyclerView categorymainlist;
    private List<CategoryMainModel> categoryModelList;
    private MainCategoryAdapter categoryAdapter;
    CategoryMainModel categoryModel;
    ViewPager2 viewpagerone;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_master);
        categorymainlist=findViewById(R.id.categorymainlist);
        viewpagerone=findViewById(R.id.viewpagerone);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.bannerone));
        sliderItems.add(new SliderItem(R.drawable.bannerone));
        sliderItems.add(new SliderItem(R.drawable.bannerone));
        viewpagerone.setAdapter(new SliderAdapter(sliderItems,viewpagerone));
        viewpagerone.setClipToPadding(false);
        viewpagerone.setClipChildren(false);
        viewpagerone.setOffscreenPageLimit(3);
        viewpagerone.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewpagerone.setPageTransformer(compositePageTransformer);

        viewpagerone.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });

        categoryModelList=new ArrayList<>();
        categoryAdapter = new MainCategoryAdapter(this,categoryModelList);
        categorydata();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(CategoryMasterActivity.this,4);
        categorymainlist.setLayoutManager(mLayoutManager);
        categorymainlist.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(0), true));
        categorymainlist.setItemAnimator(new DefaultItemAnimator());
        categorymainlist.setAdapter(categoryAdapter);
    }

    private void categorydata() {
        for(int i=0;i<8;i++){

            categoryModel=new CategoryMainModel();
            categoryModel.setProductname("");
            categoryModel.setPrice("");
            categoryModel.setDiscount("");
            categoryModelList.add(categoryModel);
        }

        categoryAdapter.notifyDataSetChanged();
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

            viewpagerone.setCurrentItem(viewpagerone.getCurrentItem() + 1);
        }
    };
}